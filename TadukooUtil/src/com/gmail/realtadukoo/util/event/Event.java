package com.gmail.realtadukoo.util.event;

/**
 * Represents a generic Event that has happened and may need to be handled 
 * by various {@link EventHandler EventHandlers}.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public abstract class Event{
	/** The type of Event */
	private String type;
	/** The message for the Event */
	private String message;
	
	/**
	 * Creates an {@link Event} for the given type with the given message.
	 * 
	 * @param type The type of Event
	 * @param message The message for the Event
	 */
	public Event(String type, String message){
		this.type = type;
		this.message = message;
	}
	
	/**
	 * Grabs the type of {@link Event} this represents.
	 * 
	 * @return The type of Event this is
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Grabs the message describing this {@link Event}.
	 * 
	 * @return The message for this Event
	 */
	public String getMessage(){
		return message;
	}
}
