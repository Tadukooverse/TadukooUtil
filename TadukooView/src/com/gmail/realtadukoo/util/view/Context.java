package com.gmail.realtadukoo.util.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmail.realtadukoo.util.event.EventHandler;
import com.gmail.realtadukoo.util.event.view.ViewChangeEventHandler;

/**
 * Represents the current Context of the game.
 * <br><br>
 * Used to store current information, such as the {@link EventHandler EventHandlers} 
 * and current {@link View Views}.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public abstract class Context{
	/** The {@link View Views} currently being shown, with the topmost one at the final index */
	private List<View> currentViewStack;
	/** The {@link EventHandler EventHandlers} used to handle {@link Event Events} */
	protected Map<Class<? extends EventHandler<?, ?>>, EventHandler<?, ?>> eventHandlers;
	
	/**
	 * Constructs a new {@link Context}, initializing the various stuff it holds.
	 * <br><br>
	 * It initializes:
	 * <ul>
	 * <li>{@link #currentViewStack} as an empty {@link ArrayList} of {@link View Views}</li>
	 * 
	 * <li>{@link #eventHandlers} as a {@link HashMap} and calls {@link #initEventHandlers()} 
	 * to populate it</li>
	 * </ul>
	 */
	public Context(){
		currentViewStack = new ArrayList<View>();
		eventHandlers = new HashMap<Class<? extends EventHandler<?, ?>>, EventHandler<?, ?>>();
		initEventHandlers();
	}
	
	/**
	 * Creates the {@link EventHandler EventHandlers} and adds them to the {@link #eventHandlers} HashMap.
	 * <br><br>
	 * This includes:
	 * <ul>
	 * <li>A {@link ViewChangeEventHandler} to be accessed via the {@link #getViewChangeEventHandler} method</li>
	 * </ul>
	 */
	protected abstract void initEventHandlers();
	
	/**
	 * Grabs all the currently active {@link View Views}.
	 * 
	 * @return The List of currently active Views
	 */
	public List<View> getCurrentViewStack(){
		return currentViewStack;
	}
	
	/**
	 * Grabs the topmost active {@link View}.
	 * 
	 * @return The topmost active View
	 */
	public View getCurrentView(){
		return currentViewStack.get(currentViewStack.size()-1);
	}
	
	/**
	 * Adds the given {@link View} to the top of the list.
	 * 
	 * @param view The View to add to the top
	 */
	public void addViewToStack(View view){
		currentViewStack.add(view);
	}
	
	/**
	 * Switches out the topmost {@link View} with the given one.
	 * 
	 * @param view The View to switch in
	 */
	public void switchCurrentView(View view){
		currentViewStack.remove(currentViewStack.size()-1);
		currentViewStack.add(view);
	}
	
	/**
	 * Clears the current list of active {@link View Views} and adds 
	 * the given one as the only View.
	 * 
	 * @param view The View to switch in as the only one
	 */
	public void switchEntirelyToView(View view){
		currentViewStack.clear();
		currentViewStack.add(view);
	}
	
	/**
	 * Grabs the {@link EventHandler} with the given class from the list 
	 * of {{@link #eventHandlers}.
	 * 
	 * @param <T> The type of EventHandler to retrieve
	 * @param clazz The Class of the EventHandler to retrieve
	 * @return The requested EventHandler
	 */
	private <T extends EventHandler<?, ?>> T getEventHandler(Class<T> clazz){
		return clazz.cast(eventHandlers.get(clazz));
	}
	
	/**
	 * Grabs the {@link ViewChangeEventHandler}, using the {@link #getEventHandler} 
	 * method.
	 * 
	 * @return The ViewChangeEventHandler
	 */
	public ViewChangeEventHandler getViewChangeEventHandler(){
		return getEventHandler(ViewChangeEventHandler.class);
	}
}
