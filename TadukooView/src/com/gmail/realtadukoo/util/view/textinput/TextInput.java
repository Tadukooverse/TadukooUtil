package com.gmail.realtadukoo.util.view.textinput;

public interface TextInput{
	
	public abstract void handleKeyType(char c);
	
	public abstract void handleBackspace();
	
	public abstract void handleDelete();
	
	public abstract void handleLeft();
	
	public abstract void handleRight();
	
	public abstract void handleUp();
	
	public abstract void handleDown();
	
	public abstract boolean handleEscape();
	
	public abstract boolean handleTab();
	
	public abstract boolean handleEnter();
}
