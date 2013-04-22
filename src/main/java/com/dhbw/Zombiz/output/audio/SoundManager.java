/*******************************************************************************
 * Copyright (c) 2013 DHBW.
 * This source is subject to the DHBW Permissive License.
 * Please see the License.txt file for more information.
 * All other rights reserved.
 * 
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *Project: Zombiz
 *Package: com.dhbw.zombiz.output.audio
 *
 *Contributors:
 * -Christoph Schabert
 ********************************************************************************/
package com.dhbw.Zombiz.output.audio;


import java.io.*;
import javax.sound.sampled.*;
import com.dhbw.Zombiz.output.audio.ThreadPool;
import com.dhbw.Zombiz.output.audio.LoopingByteInputStream;
import com.dhbw.Zombiz.output.audio.Sound;

/**The Soundmanager is a ThreadPool and supports a limited number of simultaneous
 * played Sounds.
 * 
 * @author Christoph Schabert
 * @version 1.0
 */
public class SoundManager extends ThreadPool{

    private AudioFormat playbackFormat;		//the audioFormat for the Sounds
    private ThreadLocal localLine;		
    private ThreadLocal localBuffer;
    private Object pausedLock;				//locks all threads for the pause	
    private boolean paused;					//boolean for pause stats
    
    /** Creates a new SoundManager with a maximum number of simultaneous sounds,
     * two simultaneous Sounds should be ok.
     * 
     * @param playbackFormat	the Audio Format
     * @param maxSimultaneousSounds		the maximum Number of simultaneous Sounds
     */
    public SoundManager(AudioFormat playbackFormat,
        int maxSimultaneousSounds)
    {
        super(maxSimultaneousSounds);
        this.playbackFormat = playbackFormat;
        localLine = new ThreadLocal();
        localBuffer = new ThreadLocal();
        pausedLock = new Object();
        // notify threads in pool it's okay to start
        synchronized (this) {
            notifyAll();
        }
    }

    /**unpause and stop all sounds before closing it.
     * 
     */
    protected void cleanUp() {
        // signal to unpause
        setPaused(false);

        // close the mixer (stops any running sounds)
        Mixer mixer = AudioSystem.getMixer(null);
        if (mixer.isOpen()) {
            mixer.close();
        }
    }

    /**closes the SoundManager.
     * 
     */
    public void close() {
        cleanUp();
        super.close();
    }

    /**Set all to pause,
     * sounds may not stop immediately
     * 
     * @param paused true = end pause ; false = pause
     */
    public void setPaused(boolean paused) {
        if (this.paused != paused) {
            synchronized (pausedLock) {
                this.paused = paused;
                if (!paused) {
                    // restart sounds
                    pausedLock.notifyAll();
                }
            }
        }
    }


    /**Returns the state of Pause
     * 
     * @return boolean pause state
     */
    public boolean isPaused() {
        return paused;
    }


    /**Get a InputStream from a file,
     * returns null if its unable to load the file.
     * 
     * @param filename name of the file in
     * @return
     */
    public Sound getSound(String filename) {
        return getSound(getAudioInputStream(filename));
    }


    /**Loads a Sound from an AudioInputStream.
     * 
     * @param audioStream the InputStream
     */
    public Sound getSound(AudioInputStream audioStream) {
        if (audioStream == null) {
            return null;
        }

        // get the number of bytes to read
        int length = (int)(audioStream.getFrameLength() *
            audioStream.getFormat().getFrameSize());

        // read the entire stream
        byte[] samples = new byte[length];
        DataInputStream is = new DataInputStream(audioStream);
        try {
            is.readFully(samples);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        // return the samples
        return new Sound(samples);
    }


    /**Creates an AudioInputStream from a sound from the file
     * system.
     * @param the file to convert to a InputStream
     */
    public AudioInputStream getAudioInputStream(String filename) {

        try {
            // open the source file
            AudioInputStream source =
            AudioSystem.getAudioInputStream(new File(filename));

            // convert to playback format
            return AudioSystem.getAudioInputStream(
                playbackFormat, source);
        }
        catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    /**
     *   Plays a sound with an optionally
     *   looping. This method returns immediately.
     *   
     *   @param sound the Sound to play
     *   @param loop true if the sound should be looped
     */
    public InputStream play(Sound sound,
        boolean loop)
    {
        InputStream is;
        if (sound != null) {
            if (loop) {
                is = new LoopingByteInputStream(
                    sound.getSamples());
            }else {
                is = new ByteArrayInputStream(sound.getSamples());
            }

            return play(is);
        }
        return null;
    }

    /**play a Input Stream as a new Thread
     * 
     * @param is the InputStream to play
     * @return the InputStream
     */
    public InputStream play(InputStream is) {
        if (is != null) {
            runTask(new SoundPlayer(is));
        }
        return is;
    }

    
    /**sends a Signal thats starts a Thread
     * 
     */
    protected void threadStarted() {
        // wait for the SoundManager constructor to finish
        synchronized (this) {
            try {
                wait();
            }
            catch (InterruptedException ex) { }
        }

        // use a short, 100ms (1/10th sec) buffer for filters that
        // change in real-time
        int bufferSize = playbackFormat.getFrameSize() *
            Math.round(playbackFormat.getSampleRate() / 10);

        // create, open, and start the line
        SourceDataLine line;
        DataLine.Info lineInfo = new DataLine.Info(
            SourceDataLine.class, playbackFormat);
        try {
            line = (SourceDataLine)AudioSystem.getLine(lineInfo);
            line.open(playbackFormat, bufferSize);
        }
        catch (LineUnavailableException ex) {
            // the line is unavailable - signal to end this thread
            Thread.currentThread().interrupt();
            return;
        }

        line.start();

        // create the buffer
        byte[] buffer = new byte[bufferSize];

        // set this thread's locals
        localLine.set(line);
        localBuffer.set(buffer);
    }


    /**sends a Signal that stops the Tread 
     * 
     */
    protected void threadStopped() {
        SourceDataLine line = (SourceDataLine)localLine.get();
        if (line != null) {
            line.drain();
            line.close();
        }
    }

    /**The Sound Player plays the sounds buffered in the SoundManager.
     * only can be called from the SoundManager.
     * 
     * @author Christoph Schabert
     * @version 1.0
     */
    protected class SoundPlayer implements Runnable {

        private InputStream source;

        /**the Constructor set a new InputStream as source.
         * 
         * @param source new InputStream Source
         */
        public SoundPlayer(InputStream source) {
            this.source = source;
        }

        /**the run() function is called by the Sound manager to play a Sound.
         *         
         */
        public void run() {
            // get line and buffer from ThreadLocals
            SourceDataLine line = (SourceDataLine)localLine.get();
            byte[] buffer = (byte[])localBuffer.get();
            if (line == null || buffer == null) {
                // the line is unavailable
                return;
            }

            // copy data to the line
            try {
                int numBytesRead = 0;
                while (numBytesRead != -1) {
                    // if paused, wait until unpaused
                    synchronized (pausedLock) {
                        if (paused) {
                            try {
                                pausedLock.wait();
                            }
                            catch (InterruptedException ex) {
                                return;
                            }
                        }
                    }
                    // copy data
                    numBytesRead =
                        source.read(buffer, 0, buffer.length);
                    if (numBytesRead != -1) {
                       line.write(buffer, 0, numBytesRead);
                    }
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}