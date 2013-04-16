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

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;




/**
    The Sound class is a container for sound samples. The sound
    samples are format-agnostic and are stored as a byte array.
*/
public class Sound {

    private byte[] samples;

    /**
        Create a new Sound object with the specified byte array.
        The array is not copied.
    */
    public Sound(byte[] samples) {
        this.samples = samples;
    }


    /**
        Returns this Sound's objects samples as a byte array.
    */
    public byte[] getSamples() {
        return samples;
    }

}



/**
 * @author Christoph Schabert
 * @version 1.0
 */
/*
public class Sound implements Runnable {
	
	private boolean repeate;
    AudioInputStream audio;
    private String ending = ".wav";
    private String path = "src\\main\\resources\\audio\\";
    AudioInputStream audioInputStream;
    Clip clip;
    

    
    public Sound(String nfilename,boolean nrep){
		try {
			clip = AudioSystem.getClip();
			audioInputStream = AudioSystem.getAudioInputStream(new File(path+nfilename+ending).getAbsoluteFile( ));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repeate = nrep;
	}

	public void setRepeate(boolean nrepeate){
		this.repeate = nrepeate;
	}
	
	public void run() {
		try{
			Clip clip = AudioSystem.getClip( );
			clip.open(audioInputStream); 
			clip.start( );
		}catch(Exception ex){
			System.out.println("Error with playing sound.");
			ex.printStackTrace( );
		}
		System.out.println("gooo");
	}
	
	public void playSound(){
		try{
			Clip clip = AudioSystem.getClip( );
			clip.open(audioInputStream); 
			clip.start( );
		}catch(Exception ex){
			System.out.println("Error with playing sound.");
			ex.printStackTrace( );
		}
		System.out.println("gooo");
	}	
}	
*/