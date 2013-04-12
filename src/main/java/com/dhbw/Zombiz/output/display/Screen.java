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
 * - Jan Brodhäcker
 */
package com.dhbw.Zombiz.output.display;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.dhbw.Zombiz.gameEngine.logic.*;
import com.dhbw.Zombiz.gameEngine.logic.Runtime;

public class Screen {
	final JFrame frame = new JFrame("Nightmare On Coblitzallee");
	
	int firstRoom = -1; 
	
	public Screen(){
		
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Menu menu = new Menu();
		menu.pauseMenu(frame);
		frame.setVisible(true);
		while(Menu.clicked == false)
			System.out.println("Wusaaaaa");
		frame.setVisible(false);
		frame.removeAll();
		frame.revalidate();
		frame.repaint();
		BuildRoom room01 = new BuildRoom(Runtime.getFirstRoom()  , frame); 
		frame.setVisible(true);
		System.out.println("Worked!");
	}
	
	
	
	
	
	  
	
}

	 
	  
	  
	 






	       
