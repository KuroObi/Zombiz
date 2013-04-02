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


public class XmlParser {
	String xmlFilePath ="";
	Document xmlFile;
	boolean debugConsole;
	
	List<Actor> listActors = new ArrayList<Actor>();
	List<Item>	listItems = new ArrayList<Item>();
	List<Room>	listRooms = new ArrayList<Room>(); 
	
	
	public List<Actor> getListOfActors(){
		return listActors;
	}
	
	public List<Item> getListOfItems(){
		return listItems;
	}
	
	public List<Room> getListOfRooms(){
		return listRooms;
	}
	
	
	
	
	public  XmlParser(String filePath){
		setXmlFilePath(filePath);
	    openXmlFile();
	    
	    
	    getAllActors();
	    getAllItems();
	    getAllRooms();
	    
	}
	
	
	public void setDebugConsole(boolean debug){
		debugConsole = debug;
	}
	
	
	public void setXmlFilePath(String filePath){
		xmlFilePath = filePath;
		
	}
	
	public String getXmlFilePath(){
		return xmlFilePath;
	}
	
	
	public void setXmlFile(Document file){
		xmlFile = file;
	}
	
	public Document getXmlFile(){
		return xmlFile;
	}
	
	
	public void openXmlFile(){
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
	
	
	public void getAllActors(){
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
			Actor actor = new Actor(temp);
			Node nNode = nList.item(temp);
			
			
			
			Element eElement = (Element) nNode;
			
			
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
				actor.setRank(eElement.getElementsByTagName("Value").item(6).getTextContent());
				actor.setFaction(eElement.getElementsByTagName("Value").item(7).getTextContent());
				actor.setActorClass(eElement.getElementsByTagName("Value").item(8).getTextContent());
				actor.setActorSubClass(eElement.getElementsByTagName("Value").item(9).getTextContent());
				actor.setAbility(eElement.getElementsByTagName("Value").item(10).getTextContent());
				actor.setHometown(eElement.getElementsByTagName("Value").item(11).getTextContent());
				actor.setFiles2dPath(eElement.getElementsByTagName("Value").item(12).getTextContent());
				actor.setFiles3dPath(eElement.getElementsByTagName("Value").item(13).getTextContent()); 
				
				
				
				if(debugConsole){
				System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Is Player : " + 			eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Age : " + 					eElement.getElementsByTagName("Value").item(4).getTextContent());
				System.out.println("Gender: " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("Rank : " + 					eElement.getElementsByTagName("Value").item(6).getTextContent());
				System.out.println("Faction : " + 				eElement.getElementsByTagName("Value").item(7).getTextContent());
				System.out.println("Class : " + 				eElement.getElementsByTagName("Value").item(8).getTextContent());
				System.out.println("Subclass : " + 				eElement.getElementsByTagName("Value").item(9).getTextContent()); 
				System.out.println("Ability : " + 				eElement.getElementsByTagName("Value").item(10).getTextContent()); 
				System.out.println("Hometown : " + 				eElement.getElementsByTagName("Value").item(11).getTextContent());
				System.out.println("Texture Files (2D) : " + 	eElement.getElementsByTagName("Value").item(12).getTextContent());
				System.out.println("Texture Files (3D) : " + 	eElement.getElementsByTagName("Value").item(13).getTextContent());

				System.out.println("");
				}
			}
			
			
			listActors.add(actor);
		}
	}
	
	
	public void getActorById(int id){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Actor");
		
		if(id == 0 || id < 0 || id > nList.getLength()){
			System.out.println("Please enter a valid ActorID !");
			return;
		}
		
		if(debugConsole){
		System.out.println("Actor with ID "+id+" in XML-File");
		System.out.println("-------------------");
		System.out.println("");
		}
		
		Node nNode = nList.item(id-1);
		
		Element eElement = (Element) nNode;
		if(debugConsole){
		System.out.println("ActorID: " + eElement.getAttribute("ID"));
		System.out.println("--------");
		}
		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			
			if(debugConsole){
			System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
			System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
			System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
			System.out.println("Is Player : " + 			eElement.getElementsByTagName("Value").item(3).getTextContent());
			System.out.println("Age : " + 					eElement.getElementsByTagName("Value").item(4).getTextContent());
			System.out.println("Gender: " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
			System.out.println("Rank : " + 					eElement.getElementsByTagName("Value").item(6).getTextContent());
			System.out.println("Faction : " + 				eElement.getElementsByTagName("Value").item(7).getTextContent());
			System.out.println("Class : " + 				eElement.getElementsByTagName("Value").item(8).getTextContent());
			System.out.println("Subclass : " + 				eElement.getElementsByTagName("Value").item(9).getTextContent()); 
			System.out.println("Ability : " + 				eElement.getElementsByTagName("Value").item(10).getTextContent()); 
			System.out.println("Hometown : " + 				eElement.getElementsByTagName("Value").item(11).getTextContent());
			System.out.println("Texture Files (2D) : " + 	eElement.getElementsByTagName("Value").item(12).getTextContent());
			System.out.println("Texture Files (3D) : " + 	eElement.getElementsByTagName("Value").item(13).getTextContent());

			System.out.println("");
			}
		}	
	}
	
	
	public void getActorByName(String name){
			Document doc = getXmlFile();
			NodeList nList = doc.getElementsByTagName("Actor");
			int searchedID = 0;
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				
				if(name.equalsIgnoreCase(eElement.getElementsByTagName("Value").item(0).getTextContent())) {
					searchedID = temp+1;
					if(debugConsole){
					System.out.println("Actor with the Name : "+name);
					System.out.println("-------------------");
					}
					getActorById(searchedID);
				}
			}
			
			
	}
	

	public void getAllItems(){
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
			
			Item item = new Item(temp);
			Node nNode = nList.item(temp);
			
			Element eElement = (Element) nNode;
			
			if(debugConsole){
			System.out.println("ItemID: " + eElement.getAttribute("ID"));
			System.out.println("--------");
			}
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				item.setName(eElement.getElementsByTagName("Value").item(0).getTextContent());
				item.setPicturePath(eElement.getElementsByTagName("Value").item(1).getTextContent());
				item.setDescription(eElement.getElementsByTagName("Value").item(2).getTextContent());
				item.setPurpose(eElement.getElementsByTagName("Value").item(3).getTextContent());
				item.setPrimaryLocation(eElement.getElementsByTagName("Value").item(4).getTextContent());
				item.setEffect(eElement.getElementsByTagName("Value").item(5).getTextContent());
				item.setAssociatedWith(eElement.getElementsByTagName("Value").item(6).getTextContent());
				item.setCombinesWith(eElement.getElementsByTagName("Value").item(7).getTextContent());
				item.setCondition(eElement.getElementsByTagName("Value").item(8).getTextContent());
				item.setFiles2dPath(eElement.getElementsByTagName("Value").item(9).getTextContent());
				item.setFiles3dPath(eElement.getElementsByTagName("Value").item(10).getTextContent());
				if(eElement.getElementsByTagName("Value").item(11).getTextContent().equalsIgnoreCase("True")){
					item.setInInventory(true);
					}
					else item.setInInventory(false);
				if(eElement.getElementsByTagName("Value").item(12).getTextContent().equalsIgnoreCase("True")){
					item.setFocussed(true);
					}
					else item.setFocussed(false);
				
				if(debugConsole){
				System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Purpose : " +	 			eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Primary Location : " + 		eElement.getElementsByTagName("Value").item(4).getTextContent());
				System.out.println("Effect: " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("Associated with: " + 		eElement.getElementsByTagName("Value").item(6).getTextContent());
				System.out.println("Combines with : " + 		eElement.getElementsByTagName("Value").item(7).getTextContent());
				System.out.println("Condition : " + 			eElement.getElementsByTagName("Value").item(8).getTextContent());
				System.out.println("Texture Files (2D) : " + 	eElement.getElementsByTagName("Value").item(9).getTextContent()); 
				System.out.println("Texture Files (3D) : " + 	eElement.getElementsByTagName("Value").item(10).getTextContent()); 
				System.out.println("inInventory : " + 			eElement.getElementsByTagName("Value").item(11).getTextContent()); 
				System.out.println("focussed : " + 				eElement.getElementsByTagName("Value").item(12).getTextContent());
			
				System.out.println("");
				}
			}	
			
			listItems.add(item);
		}
		
		
		
	}
	
	
	public void getItemById(int id) {
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Item");
	
		if(id == 0 || id < 0 || id > nList.getLength()){
			System.out.println("Please enter a valid ItemID !");
			return;
		}
		
		if(debugConsole){
		System.out.println("Item with ID "+id+" in XML-File");
		System.out.println("-------------------");
		System.out.println("");
		}
	
		Node nNode = nList.item(id-1);
	
		Element eElement = (Element) nNode;
		
		if(debugConsole){
		System.out.println("ItemID: " + eElement.getAttribute("ID"));
		System.out.println("--------");
		}
	
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			
			if(debugConsole){
			System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
			System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
			System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
			System.out.println("Purpose : " +	 			eElement.getElementsByTagName("Value").item(3).getTextContent());
			System.out.println("Primary Location : " + 		eElement.getElementsByTagName("Value").item(4).getTextContent());
			System.out.println("Effect: " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
			System.out.println("Associated with: " + 		eElement.getElementsByTagName("Value").item(6).getTextContent());
			System.out.println("Combines with : " + 		eElement.getElementsByTagName("Value").item(7).getTextContent());
			System.out.println("Condition : " + 			eElement.getElementsByTagName("Value").item(8).getTextContent());
			System.out.println("Texture Files (2D) : " + 	eElement.getElementsByTagName("Value").item(9).getTextContent()); 
			System.out.println("Texture Files (3D) : " + 	eElement.getElementsByTagName("Value").item(10).getTextContent()); 
			System.out.println("inInventory : " + 			eElement.getElementsByTagName("Value").item(11).getTextContent()); 
			System.out.println("focussed : " + 				eElement.getElementsByTagName("Value").item(12).getTextContent());
	
			System.out.println("");
			}
		}	
	}

	
	public void getItemByName(String name){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Item");
		int searchedID = 0;
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			
			if(name.equalsIgnoreCase(eElement.getElementsByTagName("Value").item(0).getTextContent())) {
				searchedID = temp+1;
				
				if(debugConsole){
				System.out.println("Item with Name : "+name);
				System.out.println("-------------------");
				}
				
				getItemById(searchedID);
			}
		}
		
		
	}
	
	
	public void getAllRooms(){
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
			
			Room room = new Room(temp);
			Node nNode = nList.item(temp);
			
			Element eElement = (Element) nNode;
			
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
				room.setMood(eElement.getElementsByTagName("Value").item(7).getTextContent());
				room.setLocationId(eElement.getElementsByTagName("Value").item(8).getTextContent());
				room.setGameObjectsIncluded(eElement.getElementsByTagName("Value").item(9).getTextContent());
				room.setBuildingFloor(eElement.getElementsByTagName("Value").item(10).getTextContent());
				
				
				if(debugConsole){
				System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Act : " + 			eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Chapter : " + 					eElement.getElementsByTagName("Value").item(4).getTextContent());
				System.out.println("Scene : " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("Function : " + 					eElement.getElementsByTagName("Value").item(6).getTextContent());
				System.out.println("Mood : " + 				eElement.getElementsByTagName("Value").item(7).getTextContent());
				System.out.println("Location ID : " + 				eElement.getElementsByTagName("Value").item(8).getTextContent());
				System.out.println("GameObjectsIncluded : " + 				eElement.getElementsByTagName("Value").item(9).getTextContent()); 
				System.out.println("Building Floor :" + 				eElement.getElementsByTagName("Value").item(10).getTextContent()); 
				

				System.out.println("");
				}
			}	
			listRooms.add(room);
		}
	}

	
	public void getRoomById(int id){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Location");
		
		if(id == 0 || id < 0 || id > nList.getLength()){
			System.out.println("Please enter a valid LocationID !");
			return;
		}
		
		if(debugConsole){
		System.out.println("Location ID "+id+" in XML-File");
		System.out.println("-------------------");
		System.out.println("");
		}
		
		Node nNode = nList.item(id-1);
		
		Element eElement = (Element) nNode;
		if(debugConsole){
		System.out.println("LocationID: " + eElement.getAttribute("ID"));
		System.out.println("--------");
		}
		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			
			if(debugConsole){
				System.out.println("Name : " + 					eElement.getElementsByTagName("Value").item(0).getTextContent());
				System.out.println("Picture path (?) : " + 		eElement.getElementsByTagName("Value").item(1).getTextContent());
				System.out.println("Description : " + 			eElement.getElementsByTagName("Value").item(2).getTextContent());
				System.out.println("Act : " + 			eElement.getElementsByTagName("Value").item(3).getTextContent());
				System.out.println("Chapter : " + 					eElement.getElementsByTagName("Value").item(4).getTextContent());
				System.out.println("Scene : " + 				eElement.getElementsByTagName("Value").item(5).getTextContent());
				System.out.println("Function : " + 					eElement.getElementsByTagName("Value").item(6).getTextContent());
				System.out.println("Mood : " + 				eElement.getElementsByTagName("Value").item(7).getTextContent());
				System.out.println("Location ID : " + 				eElement.getElementsByTagName("Value").item(8).getTextContent());
				System.out.println("GameObjectsIncluded : " + 				eElement.getElementsByTagName("Value").item(9).getTextContent()); 
				System.out.println("Building Floor :" + 				eElement.getElementsByTagName("Value").item(10).getTextContent()); 
				

			System.out.println("");
			}
		}	
	}

	
	public void getRoomByName(String name){
		Document doc = getXmlFile();
		NodeList nList = doc.getElementsByTagName("Location");
		int searchedID = 0;
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			
			if(name.equalsIgnoreCase(eElement.getElementsByTagName("Value").item(0).getTextContent())) {
				searchedID = temp+1;
				
				if(debugConsole){
				System.out.println("Location : "+name);
				System.out.println("-------------------");
				}
				getRoomById(searchedID);
			}
		}
		
	}
	
	
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
	
	
	public void getInformationAboutLinkedDialogs(NodeList lList){
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
	
	
	
	
	

}
