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


	
	String name;
	String picturePath;
	String description;
	String primaryLocation; 
	String secondaryLocation;
	String associatedWith;
	String combinesWith;
	String contains;
	boolean isCollectible;
	boolean isUseable;
	int id;
	boolean isRoomObject; 
	String roomObject;
	String condition;
	
	

	public Item(int id) {
		super(id);
		// TODO Auto-generated constructor stub
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



	public String getPrimaryLocation() {
		return primaryLocation;
	}



	public void setPrimaryLocation(String primaryLocation) {
		this.primaryLocation = primaryLocation;
	}



	public String getSecondaryLocation() {
		return secondaryLocation;
	}



	public void setSecondaryLocation(String secondaryLocation) {
		this.secondaryLocation = secondaryLocation;
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



	public String getContains() {
		return contains;
	}



	public void setContains(String contains) {
		this.contains = contains;
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



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public boolean isRoomObject() {
		return isRoomObject;
	}



	public void setRoomObject(boolean isRoomObject) {
		this.isRoomObject = isRoomObject;
	}



	public String getRoomObject() {
		return roomObject;
	}



	public void setRoomObject(String roomObject) {
		this.roomObject = roomObject;
	}



	public String getCondition() {
		return condition;
	}



	public void setCondition(String condition) {
		this.condition = condition;
	}



	
	

	

}
