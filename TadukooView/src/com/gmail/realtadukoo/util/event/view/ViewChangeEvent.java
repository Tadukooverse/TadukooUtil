package com.gmail.realtadukoo.util.event.view;

import java.util.List;

import com.gmail.realtadukoo.util.event.Event;
import com.gmail.realtadukoo.util.view.View;

/**
 * Represents a generic {@link Event} where the current {@link View} list is changing, 
 * such as switching to the New Game Menu or opening the Login View on 
 * top of the Main Menu.
 * <br>
 * Stores a {@link List} of old Views that are being removed and a List of new 
 * Views that are being opened or switched to.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class ViewChangeEvent extends Event{
	/** The {@link View Views} being removed */
	private List<View> oldViews;
	/** The {@link View Views} being added or switched to */
	private List<View> newViews;
	
	/**
	 * Creates a View Change Event for the given old {@link View Views} and new Views.
	 * 
	 * @param type The type of Event
	 * @param message The message for the Event
	 * @param oldViews The Views being removed
	 * @param newViews The Views being added or switched to
	 */
	public ViewChangeEvent(String type, String message, List<View> oldViews, List<View> newViews){
		super(type, message);
		this.oldViews = oldViews;
		this.newViews = newViews;
	}
	
	/**
	 * Grabs the {@link View Views} being removed in this Event.
	 * 
	 * @return The Views being removed
	 */
	public List<View> getOldViews(){
		return oldViews;
	}
	
	/**
	 * Grabs the {@link View Views} being added or switched to in this Event.
	 * 
	 * @return The Views being added or switched to
	 */
	public List<View> getNewViews(){
		return newViews;
	}
}
