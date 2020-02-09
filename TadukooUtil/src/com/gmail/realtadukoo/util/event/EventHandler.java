package com.gmail.realtadukoo.util.event;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class to handle the creation of {@link Event Events} and send them out to the 
 * various {@link EventListener EventListeners} to handle them appropriately.
 *
 * @param <EventType> The {@link Event} subclass to handle events for
 * @param <Listener> The {@link EventListener} implementation to send {@link Event Events} to
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public abstract class EventHandler<EventType extends Event, Listener extends EventListener<EventType>>{
	/** The registered {@link EventListener EventListeners} to be notified of {@link Event Events} */
	private final List<Listener> listeners = new ArrayList<Listener>();
	/** Whether this EventHandler is currently handling {@link Event Events} */
	private boolean inProcess = false;
	/** {@link EventListener EventListeners} to be added once the EventHandler is no longer in process */
	private final List<Listener> listenerAddQueue = new ArrayList<Listener>();
	/** {@link EventListener EventListeners} to be removed once the EventHandler is no longer in process */
	private final List<Listener> listenerRemoveQueue = new ArrayList<Listener>();
	
	/**
	 * Register a {@link EventListener} so it will be notified of {@link Event Events} 
	 * handled by this class.
	 * 
	 * @param listener The EventListener to register
	 */
	public final void registerListener(Listener listener){
		// If it's in process, add to the add queue, otherwise add it now
		if(inProcess){
			listenerAddQueue.add(listener);
		}else{
			listeners.add(listener);
		}
	}
	
	/**
	 * Unregisters a {@link EventListener} so it will no longer be notified about 
	 * {@link Event Events} handled by this class.
	 * 
	 * @param listener The EventListener to unregister
	 */
	public final void unregisterListener(Listener listener){
		// If it's in process, add to the remove queue, otherwise add it now
		if(inProcess){
			listenerRemoveQueue.add(listener);
		}else{
			listeners.remove(listener);
		}
	}
	
	/**
	 * Sends the given {@link Event} out to all of the registered {@link EventListener EventListeners}.
	 * 
	 * @param e The Event to send out
	 */
	protected final void sendEventToListeners(EventType e){
		// Set this in process until done
		inProcess = true;
		// Send the event to the listeners
		listeners.forEach(listener -> listener.handleEvent((EventType) e));
		// Set this as no longer in process
		inProcess = false;
		
		// Remove the listeners that are in the remove queue
		listeners.removeAll(listenerRemoveQueue);
		listenerRemoveQueue.clear();
		
		// Add the listeners that are in the add queue
		listeners.addAll(listenerAddQueue);
		listenerAddQueue.clear();
	}
}
