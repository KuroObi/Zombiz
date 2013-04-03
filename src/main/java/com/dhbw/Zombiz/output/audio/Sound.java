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
 * @author Christoph Schabert
 * @version 1.0
 */
public class Sound implements Runnable {
	
	private boolean repeate = false;
    AudioInputStream audio = null;
    private String ending = ".wav";

    public Sound(String nfilename){
		try {
			this.audio = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\audio\\"+nfilename+ending));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public Sound(String nfilename,boolean nrep){
		this.repeate = nrep;
		try {
			this.audio = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\audio\\"+nfilename+ending));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		 try {
			 do{
				 System.out.println("Yeaaaa!");
				 Clip clip = AudioSystem.getClip();
				 clip.open(audio);
				 clip.start();
			 }while(repeate);
	        }
	        catch(IOException ioe) {
	            System.out.println(ioe);
	        }
	        catch(LineUnavailableException lua) {
	            System.out.println(lua);
	        }
	}
}	
