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

import com.dhbw.Zombiz.gameEngine.logic.Runtime;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Menu {
	
	public static void mainMenu(final JFrame frame){
		
		
	
		
		BufferedImage backgroundImage = null;
		BufferedImage startGameImage = null; 
		BufferedImage loadGameImage = null;
		BufferedImage creditsGameImage = null;
		BufferedImage exitGameImage = null;
		
		String path = "src/main/resources/Picture/Menue/";
		
		try {
			backgroundImage = ImageIO.read(new File(path+"background.jpg"));
			startGameImage = ImageIO.read(new File(path+"btnNewGame.png"));
			loadGameImage = ImageIO.read(new File(path+"btnLoadGame.png"));	 
			creditsGameImage = ImageIO.read(new File(path+"btnCredits.png"));	
			exitGameImage = ImageIO.read(new File(path+"btnExit.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel label = new JLabel(new ImageIcon(backgroundImage));
		
		backgroundImage.getGraphics().drawImage(startGameImage, 30, 200, 180, 25, null);
		backgroundImage.getGraphics().drawImage(loadGameImage, 30, 250, 180, 25, null);
		backgroundImage.getGraphics().drawImage(creditsGameImage, 30, 300, 180, 25, null);
		backgroundImage.getGraphics().drawImage(exitGameImage, 30, 350, 180, 25, null);
		
		JLabel startGamelabel = new JLabel();
		startGamelabel.setBounds(30, 200, 180, 25);
		
		JLabel loadGamelabel = new JLabel();
		loadGamelabel.setBounds(30, 250, 180, 25);
		
		JLabel creditsGamelabel = new JLabel();
		creditsGamelabel.setBounds(30, 300, 180, 25);
		
		JLabel exitGamelabel = new JLabel();
		exitGamelabel.setBounds(30, 350, 180, 25);
		
		startGamelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
                            System.out.println("you started a new game ");
				Runtime r = new Runtime(true, frame);
			}});
		
		loadGamelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				System.out.println("Load ....");
			}});
		
		creditsGamelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				System.out.println("Credits ...");
			}});
		
		exitGamelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				closeGame();
			}});
		
		
		
		frame.add(startGamelabel);
		frame.add(loadGamelabel);
		frame.add(creditsGamelabel);
		frame.add(exitGamelabel);
		
		 frame.add(label);
		 frame.setVisible(true);
		 frame.repaint();		 
	    
		
	        
	}

	
	
	public static void closeGame(){
		System.exit(0);
	}

	
}
