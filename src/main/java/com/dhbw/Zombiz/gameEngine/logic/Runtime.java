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

import com.dhbw.Zombiz.output.display.DialogOutput;
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

	private static List <Item> inventory = new ArrayList<Item>();	//List of Items the play have
        private static List <Item> remItems = new ArrayList<Item> () ;
	private static List <Actor> metActors = null;		//List of already meet Actors
	private static List <Room> enterdRooms = null;		//List of already entered Rooms
	private static List <Room> enterableRooms = null;	//List of Rooms the player is able to enter
	private static int enterdRoomCounter = 0;		//Counter of how many Rooms have been entered
	private static int gameState = 0;                       //Monitores the flowcontrol
	public static int currRoomId = 5 ;					//Gives the current Room ID, mainuse for save and starting a new Game
        private static boolean firstConv = true;
        public static int conditionCounter = 0;
        public static boolean dontDraw = false;
        public static boolean receiveYes=false;
        public static boolean profNotMet=true;
        public static int chosenRoom=0;
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
//            BuildRoom br = new BuildRoom(id, frame);
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
        
        public static void checkDialog(int dialogId, int convId){
            switch (convId){
                case 12: if (dialogId==57) receiveYes=true; break;
                case 9:  if (dialogId==16) chosenRoom=21;
                         else if (dialogId==11) chosenRoom=23; break;
                case 10: if (dialogId==16) chosenRoom=21;
                         else if (dialogId==11) chosenRoom=23; break; 
                default: break;
            }
        }        
        
        public static void checkInteraction (int roomObjId, int itemId){
            //using key on door
            if (roomObjId==1 && itemId==1){
                gameState=2;
                conditionCounter=0;
            }
            //using sponge on beamer
            else if (roomObjId==8 && itemId==9){
                conditionCounter+=1;
                dontDraw=true;
                System.out.println("You combined the sponge and the Beamer. Your Counter is "+conditionCounter);
            }
            //all conditions for repairing the elevator
            else if ((roomObjId==11&&itemId==8)||(roomObjId==34&&itemId==14)||(roomObjId==14&&itemId==10))
                conditionCounter+=1;
            //if you get the last note by a combination you change the game state
            if (gameState==6&&conditionCounter==3){
                gameState=7;
            }
            //changing the state if the elevator is repaired
            else if (gameState==8&&conditionCounter==3)
                gameState=9;            
        }        
       
        public static boolean checkStep (int id, char type, char origin){
            boolean possible = true;
            
            //This is the section where the id of the roomobjects will be checked 
            if (type=='o'){
                int blackList = 0;
                if (id==8&&dontDraw){possible=false;}
                //the prohibitions for the gameStates 2-5 are the same, so it would only result in code duplication to write 4 cases.
                if (gameState==2||gameState==3||gameState==4||gameState==5){
                    if (id==3||id==1||id==9) possible=false;
                    }
                else {
                    switch (gameState){
                    case 0: if(id==33) possible=false; break;
                    case 1: if (id==28||id==16) possible=false; break;
                    /*case 2: if (id==3||id==1||id==9) possible=false; break;
                    case 3: if (id==3||id==1||id==9) possible=false; break;
                    case 4: if (id==3||id==1||id==9) possible=false; break;
                    case 5: if (id==3||id==1||id==9) possible=false; break;*/
                    case 6: if (id==3||id==1) possible=false; break;
                    case 7: if (id==3||id==1) possible=false; break;
                    case 8: if (id==1)  possible=false; 
                            if (origin=='u'){
                                if(id==26)firstConv=true;
                                if(id==16||id==15||id==17) possible=false;
                                if(chosenRoom!=0){
                                    if (id!=chosenRoom) possible=false;
                                }
                            }
                            break;
                    
                    default: possible=true;
                            }
                }
            }
                                                           
            //This is the section where the id of the items will be checked
            // 'p'=pickup; 'd'= draw
            /**
             * TODO: Add the contained Items
             */
            else if (type=='i'){
                 if (id==1&&origin=='p'){gameState=1;}
                 else if (origin=='d'&&(id==16||id==2||id==3||id==8||id==10||id==11||id==17)) possible=false;
            }
            //This is the section where the id of the actors is checked
            else if (type=='a'){
            }
            //This is the section where the id of the rooms will be checked
            else if (type=='r'){
                switch (gameState){
                    case 8: if(id==21) chosenRoom=21;
                            else if (id==23) chosenRoom=23; break;
                    default: possible = true; break;
                }
            }
            return possible;
        }
        
        public static boolean checkTrigger(int id){
            boolean triggerSet= false;
            switch (gameState){
                case 2: if (id==1&&firstConv){System.out.println("Yout want to automatically display a conv."); BuildRoom.convStatic=1; BuildRoom.option='b'; triggerSet=true; firstConv=false;}; break;
                case 3: if (id==1&&firstConv){System.out.println("I changed the trigger. ");BuildRoom.convStatic=4; BuildRoom.option='b'; triggerSet=true; gameState=4;} break;
                case 4: break;
                default: triggerSet=false;
            }
            System.out.println("I was called and I want to trigger "+triggerSet+ " I have this state " + gameState + " and I am the firstConv "+firstConv);
            return triggerSet;
        }
        
        //possibility for 'you shall not pass' replacement: actorId 0 stands for automatically called dialog, ergo a monologue displayed when doing something
        public static int chooseConv (int actorId, int roomId){
            int conv=14;
            switch (roomId){
                case 2: if (!receiveYes) {conv=12;}
                        else {conv=13;} break;
                case 7: if(gameState==2){conv=2; gameState=3; firstConv=true;}
                        else if(gameState==3||gameState==4) conv=3;
                        else if(gameState==5)  {conv=5; gameState=6; remItemFromInventory(11);}
                        else if(gameState==7) {
                           conv=7; gameState=8; conditionCounter=0; firstConv=false; 
                           remItemFromInventory(3); remItemFromInventory(15);remItemFromInventory(16); remItemFromInventory(17);
                        }; break;
                case 14: if (firstConv){conv=8; profNotMet=false; firstConv=false;}; break;
                case 10: if (firstConv) {conv=10;}
                         else conv=11; break;
            }
            return conv;
        }
        
        public static int checkAutoItem(int id, char type ,char origin){
            int itemId=0; 
            //origin C=Conversation; r=Combination, i=Inspection; in this case the id of the roomObject is contained in id
            // type c=conversation; i=item; r=roomObject
            //during a conversation you receive an item
            if (origin=='c'){
                switch (id){
                    case 2: itemId=16; break;
                    case 5: itemId=10; break;
                    case 12: if (receiveYes){ itemId=8;} break;
                }
            }
            // for adding an item as a result of a combination
            else if (origin =='r')
                switch (id){
                    case 8: itemId=3; conditionCounter+=1; 
                    if (conditionCounter==3) gameState=7; break;
                }
            //when you inspect an RoomObject and find an Item in it
            else if (origin =='i'){
                //you inspect a roomObj
                if (type=='r')
                    switch (id){
                        case 9: itemId=17; conditionCounter+=1; if (conditionCounter==3) gameState=7; break;
                        case 10: itemId=11; gameState=5; break;
                    }
                else if (type=='i'){
                    System.out.println("You want to find something and inspected item " + id);                    
                }
                
            }
            return itemId;
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
                Runtime.remItems.add(item);
                System.out.println("I added Item " +item.getName());
	}
	/**removes a item from the Inventory
	 * 
	 * @param item the item to remove
	 */
	public static void remItemFromInventory(int item){
		int remIndex = 0;
		for(int cnt = 0; cnt < Runtime.inventory.size(); cnt++){
			int indexInInventory = Runtime.inventory.get(cnt).getId();			
			if(item == indexInInventory)
			 remIndex = cnt;
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
	/**checks if the Room was already entered
	 * 
	 * @param enterdRoom the Room to check
	 * @return true if already entered
	 */
	public static boolean enterdRoom(Room enterdRoom){
		return enterdRooms.contains(enterdRoom);
	}
	/**adds a Room to the EnterableRoom List,
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

        public static List<Item> getRemItems() {
            return remItems;
        }

        public static void setRemItems(List<Item> remItems) {
            Runtime.remItems = remItems;
        }
       
	/**Returns the current Room ID
	 * 
	 * @return current Room ID
	 */
	public static int getCurrRoomId() {
		return currRoomId;
	}
	/**set a new current Room ID
	 * 
	 * @param currRoomId new current Room ID
	 */
	public static void setCurrRoomId(int currRoomId) {
		Runtime.currRoomId = currRoomId;
	}
	/**returns the Game State
	 * 
	 * @return Game state
	 */
    public static int getGameState() {
        return gameState;
    }
    /**set a new Game State
     * 
     * @param gameState the new Game State
     */
    public static void setGameState(int gameState) {
        Runtime.gameState = gameState;
    }
	
    public static boolean isFirstConv() {
        return firstConv;
    }

    public static void setFirstConv(boolean firstConv) {
        Runtime.firstConv = firstConv;
    }
	
}
