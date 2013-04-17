package com.dhbw.Zombiz.gameEngine.logic;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.dhbw.Zombiz.gameEngine.logic.Runtime;

import com.dhbw.Zombiz.gameEngine.parser.XmlParser;
import com.dhbw.Zombiz.output.display.DialogOutput;

public class BuildRoom {

	List <Item> items;
	List <Item> roomObjects;
	List <Actor> actors; 
	static BufferedImage backgroundImage ; 
	String roomImagePath; 
	Room room; 
	JFrame frame; 
	static XmlParser parser;
	int roomId;
	

	int itemsFocussedInInventory = 0; 
	Item firstFocussedItem; 
	Item secondFocussedItem;
	
	
	
	static int  cnt = 0;
	
	Item roomObj;
	boolean wantToCombineRoomObjWithItem;
	
	
	
	
	public int getItemsFocussedInInventory() {
		return itemsFocussedInInventory;
	}
	public void setItemsFocussedInInventory(int itemsFocussedInInventory) {
		this.itemsFocussedInInventory = itemsFocussedInInventory;
	}
	public Item getFirstFocussedItem() {
		return firstFocussedItem;
	}
	public void setFirstFocussedItem(Item firstFocussedItem) {
		this.firstFocussedItem = firstFocussedItem;
	}
	public Item getSecondFocussedItem() {
		return secondFocussedItem;
	}
	public void setSecondFocussedItem(Item secondFocussedItem) {
		this.secondFocussedItem = secondFocussedItem;
	}
	public boolean isWantToCombineRoomObjWithItem() {
		return wantToCombineRoomObjWithItem;
	}
	public void setWantToCombineRoomObjWithItem(boolean wantToCombineRoomObjWithItem) {
		this.wantToCombineRoomObjWithItem = wantToCombineRoomObjWithItem;
	}
	public Item getRoomObj() {
		return roomObj;
	}
	public void setRoomObj(Item roomObj) {
		this.roomObj = roomObj;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomImagePath() {
		return roomImagePath;
	}
	public void setRoomImagePath(String roomImagePath) {
		this.roomImagePath = roomImagePath;
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	

	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<Item> getRoomObjects() {
		return roomObjects;
	}
	public void setRoomObjects(List<Item> roomObjects) {
		this.roomObjects = roomObjects;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public void setParser(XmlParser p){
		this.parser = p;
	}
	
	public static XmlParser getParser(){
		return parser;
	}
	
	
	
	
	
	public BuildRoom(int roomId, JFrame frame){
		
		
		
		XmlParser p = new XmlParser("src/main/resources/XML/chapter1.xml");
		
		setRoomId(roomId);
		Runtime.setCurrRoomId(roomId);
		setParser(p);
		setRoom(p.getRoomById(roomId));
		
		setItems(p.getAllItemsByRoomId(roomId));
		setRoomObjects(p.getAllRoomObjectsByRoomId(roomId));
		
		if(!Runtime.getInventory().isEmpty()){
		
			validateItemsInRoom();
			
		}
		
		
		setActors(p.getAllNpcsByRoomId(roomId));
		setRoomImagePath(trimmPicPath(room.getPicturePath()));
		
		
		
		
		deleteFrame(frame);
		frame.repaint();
		
		
		setFrame(frame);
		// setBackground Image
		
		JLabel label = setBackgroundImage(frame);
		drawInventoryBag(frame);
		drawActors(frame);
		drawObjects(frame, true); 
		drawRoomObjects(frame, true);
	
		frame.add(label);
	}
	
	public void drawActors(JFrame frame){
		List<Actor> actors = getActors();
		
		for(int cnt = 0; cnt < actors.size(); cnt++){
			Actor actor = actors.get(cnt);
			addClickableFunction((int)actor.getNpcLocX(), (int)actor.getNpcLocY(), 100, 300, actor.getId(), frame, "actor");
			
		}
		
		
	}
	
	
	
	public void validateItemsInRoom(){
		List<Item> inventory = Runtime.getInventory();
		List<Item> items = getItems();
		
		for(int cnt = 0; cnt < getItems().size(); cnt++){
			for(int count = 0; count < inventory.size(); count++){
				if(getItems().get(cnt).getId() == inventory.get(count).getId()){
					int id = getItemIDById(items, getItems().get(cnt).getId());
					items.remove(id);
					}
				}
			}
			setItems(items);
		}
	
	public void drawObjects(JFrame frame, boolean addClickableFct){
		List <Item> itemsInDrawFunction = getItems();
		
		for(int cntItemPic = 0; cntItemPic < itemsInDrawFunction.size(); cntItemPic++){
			
			
			
			String itemPicPath = trimmPicPath(itemsInDrawFunction.get(cntItemPic).getPicturePath());
			float xLoc = itemsInDrawFunction.get(cntItemPic).getItemLocY();
			float yLoc = itemsInDrawFunction.get(cntItemPic).getItemLocX();
			
			
			
			BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage,(int)yLoc, (int)xLoc, null);
			if(addClickableFct){
			addClickableFunction((int)yLoc, (int)xLoc,foregroundImage.getWidth(), foregroundImage.getHeight(), itemsInDrawFunction.get(cntItemPic).getId(), frame, "item");
			}
			
		}
	}
	
	public void drawRoomObjects(JFrame frame, boolean addClickableFct){
		List<Item> roomObjectsInDrawFunction = getRoomObjects();
		
		for(int cntItemPic = 0; cntItemPic < roomObjectsInDrawFunction.size(); cntItemPic++){
			String itemPicPath = trimmPicPath(roomObjectsInDrawFunction.get(cntItemPic).getPicturePath());
			float xLoc = roomObjectsInDrawFunction.get(cntItemPic).getItemLocY();
			float yLoc = roomObjectsInDrawFunction.get(cntItemPic).getItemLocX();
			
		

			
			BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage,(int)yLoc, (int)xLoc, null);
			if(addClickableFct){
			addClickableFunction((int)yLoc, (int)xLoc,foregroundImage.getWidth(), foregroundImage.getHeight(), roomObjectsInDrawFunction.get(cntItemPic).getId(), frame, "roomObjects");
			}
		}
	}
	
	
	
	
	
	public JLabel setBackgroundImage(JFrame frame){
		try {
			setBackgroundImage(ImageIO.read(new File(getRoomImagePath())));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		
		BufferedImage backgroundImagetmp = getBackgroundImage();
		JLabel label = new JLabel(new ImageIcon(backgroundImagetmp));
		return label;
		
	}	

	
	
	
	
	public void addClickableFunction(final int xLoc, final int yLoc, int width, int height, final int itemId, final JFrame frame, final String type){
		JLabel label = new JLabel();
		label.setBounds(xLoc, yLoc, width, height);
	
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if(type.equalsIgnoreCase("inventory")){
					System.out.println("You pressed the Inventory");
					drawInventory(frame);
					frame.repaint();
				}
				if(type.equalsIgnoreCase("inventory:close")){
					System.out.println("You closed the inventory !!!");
					refreshFrame(frame);
					setItemsFocussedInInventory(0);
				}
				if(type.equalsIgnoreCase("item")){
					System.out.println("You pressed Item "+itemId);
					drawItemMenue(frame, xLoc, yLoc, itemId, 'i'); }
				if(type.equalsIgnoreCase("roomObjects")){
					System.out.println("You pressed RoomObject "+itemId);
					drawItemMenue(frame, xLoc, yLoc, itemId, 'r'); 
				}
				
				if(type.equalsIgnoreCase("actor"))
					System.out.println("You pressed Item "+itemId);
				
				
				
				if(type.equalsIgnoreCase("leaveRoom")){
					System.out.println("You want to leave ... ? :(");
					deleteFrame(frame);
					
					
					Runtime.nextRoom(getRoom().getId(), frame);
			
					
					}
				if(type.equalsIgnoreCase("inGameMenue")){
					System.out.println("InGameMenue");
					}
				
				
				//Options for Items
				if(type.equalsIgnoreCase("pickup:itemmenue")){
					Runtime.addItemToInventory(getItemById(getItems(), itemId));
					deleteItem(frame, itemId);
					}
				if(type.equalsIgnoreCase("inspect:itemmenue")){
					System.out.println("inspect item ...");
					}
				if(type.equalsIgnoreCase("leave:item")){
					refreshFrame(frame);
					}
				
				//Options for RoomObjects
				if(type.equalsIgnoreCase("use:RoomObjMenue")){
					Item item = getRoomObjectById(itemId);
					String aimLoc = item.getLocationPointer();
					aimLoc = aimLoc.substring(11, 14);
					int aimLocId = Integer.parseInt(aimLoc);
					System.out.println(aimLocId);
					
					BuildRoom br = new BuildRoom(aimLocId, frame);
					}
				if(type.equalsIgnoreCase("item:RoomObjMenue")){
				
				Item roomObj = getRoomObjectById(itemId);
				setRoomObj(roomObj);
				drawInventory(frame);
				setWantToCombineRoomObjWithItem(true);
				}
				
				if(type.equalsIgnoreCase("actor")){
					DialogOutput dout = new DialogOutput(frame, getParser().getConversationById(2), getBackgroundImage(), getParser().getListOfActors(), getRoomId());
				}
				
				if(type.equalsIgnoreCase("inventory:click")){
					
					Item itemInInventory = null; 
					Item nextItem = null;
					itemInInventory= getItemById(Runtime.getInventory(), itemId);
					if(isWantToCombineRoomObjWithItem()){
							itemAndRoomObjInteraction(itemInInventory);
						}
					
					focusItemInInventory(frame, itemInInventory);
					
					
					
					
				}
				
			
				 
				
					
			}});
		frame.add(label);
	}
	

	
	
	
	
	public static int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}
	
	public String trimmPicPath(String picPath){
		picPath = picPath.trim();
		picPath = picPath.substring(1, picPath.length()-1);
		return picPath;
		
	}
	

	//TO DO :  Validate x,y Coordinates, so ItemMenue wont be drawn out of screen !!! 
	/*if(yLoc < 40) yLoc = yLoc+40; 
		else yLoc = yLoc-40; */
	public void drawItemMenue(JFrame frame, int xLoc, int yLoc, int itemId, char option){
		
		
		if(option == 'i'){
		BufferedImage btnTakeItem = null;
		BufferedImage btnLeaveItem = null;
		BufferedImage btnInspectItem = null;
		try {
			btnTakeItem = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnTakeItem.png"));
			btnLeaveItem = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnLeaveItem.png"));
			btnInspectItem = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnInspectItem.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		backgroundImage.getGraphics().drawImage(btnTakeItem, xLoc-60, yLoc-40, 180, 30, null);
		addClickableFunction(xLoc-60, yLoc-40, 180, 30, itemId, frame, "pickup:itemmenue");
		
		backgroundImage.getGraphics().drawImage(btnLeaveItem, xLoc-60, yLoc+40, 180, 30, null);
		addClickableFunction(xLoc-60, yLoc+40, 180, 30, itemId, frame, "leave:item");
		
		backgroundImage.getGraphics().drawImage(btnInspectItem, xLoc+80, yLoc, 180, 30, null);
		//addClickableFunction(xLoc+80, yLoc, 180, 30, itemId, frame, "pickup:itemmenue");
		frame.repaint();
		}
		
		if(option == 'r'){
			BufferedImage btnUseRoomObj = null;
			BufferedImage btnInspectRoomObj = null;
			BufferedImage btnNothingRoomObj = null;
			BufferedImage btnUseItemOnRoomObj = null;
			try {
				btnUseRoomObj = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnUseRoomObj.png"));
				btnInspectRoomObj = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnInspectRoomObj.png"));
				btnNothingRoomObj = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnNothingRoomObj.png"));
				btnUseItemOnRoomObj = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnUseItemOnRoomObj.png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			//Liste fŸr RoomObjects, bei denen der MenŸPunkt : Benutzen erscheinen soll !
			
			if(!(itemId == 1 || itemId == 2 ||itemId == 3 || (itemId >= 15 && itemId <= 32))){
			backgroundImage.getGraphics().drawImage(btnInspectRoomObj, xLoc+30, yLoc, 180, 30, null);
			//addClickableFunction(xLoc+30, yLoc, 180, 30, itemId, frame, "");
			}
			
			backgroundImage.getGraphics().drawImage(btnUseRoomObj, xLoc-60, yLoc-40, 180, 30, null);
			addClickableFunction(xLoc-60, yLoc-60, 180, 30, itemId, frame, "use:RoomObjMenue");
			
			backgroundImage.getGraphics().drawImage(btnNothingRoomObj, xLoc+30, yLoc+30, 180, 30, null);
			addClickableFunction(xLoc+30, yLoc+30, 180, 30, itemId, frame, "leave:item");
			
			backgroundImage.getGraphics().drawImage(btnUseItemOnRoomObj, xLoc-170, yLoc+30, 180, 30, null);
			addClickableFunction(xLoc-170, yLoc+30, 180, 30, itemId, frame, "item:RoomObjMenue");
			
			
			
			frame.repaint();
		}
		
		
	}
	

	public static void deleteFrame(JFrame frame){
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();

	}
	
	
	public int getItemIDById(List<Item> items, int id){
		int cnt = 0;
		for(cnt = 0; cnt < items.size(); cnt++){
			int itemId = items.get(cnt).getId();
			if(itemId == id){
				return cnt;
			}
			
		}
		return cnt;
	}
	
	public Item getItemById(List<Item> items, int id){
		Item item = null;
		for(int cnt = 0; cnt < items.size(); cnt++){
			int itemId = items.get(cnt).getId();
			if(itemId == id){
				
				return items.get(cnt);
			}
			
		}
		return item;
	}
	
	
	public Item getItemFromInventoryById(int id){
		Item item = null;
		List<Item> inventory = Runtime.getInventory();
		
		for(int cnt = 0; cnt < inventory.size() ; cnt++){
			if(id == inventory.get(cnt).getId()){
				item = inventory.get(cnt);
			}
		}
		return item;
	}
	
	
	public void deleteItem(JFrame frame, int notToDrawItemId){
		deleteFrame(frame);
		List<Item> items = getItems();
		
		int removeId = getItemIDById(items, notToDrawItemId);
		items.remove(removeId);
		setItems(items);
		

		
		JLabel label = setBackgroundImage(frame);
		drawInventoryBag(frame);
		drawObjects(frame, true);
		drawRoomObjects(frame, true);
		frame.add(label);
		
	
	}
	
	
	
	public void drawInventoryBag(JFrame frame){
		int roomId = this.room.getId(); 

		
		// if room is floor draw another HUD
		if(!(roomId == 1 || roomId == 3 || roomId == 14 || roomId == 13 || roomId == 16 )){
			BufferedImage inventoryBag = null;
			try {
				inventoryBag = ImageIO.read(new File("src/main/resources/Picture/hud.png"));
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
			backgroundImage.getGraphics().drawImage(inventoryBag,0,0, 800, 590, null);
			// special ItemID 
			addClickableFunction(670, 0, 130, 115, 999, frame, "inventory");
			addClickableFunction(710, 510, 90, 90, 999, frame, "leaveRoom");
			addClickableFunction(0, 0, 100, 90, 999, frame, "inGameMenue");
		}
		else {
			BufferedImage inventoryBag = null;
			try {
				inventoryBag = ImageIO.read(new File("src/main/resources/Picture/hudFloor.png"));
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
			backgroundImage.getGraphics().drawImage(inventoryBag,0,0, 800, 590, null);
			// special ItemID 
			addClickableFunction(670, 0, 130, 115, 999, frame, "inventory");
			addClickableFunction(0, 0, 100, 90, 999, frame, "inGameMenue");
		}

		
	}
	
	public void focusItemInInventory(JFrame frame, Item item){
		
		
		itemsFocussedInInventory++;
		List<Item> inventory = Runtime.getInventory();
		int itemId = item.getId();
		int pos = 0;
		
		
		
		System.out.println("Items focussed = "+itemsFocussedInInventory);
		
		
		for(int cnt = 0; cnt < inventory.size(); cnt++){
			if(itemId == inventory.get(cnt).getId()){
				pos = cnt;
				System.out.println("cnt "+cnt);
			}
		}
		
		
		

		System.out.println("Pos "+pos );
		if(pos == 0){
		frame.getContentPane().getGraphics().drawRect(110, 154, 90, 84);
		}
		if(pos > 0 && pos <= 5) {
			frame.getContentPane().getGraphics().drawRect(110+(pos*120), 154, 90, 84);
		}
		if (pos >= 5 && pos <= 10){
			frame.getContentPane().getGraphics().drawRect(110+(pos*120), 257, 90, 84);
		}
		if (cnt >= 10 && cnt <= 15){
			frame.getContentPane().getGraphics().drawRect(110+(cnt*120), 257+103, 90, 84);
		}
		
		
		if(itemsFocussedInInventory == 1){
			setFirstFocussedItem(item);
		}
		if(itemsFocussedInInventory == 2){
			setSecondFocussedItem(item);
			checkIfItemsCombineable();
		}
		
		
		
	}
	
	private void checkIfItemsCombineable() {
		Item firstItem = getFirstFocussedItem();
		Item secondItem = getSecondFocussedItem();
		
		setFirstFocussedItem(null);
		setSecondFocussedItem(null);
		
		setItemsFocussedInInventory(0);
		
		if(Integer.parseInt(firstItem.getCombinesWith()) == secondItem.getId() ||firstItem.getId() == Integer.parseInt(secondItem.getCombinesWith())){
			System.out.println("fit");
		}
		else {
			System.out.println("doesn+t");
		}
		
		
	}
	public void drawInventory(JFrame frame){
		deleteFrame(frame);
		JLabel label = setBackgroundImage(frame);
		
		drawObjects(frame, false);
		drawRoomObjects(frame, false);
		

		
		
		BufferedImage inventoryBackground = null;
		BufferedImage btnCloseInventory = null;
		try {
			inventoryBackground = ImageIO.read(new File("src/main/resources/Picture/Inventory/inventoryBackground.png"));
			btnCloseInventory = ImageIO.read(new File("src/main/resources/Picture/Inventory/btnCloseInventory.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		

		backgroundImage.getGraphics().drawImage(inventoryBackground , 90, 80 , 600, 400, null);
		backgroundImage.getGraphics().drawImage(btnCloseInventory , 630, 85 , 50, 50, null);
		addClickableFunction(630, 85, 50, 50, 999, frame, "inventory:close");
		List<Item> inventory = Runtime.getInventory();
			
		
		
		
		
			int xLoc = 110;
			int yLoc = 165;
			
			int xLocClick = 110;
			int yLocClick = 154;
			
		for(int cntItemPic = 0; cntItemPic < inventory.size(); cntItemPic++){
			
			String itemPicPath = trimmPicPath(inventory.get(cntItemPic).getPicturePath());
			//itemPicPath = itemPicPath.replace(".png", "");
			//itemPicPath = itemPicPath+"_inventory.png";
			
			
			BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage, xLoc , yLoc, null);
			
			addClickableFunction(xLocClick, yLocClick, 90, 84, inventory.get(cntItemPic).getId(), frame, "inventory:click");
			
			xLoc = xLoc+120;
			xLocClick = xLocClick+120;
			if(cntItemPic > 6){
				xLoc = 110;
				xLocClick = 110;
				
				yLoc = yLoc+103;
				yLocClick = yLocClick+103;
			}
			if(cntItemPic > 11){
				xLoc = 110;
				xLocClick = 110;
				
				yLoc = yLoc+103;
				yLocClick = yLocClick+103;
			}
			
			
			
		}
		frame.add(label);
		frame.repaint();
	}
	
	public void refreshFrame(JFrame frame){
		deleteFrame(frame);
		JLabel label = setBackgroundImage(frame);
		drawInventoryBag(frame);
		drawObjects(frame, true);
		drawRoomObjects(frame, true);
		frame.add(label);
		frame.repaint();
	}
	
	
	public Item getRoomObjectById(int id){
		Item item = null; 
		
		for(int cnt = 0; cnt < getRoomObjects().size(); cnt++){
			if(getRoomObjects().get(cnt).getId() == id){
				item = getRoomObjects().get(cnt);
			}
		}
		
		
		return item;
	}
	
	public void itemAndRoomObjInteraction(Item item){
		Item roomObj = getRoomObj(); 
		Item itemToCombine 	= item;
		
		System.out.println("Item "+itemToCombine.getName());
		
		
		
		if(Integer.parseInt(itemToCombine.getCombinesWith()) == roomObj.getId()){
			
			// Do somethin if works ... 
			System.out.println("is ready to combine ...");
		}
		else {
			// Do somethin if it not works ...
			System.out.println("You wanted to combine a "+roomObj.getName()+" what is only combined with "+itemToCombine.getId()+","+itemToCombine.getName());
		}
		 
		
		
	}
	
	public void checkIfCombineable(Item item1, Item item2){
		if(Integer.parseInt(item1.getCombinesWith()) == item2.getId() || Integer.parseInt(item2.getCombinesWith()) == item1.getId()){
			System.out.println("You can combine !!!");
		}
		else {
			System.out.println("Item 1 :"+item1.getName()+" is not combineable with "+item2.getName());
		}
		
	}
	
	
	
	
}
