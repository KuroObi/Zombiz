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

	List<Item> items;
	List<Item> roomObjects;
	List<Actor> actors;
	static BufferedImage backgroundImage;
	String roomImagePath;
	Room room;
	JFrame frame;
	static XmlParser parser;
	int roomId;
	int side = 0;
	static char option;
	static int convStatic;

	int itemsFocussedInInventory = 0;
	Item firstFocussedItem;
	Item secondFocussedItem;

	int menueIsOpenFlag = 0; // Is set to 1 if a RoomObjectMenu or an ItemMenu
								// is currently open.

	static int cnt = 0;

	Item roomObj;
	boolean wantToCombineRoomObjWithItem;

	public int getConvStatic() {
		return convStatic;
	}

	public int getOption() {
		return option;
	}

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

	public void setWantToCombineRoomObjWithItem(
			boolean wantToCombineRoomObjWithItem) {
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

	public void setParser(XmlParser p) {
		this.parser = p;
	}

	public static XmlParser getParser() {
		return parser;
	}

	public void automaticTrigger(int convId, int roomId) {
		System.out.println("I was triggered.");
		if (convId == 14)
			convId = getConvStatic();
		/*
		 * if (option=='c'){ DialogOutput dout = new DialogOutput(frame,
		 * getParser().getConversationById(convId), getBackgroundImage(),
		 * getParser().getListOfActors(), getRoomId()); }
		 */
		if (option == 'r') {
			System.out.println("I was called to build a room. I knew Room "
					+ roomId + " and Conversation " + convId);
			BuildRoom br = new BuildRoom(roomId, frame);
		}
		if (option == 'b') {
			System.out.println("I was called to build a dialog. I knew Room "
					+ roomId + " and Conversation " + convId);
			// BuildRoom br = new BuildRoom(roomId, frame);
			DialogOutput dout = new DialogOutput(frame, getParser()
					.getConversationById(convId), getBackgroundImage(),
					getParser().getListOfActors(), roomId);
		}
	}

	public BuildRoom(int roomId, JFrame frame) {

		XmlParser p = new XmlParser("src/main/resources/XML/chapter1.xml");

		setRoomId(roomId);
		Runtime.setCurrRoomId(roomId);
		setParser(p);
		setRoom(p.getRoomById(roomId));

		setItems(p.getAllItemsByRoomId(roomId));
		setRoomObjects(p.getAllRoomObjectsByRoomId(roomId));

		if (!Runtime.getInventory().isEmpty()) {

			validateItemsInRoom();

		}

		setActors(p.getAllNpcsByRoomId(roomId));

		setRoomImagePath(trimmPicPath(room.getPicturePath()));

		deleteFrame(frame);
		frame.repaint();

		setFrame(frame);
		// setBackground Image

		JLabel label = setBackgroundImage(frame);
		drawRoomObjects(frame, true);

		drawObjects(frame, true);

		drawInventoryBag(frame);
		drawActors(frame);
		frame.add(label);
	}

	public void drawActors(JFrame frame) {
		List<Actor> actors = getActors();
		for (int cnt = 0; cnt < actors.size(); cnt++) {
			Actor actor = actors.get(cnt);
			String actorPicPath = trimmPicPath(actor.getPicturePath());

			BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(actorPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage,
					(int) actor.getNpcLocX(), (int) actor.getNpcLocY(), null);
			System.out.println("You draw NPC " + actor.getId());
			addClickableFunction((int) actor.getNpcLocX(),
					(int) actor.getNpcLocY(), 100, 300, actor.getId(), frame,
					"actor");

		}

	}

	public void validateItemsInRoom() {
		List<Item> remItems = Runtime.getRemItems();
		List<Item> items = getItems();

		for (int cnt = 0; cnt < getItems().size(); cnt++) {
			for (int count = 0; count < (remItems.size())
					&& (!getItems().isEmpty()); count++) {
				System.out.println("You picked up " + remItems.size()
						+ " item(s). Count is " + count
						+ " and getItemsSize is " + getItems().size()
						+ " cnt is " + cnt);
				if (getItems().get(cnt).getId() == remItems.get(count).getId()) {
					int id = getItemIDById(items, getItems().get(cnt).getId());
					System.out
							.println("Because it is in removed list I won't draw item "
									+ getItems().get(cnt).getName());
					items.remove(id);
				}
			}
			setItems(items);
		}
	}

	public void drawObjects(JFrame frame, boolean addClickableFct) {
		List<Item> itemsInDrawFunction = getItems();

		for (int cntItemPic = 0; cntItemPic < itemsInDrawFunction.size(); cntItemPic++) {
			if (Runtime.checkStep(itemsInDrawFunction.get(cntItemPic).getId(),
					'i', 'd')) {
				System.out.println("I want to draw item"
						+ itemsInDrawFunction.get(cntItemPic).getId());
				String itemPicPath = trimmPicPath(itemsInDrawFunction.get(
						cntItemPic).getPicturePath());
				float xLoc = itemsInDrawFunction.get(cntItemPic).getItemLocY();
				float yLoc = itemsInDrawFunction.get(cntItemPic).getItemLocX();

				BufferedImage foregroundImage = null;
				try {
					foregroundImage = ImageIO.read(new File(itemPicPath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				backgroundImage.getGraphics().drawImage(foregroundImage,
						(int) yLoc, (int) xLoc, null);
				if (addClickableFct) {
					addClickableFunction((int) yLoc, (int) xLoc,
							foregroundImage.getWidth(),
							foregroundImage.getHeight(), itemsInDrawFunction
									.get(cntItemPic).getId(), frame, "item");
				}

			}
		}
	}

	public void drawRoomObjects(JFrame frame, boolean addClickableFct) {
		List<Item> roomObjectsInDrawFunction = getRoomObjects();

		for (int cntItemPic = 0; cntItemPic < roomObjectsInDrawFunction.size(); cntItemPic++) {
			if (Runtime.checkStep(roomObjectsInDrawFunction.get(cntItemPic)
					.getId(), 'o', 'd')) {
				String itemPicPath = trimmPicPath(roomObjectsInDrawFunction
						.get(cntItemPic).getPicturePath());
				float xLoc = roomObjectsInDrawFunction.get(cntItemPic)
						.getItemLocY();
				float yLoc = roomObjectsInDrawFunction.get(cntItemPic)
						.getItemLocX();

				BufferedImage foregroundImage = null;
				try {
					foregroundImage = ImageIO.read(new File(itemPicPath));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				backgroundImage.getGraphics().drawImage(foregroundImage,
						(int) yLoc, (int) xLoc, null);
				if (addClickableFct) {
					addClickableFunction((int) yLoc, (int) xLoc,
							foregroundImage.getWidth(),
							foregroundImage.getHeight(),
							roomObjectsInDrawFunction.get(cntItemPic).getId(),
							frame, "roomObjects");
					/*
					 * if(Runtime.checkStep(roomObjectsInDrawFunction.get(cntItemPic
					 * ).getId(), 'o')){
					 * 
					 * } else { System.out.println("You cannot click on item "+
					 * roomObjectsInDrawFunction.get(cntItemPic).getId()); }
					 */
				}
			} else {
				System.out.println("You didn't draw item "
						+ roomObjectsInDrawFunction.get(cntItemPic).getId());
			}
		}
	}

	public JLabel setBackgroundImage(JFrame frame) {
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

	public void addClickableFunction(final int xLoc, final int yLoc, int width,
			int height, final int itemId, final JFrame frame, final String type) {
		JLabel label = new JLabel();
		label.setBounds(xLoc, yLoc, width, height);

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				
				if (!(menueIsOpenFlag == 1)) {

					if (type.equalsIgnoreCase("inventory")) {
						System.out.println("You pressed the Inventory");
						drawInventory(frame);
						frame.repaint();
					}
					if (type.equalsIgnoreCase("inventory:close")) {
						System.out.println("You closed the inventory !!!");
						refreshFrame(frame);
						setItemsFocussedInInventory(0);
					}
					if (type.equalsIgnoreCase("inventory:next")) {
						System.out.println("You turned the page!!!");
						side = 15;
						refreshFrame(frame);
						setItemsFocussedInInventory(0);
						drawInventory(frame);
						frame.repaint();

					}
					if (type.equalsIgnoreCase("inventory:back")) {
						System.out.println("You turned the page!!!");
						side = 0;
						refreshFrame(frame);
						setItemsFocussedInInventory(0);
						drawInventory(frame);
						frame.repaint();

					}
					if (type.equalsIgnoreCase("item")) {
						if (menueIsOpenFlag == 1) {
							refreshFrame(frame);
							frame.repaint();
							menueIsOpenFlag = 0;
						} else {
							System.out.println("You pressed Item " + itemId);
							drawItemMenue(frame, xLoc, yLoc, itemId, 'i');
						}
					}
					if (type.equalsIgnoreCase("roomObjects")) {
						if (menueIsOpenFlag == 1) {
							refreshFrame(frame);
							frame.repaint();
							menueIsOpenFlag = 0;
						} else {
							System.out.println("You pressed RoomObject "
									+ itemId);
							drawItemMenue(frame, xLoc, yLoc, itemId, 'r');
						}
					}

					if (type.equalsIgnoreCase("leaveRoom")) {
						System.out.println("You want to leave ... ? :(");
						deleteFrame(frame);
						// Runtime.changeRoom(getRoom().getLocationPointer(),
						// frame);
						// TODO: Delete the help variable once the whole game is
						// finished
						// Checking whether there is simply a pointer missing in
						// the XML
						int help = getRoom().getLocationPointer();
						if (help == 0) {
							System.out
									.println("There is no Locationpointer specified.");
						} else {
							if (Runtime.checkStep(getRoom()
									.getLocationPointer(), 'r', 'd')) {
								BuildRoom br = new BuildRoom(getRoom()
										.getLocationPointer(), frame);
								if (Runtime.checkTrigger(help)) {
									automaticTrigger(14, getRoom()
											.getLocationPointer());
								}
							}
						}
					}

					if (type.equalsIgnoreCase("inGameMenue")) {
						System.out.println("InGameMenue");
					}
					
					if (type.equalsIgnoreCase("actor")) {
						int conv = Runtime.chooseConv(actors.get(cnt).getId(),
								roomId);
						System.out.println("You NPC: " + actors.get(cnt).getId()
								+ " Your Room: " + roomId + " Your Conversation: "
								+ conv + " Your State: " + Runtime.getGameState());
						DialogOutput dout = new DialogOutput(frame, getParser()
								.getConversationById(conv), getBackgroundImage(),
								getParser().getListOfActors(), getRoomId());
						int autoItem = Runtime.checkAutoItem(conv, 'c', 'c');
						System.out.println("I want to add item " + autoItem);
						if (autoItem != 0) {
							Runtime.addItemToInventory(getItemById(getItems(),
									autoItem));
						}
					}

					if (type.equalsIgnoreCase("inventory:click")) {

						Item itemInInventory = null;
						Item nextItem = null;
						itemInInventory = getItemById(Runtime.getInventory(),
								itemId);
						if (isWantToCombineRoomObjWithItem()) {
							itemAndRoomObjInteraction(itemInInventory);
							// transition from state one to state two by using the
							// key on the door
							Runtime.checkInteraction(roomObj.id, itemInInventory.id);
						}

						focusItemInInventory(frame, itemInInventory);

					}
					
				} // check if MenuIsOpenFlag
				
			
				// Options for Items
				if (type.equalsIgnoreCase("pickup:itemmenue")) {
					// Once the key is picked up, the game will change the state
					Runtime.checkStep(itemId, 'i', 'p');
					Runtime.addItemToInventory(getItemById(getItems(), itemId));
					deleteItem(frame, itemId);
					System.out.println("item not deleted");
					menueIsOpenFlag = 0;
				}
				if (type.equalsIgnoreCase("inspect:itemmenue")) {
					int autoItem = Runtime.checkAutoItem(itemId, 'i', 'i');
					menueIsOpenFlag = 0;
					if (autoItem != 0) {
						Runtime.addItemToInventory(getItemById(getItems(),
								autoItem));
					}
					System.out.println("inspect item ..." + itemId);
				}
				if (type.equalsIgnoreCase("leave:item")) {
					refreshFrame(frame);
					menueIsOpenFlag = 0;
				}

				// Options for RoomObjects
				if (type.equalsIgnoreCase("use:RoomObjMenue")) {
					// checks whether a roomobject may be used.
					Item item = getRoomObjectById(itemId);
					String aimLoc = item.getLocationPointer();
					aimLoc = aimLoc.substring(11, 14);
					int aimLocId = Integer.parseInt(aimLoc);
					System.out.println(aimLocId);
					menueIsOpenFlag = 0;

					if (Runtime.checkStep(itemId, 'o', 'u')) {
						if (aimLocId == 0) {
							System.out
									.println("There is no Locationpointer specified.");
						} else {
							BuildRoom br = new BuildRoom(aimLocId, frame);
							if (Runtime.checkTrigger(aimLocId)) {
								automaticTrigger(14, aimLocId);
							}
						}
					}
					// in case the roomobject may not be used.
					/**
					 * TODO: Has to be replaced by calling a dialog which states
					 * something about better not passing the door/ going that
					 * way.
					 * 
					 */
					else {
						System.out.println("You shall not pass.");
					}

				}

				if (type.equalsIgnoreCase("inspect:RoomObjMenue")) {
					int autoItem = Runtime.checkAutoItem(itemId, 'r', 'i');
					menueIsOpenFlag = 0;
					if (autoItem != 0) {
						Runtime.addItemToInventory(getItemById(getItems(),
								autoItem));
					}
					System.out.println("inspect roomObject ..." + itemId);
				}

				if (type.equalsIgnoreCase("item:RoomObjMenue")) {
					Item roomObj = getRoomObjectById(itemId);
					setRoomObj(roomObj);
					drawInventory(frame);
					setWantToCombineRoomObjWithItem(true);
					menueIsOpenFlag = 0;
				}
			

				

			}
		});
		frame.add(label);
	}

	public void addRefreshListener(final JFrame frame) {
		JLabel label = new JLabel();
		label.setBounds(0,0,800,600);

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
					if(menueIsOpenFlag == 1){
						deleteFrame(frame);
						JLabel label = setBackgroundImage(frame);
					
						menueIsOpenFlag = 0;
						drawObjects(frame, true);
						drawRoomObjects(frame, true);
						drawInventoryBag(frame);
						frame.add(label);
						frame.repaint();
						
					}
			}});
		frame.add(label);
	}
	
	
	public static int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}

	public String trimmPicPath(String picPath) {
		picPath = picPath.trim();
		picPath = picPath.substring(1, picPath.length() - 1);
		return picPath;

	}

	public void drawItemMenue(JFrame frame, int xLoc, int yLoc, int itemId,
			char option) {

		deleteFrame(frame);
		JLabel label = setBackgroundImage(frame);
	
		drawObjects(frame, false);
		drawRoomObjects(frame, false);
		drawInventoryBag(frame);

		menueIsOpenFlag = 1;
		if (option == 'i') {
			BufferedImage btnTakeItem = null;
			BufferedImage btnLeaveItem = null;
			BufferedImage btnInspectItem = null;
			try {
				btnTakeItem = ImageIO
						.read(new File(
								"src/main/resources/Picture/Menue/Itemmenue/btnTakeItem.png"));
				btnLeaveItem = ImageIO
						.read(new File(
								"src/main/resources/Picture/Menue/Itemmenue/btnLeaveItem.png"));
				btnInspectItem = ImageIO
						.read(new File(
								"src/main/resources/Picture/Menue/Itemmenue/btnInspectItem.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			backgroundImage.getGraphics().drawImage(btnTakeItem, xLoc - 60,
					yLoc - 40, 180, 30, null);
			addClickableFunction(xLoc - 60, yLoc - 50, 180, 30, itemId, frame,
					"pickup:itemmenue");

			backgroundImage.getGraphics().drawImage(btnLeaveItem, xLoc - 60,
					yLoc + 40, 180, 30, null);
			addClickableFunction(xLoc - 60, yLoc + 40, 180, 30, itemId, frame,
					"leave:item");

			if (xLoc + 180 < 800) {
				backgroundImage.getGraphics().drawImage(btnInspectItem,
						xLoc + 80, yLoc, 180, 30, null);
				addClickableFunction(xLoc + 80, yLoc, 180, 30, itemId, frame,
						"inspect:itemmenue");
			} else {
				backgroundImage.getGraphics().drawImage(btnInspectItem,
						xLoc - 210, yLoc, 180, 30, null);
				addClickableFunction(xLoc - 210, yLoc, 180, 30, itemId, frame,
						"inspect:itemmenue");
			}
			addRefreshListener(frame);
			frame.add(label);
			frame.repaint();
		}

		if (option == 'r') {

			BufferedImage btnUseRoomObj = null;
			BufferedImage btnInspectRoomObj = null;
			BufferedImage btnNothingRoomObj = null;
			BufferedImage btnUseItemOnRoomObj = null;
			try {
				btnUseRoomObj = ImageIO
						.read(new File(
								"src/main/resources/Picture/Menue/Itemmenue/btnUseRoomObj.png"));
				btnInspectRoomObj = ImageIO
						.read(new File(
								"src/main/resources/Picture/Menue/Itemmenue/btnInspectRoomObj.png"));
				btnNothingRoomObj = ImageIO
						.read(new File(
								"src/main/resources/Picture/Menue/Itemmenue/btnNothingRoomObj.png"));
				btnUseItemOnRoomObj = ImageIO
						.read(new File(
								"src/main/resources/Picture/Menue/Itemmenue/btnUseItemOnRoomObj.png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Liste f࠲ RoomObjects, bei denen der Menࠐunkt : Untersuchen nicht
			// erscheinen soll !

			// Sets correction values to ensure that the button is not outside
			// the frame
			int upsideCorrection = 0;
			int rightsideCorrection = 0;

			if (yLoc + 90 > 600) {
				upsideCorrection = 100;
			}

			if (xLoc + 180 > 800) {
				rightsideCorrection = xLoc + 180 - 800;
			}

			if (!(itemId == 1 || itemId == 2 || itemId == 3 || (itemId >= 15 && itemId <= 32))) {
				backgroundImage.getGraphics().drawImage(btnInspectRoomObj,
						xLoc - rightsideCorrection,
						yLoc + 90 - upsideCorrection, 180, 30, null);
				addClickableFunction(xLoc - rightsideCorrection, yLoc + 75
						- upsideCorrection, 180, 30, itemId, frame,
						"inspect:RoomObjMenue");
			}

			backgroundImage.getGraphics().drawImage(btnUseRoomObj,
					xLoc - rightsideCorrection, yLoc - upsideCorrection, 180,
					30, null);
			addClickableFunction(xLoc - rightsideCorrection, yLoc - 15
					- upsideCorrection, 180, 30, itemId, frame,
					"use:RoomObjMenue");

			backgroundImage.getGraphics().drawImage(btnNothingRoomObj,
					xLoc - rightsideCorrection, yLoc + 30 - upsideCorrection,
					180, 30, null);
			addClickableFunction(xLoc - rightsideCorrection, yLoc + 15
					- upsideCorrection, 180, 30, itemId, frame, "leave:item");

			backgroundImage.getGraphics().drawImage(btnUseItemOnRoomObj,
					xLoc - rightsideCorrection, yLoc + 60 - upsideCorrection,
					180, 30, null);
			addClickableFunction(xLoc - rightsideCorrection, yLoc + 45
					- upsideCorrection, 180, 30, itemId, frame,
					"item:RoomObjMenue");
			addRefreshListener(frame);
			frame.add(label);
			frame.repaint();
		}

	}

	public static void deleteFrame(JFrame frame) {
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();

	}

	public int getItemIDById(List<Item> items, int id) {
		int cnt = 0;
		for (cnt = 0; cnt < items.size(); cnt++) {
			int itemId = items.get(cnt).getId();
			if (itemId == id) {
				return cnt;
			}

		}
		return cnt;
	}

	public Item getItemById(List<Item> items, int id) {
		Item item = null;
		for (int cnt = 0; cnt < items.size(); cnt++) {
			int itemId = items.get(cnt).getId();
			if (itemId == id) {

				return items.get(cnt);
			}

		}
		return item;
	}

	public Item getItemFromInventoryById(int id) {
		Item item = null;
		List<Item> inventory = Runtime.getInventory();

		for (int cnt = 0; cnt < inventory.size(); cnt++) {
			if (id == inventory.get(cnt).getId()) {
				item = inventory.get(cnt);
			}
		}
		return item;
	}

	public void deleteItem(JFrame frame, int notToDrawItemId) {
		deleteFrame(frame);
		List<Item> items = getItems();

		int removeId = getItemIDById(items, notToDrawItemId);
		items.remove(removeId);
		setItems(items);

		JLabel label = setBackgroundImage(frame);
		drawObjects(frame, true);
		drawRoomObjects(frame, true);
		drawInventoryBag(frame);
		frame.add(label);

	}

	public void drawInventoryBag(JFrame frame) {
		int roomId = this.room.getId();

		// if room is floor draw another HUD
		if (!(roomId == 1 || roomId == 2 || roomId == 3 || roomId == 14
				|| roomId == 13 || roomId == 16 || roomId == 17)) {
			BufferedImage inventoryBag = null;
			try {
				inventoryBag = ImageIO.read(new File(
						"src/main/resources/Picture/hud.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(inventoryBag, 0, 0, 800,
					590, null);
			// special ItemID
			addClickableFunction(670, 0, 130, 115, 999, frame, "inventory");
			addClickableFunction(710, 510, 90, 90, 999, frame, "leaveRoom");
			addClickableFunction(0, 0, 100, 90, 999, frame, "inGameMenue");
		} else {
			BufferedImage inventoryBag = null;
			try {
				inventoryBag = ImageIO.read(new File(
						"src/main/resources/Picture/hudFloor.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(inventoryBag, 0, 0, 800,
					590, null);
			// special ItemID
			addClickableFunction(670, 0, 130, 115, 999, frame, "inventory");
			addClickableFunction(0, 0, 100, 90, 999, frame, "inGameMenue");
		}

	}

	public void focusItemInInventory(JFrame frame, Item item) {

		itemsFocussedInInventory++;
		List<Item> inventory = Runtime.getInventory();
		int itemId = item.getId();
		int pos = 0;

		System.out.println("Items focussed = " + itemsFocussedInInventory);

		for (int cnt = 0; cnt < inventory.size(); cnt++) {
			if (itemId == inventory.get(cnt).getId()) {
				pos = cnt;
				System.out.println("cnt " + cnt);
			}
		}

		System.out.println("Pos " + pos);
		if (pos == 0) {
			frame.getContentPane().getGraphics().drawRect(110, 154, 90, 84);
		}
		if (pos > 0 && pos <= 5) {
			frame.getContentPane().getGraphics()
					.drawRect(110 + (pos * 120), 154, 90, 84);
		}
		if (pos >= 5 && pos <= 10) {
			frame.getContentPane().getGraphics()
					.drawRect(110 + (pos * 120), 257, 90, 84);
		}
		if (cnt >= 10 && cnt <= 15) {
			frame.getContentPane().getGraphics()
					.drawRect(110 + (cnt * 120), 257 + 103, 90, 84);
		}

		if (itemsFocussedInInventory == 1) {
			setFirstFocussedItem(item);
		}
		if (itemsFocussedInInventory == 2) {
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

		if (Integer.parseInt(firstItem.getCombinesWith()) == secondItem.getId()
				|| firstItem.getId() == Integer.parseInt(secondItem
						.getCombinesWith())) {
			System.out.println("fit");
		} else {
			System.out.println("doesnt");
		}

	}

	public void drawInventory(JFrame frame) {
		deleteFrame(frame);
		JLabel label = setBackgroundImage(frame);

		drawObjects(frame, false);
		drawRoomObjects(frame, false);

		BufferedImage inventoryBackground = null;
		BufferedImage btnCloseInventory = null;
		BufferedImage btninventoryNext = null;
		BufferedImage btninventoryBack = null;
		try {
			inventoryBackground = ImageIO
					.read(new File(
							"src/main/resources/Picture/Inventory/inventoryBackground.png"));
			btnCloseInventory = ImageIO
					.read(new File(
							"src/main/resources/Picture/Inventory/btnCloseInventory.png"));
			btninventoryNext = ImageIO
					.read(new File(
							"src/main/resources/Picture/Inventory/btninventoryNext.png"));
			btninventoryBack = ImageIO
					.read(new File(
							"src/main/resources/Picture/Inventory/btninventoryBack.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		backgroundImage.getGraphics().drawImage(inventoryBackground, 90, 80,
				600, 400, null);
		backgroundImage.getGraphics().drawImage(btnCloseInventory, 630, 85, 60,
				50, null);
		if (side == 0) {
			backgroundImage.getGraphics().drawImage(btninventoryNext, 560, 450,
					60, 25, null);
		} else {
			backgroundImage.getGraphics().drawImage(btninventoryBack, 160, 450,
					25, 25, null);
		}
		addClickableFunction(630, 85, 50, 50, 999, frame, "inventory:close");
		addClickableFunction(560, 450, 60, 25, 999, frame, "inventory:next");
		addClickableFunction(160, 450, 60, 25, 999, frame, "inventory:back");
		List<Item> inventory = Runtime.getInventory();

		int rowid = 0;
		int xLoc = 110;
		int yLoc = 165;

		int xLocClick = 110;
		int yLocClick = 154;

		for (int cntItemPic = 0 + side; cntItemPic < inventory.size(); cntItemPic++) {

			String itemPicPath = trimmPicPath(inventory.get(cntItemPic)
					.getPicturePath());
			itemPicPath = itemPicPath.replace(".png", "");
			itemPicPath = itemPicPath + "_inventory.png";

			BufferedImage foregroundImage = null;
			try {
				foregroundImage = ImageIO.read(new File(itemPicPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backgroundImage.getGraphics().drawImage(foregroundImage, xLoc,
					yLoc, null);

			addClickableFunction(xLocClick, yLocClick, 90, 84,
					inventory.get(cntItemPic).getId(), frame, "inventory:click");

			if (rowid == 0) {
				xLocClick = 110;
				yLocClick = 154;
				xLoc = xLoc + 120;
				xLocClick = xLocClick + 120;
				if (cntItemPic >= 4 + side) {
					rowid = 1;
					xLoc = -10;
					xLocClick = -10;
					yLoc = 267;
					yLocClick = 267;
				}
			}
			if (rowid == 1) {
				xLoc = xLoc + 120;
				xLocClick = xLocClick + 120;
				if (cntItemPic >= 9 + side) {
					rowid = 2;
					xLoc = -10;
					xLocClick = -10;
					yLoc = 380;
					yLocClick = 380;
				}
			}
			if (rowid == 2) {
				xLoc = xLoc + 120;
				xLocClick = xLocClick + 120;
				if (cntItemPic >= 13 + side) {
					rowid = -1;
				}
			}

			/*
			 * if(cntItemPic > 6){ xLoc = 110; xLocClick = 110;
			 * 
			 * yLoc = yLoc+103; yLocClick = yLocClick+103; } if(cntItemPic >
			 * 11){ xLoc = 110; xLocClick = 110;
			 * 
			 * yLoc = yLoc+103; yLocClick = yLocClick+103; }
			 */
			System.out.println(cntItemPic);

		}
		frame.add(label);
		frame.repaint();
	}

	public void refreshFrame(JFrame frame) {
		deleteFrame(frame);
		JLabel label = setBackgroundImage(frame);
		drawObjects(frame, true);
		drawRoomObjects(frame, true);
		drawInventoryBag(frame);
		frame.add(label);
		frame.repaint();
	}

	public Item getRoomObjectById(int id) {
		Item item = null;

		for (int cnt = 0; cnt < getRoomObjects().size(); cnt++) {
			if (getRoomObjects().get(cnt).getId() == id) {
				item = getRoomObjects().get(cnt);
			}
		}

		return item;
	}

	public void itemAndRoomObjInteraction(Item item) {
		Item roomObj = getRoomObj();
		Item itemToCombine = item;

		System.out.println("Item " + itemToCombine.getName());

		if (Integer.parseInt(itemToCombine.getCombinesWith()) == roomObj
				.getId()) {
			// Deletes item from Inventory if it works
			System.out.println("is ready to combine ...");
			List<Item> inventory = Runtime.getInventory();
			if (inventory.contains(itemToCombine)) {
				inventory.remove(itemToCombine);
				Runtime.setInventory(inventory);
				drawInventory(frame);
				frame.repaint();
			}
			int autoItem = Runtime.checkAutoItem(roomObj.id, 'r', 'r');
			System.out.println("There was a combination " + roomObj.id
					+ " and I want to add item " + autoItem);
			if (autoItem != 0) {
				Runtime.addItemToInventory(getItemById(getItems(), autoItem));
			}
		} else {
			// Do somethin if it not works ...
			System.out.println("RoomObj:  " + roomObj.getName());
			System.out.println("Item :" + item.getName() + " , "
					+ item.getCombinesWith());
		}

	}

	public void checkIfCombineable(Item item1, Item item2) {
		if (Integer.parseInt(item1.getCombinesWith()) == item2.getId()
				|| Integer.parseInt(item2.getCombinesWith()) == item1.getId()) {
			System.out.println("You can combine !!!");
		} else {
			System.out.println("Item 1 :" + item1.getName()
					+ " is not combineable with " + item2.getName());
		}

	}

}
