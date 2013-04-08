package com.dhbw.Zombiz.gameEngine.logic;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import com.dhbw.Zombiz.gameEngine.parser.XmlParser;
import com.dhbw.Zombiz.output.display.Background;
import com.dhbw.Zombiz.output.display.Screen;

public class BuildRoom {

	
	public BuildRoom(int roomId, JFrame frame){
		XmlParser p = new XmlParser("src/main/resources/XML/chapter1.xml");
		
		Room room = p.getRoomById(roomId);
		List <Item> items = p.getAllItemsByRoomId(roomId);
		List <Item> roomObjects = p.getAllRoomObjectsByRoomId(roomId);
		List <Actor> actors		= p.getAllNpcsByRoomId(roomId);
		String roomImagePath = room.getPicturePath();
		
		roomImagePath = roomImagePath.trim();
		roomImagePath = roomImagePath.substring(1, roomImagePath.length()-1);
		
		
		try {
			frame.getContentPane().add(new Background(roomImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
		
		
		
		
	}

}
