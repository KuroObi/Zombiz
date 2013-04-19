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
 * - Jan Brodhäcker
 */

package com.dhbw.Zombiz.gameEngine.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.dhbw.Zombiz.output.display.Menu;
/**This Class contains all Runtime Variables, the Inventory,
 * is responsible for saveing and loading the game and
 * starts a new Game.
 * 
 * @author Christoph Schabert, Jan Brodhäcker
 * @version 1.0
 */
public class Runtime{
	
	private static String savegame = "src\\main\\resources\\savegame.sav";	//path of the Savegame File
	
	//Runtime Variables
	
	private static List <Actor> metActors = null;		//List of already meet Actors
	private static List <Room> enterdRooms = null;		//List of already entered Rooms
	private static List <Room> enterableRooms = null;	//List of Rooms the player is able to enter
	private static int enterdRoomCounter = 0;		//Counter of how many Rooms have been entered
	private static int gameState = 0;                       //Monitores the flowcontrol

    public static int getGameState() {
        return gameState;
    }

    public static void setGameState(int gameState) {
        Runtime.gameState = gameState;
    }
	
	private static List <Item> inventory = new ArrayList<Item>();	//List of Items the play have
	
	public static int currRoomId = 5 ;
	
	/** Constructor for a new Game
	 * 
	 * @param newGame 1 for a new game; 0 for load game
	 * @param frame	the game Frame
	 */
	public Runtime(boolean newGame, JFrame frame){	
		if(newGame){
			//Hier kommt der Prolog hin ... 
			setCurrRoomId(5);
                        gameState=0;
		}else{
			loadGame();
		}
		BuildRoom br = new BuildRoom(currRoomId, frame);
	}
	
	
        /**
         * @param id    The Id of the room that has to be displayed next
         * @param frame A frame object so that the room will be displayed
         * @deprecated 
         */
        public static void changeRoom (int id, JFrame frame){
            BuildRoom br = new BuildRoom(id, frame);
        }
 
        /**
         * @param id            The id of the "thing" to be checked. What that thing is, is determined by the type.
         * @param type          To what does the id belong? If further types will be needed (perhaps Conversation or dialog) they can be easily implemented by assigning a char to them
         * @values for type     o - RoomObject; 
         *                      i - item; 
         *                      a - NPC; 
         *                      r - Room/Location;
         * @param origin        From where was the function called. With this variable it is possible to distinguish what sort of step is being checked
         * @values for origin   u - 'Use' [For example if you still want to make interaction with an object possible, but you're not allowed to use it anymore or use it yet]
         *                      d - 'Draw' [Determines whether a thing should be drawn or not. Helpful for example in the room with Meier, where the Pulley should only be drawn when you're in one certain state.]
         * @return              returns whether the step will be granted or denied
         */
        
