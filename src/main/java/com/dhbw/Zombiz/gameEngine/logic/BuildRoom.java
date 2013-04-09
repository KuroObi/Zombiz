package com.dhbw.Zombiz.gameEngine.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.dhbw.Zombiz.gameEngine.parser.XmlParser;

public class BuildRoom {

	
	BufferedImage backgroundImage ; 
	 
	
	public BuildRoom(int roomId, JFrame frame){
		XmlParser p = new XmlParser("src/main/resources/XML/chapter1.xml");
		
		Room room = p.getRoomById(roomId);
		List <Item> items = p.getAllItemsByRoomId(roomId);
		List <Item> roomObjects = p.getAllRoomObjectsByRoomId(roomId);
		List <Actor> actors		= p.getAllNpcsByRoomId(roomId);
		String roomImagePath = trimmPicPath(room.getPicturePath());
		
		// setBackground Image
		try {
			backgroundImage = ImageIO.read(new File(roomImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		// pickable Items
		for(int cntItemPicPath = 0; cntItemPicPath < items.size(); cntItemPicPath++){
	
			String itemPicPath = trimmPicPath(items.get(cntItemPicPath).getPicturePath());
			float xLoc = items.get(cntItemPicPath).getItemLocX();
			float yLoc = items.get(cntItemPicPath).getItemLocY();
			BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage, myRandom(1,800), myRandom(1,600), 40, 60, null);
		}
			
		// RoomObjects 
		for(int cntRoomObjects = 0; cntRoomObjects < roomObjects.size(); cntRoomObjects++){
			String itemPicPath = trimmPicPath(roomObjects.get(cntRoomObjects).getPicturePath());
			float xLoc = roomObjects.get(cntRoomObjects).getItemLocX();
			float yLoc = roomObjects.get(cntRoomObjects).getItemLocY();
			/* BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage, myRandom(1,800), myRandom(1,600), 40, 60, null); */
		}
		
		// NPCs
		for(int cntActors = 0; cntActors < actors.size(); cntActors++){
			String itemPicPath = trimmPicPath(actors.get(cntActors).getPicturePath());
			float xLoc = actors.get(cntActors).getNpcLocX();
			float yLoc = actors.get(cntActors).getNpcLocY();
			/* BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage, myRandom(1,800), myRandom(1,600), 40, 60, null); */
		}
		
		
		
			
		JLabel label = new JLabel(new ImageIcon(backgroundImage));
		 
        frame.add(label);
		
	}	
		
		
	

	
	public String trimmPicPath(String picPath){
		picPath = picPath.trim();
		picPath = picPath.substring(1, picPath.length()-1);
		
		return picPath;
		
	}
	
	
	public static int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}
	
	
}
