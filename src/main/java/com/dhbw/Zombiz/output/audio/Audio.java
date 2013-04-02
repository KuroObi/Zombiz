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
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;



/**
 * @author Christoph Schabert
 * @version 1.0
 */
public class Audio implements Runnable {
	
	private boolean repeate = false;
	private String filename = "test";
	private String ending = ".wav";

	final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;

	public Audio(String nfilename){
		this.filename = nfilename;
	}
	
	public Audio(String nfilename, boolean nrepeate){
		this.filename = nfilename;
		this.repeate = nrepeate;
	}
	
	public void setRepeate(boolean nrepeate){
		this.repeate = nrepeate;
	}
	
	public void run() {
		playSound();
	}	
	
	/**
	 * 
	 * @param filename the name of the file that is going to be played
	 *
	 */
	public void playSound(){
		URL url = this.getClass().getResource("/"+filename+ending);
	try {
		soundFile = new File(url.getFile());
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
	
	try {
		audioStream = AudioSystem.getAudioInputStream(soundFile);
	} catch (Exception e){
		e.printStackTrace();
		System.exit(1);
	}
	
	audioFormat = audioStream.getFormat();
	
	DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	try {
		sourceLine = (SourceDataLine) AudioSystem.getLine(info);
		sourceLine.open(audioFormat);
	} catch (LineUnavailableException e) {
		e.printStackTrace();
		System.exit(1);
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
	
	sourceLine.start();
	do{
		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {       
				nBytesRead = audioStream.read(abData, 0, abData.length);            	
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}
	}while(repeate);
	sourceLine.drain();
	sourceLine.close();
	}

}	
