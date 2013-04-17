package com.dhbw.Zombiz.output.display;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.dhbw.Zombiz.gameEngine.logic.Actor;
import com.dhbw.Zombiz.gameEngine.logic.BuildRoom;
import com.dhbw.Zombiz.gameEngine.logic.Conversation;
import com.dhbw.Zombiz.gameEngine.logic.DialogEntry;
import com.dhbw.Zombiz.gameEngine.parser.XmlParser;

public class DialogOutput {

	public static int cnt;
	public static List<DialogEntry> de;
	public static List<Actor> actors;
	public static BufferedImage backgroundImage;
	public static Conversation c;
	public static JLabel label;
	public static int rootRoomId;
	
	public static JFrame tempFrame;
	
	static JTextArea dialog;
	static JLabel next;
	static BufferedImage nextImage;
	
	
	public void setDialogEntries(List<DialogEntry> dialogEntries){
		this.de = dialogEntries;
	}

	public static List<DialogEntry> getDialogEntries(){
		return de;
	}


	public static List<Actor> getActors() {
		return actors;
	}


	public static void setActors(List<Actor> actors) {
		DialogOutput.actors = actors;
	}


	public static BufferedImage getBackgroundImage() {
		return backgroundImage;
	}


	public static void setBackgroundImage(BufferedImage backgroundImage) {
		DialogOutput.backgroundImage = backgroundImage;
	}


	public static Conversation getConversation() {
		return c;
	}


	public static void setConversation(Conversation c) {
		DialogOutput.c = c;
	}
	
	

	
	public static int getRootRoomId() {
		return rootRoomId;
	}


	public void setRootRoomId(int rootRoomId) {
		this.rootRoomId = rootRoomId;
	}


	//Constructor ! 
	public DialogOutput(JFrame frame, Conversation c, BufferedImage backgroundImage, List<Actor> actors, int rootRoomId ){
		
		
	

		setRootRoomId(rootRoomId);
		setConversation(c);
		setBackgroundImage(backgroundImage);
		setActors(actors);
		setDialogEntries(c.getDialogEntries());
		
		getDialogText(frame, 0);
	
		
	}
	
	

	
    public static void getDialogText(JFrame frame, int cnt){
    	BufferedImage backgroundImage = null;
    	BufferedImage dialogOverlay = null;
    	ImageIcon image = null; 
    	
    	try {
			backgroundImage = getBackgroundImage();
			dialogOverlay	= 	(ImageIO.read(new File("src/main/resources/Picture/DialogBackgrounds/dialogGround.png")));
			nextImage		=  (ImageIO.read(new File("src/main/resources/Picture/Menue/btnExit.png")));
			image = new ImageIcon("src/main/resources/Picture/Menue/btnExit.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	label = new JLabel(new ImageIcon(backgroundImage));
    	
    	backgroundImage.getGraphics().drawImage(dialogOverlay, 0, -15, null);
    	backgroundImage.getGraphics().drawImage(nextImage, 550, 550, 230, 30, null);
    	
    	JLabel nextImage = new JLabel(image);
    	nextImage.setOpaque(true);
    	
    	
    	int speakerId = Integer.parseInt(de.get(cnt).getActor());
		String speakerName = actors.get((speakerId)-1).getName();
		String text = "";
		
		if(de.get(cnt).isGroup()){
			text = " Is Group ...";
			
			List<Integer> linkedDe = de.get(cnt).getLinkedDialogEntries();
			for(int cnt2 = 0; cnt2 < linkedDe.size(); cnt2++){
				// ... do somethin ... if it is group ...
			}
			
			
			
		}else {
			
			
			text = speakerName+" : "+de.get(cnt).getDialogText();
			
			
		
    	}
    
		dialog = new JTextArea();
		
		int dialogEntrySize = de.size();
		
		dialog.setText(text);
		dialog.setForeground(Color.WHITE);
		dialog.setLineWrap(true);
    	dialog.setWrapStyleWord(true);
    	dialog.setEditable(false);
    	dialog.setOpaque(false);
    	dialog.setSize(600, 200);
    	dialog.setLocation(10, 420);
    	dialog.setBorder(new EmptyBorder(10, 10, 10, 10) );
		addClickableFunction(frame, 550, 550, 230, 30, dialogEntrySize);

		
    	frame.add(dialog);
    	frame.add(nextImage);
    	frame.add(label);
    	
    	
		frame.setVisible(true); 
    	frame.repaint();
   }

	  public static void addClickableFunction(final JFrame frame, int x,int  y,int width, int height, final int max){
	    	next = new JLabel();
			next.setBounds(x, y, width,height);
			next.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent me) {
					
					if(cnt < max-1){
					
					
					int nextId = getDialogEntries().get(0).getLinkedDialogEntries().get(0);
					
						
						
					
					
					frame.getContentPane().removeAll();
					frame.repaint();
					
					getDialogText(frame, nextId);
					
					
					 }
					
					
					if(cnt == max-1){
						cnt = 0;
						
						BuildRoom br = new BuildRoom(getRootRoomId(), frame);
						
						
					}
					
					
				}});
			
			
			
			
			
			frame.add(next);
	    }
	
    
 
    
    
    
    
    
    
}