        public static boolean checkStep (int id, char type, char origin){
            boolean possible = true;
            
            //This is the section where the id of the roomobjects will be checked 
            if (type=='o'){
                switch (gameState){
                    case 0: if(id==33) possible=false; break;
                    case 1: if (id==28||id==16) possible=false; break;
                    case 2: if (id==3||id==1||(id==16&&origin=='u')) possible=false; break;
                    default: possible=true;
                            
                            }
                }
            
/* former approach; deprecated because the switch case approach is much more practical, but left for further references
 * if (gameState==0&&id==33){
   * possible=false;
                }
                if (gameState==1&&(id==28||id==16)){
                    possible=false;
                }
                if(gameState==2&&(id==3||id==1)){
                    possible=false;
                }*/
                                    
            
            //This is the section where the id of the items will be checked
            if (type=='i'){
            }
            //This is the section where the id of the actors is checked
            if (type=='a'){
            }
            //This is the section where the id of the rooms will be checked
            if (type=='r'){
            }
            return possible;
        }
        
        
	/**saves all Runtime Variables and
	 * the current Room the player is in into the savefile
	 * 
	 */
	public static void saveGame(){
		
		try{
			FileOutputStream saveFile=new FileOutputStream(savegame);
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
		
			//save the Runtime Data
			save.writeObject(metActors);
			save.writeObject(enterdRooms);
			save.writeObject(enterableRooms);
			save.writeObject(enterdRoomCounter);
			save.writeObject(inventory);
			save.writeObject(currRoomId);
			// Close the file.
			save.close();
		}catch(Exception exc){
			System.out.println("Unable to Save game");
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}

	/**loads the game,
	 * all saved Runtime variables are restored and
	 * the last room is returned.
	 * 
	 * if there is no Save File 
	 * or the Game is unable to load it a new Game is started.
	 *
	 * @return return the last Room the player was in
	 */
	public static int loadGame(){
		int lastRoom,ncurrRoomId ;
		try{
			FileInputStream saveFile = new FileInputStream(savegame);
			ObjectInputStream save = new ObjectInputStream(saveFile);
		
			// Load the Objects into the Runtime
			metActors = (List<Actor>) save.readObject();
			enterdRooms = (List<Room>) save.readObject();
			enterableRooms = (List<Room>) save.readObject();
			enterdRoomCounter = (Integer) save.readObject();
			inventory = (List<Item>) save.readObject();
			ncurrRoomId = (Integer) save.readObject();
			// Close the file.
			save.close();
		}catch(Exception exc){
			System.out.println("Unable to load Savegame");
			metActors = null;
			enterdRooms = null;
			enterableRooms = null;
			enterdRoomCounter = 0;
			inventory = null; 			//TODO: Set start Inventory
			ncurrRoomId = 5;
		}
		setCurrRoomId(ncurrRoomId);
		return ncurrRoomId;
	}
	
	/**Adds a item into the Inventory
	 * 
	 * @param item the item to add into the Inventory
	 */
	public static void addItemToInventory(Item item){
		Runtime.inventory.add(item);
		System.out.println("Added Item "+item.getName());
	}
	/**removes a item from the Inventory
	 * 
	 * @param item the item to remove
	 */
	public static void remItemFromInventory(Item item){
		int remItemId = item.getId();
		int remIndex = 0;
		for(int cnt = 0; cnt < Runtime.inventory.size(); cnt++){
			int indexInInventory = Runtime.inventory.get(cnt).getId();
			
			if(remItemId == indexInInventory)
				remIndex = indexInInventory;
		}
		
		Runtime.inventory.remove(remIndex);
		
	}
	/**add a Actor to the already meet Actors List
	 * 
	 * @param newmetAcctor the new Acctor
	 */
	public static void addMetAcctor(Actor newmetAcctor){
		metActors.add(newmetAcctor);
	}
	/**checks if the player allready meet the Actor
	 * 
	 * @param metAcctor the Actor to check
	 * @return ture if allready meet and false if not
	 */
	public static boolean metAcctor(Actor metAcctor){
		return metActors.contains(metAcctor);
	}
	/**addes a new Enterd Room to the Enterd Room List
	 * and increment the enterdRoomCounter,
	 * if the Room is allready in the List nothing happens
	 * 
	 * @param newEnterdRoom the new Enterd Room
	 */
	public static void addenterdRoom(Room newEnterdRoom){
		if(enterdRooms.contains(newEnterdRoom))
			return;
		enterdRooms.add(newEnterdRoom);
		enterdRoomCounter++;
	}
	/**checks if the Room was allrady enterd
	 * 
	 * @param enterdRoom the Room to check
	 * @return true if allready enterd
	 */
	public static boolean enterdRoom(Room enterdRoom){
		return enterdRooms.contains(enterdRoom);
	}
	/**addes a Room to the EnterableRoom List,
	 * if the Room is already in the list nothing happens
	 * 
	 * @param newEnterableRoom the new enterable Room
	 */
	public static void addEnterableRoom(Room newEnterableRoom){
		if(!enterableRooms.contains(newEnterableRoom))
			enterableRooms.add(newEnterableRoom);		
	}
	/**remove a Room from the enterable Room list,
	 * if the Room is not in the List nothing happens
	 * @param oldEntarableRoom the Room to remove
	 */
	public static void removeEnterableRomm(Room oldEntarableRoom){
		if(enterableRooms.contains(oldEntarableRoom))
			enterableRooms.remove(oldEntarableRoom);
	}
	/** Checks if a Room is enterable
	 * 
	 * @param enterableRoom the Room to check
	 * @return ture if the Room is enterable
	 */
	public static boolean enterableRoom(Room enterableRoom){
		return enterableRooms.contains(enterableRoom);
	}
	/**Return the number of Visited Rooms
	 * 
	 * @return number of enterd Rooms
	 */
	public static int getEnterdRoomCounter(){
		return enterdRoomCounter;
	}
	/** returns the current Inventory List
	 * 
	 * @return a List<Item>
	 */
	public static List<Item> getInventory() {
		return inventory;
	}
	/**replace the Current inventory
	 * 
	 * @param inventory the new Inventory
	 */
	public static void setInventory(List<Item> inventory) {
		Runtime.inventory = inventory;
	}

	public static int getCurrRoomId() {
		return currRoomId;
	}

	public static void setCurrRoomId(int currRoomId) {
		Runtime.currRoomId = currRoomId;
	}
	
	
	
}
