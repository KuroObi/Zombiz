package com.dhbw.Zombiz.gameEngine.logic;

import java.util.ArrayList;
import java.util.List;

public class Room  {

	
	
	private final int id;
	private String name;
	private String picturePath;
	private String description;
	private String act; 
	private String chapter;
	private String scene;
	private String function;
	private String locationId;
	private String gameObjectsIncluded;
	private String npcs;
	private String buildingFloor;
	
	private List<Item>	listRoomItems = new ArrayList<Item>();
	private List<Item>	listPickableItems = new ArrayList<Item>();


	public Room(int id) {
		this.id = id;
		// TODO Auto-generated constructor stub
	}



	//getter & setter
	public int getId() {
		return id;
	}
	
	public void setListRoomItems(List<Item> listRoomItems) {
		this.listRoomItems = listRoomItems;
	}

	public void setListPickableItems(List<Item> listPickableItems) {
		this.listPickableItems = listPickableItems;
	}

	/*
	 * Gives back a String Array with [x-Pos,Y-Pos,Pic-Location]
	 */
	
	public String[][] getItemPos(){
		
		int RoomItemsLength = listRoomItems.size();
		int pickableItemsLength = listPickableItems.size(); 
		
		String[][] pos = new String[RoomItemsLength+pickableItemsLength][3];
		
		for(int i = 1;i <= RoomItemsLength; i++){
		// 	pos[i][0] = listRoomItems.get(i).getXPos();	//Insert the Paser X-Pos Method
		// 	pos[i][1] = listRoomItems.get(i).getyPos();	//Insert the Paser y-Pos Method
		//	pos[i][2] = listRoomItems.get(i).getPicturePath(); // Insert the Paser Pic Path Medthod
		}	

		for(int j = 1;j <= RoomItemsLength; j++){
		// 	pos[i+RoomItemsLength][0] = listPickableItems.get(i).getXPos();	//Insert the Paser X-Pos Method
		// 	pos[i+RoomItemsLength][1] = listPickableItems.get(i).getyPos();	//Insert the Paser y-Pos Method
		//	pos[i+RoomItemsLength][2] = listPickableItems.get(i).getPicturePath(); // Insert the Paser Pic Path Medthod
		}
		
		return pos;
	}
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPicturePath() {
		return picturePath;
	}




	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getAct() {
		return act;
	}




	public void setAct(String act) {
		this.act = act;
	}




	public String getChapter() {
		return chapter;
	}




	public void setChapter(String chapter) {
		this.chapter = chapter;
	}




	public String getScene() {
		return scene;
	}




	public void setScene(String scene) {
		this.scene = scene;
	}




	public String getFunction() {
		return function;
	}




	public void setFunction(String function) {
		this.function = function;
	}




	public String getLocationId() {
		return locationId;
	}




	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}




	public String getGameObjectsIncluded() {
		return gameObjectsIncluded;
	}




	public void setGameObjectsIncluded(String gameObjectsIncluded) {
		this.gameObjectsIncluded = gameObjectsIncluded;
	}




	public String getNpcs() {
		return npcs;
	}




	public void setNpcs(String npcs) {
		this.npcs = npcs;
	}




	public String getBuildingFloor() {
		return buildingFloor;
	}




	public void setBuildingFloor(String buildingFloor) {
		this.buildingFloor = buildingFloor;
	}
	
	
	
	
	
	
	
	
	
	
	

}
