package com.dhbw.Zombiz.output.display;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
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

import com.dhbw.Zombiz.output.audio.Sound;
import com.dhbw.Zombiz.gameEngine.logic.Runtime;

public class Menu {

	BufferedImage backgroundImage, startGameImage, LoadGameImage;
	String path = "src\\main\\resources\\Picture\\Menue\\";
	Sound sbackgroundSound = new Sound("test",true);
	Thread tbackgroundSound;
	static boolean clicked = false;
	
	public void mainMenu(JFrame frame){
		tbackgroundSound = new Thread(sbackgroundSound,"backgroundSound");
	    tbackgroundSound.start();
		try {
			backgroundImage = ImageIO.read(new File(path+"background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		BufferedImage backgroundImagetmp = backgroundImage;
		JLabel label = new JLabel(new ImageIcon(backgroundImagetmp));
		
		drawButtons(frame);
		
		frame.add(label);

		return;
	}
		
	public void drawButtons(JFrame frame){
		BufferedImage inventoryBag = null;
		BufferedImage btnNewGame,btnLoadGame,btnCredits,btnExit;
		try {
			btnNewGame = ImageIO.read(new File(path+"btnNewGame.png"));
			btnLoadGame = ImageIO.read(new File(path+"btnLoadGame.png"));
			btnCredits = ImageIO.read(new File(path+"btnCredits.png"));
			btnExit = ImageIO.read(new File(path+"btnExit.png"));

		backgroundImage.getGraphics().drawImage(btnNewGame, 25, 200, 200, 33, null);
		JLabel labelNewGame = new JLabel();
		labelNewGame.setBounds(25, 200, 200, 33);
		labelNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent nc){
				Runtime.setFirstRoom(1);
				clicked = true;
				//tclick.interrupt();
			}
		});
		
		backgroundImage.getGraphics().drawImage(btnLoadGame, 25, 250, 200, 33, null);
		JLabel labelLoadGame = new JLabel();
		labelLoadGame.setBounds(50, 250, 200, 33);
		labelLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent lc) {
				if(Runtime.loadGame())
					clicked = true;
				else{
					System.out.println("Unabel to load Savegame Window");
					//TOD
				}
			}			
		});
		
		backgroundImage.getGraphics().drawImage(btnCredits, 25, 300, 200, 33, null);
		JLabel labelCredits = new JLabel();
		labelCredits.setBounds(25, 300, 200, 33);
		labelCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent cc) {
				}
					
			});
		
		backgroundImage.getGraphics().drawImage(btnExit, 25, 350, 200, 33, null);
		JLabel labelExitGame = new JLabel();
		labelExitGame.setBounds(25, 350, 200, 33);
		labelExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ec) {
				Toolkit.getDefaultToolkit().beep();
				System.exit(0);
				}
					
			});
		
		frame.add(labelNewGame);
		frame.add(labelLoadGame);
		frame.add(labelCredits);
		frame.add(labelExitGame);
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		// special ItemID 
	/*	addClickableFunction(700, 50, 80, 80, 999, frame, "inventory");
	*/
	}
		
	/*	
		try {
			backgroundImage = ImageIO.read(new File(path+"background.jpg"));
			JLabel label = new JLabel(new ImageIcon(backgroundImage));		 
	        frame.add(label);
		} catch (IOException e) {
			e.printStackTrace();
		}

		 frame.setVisible(true);

		 final JPanel controls = new JPanel(new GridBagLayout());
		 controls.setOpaque(true);									//JPanel durchsichtig machen
		 GridBagConstraints c = new GridBagConstraints();
		 
		 JLabel label = new JLabel();
			label.setBounds(30, 50, 200, 33);
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					System.exit(0);						
					}
				});
			frame.add(label);
		 
		 /*	BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage,xCoord, yCoord, 40, 60, null);
			
		 try {
			 startGameImage = ImageIO.read(new File(path+"btnNewGame.png"));	 
			 ImageIcon ImageStartGame = new ImageIcon(startGameImage);
			 JLabel btnStartNewGame = new JLabel();
			 btnStartNewGame.setIcon(ImageStartGame);
			 btnStartNewGame.setPreferredSize(new Dimension(200, 33));
			 btnStartNewGame.setOpaque(false);

			 c.fill = GridBagConstraints.HORIZONTAL;
			 c.gridx = 0;
			 c.gridy = 0;
			 controls.add(btnStartNewGame, c);
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
	
	public static void closeGame(){
		System.exit(0);
	}

	
}
