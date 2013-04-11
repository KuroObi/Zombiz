package com.dhbw.Zombiz.gameEngine.logic;

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
import javax.swing.JPanel;

import com.dhbw.Zombiz.gameEngine.parser.XmlParser;

public class BuildRoom {

	List <Item> items;
	List <Item> roomObjects;
	List <Actor> actors; 
	BufferedImage backgroundImage ; 
	String roomImagePath; 
	Room room; 
	JFrame frame; 
	
	
	
	
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
	public BuildRoom(int roomId, JFrame frame){
		XmlParser p = new XmlParser("src/main/resources/XML/chapter1.xml");
		
		setRoom(p.getRoomById(roomId));
		setItems(p.getAllItemsByRoomId(roomId));
		setRoomObjects(p.getAllRoomObjectsByRoomId(roomId));
		setActors(p.getAllNpcsByRoomId(roomId));
		setRoomImagePath(trimmPicPath(room.getPicturePath()));
		
		setFrame(frame);
		
		
		
		
		// setBackground Image
		JLabel label = setBackgroundImage(frame);
		drawInventoryBag(frame);
		drawObjects(frame); 
		
	
		frame.add(label);
	}
	
	
	public void drawObjects(JFrame frame){
		List <Item> itemsInDrawFunction = getItems();
		
		for(int cntItemPic = 0; cntItemPic < itemsInDrawFunction.size(); cntItemPic++){
			String itemPicPath = trimmPicPath(itemsInDrawFunction.get(cntItemPic).getPicturePath());
			float xLoc = itemsInDrawFunction.get(cntItemPic).getItemLocX();
			float yLoc = itemsInDrawFunction.get(cntItemPic).getItemLocY();
			
			int xCoord = myRandom(1,800);
			int yCoord = myRandom(1,600);
			BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage,xCoord, yCoord, 40, 60, null);
			addClickableFunction(xCoord, yCoord, 40, 60,itemsInDrawFunction.get(cntItemPic).getId(), frame, "item");
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
				if(type.equalsIgnoreCase("inventory"))
					//System.out.println("You pressed the Inventory");
				if(type.equalsIgnoreCase("item")){
					System.out.println("You pressed Item "+itemId);
					//drawItemMenue(frame, xLoc, yLoc, itemId); }
				if(type.equalsIgnoreCase("roomObject"))
					//System.out.println("You pressed Item "+itemId);
				if(type.equalsIgnoreCase("actor"))
					//System.out.println("You pressed Item "+itemId);
				if(type.equalsIgnoreCase("pickup:itemmenue")){
					deleteItem(frame, itemId);
					
				}
					
			}}});
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
	
	public void drawInventoryBag(JFrame frame){
		BufferedImage inventoryBag = null;
		try {
			inventoryBag = ImageIO.read(new File("src/main/resources/Picture/Inventory/inventory.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		backgroundImage.getGraphics().drawImage(inventoryBag, 700, 50, 80, 80, null);
		// special ItemID 
		addClickableFunction(700, 50, 80, 80, 999, frame, "inventory");
	}
	
	public void drawItemMenue(JFrame frame, int xLoc, int yLoc, int itemId){
		BufferedImage btnTakeItem = null;
		try {
			btnTakeItem = ImageIO.read(new File("src/main/resources/Picture/Menue/Itemmenue/btnTakeItem.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//TO DO :  Validate x,y Coordinates, so ItemMenue wont be drawn out of screen !!! 
		/*if(yLoc < 40) yLoc = yLoc+40; 
			else yLoc = yLoc-40; */
		backgroundImage.getGraphics().drawImage(btnTakeItem, xLoc-60, yLoc-40, 200, 30, null);
		addClickableFunction(xLoc-60, yLoc-60, 200, 30, itemId, frame, "pickup:itemmenue");
		frame.repaint();
	}
	

	public void deleteFrame(JFrame frame){
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();

	}
	
	
	public int getItemById(List<Item> items, int id){
		int cnt = 0;
		for(cnt = 0; cnt < items.size(); cnt++){
			int itemId = items.get(cnt).getId();
			if(itemId == id){
				return cnt;
			}
			
		}
		return cnt;
	}
	
	
	
	public void deleteItem(JFrame frame, int notToDrawItemId){
		deleteFrame(frame);
		List<Item> items = getItems();
		
		int removeId = getItemById(items, notToDrawItemId);
		items.remove(removeId);
		setItems(items);
		
		System.out.println("Items size"+items.size());
		
		JLabel label = setBackgroundImage(frame);
		drawInventoryBag(frame);
		drawObjects(frame);
		frame.add(label);
		
	
	}
	
	
	
}
