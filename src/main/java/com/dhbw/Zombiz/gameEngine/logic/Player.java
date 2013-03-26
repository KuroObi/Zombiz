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
package com.dhbw.zombiz.gameEngine.logic;

/**
 * TODO:
 * -
 * 
 */
public class Player {

	private final int inventarSize = 6;
	
	@SuppressWarnings("unused") //only for testing
	private final String name;

	private Item[] inventory = new Item[inventarSize];

	public Player(String name){
		this.name = name;
	}
	
	/*
	 * puts a Item into the Inventory
	 * if the Inventory is full o is returned
	 */
	public int collectItem(Item collected){
		
		for(int i = 0; i < inventarSize; i++){
			if(inventory[i] == null){
				inventory[i] = collected;
				return 1;
			}		
		}
		return 0;	
	}
	
	//Getter and Setters
	
	public Item getItem(int number){
		return inventory[number];
	}
	
	public Item[] getItem(){
		return inventory;
	}
	
	public int getInventarSize(){
		return inventarSize;
	}
	
}