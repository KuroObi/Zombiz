package com.dhbw.Zombiz.output.display;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {
	
	public static void mainMenu(JFrame frame){
		
		System.out.println("Yea");
		
		BufferedImage backgroundImage, startGameImage, LoadGameImage;
		String path = "src\\main\\resources\\Picture\\Menue\\";
		
		try {
			backgroundImage = ImageIO.read(new File(path+"background.jpg"));
			JLabel label = new JLabel(new ImageIcon(backgroundImage));		 
	        frame.add(label);
		} catch (IOException e) {
			e.printStackTrace();
		}

		 frame.setVisible(true);
		 
		 final JPanel controls = new JPanel(new GridBagLayout());
		 controls.setOpaque(false);//JPanel durchsichtig machen
		 GridBagConstraints c = new GridBagConstraints();

		 try {
			 startGameImage = ImageIO.read(new File(path+"btnNewGame.png"));	 
			 ImageIcon ImageStartGame = new ImageIcon(startGameImage);
			 JLabel btnStartNewGame = new JLabel();
			 btnStartNewGame.setIcon(ImageStartGame);
			 //btnStartNewGame.setPreferredSize(new Dimension(200, 33));
			 btnStartNewGame.setOpaque(true);

			 c.gridx = 0;
			   c.gridy = 1;
			 controls.add(btnStartNewGame,c ); 
			 btnStartNewGame.addMouseListener(new MouseAdapter(){
				 public void mouseClicked(MouseEvent e){
					 System.out.println("Starting Game NOW!");
				 }
			 });
			 frame.add(btnStartNewGame);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		try {
		 	LoadGameImage = ImageIO.read(new File(path+"btnLoadGame.png"));	 
		 	ImageIcon ImageStartGame = new ImageIcon(LoadGameImage);
		 	JLabel btnLoadGame = new JLabel();
		 	btnLoadGame.setIcon(ImageStartGame);
		 	btnLoadGame.setPreferredSize(new Dimension(200, 33));
		 	btnLoadGame.setOpaque(false);
		    
		 	c.gridx = 0;
		 	c.gridy = 2;
		 	controls.add(btnLoadGame,c );
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*      
		 -      ImageIcon backgroundImageCredits = new ImageIcon(getClass().getResource("/Pictures/Menue/btnCredits.png"));
		 -      JLabel btnCredits = new JLabel();
		 -      btnCredits.setIcon(backgroundImageCredits);
		 -      btnCredits.setPreferredSize(new Dimension(200, 33));
		 -      btnCredits.setOpaque(false);
		 -      
		 -      c.gridx = 0;
		 -        c.gridy = 3;
		 -      controls.add(btnCredits,c ); 
		 -        
		 -      ImageIcon backgroundExit = new ImageIcon(getClass().getResource("/Pictures/Menue/btnExit.png"));
		 -      JLabel btnExit = new JLabel();
		 -      btnExit.setIcon(backgroundExit);
		 -      btnExit.setPreferredSize(new Dimension(200, 33));
		 -      btnExit.setOpaque(false); 
		*/
	
	
		 frame.setVisible(true);
	
	}

	
	
	public static void closeGame(){
		System.exit(0);
	}

	
}
