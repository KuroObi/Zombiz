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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

	  public Screen() {
		  JFrame frame = new JFrame();
	        
		  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//beendet das Programm, wenn das Fenster geschlossen wird:
		  frame.setSize(800, 600);
	      frame.setLocationRelativeTo(null);   //Fenster zentrieren
	      
		  drawMenu(frame);
	      
	      
	      frame.setVisible(true);
	  }	
	  
	 public static void main(final String args[]) {
		  Runnable gui = new Runnable() {
			  public void run() {
				  Screen screen = new Screen();
	            	}	
	        };
	        
	        //jede GUI muss auf dem EventDispatchThread gestartet werden:
	        SwingUtilities.invokeLater(gui);
	    }
	    
	  
	  
	  public JLabel setBackgroundOnScreen(String path, JFrame frame){
		  ImageIcon backgroundImage = new ImageIcon(getClass().getResource(path));   //Bild laden:
	      JLabel contentPane = new JLabel();  //als Hintergrundbild setzen;
	      contentPane.setIcon(backgroundImage);
	      contentPane.setLayout(new BorderLayout());
	      frame.setContentPane(contentPane);
	      
	      return contentPane;
		  
	  }
	  
	  public void drawMenu(final JFrame frame){
		 
		  JLabel contentPane = setBackgroundOnScreen("/Pictures/Menue/background.jpg", frame);
		  final JPanel controls = new JPanel(new GridBagLayout());
		  controls.setOpaque(false);//JPanel durchsichtig machen
		  GridBagConstraints c = new GridBagConstraints();
		  
		  
		 
		  
		
		 
		  
		  
		  
	      
		  ImageIcon backgroundImageStartGame = new ImageIcon(getClass().getResource("/Pictures/Menue/btnNewGame.png"));
		  JLabel btnStartNewGame = new JLabel();
		  btnStartNewGame.setIcon(backgroundImageStartGame);
		  btnStartNewGame.setPreferredSize(new Dimension(200, 33));
		  btnStartNewGame.setOpaque(false);
	
		  c.gridx = 0;
	      c.gridy = 1;
		  controls.add(btnStartNewGame,c ); 
		  btnStartNewGame.addMouseListener(new MouseAdapter()
		    {
		        public void mouseClicked(MouseEvent e)
		        {
		            System.out.println("Hello !!!");

		        }
		    });
		  
		  
		  
		  
		  
	      
	      
		  ImageIcon backgroundImageLoadGame = new ImageIcon(getClass().getResource("/Pictures/Menue/btnLoadGame.png"));
		  JLabel btnLoadGame = new JLabel();
		  btnLoadGame.setIcon(backgroundImageLoadGame);
		  btnLoadGame.setPreferredSize(new Dimension(200, 33));
		  btnLoadGame.setOpaque(false);
		 
		  c.gridx = 0;
	      c.gridy = 2;
		  controls.add(btnLoadGame,c ); 
	      
		  ImageIcon backgroundImageCredits = new ImageIcon(getClass().getResource("/Pictures/Menue/btnCredits.png"));
		  JLabel btnCredits = new JLabel();
		  btnCredits.setIcon(backgroundImageCredits);
		  btnCredits.setPreferredSize(new Dimension(200, 33));
		  btnCredits.setOpaque(false);
		  
		  c.gridx = 0;
	      c.gridy = 3;
		  controls.add(btnCredits,c ); 
	      
		  ImageIcon backgroundExit = new ImageIcon(getClass().getResource("/Pictures/Menue/btnExit.png"));
		  JLabel btnExit = new JLabel();
		  btnExit.setIcon(backgroundExit);
		  btnExit.setPreferredSize(new Dimension(200, 33));
		  btnExit.setOpaque(false);
		
		  c.gridx = 0;
	      c.gridy = 4;
		  controls.add(btnExit,c ); 
		  btnExit.addMouseListener(new MouseAdapter()
		    {
		        public void mouseClicked(MouseEvent e)
		        {
		            Menu.closeGame();

		        }
		    });
		  
		  
		  
	      
	      
	      controls.setBorder(new EmptyBorder(0,0,100,500));
	      contentPane.add(controls);
	      
	      }
	  
	  
	 
	  
	  
	 }






	       
