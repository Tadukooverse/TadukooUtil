package com.gmail.realtadukoo.util.view.textinput;

import com.gmail.realtadukoo.util.view.draw.clickable.Clickable;

public abstract class FocusableTextInput extends Clickable implements TextInput{
	private StringBuilder text;
	private int cursor;
	
	public FocusableTextInput(){
		setFocusable(true);
		setOnClick(null);
		text = new StringBuilder("");
		cursor = 0;
	}
	
	@Override
	public final void handleKeyType(char c){
		text.insert(cursor, c);
		cursor++;
	}
	
	@Override
	public final void handleBackspace(){
		if(cursor > 0){
			text.delete(cursor-1, cursor);
			cursor--;
		}
	}
	
	@Override
	public final void handleDelete(){
		if(cursor < text.length()){
			text.delete(cursor, cursor+1);
		}
	}
	
	@Override
	public final void handleLeft(){
		cursor--;
	}
	
	@Override
	public final void handleRight(){
		cursor++;
	}
	
	@Override
	public final void handleUp(){
		// TODO: Allow for multi-line input?
	}
	
	@Override
	public final void handleDown(){
		// TODO: Allow for multi-line input?
	}
	
	@Override
	public final boolean handleEscape(){
		// Make it so this is no longer focused
		return true;
	}
	
	@Override
	public final boolean handleTab(){
		// Make it so this is no longer focused
		return true;
	}
	
	@Override
	public final boolean handleEnter(){
		// Make it so this is no longer focused
		return true;
	}
}
