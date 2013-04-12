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
 *Package: com.dhbw.Zombiz.gameEngine.logic
 *
 *Contributors:
 * -Christoph Schabert
 */
package com.dhbw.Zombiz.gameEngine.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Runtime{
	
	private static String savegame = "src\\main\\resources\\savegame.sav";
	
	//Runtime Variables
	
	private static List <Actor> metActors = null;
	private static List <Room> enterdRooms = null;
	private static List <Room> enterableRooms = null;
	private static int enterdRoomCounter = 0;
	private static int firstRoom = 1;
	
	/** Construtor for a new Game
	 * 
	 */
	public Runtime(){
	}

	/** Construtor for load a Game
	 * @param load = 1 [load Savegame]
	 */
	public Runtime(boolean load){
	
	}
	
	public static void saveGame(){
		
		try{
			// Open a file to write to, named SavedObj.sav.
			FileOutputStream saveFile=new FileOutputStream(savegame);
	
			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
		
			//save the Runtime Data
			save.writeObject(metActors);
			save.writeObject(enterdRooms);
			save.writeObject(enterableRooms);
			save.writeObject(enterdRoomCounter);
			int getCurrentRoom = firstRoom; 			//Testing
			save.writeObject(getCurrentRoom); //
			
			// Close the file.
			save.close();
		}catch(Exception exc){
			System.out.println("Unable to Save game");
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}

	public static boolean loadGame(){
	
		try{
			// Open file to read from, named SavedObj.sav.
			FileInputStream saveFile = new FileInputStream(savegame);
		
			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream save = new ObjectInputStream(saveFile);
		
			metActors = (List<Actor>) save.readObject();
			enterdRooms = (List<Room>) save.readObject();
			enterableRooms = (List<Room>) save.readObject();			
			enterdRoomCounter = (Integer) save.readObject();
			firstRoom = (Integer) save.readObject();
			
			// Close the file.
			save.close();
		}catch(Exception exc){
			System.out.println("Unable to load Savegame");
			exc.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void addMetAcctor(Actor newmetAcctor){
		metActors.add(newmetAcctor);
	}
	public static boolean metAcctor(Actor metAcctor){
		return metActors.contains(metAcctor);
	}
	public static void addenterdRoom(Room newEnterdRoom){
		enterdRooms.add(newEnterdRoom);
		enterdRoomCounter++;
	}
	public static boolean enterdRoom(Room enterdRoom){
		return enterdRooms.contains(enterdRoom);
	}
	public static void addEnterableRoom(Room newEnterableRoom){
		enterableRooms.add(newEnterableRoom);		
	}
	public static void removeEnterableRomm(Room oldEntarableRoom){
		if(enterableRooms.contains(oldEntarableRoom))
			enterableRooms.remove(oldEntarableRoom);
	}
	public static boolean enterableRoom(Room enterableRoom){
		return enterableRooms.contains(enterableRoom);
	}
	public static int getEnterdRoomCounter(){
		return enterdRoomCounter;
	}
	public static int getFirstRoom(){
		return firstRoom;
	}
	public static void setFirstRoom(int nFirstRoom){
		firstRoom = nFirstRoom; 
	}
}
