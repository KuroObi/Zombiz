package com.dhbw.Zombiz.gameEngine.parser;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dhbw.Zombiz.gameEngine.logic.*;


public  class XmlParser {
	String xmlFilePath ="";
	Document xmlFile;
	boolean debugConsole;
	
	List<Actor> listActors = new ArrayList<Actor>();
	List<Item>	listRoomItems = new ArrayList<Item>();
	List<Item>	listPickableItems = new ArrayList<Item>();
	List<Room>	listRooms = new ArrayList<Room>(); 
	
	public  XmlParser(String filePath){
		setXmlFilePath(filePath);
	    openXmlFile();
	    
	    
	    getAllActors();
	    getAllItems();
	    getAllRooms();
	    
	}
	
	
	
	
	 public Actor getActorById (int actorId){
		 Actor actor = listActors.get(actorId);
		 return actor;
	 }
	 
	 public Room getRoomById (int roomId){
		 Room room = listRooms.get(roomId-1);
		 return room;
	 }
	 
	 
	 public Item getPickableItemById(int itemId){
		 Item item = listPickableItems.get(itemId);
		 return item;
	 }
	 
	 public Item getRoomItemsById(int itemId){
		 Item item = listRoomItems.get(itemId);
		 return item;
	 }
	 
	 
	 
	 
	 public List<Item> getAllItemsByRoomId(int roomId){
		 Room room = getRoomById(roomId);
		 String roomObjects = room.getGameObjectsIncluded();
		 String[] values = roomObjects.split(","); 
		 List<Item> tmpListRoomItems = new ArrayList<Item>();

			for(int cntItems=0; cntItems<values.length; cntItems++){
				if(values[cntItems].contains("ItemID")){
					values[cntItems] = values[cntItems].trim();
					String itemId = values[cntItems].substring(7,10);
					String x = values[cntItems].substring(11,17);
			        String y = values[cntItems].substring(18,24);
			       
			        float itemLocX = Float.parseFloat(x);
			        float itemLocY = Float.parseFloat(y);
					
					
					Item item = getPickableItemById(Integer.parseInt(itemId)-1);
					item.setItemLocX(itemLocX);
					item.setItemLocY(itemLocY);
					
					tmpListRoomItems.add(item); 
				}
			}
		 return tmpListRoomItems;
	 }
	 
	 public List<Item> getAllRoomObjectsByRoomId(int roomId){
		 Room room = getRoomById(roomId);
		 String roomObjects = room.getGameObjectsIncluded();
		 String[] values = roomObjects.split(","); 
		 List<Item> tmpListRoomItems = new ArrayList<Item>();
		    
		 for(int cntItems=0; cntItems<values.length; cntItems++){
				
				
				if(values[cntItems].contains("RoomObjectID")){
					values[cntItems] = values[cntItems].trim();
					String itemId = values[cntItems].substring(13,16);
					String x = values[cntItems].substring(17,23);
				    String y = values[cntItems].substring(24,30);
				    
				    float itemLocX = Float.parseFloat(x);
				    float itemLocY = Float.parseFloat(y);
				    
					
					Item item = getRoomItemsById(Integer.parseInt(itemId)-1);
					item.setItemLocX(itemLocX);
					item.setItemLocY(itemLocY);
					
					tmpListRoomItems.add(item); 
				}
			}
		 return tmpListRoomItems;
	 }
	
	 
	 public List<Actor> getAllNpcsByRoomId(int roomId){
		 Room room = getRoomById(roomId);
		 String roomObjects = room.getNpcs();
		 String[] values = roomObjects.split(","); 
		 List<Actor> tmpListActor = new ArrayList<Actor>();
		 
		 for(int cntItems=0; cntItems<values.length; cntItems++){
				if(values[cntItems].contains("NPCID")){
					values[cntItems] = values[cntItems].trim();
					
					String itemId = values[cntItems].substring(6,9);
					String x = values[cntItems].substring(10,16);
			        String y = values[cntItems].substring(17,23);
			       
			        float npcLocX = Float.parseFloat(x);
			        float npcLocY = Float.parseFloat(y);
					
					Actor actor = getActorById(Integer.parseInt(itemId));
					actor.setNpcLocX(npcLocX);
					actor.setNpcLocY(npcLocY);
					tmpListActor.add(actor); 
					
					
				}
			}
		 
		 
		 
		 
		 return tmpListActor;
		 
	 }
	
	 
	 
	 
	 
	 
	
