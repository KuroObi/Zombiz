package com.dhbw.Zombiz.gameEngine.logic;

public class Actor {

	/**
	 * @param args
	 */
	
	
	int id;
	String name;
	String picturePath;
	String description;
	boolean isPlayer;
	int age;
	String gender;
	String occupation;
	String rank;
	String faction;
	String abilities;
	boolean fixedLocation;
	String actorClass;
	String location; 
	String actorSubClass;
	String ability;
	
	float npcLocX;
	float npcLocY;
	
	
	public float getNpcLocX() {
		return npcLocX;
	}






	public void setNpcLocX(float npcLocX) {
		this.npcLocX = npcLocX;
	}






	public float getNpcLocY() {
		return npcLocY;
	}






	public void setNpcLocY(float npcLocY) {
		this.npcLocY = npcLocY;
	}






	public int getId(){
		return id;
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



	public boolean isPlayer() {
		return isPlayer;
	}



	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getOccupation() {
		return occupation;
	}



	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}



	public String getRank() {
		return rank;
	}



	public void setRank(String rank) {
		this.rank = rank;
	}



	public String getFaction() {
		return faction;
	}



	public void setFaction(String faction) {
		this.faction = faction;
	}



	public String getAbilities() {
		return abilities;
	}



	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}



	public boolean isFixedLocation() {
		return fixedLocation;
	}



	public void setFixedLocation(boolean fixedLocation) {
		this.fixedLocation = fixedLocation;
	}



	public String getActorClass() {
		return actorClass;
	}



	public void setActorClass(String actorClass) {
		this.actorClass = actorClass;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getActorSubClass() {
		return actorSubClass;
	}



	public void setActorSubClass(String actorSubClass) {
		this.actorSubClass = actorSubClass;
	}



	public String getAbility() {
		return ability;
	}



	public void setAbility(String ability) {
		this.ability = ability;
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



	public Item[] getInventory() {
		return inventory;
	}



	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}



	public int getInventarSize() {
		return inventarSize;
	}



	String files2dPath;
	String files3dPath;

	private final int inventarSize = 6;
	private Item[] inventory = new Item[inventarSize];

	

	public Actor(int id){
		this.id = id; 
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
	
	


}

