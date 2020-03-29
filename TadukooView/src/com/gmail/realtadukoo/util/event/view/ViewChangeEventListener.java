package com.gmail.realtadukoo.util.event.view;

import com.gmail.realtadukoo.util.event.EventListener;

/**
 * ViewEventListeners are called by the {@link ViewChangeEventHandler} to handle 
 * {@link ViewChangeEvent ViewChangeEvents}.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public interface ViewChangeEventListener extends EventListener<ViewChangeEvent>{
	
	/**
	 * Handles a generic {@link ViewChangeEvent}.
	 * <br><br>
	 * A real ViewChangeEventListener interface ought to default to 
	 * calling sub-methods for specific {@link ViewChangeEvent} subclasses.
	 * 
	 * @param e The ViewChangeEvent to handle
	 */
	@Override
	void handleEvent(ViewChangeEvent e);
}
