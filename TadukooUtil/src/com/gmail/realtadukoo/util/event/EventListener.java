package com.gmail.realtadukoo.util.event;

/**
 * A generic interface for listening for an {@link Event} to be handled. EventListeners 
 * will be registered in their corresponding {@link EventHandler} to be called when 
 * their {@code EventType} Events happen.
 * 
 * @param <EventType> The {@link Event} subclass that this EventListener should implement.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public interface EventListener<EventType extends Event>{
	
	/**
	 * Handles the given {@link Event}.
	 * 
	 * @param e The Event for this EventListener to handle
	 */
	void handleEvent(EventType e);
}