	public List<Actor> getListOfActors(){
		return listActors;
	}
	
	
	public List<Room> getListOfRooms(){
		return listRooms;
	}
	
	public List<Item> getPickableItems(){
		return listPickableItems;
	}
	
	public List<Item> getRoomItems(){
		return listRoomItems;
	}
	
	
	
	
	
	
	public void setDebugConsole(boolean debug){
		debugConsole = debug;
	}
	
	
	private void setXmlFilePath(String filePath){
		xmlFilePath = filePath;
		
	}
	
	private String getXmlFilePath(){
		return xmlFilePath;
	}
	
	
	private void setXmlFile(Document file){
		xmlFile = file;
	}
	
	private Document getXmlFile(){
		return xmlFile;
	}
	
	
	private void openXmlFile(){
		String filePath = getXmlFilePath();
		
		
		
		try {
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			setXmlFile(doc);
		 }
		 catch (Exception e) {
			e.printStackTrace();
			    }
	}	
	
	
	private void getAllActors(){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Actor");
		
		
		if(debugConsole){
		System.out.println("Actors in XML-File");
		System.out.println("-------------------");
		System.out.println("");
		System.out.println("Number : "+nList.getLength());
		}
		
		System.out.println("");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
		
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			Actor actor = new Actor(Integer.parseInt(eElement.getElementsByTagName("Value").item(12).getTextContent()));

			if(debugConsole){
			System.out.println("ActorID: " + eElement.getAttribute("ID"));
			System.out.println("--------");
			}
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				
				actor.setName(eElement.getElementsByTagName("Value").item(0).getTextContent());
				actor.setPicturePath(eElement.getElementsByTagName("Value").item(1).getTextContent());
				actor.setDescription(eElement.getElementsByTagName("Value").item(2).getTextContent());
				if(eElement.getElementsByTagName("Value").item(3).getTextContent().equalsIgnoreCase("True")){
					actor.setPlayer(true);
					}
					else actor.setPlayer(false);
				actor.setAge(Integer.parseInt(eElement.getElementsByTagName("Value").item(4).getTextContent()));
				actor.setGender(eElement.getElementsByTagName("Value").item(5).getTextContent());
				actor.setOccupation(eElement.getElementsByTagName("Value").item(6).getTextContent());
				actor.setRank(eElement.getElementsByTagName("Value").item(7).getTextContent());
				actor.setFaction(eElement.getElementsByTagName("Value").item(8).getTextContent());
				actor.setAbilities(eElement.getElementsByTagName("Value").item(9).getTextContent());
				if(eElement.getElementsByTagName("Value").item(10).getTextContent().equalsIgnoreCase("True")){
					actor.setFixedLocation(true);
					}
					else actor.setFixedLocation(false);
				actor.setLocation(eElement.getElementsByTagName("Value").item(11).getTextContent());
				actor.setActorClass(eElement.getElementsByTagName("Value").item(13).getTextContent());
				actor.setActorSubClass(eElement.getElementsByTagName("Value").item(14).getTextContent());
				actor.setFiles2dPath(eElement.getElementsByTagName("Value").item(15).getTextContent());
				actor.setFiles3dPath(eElement.getElementsByTagName("Value").item(16).getTextContent()); 
				
				
				
				if(debugConsole){
				System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Is Player : " + 			eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Age : " + 					eElement.getElementsByTagName("Value").item(4).getTextContent());
				System.out.println("Gender: " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("Occupation : " + 			eElement.getElementsByTagName("Value").item(6).getTextContent()); 
				System.out.println("Rank : " + 					eElement.getElementsByTagName("Value").item(7).getTextContent());
				System.out.println("Faction : " + 				eElement.getElementsByTagName("Value").item(8).getTextContent());
				System.out.println("Ability : " + 				eElement.getElementsByTagName("Value").item(9).getTextContent()); 
				System.out.println("Fixed Location : " + 		eElement.getElementsByTagName("Value").item(10).getTextContent()); 
				System.out.println("Location : " + 				eElement.getElementsByTagName("Value").item(11).getTextContent()); 
				System.out.println("NPC Id : " + 				eElement.getElementsByTagName("Value").item(12).getTextContent()); 
				System.out.println("Class : " + 				eElement.getElementsByTagName("Value").item(13).getTextContent());
				System.out.println("Subclass : " + 				eElement.getElementsByTagName("Value").item(14).getTextContent()); 
				System.out.println("Texture Files (2D) : " + 	eElement.getElementsByTagName("Value").item(15).getTextContent());
				System.out.println("Texture Files (3D) : " + 	eElement.getElementsByTagName("Value").item(16).getTextContent());

				System.out.println("");
				}
			}
			
			
			listActors.add(actor);
		}
	}
	
	

	

	private void getAllItems(){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Item");
		
		if(debugConsole){
		System.out.println("Items in XML-File");
		System.out.println("-------------------");
		System.out.println("");
		System.out.println("Number : "+nList.getLength());
		
		System.out.println("");
		}
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			
			Node nNode = nList.item(temp);
			
			Element eElement = (Element) nNode;
			
			boolean tmpIsRoomObject = false;
			Item item = new Item(Integer.parseInt(eElement.getElementsByTagName("Value").item(10).getTextContent()));

			if(debugConsole){
			System.out.println("ItemID: " + eElement.getAttribute("ID"));
			System.out.println("--------");
			}
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				item.setName(eElement.getElementsByTagName("Value").item(0).getTextContent());
				item.setPicturePath(eElement.getElementsByTagName("Value").item(1).getTextContent());
				item.setDescription(eElement.getElementsByTagName("Value").item(2).getTextContent());
				item.setPrimaryLocation(eElement.getElementsByTagName("Value").item(3).getTextContent());
				item.setSecondaryLocation(eElement.getElementsByTagName("Value").item(4).getTextContent());
				item.setAssociatedWith((eElement.getElementsByTagName("Value").item(5).getTextContent()));
				item.setCombinesWith((eElement.getElementsByTagName("Value").item(6).getTextContent()));
				item.setContains(eElement.getElementsByTagName("Value").item(7).getTextContent());
				if(eElement.getElementsByTagName("Value").item(8).getTextContent().equalsIgnoreCase("True")){
					item.setCollectible(true);
					}
					else item.setCollectible(false);
				if(eElement.getElementsByTagName("Value").item(9).getTextContent().equalsIgnoreCase("True")){
					item.setUseable(true);
					}
					else item.setUseable(false);
				//item.setId(Integer.parseInt(eElement.getElementsByTagName("Value").item(10).getTextContent()));
				if(eElement.getElementsByTagName("Value").item(11).getTextContent().equalsIgnoreCase("True")){
					item.setRoomObject(true);
					tmpIsRoomObject = true; 
					}
					else item.setRoomObject(false);
				item.setCondition(eElement.getElementsByTagName("Value").item(12).getTextContent());
				
				
			
				
	
				
				
				
				if(debugConsole){
				System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Primary Location : " +	 			eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Secondary Location : " + 		eElement.getElementsByTagName("Value").item(4).getTextContent());				
				System.out.println("associated with : " + 		eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("combines with : " + 			eElement.getElementsByTagName("Value").item(6).getTextContent());
				System.out.println("contains  " + 	eElement.getElementsByTagName("Value").item(7).getTextContent()); 
				System.out.println("Is collectible : " + 	eElement.getElementsByTagName("Value").item(8).getTextContent()); 
				System.out.println("Is usable : " + 			eElement.getElementsByTagName("Value").item(9).getTextContent()); 
				System.out.println("Id : " + 				eElement.getElementsByTagName("Value").item(10).getTextContent());
				System.out.println(" is RoomObject : " + 				eElement.getElementsByTagName("Value").item(11).getTextContent());
				System.out.println("roomObjectID : " + 				eElement.getElementsByTagName("Value").item(12).getTextContent());

				
				System.out.println("");
				}
			}	
			
			
			//two lists ! roomObjects &  pickableItems
			if(tmpIsRoomObject){
				listRoomItems.add(item);
			}
			else listPickableItems.add(item);
			
		}

		
		
		
	}
	
	

	
	
	
	
	private void getAllRooms(){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Location");
		
		if(debugConsole){
		System.out.println("Locations in XML-File");
		System.out.println("-------------------");
		System.out.println("");
		System.out.println("Number : "+nList.getLength());
		
		System.out.println("");
		}
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			
			Node nNode = nList.item(temp);
			
			Element eElement = (Element) nNode;
			
			
			Room room = new Room(Integer.parseInt(eElement.getElementsByTagName("Value").item(9).getTextContent()));
			
			if(debugConsole){
			System.out.println("Location: " + eElement.getAttribute("ID"));
			System.out.println("--------");
			}
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				room.setName(eElement.getElementsByTagName("Value").item(0).getTextContent());
				room.setPicturePath(eElement.getElementsByTagName("Value").item(1).getTextContent());
				room.setDescription(eElement.getElementsByTagName("Value").item(2).getTextContent());
				room.setAct(eElement.getElementsByTagName("Value").item(3).getTextContent());
				room.setChapter(eElement.getElementsByTagName("Value").item(4).getTextContent());
				room.setScene(eElement.getElementsByTagName("Value").item(5).getTextContent());
				room.setFunction(eElement.getElementsByTagName("Value").item(6).getTextContent());
				room.setFloor(eElement.getElementsByTagName("Value").item(7).getTextContent());
				room.setMood(eElement.getElementsByTagName("Value").item(8).getTextContent());
				room.setLocationId(eElement.getElementsByTagName("Value").item(9).getTextContent());
				room.setGameObjectsIncluded(eElement.getElementsByTagName("Value").item(10).getTextContent());
				room.setNpcs(eElement.getElementsByTagName("Value").item(11).getTextContent());

				
				
				if(debugConsole){
				System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Act : " + 			eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Chapter : " + 					eElement.getElementsByTagName("Value").item(4).getTextContent());
				System.out.println("Scene : " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("Floor : " + 				eElement.getElementsByTagName("Value").item(6).getTextContent());
				System.out.println("Function : " + 					eElement.getElementsByTagName("Value").item(7).getTextContent());
				System.out.println("Location ID : " + 				eElement.getElementsByTagName("Value").item(8).getTextContent());
				System.out.println("GameObjectsIncluded : " + 				eElement.getElementsByTagName("Value").item(9).getTextContent()); 
				System.out.println("NPCs : " + 				eElement.getElementsByTagName("Value").item(10).getTextContent()); 
				

				System.out.println("");
				}
			}	
			listRooms.add(room);
		}
	}

	

	
