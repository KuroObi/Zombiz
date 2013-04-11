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
 *Package: com.dhbw.zombiz.output.display
 *
 *Contributors:
 * -Christoph Schabert

 */
package com.dhbw.Zombiz.output.display;


import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.dhbw.Zombiz.gameEngine.logic.*;



/**
 * TODO:
 * 
 */
public class Screen {
	final JFrame frame = new JFrame("Nightmare On Coblitzallee");
	
	
	
	public Screen(){
		
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// draw Mainmenue 
	   
		
		BuildRoom room01 = new BuildRoom(8, frame); 
		
	    
	
		frame.setVisible(true);
		
		
	}
	
	
	
	
	
	  
	
}

	 
	  
	  
	 






	       
