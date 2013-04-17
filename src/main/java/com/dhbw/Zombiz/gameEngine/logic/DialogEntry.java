package com.dhbw.Zombiz.gameEngine.logic;


import java.util.ArrayList;
import java.util.List;

public class DialogEntry {

	int dialogEntryId;
	int conversationId;
	
	
	boolean isGroup; 
	
	List<Integer> linkedDialogEntries = new ArrayList<Integer>();
	
	
	String title; 
	String pictures;
	String description;
	String actor; 
	String conversant;
	String menuTest;
	String dialogText;
	String parenthical;
	String audioFiles;
	String videoFiles;
	String lipsyncFiles;
	String animationFiles;
	String mood; 
	
	
	
	public DialogEntry(int id){
		this.dialogEntryId = id;
	}


	

	public int getDialogEntryId() {
		return dialogEntryId;
	}



	public void setDialogEntryId(int dialogEntryId) {
		this.dialogEntryId = dialogEntryId;
	}



	public int getConversationId() {
		return conversationId;
	}



	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}



	public boolean isGroup() {
		return isGroup;
	}



	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}



	public List<Integer> getLinkedDialogEntries() {
		return linkedDialogEntries;
	}



	public void setLinkedDialogEntries(List<Integer> linkedDialogEntries) {
		this.linkedDialogEntries = linkedDialogEntries;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getPictures() {
		return pictures;
	}



	public void setPictures(String pictures) {
		this.pictures = pictures;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getActor() {
		return actor;
	}



	public void setActor(String actor) {
		this.actor = actor;
	}



	public String getConversant() {
		return conversant;
	}



	public void setConversant(String conversant) {
		this.conversant = conversant;
	}



	public String getMenuTest() {
		return menuTest;
	}



	public void setMenuTest(String menuTest) {
		this.menuTest = menuTest;
	}



	public String getDialogText() {
		return dialogText;
	}



	public void setDialogText(String dialogText) {
		this.dialogText = dialogText;
		
	}



	public String getParenthical() {
		return parenthical;
	}



	public void setParenthical(String parenthical) {
		this.parenthical = parenthical;
	}



	public String getAudioFiles() {
		return audioFiles;
	}



	public void setAudioFiles(String audioFiles) {
		this.audioFiles = audioFiles;
	}



	public String getVideoFiles() {
		return videoFiles;
	}



	public void setVideoFiles(String videoFiles) {
		this.videoFiles = videoFiles;
	}



	public String getLipsyncFiles() {
		return lipsyncFiles;
	}



	public void setLipsyncFiles(String lipsyncFiles) {
		this.lipsyncFiles = lipsyncFiles;
	}



	public String getAnimationFiles() {
		return animationFiles;
	}



	public void setAnimationFiles(String animationFiles) {
		this.animationFiles = animationFiles;
	}



	public String getMood() {
		return mood;
	}



	public void setMood(String mood) {
		this.mood = mood;
	}

	
	
	
	
}
