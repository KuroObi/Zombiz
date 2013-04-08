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


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import com.dhbw.Zombiz.gameEngine.logic.*;
import com.dhbw.Zombiz.gameEngine.logic.Menu;



/**
 * TODO:
 * 
 */
public class Screen {
	final JFrame frame = new JFrame("Nightmare On Coblitzallee");
	
	
	
	public Screen(){
		
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		BuildRoom room01 = new BuildRoom(8, frame); 
		
	    

		frame.setVisible(true);
		
		
	}
	
	
	
	
	
	  
	
}

	 
	  
	  
	 






	       