/*	
	public void getConversationById(int conversationId){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Conversation");
		
		if(debugConsole){
		System.out.println("Conversation : "+conversationId);
		System.out.println("-------------------");
		}
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			
			
			
			if(Integer.toString(conversationId).equalsIgnoreCase(eElement.getAttribute("ID"))) {
				
				if(debugConsole){
				System.out.println("Title : " + 						eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Pictures : " + 						eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 					eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Actor : " + 						eElement.getElementsByTagName("Value").item(4).getTextContent());
				System.out.println("Conversant : " + 					eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("Act : " + 							eElement.getElementsByTagName("Value").item(6).getTextContent());
				System.out.println("Chapter : " + 						eElement.getElementsByTagName("Value").item(7).getTextContent());
				System.out.println("Scene : " + 						eElement.getElementsByTagName("Value").item(8).getTextContent());
				System.out.println("Level : " + 						eElement.getElementsByTagName("Value").item(9).getTextContent());
				System.out.println("Mood : " + 							eElement.getElementsByTagName("Value").item(10).getTextContent());
				System.out.println("Primary Location : " + 				eElement.getElementsByTagName("Value").item(11).getTextContent());
				}
			}
		}
		
		
		
		
	}

	public void getAllDialogEntryIdByConversationId(int conversationId){
		
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("DialogEntry");
		
		if(debugConsole){
		System.out.println("All DialogEntries for ConversationID : "+conversationId);
		System.out.println("-------------------");
		}
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			
			
			
			if(Integer.toString(conversationId).equalsIgnoreCase(eElement.getAttribute("ConversationID"))) {
				System.out.println("DialogEntry :"+eElement.getAttribute("ID"));
			}
		
		}
	}

	public void getDialogEntryById (int dialogEntryId, int conversationId, char option){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("DialogEntry");
		
		
		if(debugConsole){
		System.out.println("DialogEntry : "+dialogEntryId+", in Conversation "+conversationId);
		System.out.println("-------------------");
		}
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			
			
			if(Integer.toString(conversationId).equalsIgnoreCase(eElement.getAttribute("ConversationID"))
				&& Integer.toString(dialogEntryId).equalsIgnoreCase(eElement.getAttribute("ID"))
				&& eElement.getAttribute("IsGroup").equalsIgnoreCase("false")
				){
				
				
				if(option == 'a' | option =='A'){
					if(debugConsole){
					System.out.println("Title : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
					System.out.println("Pictures : " + 					eElement.getElementsByTagName("Value").item(1).getTextContent());
					System.out.println("Description : " + 				eElement.getElementsByTagName("Value").item(2).getTextContent());
					System.out.println("Actor : " + 					eElement.getElementsByTagName("Value").item(3).getTextContent());
					System.out.println("Conversant : " + 				eElement.getElementsByTagName("Value").item(4).getTextContent());
					System.out.println("Menu Test : " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
					System.out.println("Dialog Text : " + 				eElement.getElementsByTagName("Value").item(6).getTextContent());
					System.out.println("Parenthical : " + 				eElement.getElementsByTagName("Value").item(7).getTextContent());
					System.out.println("Audio Files : " + 				eElement.getElementsByTagName("Value").item(8).getTextContent());
					System.out.println("Video Files : " + 				eElement.getElementsByTagName("Value").item(9).getTextContent());
					System.out.println("Lipsync Files : " + 			eElement.getElementsByTagName("Value").item(10).getTextContent());
					System.out.println("Animation Files : " + 			eElement.getElementsByTagName("Value").item(11).getTextContent());
					System.out.println("Mood : " + 						eElement.getElementsByTagName("Value").item(12).getTextContent()); 
					System.out.println("----------");
					}
					
					NodeList lList = eElement.getElementsByTagName("Link");
					getInformationAboutLinkedDialogs(lList);
				}
				
				
				if(option =='d' | option == 'D'){
					if(debugConsole){
					System.out.println("Dialog Text : " + 				eElement.getElementsByTagName("Value").item(6).getTextContent());
					}
					NodeList lList = eElement.getElementsByTagName("Link");
					getInformationAboutLinkedDialogs(lList);
				}
				
				if(!(option == 'd' || option == 'a' || option =='A' || option=='D'))
					if(debugConsole){
					System.out.println("Please enter a valid option, when you call the function 'getDialogEntryById()' !");
					}
			}
			
			if(Integer.toString(conversationId).equalsIgnoreCase(eElement.getAttribute("ConversationID"))
					&& Integer.toString(dialogEntryId).equalsIgnoreCase(eElement.getAttribute("ID"))
					&& eElement.getAttribute("IsGroup").equalsIgnoreCase("true")
					){
				if(debugConsole){
				System.out.println("IS GROUP !");
				System.out.println("Title : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Pictures : " + 					eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 				eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Actor : " + 					eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Conversant : " + 				eElement.getElementsByTagName("Value").item(4).getTextContent());
				}
				NodeList lList = eElement.getElementsByTagName("Link");
				getInformationAboutLinkedDialogs(lList);
			}
			
			
		}
	}
	
	
	private void getInformationAboutLinkedDialogs(NodeList lList){
		if(debugConsole){
			System.out.println("");
			System.out.println("----------");
			System.out.println("Linked to the Dialogs :");
			System.out.println("----------");
		}
		for (int linkCnt = 0; linkCnt < lList.getLength(); linkCnt++){
			Node lNode = lList.item(linkCnt);
			Element lElement = (Element) lNode;
			
		if(debugConsole){
			System.out.println("");
			System.out.println("OriginConvoID: "+lElement.getAttribute("OriginConvoID"));
			System.out.println("DestinationConvoID: "+lElement.getAttribute("DestinationConvoID"));
			System.out.println("OriginDialogID: "+lElement.getAttribute("OriginDialogID"));
			System.out.println("DestinationDialogID: "+lElement.getAttribute("DestinationDialogID"));
			System.out.println("IsConnector: "+lElement.getAttribute("IsConnector"));
			System.out.println("");
			System.out.println("----------");
		}
		}
	}
	
	
	*/
	
	

}
