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

 */
<<<<<<< HEAD
package com.dhbw.zombiz.gameEngine.logic;
=======
package com.dhbw.Zombiz.gameEngine.logic;
>>>>>>> Jan

/**
 * only for testing !
 * 
 * TODO:
 */
public class Item extends AGameElement {

<<<<<<< HEAD
=======
	int id;
	String name;
	String picturePath;
	String description;
	String purpose;
	String primaryLocation;
	String effect; 
	String associatedWith;
	String combinesWith;
	String condition;
	String files2dPath;
	String files3dPath;
	boolean inInventory;
	boolean focussed;
	
>>>>>>> Jan
	public Item(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

<<<<<<< HEAD
=======
	
	//getter & setter
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPrimaryLocation() {
		return primaryLocation;
	}

	public void setPrimaryLocation(String primaryLocation) {
		this.primaryLocation = primaryLocation;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
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

	public String getFiles2dPath() {
		return files2dPath;
	}

	public void setFiles2dPath(String files2dPath) {
		this.files2dPath = files2dPath;
	}

	public String getFiles3dPath() {
		return files3dPath;
	}

	public void setFiles3dPath(String files3dPath) {
		this.files3dPath = files3dPath;
	}

	public boolean isInInventory() {
		return inInventory;
	}

	public void setInInventory(boolean inInventory) {
		this.inInventory = inInventory;
	}

	public boolean isFocussed() {
		return focussed;
	}

	public void setFocussed(boolean focussed) {
		this.focussed = focussed;
	}

	
	
	
	

	
	
	
	
	
	
	
	
	
>>>>>>> Jan
}
