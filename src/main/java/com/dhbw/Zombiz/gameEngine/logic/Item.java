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
 *Package: com.dhbw.zombiz.gameEngine.logic
 *
 *Contributors:
 * -Christoph Schabert
 * -Jan Brodhaecker

 */



package com.dhbw.Zombiz.gameEngine.logic;


/**
 * only for testing !
 * 
 * TODO:
 */
public class Item extends AGameElement {


	int id;
	String name;
	String picturePath;
	String description;
	int primaryLocation; 
	String associatedWith;
	String combinesWith;
	String condition;
	boolean isCollectible;
	boolean isUseable;
	int usableWithItemId;
	boolean isRoomObject; 
	int roomObject;
	int secondaryLocationId;
	
	

	public Item(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public int getPrimaryLocation() {
		return primaryLocation;
	}



	public void setPrimaryLocation(int primaryLocation) {
		this.primaryLocation = primaryLocation;
	}



	public String getAssociatedWith() {
		return associatedWith;
	}



	public void setAssociatedWith(String associatedWith) {
		this.associatedWith = associatedWith;
	}



	public String getCombinesWith() {
		return combinesWith;
	}



	public void setCombinesWith(String combinesWith) {
		this.combinesWith = combinesWith;
	}



	public String getCondition() {
		return condition;
	}



	public void setCondition(String condition) {
		this.condition = condition;
	}



	public boolean isCollectible() {
		return isCollectible;
	}



	public void setCollectible(boolean isCollectible) {
		this.isCollectible = isCollectible;
	}



	public boolean isUseable() {
		return isUseable;
	}



	public void setUseable(boolean isUseable) {
		this.isUseable = isUseable;
	}



	public int getUsableWithItemId() {
		return usableWithItemId;
	}



	public void setUsableWithItemId(int usableWithItemId) {
		this.usableWithItemId = usableWithItemId;
	}



	public boolean isRoomObject() {
		return isRoomObject;
	}



	public void setRoomObject(boolean isRoomObject) {
		this.isRoomObject = isRoomObject;
	}



	public int getRoomObject() {
		return roomObject;
	}



	public void setRoomObject(int roomObject) {
		this.roomObject = roomObject;
	}



	public int getSecondaryLocationId() {
		return secondaryLocationId;
	}



	public void setSecondaryLocationId(int secondaryLocationId) {
		this.secondaryLocationId = secondaryLocationId;
	}

	
	

	

}
