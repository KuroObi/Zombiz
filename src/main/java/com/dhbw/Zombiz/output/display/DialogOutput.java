package com.dhbw.Zombiz.output.display;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.dhbw.Zombiz.gameEngine.logic.Actor;
import com.dhbw.Zombiz.gameEngine.logic.Conversation;
import com.dhbw.Zombiz.gameEngine.logic.DialogEntry;



public class DialogOutput {

	
	public int nextDeId;
	public Conversation conv;
	public List<Actor> actors;

	

	public List<Actor> getActors() {
		return actors;
	}


	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}


	public Conversation getConv() {
		return conv;
	}


	public void setConv(Conversation conv) {
		this.conv = conv;
	}


	public int getNextDeId() {
		return nextDeId;
	}


	public void setNextDeId(int nextDeId) {
		this.nextDeId = nextDeId;
	}


	//Constructor ! 
	public DialogOutput(JFrame frame, Conversation c, BufferedImage backgroundImage, List<Actor> actors, int rootRoomId ){
		
		
		
		
		setActors(actors);
		
		setConv(c);
		
		
		Conversation conversation = c; 
		JTextArea dialog =  getDialogEntry(conversation, actors, true);
		
		frame.add(dialog);
		setDialogBackground(frame, backgroundImage);
		frame.repaint(); 
		
		
	}
	
	
	public JTextArea getDialogEntry(Conversation c, List<Actor> actors, boolean firstCall){
		String text = null;
		if(firstCall){
			DialogEntry deFirst = c.getDialogEntryById(1);
			
			String actorName = actors.get(Integer.parseInt(deFirst.getActor())).getName();

			text = actorName +" : "+deFirst.getDialogText();
			setNextDeId(deFirst.getLinkedDialogEntries().get(0));
			
		}
		else {
			DialogEntry de = c.getDialogEntryById(getNextDeId());
			
			String actorName = actors.get(Integer.parseInt(de.getActor())).getName();
			
			System.out.println("next id"+getNextDeId());
			text = actorName + " : "+de.getActor()+" : "+de.getDialogText();
			
			
			int nextId = 1;
			int optionOne = 0;
			int optionTwo = 0;
			int optionThree = 0;
			
			if(!(de.isGroup())){
				nextId = de.getLinkedDialogEntries().get(0);
			}
			else {
				if(de.getLinkedDialogEntries().size() > 0)
					optionOne = de.getLinkedDialogEntries().get(0);
				if(de.getLinkedDialogEntries().size() > 1)
					optionTwo = de.getLinkedDialogEntries().get(1);
				if(de.getLinkedDialogEntries().size() > 2)
					optionThree = de.getLinkedDialogEntries().get(2);
				
				if(optionOne != 0){
					//System.out.println("Option 1 :"+c.getDialogEntryById(optionOne).getDialogText());
				}
				if(optionTwo != 0){
					//System.out.println("Option 2 :"+c.getDialogEntryById(optionTwo).getDialogText());
				}
				if(optionThree != 0){
					//System.out.println("Option 3 :"+c.getDialogEntryById(optionThree).getDialogText());
				}
				
				
				int option = 1;
				
				//System.out.println("Option : "+option);
				
				switch(option){
					case 1: 
						nextId = optionOne;
						break;
					case 2: 
						nextId = optionTwo;
						break;
					case 3:
						nextId = optionThree;
						break;
				}
				
			}
			
			
	    	
			setNextDeId(nextId);
			
		}
		
		JTextArea dialog = new JTextArea();
		dialog.setText(text);
		dialog.setForeground(Color.WHITE);
		dialog.setLineWrap(true);
    	dialog.setWrapStyleWord(true);
    	dialog.setEditable(false);
    	dialog.setOpaque(false);
    	dialog.setSize(600, 200);
    	dialog.setLocation(10, 420);
    	dialog.setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		
		return dialog;
		
		
		
		//System.out.println(text);
		
	}
	
	
	
	
	
	
	
	
	public void setDialogBackground(JFrame frame, BufferedImage backgroundImage){
		
		
		BufferedImage dialogBackground = null; 
		BufferedImage dialogNext	= null;
		try {
			dialogBackground = ImageIO.read(new File("src/main/resources/Picture/DialogBackgrounds/dialogGround.png"));
			dialogNext = ImageIO.read(new File("src/main/resources/Picture/DialogBackgrounds/nextBtn.png"));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		backgroundImage.getGraphics().drawImage(dialogBackground, 0, -10, null);
		backgroundImage.getGraphics().drawImage(dialogNext, 600, 500, 166, 84, null);
		
		addClickableFunction(frame, 600, 500, 166, 84);
		
		frame.repaint();
	}


	public void addClickableFunction(final JFrame frame, int xLoc, int yLoc,int width, int height){
		JLabel label = new JLabel();
		label.setBounds(xLoc, yLoc, width, height);
	
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				JTextArea dialog = getDialogEntry(getConv(), getActors(), false);
				frame.add(dialog);
				frame.repaint();
			}});
				
		frame.add(label);
		
	}
	
	
}

    