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
package com.dhbw.zombiz.output.display;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * TODO:
 * 
 */
public class Screen {
	  Screen() {
		  JFrame frame = new JFrame();
	        
		  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//beendet das Programm, wenn das Fenster geschlossen wird:
		  frame.setSize(300, 100);
	      
		  frame.setLocationRelativeTo(null);   //Fenster zentrieren
	      
		  ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/test.jpg"));   //Bild laden:
	      
	      JLabel contentPane = new JLabel();  //als Hintergrundbild setzen;
	      
	      contentPane.setIcon(backgroundImage);
	      contentPane.setLayout(new BorderLayout());
	      frame.setContentPane(contentPane);
	      
	      JPanel controls = new JPanel();
	      controls.setOpaque(false);//JPanel durchsichtig machen
	      JButton b1 = new JButton("eins");
	      b1.setPreferredSize(new Dimension(100, 33));
	      controls.add(b1);
	      contentPane.add(controls, BorderLayout.PAGE_END);
	      frame.setVisible(true);
	  }	
	  
	  public static void main(final String args[]) {
		  Runnable gui = new Runnable() {
			  public void run() {
				  Screen mainscreen = new Screen();
	            	}	
	        };
	        
	        //jede GUI muss auf dem EventDispatchThread gestartet werden:
	        SwingUtilities.invokeLater(gui);
	    }
}
