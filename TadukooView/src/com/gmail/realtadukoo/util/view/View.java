package com.gmail.realtadukoo.util.view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gmail.realtadukoo.util.event.Event;
import com.gmail.realtadukoo.util.event.EventListener;
import com.gmail.realtadukoo.util.event.view.ViewChangeEventListener;
import com.gmail.realtadukoo.util.map.HashMultiMap;
import com.gmail.realtadukoo.util.map.MultiMap;
import com.gmail.realtadukoo.util.view.draw.Drawable;
import com.gmail.realtadukoo.util.view.draw.clickable.Clickable;
import com.gmail.realtadukoo.util.view.textinput.TextInput;

/**
 * View represents a Menu or other "View" the user is being shown (e.g. the Game view).
 * This abstraction exists so that the {@link ViewBase Framework} doesn't need to know about all of the various views.
 * <br><br>
 * Each program utilizing this class ought to have another abstract extension that implements other 
 * {@link EventListener EventListeners} that that program uses, so as to allow its Views to listen for any 
 * {@link Event Events} that may be necessary.
 * 
 * @author Logan Ferree
 * @version 0.1-Alpha-SNAPSHOT
 */
public abstract class View implements ViewChangeEventListener{
	/** Represents the current context for general stuff like user's info */
	protected Context context;
	/** MultiMap used for storing the objects to be drawn to the screen */
	private MultiMap<Integer, Drawable> drawables;
	/** MultiMap used for storing the objects that can be clicked on */
	private MultiMap<Integer, Clickable> clickables;
	/** The Clickable that is currently being focused on */
	private Clickable focus;
	
	/**
	 * Sets the {@link Context} for subclasses to use, 
	 * registers the {@link EventListener}s so that subclasses can 
	 * override the event handling methods, 
	 * and initializes the {@link #drawables} and {@link #clickables} 
	 * {@link MultiMap}s for the subclasses to add to for their contents 
	 * to be drawn to the screen and be able to be clicked on, respectively.
	 * <br><br>
	 * Overriding methods in subclass should:
	 * <ul>
	 * <li>call this method (<b><u>IMPORTANT</u></b>)</li>
	 * <li>add their {@link Drawable drawables} to the MultiMap here using 
	 * the {@link #addDrawable} method</li>
	 * <li>add their {@link Clickable clickables} to the MultiMap here using
	 * the {@link #addClickable} method</li>
	 * </ul>
	 * 
	 * @param context The current {@link Context}
	 */
	public void init(Context context){
		this.context = context;
		// Register this as a ViewChangeEventListener
		context.getViewChangeEventHandler().registerListener(this);
		// Initialize the drawables MultiMap
		drawables = new HashMultiMap<>();
		// Initialize the clickables MultiMap
		clickables = new HashMultiMap<>();
	}
	
	/**
	 * Add the given {@link Drawable} to the drawables MultiMap for the given layer, so 
	 * that the View knows about it.
	 * <br>
	 * 0 is the lowest layer, and there's no set limit as to highest layer.
	 * Also skipping layers should be fine too.
	 * <br><br>
	 * The {@link #draw} method will draw the lower layers first, so 
	 * that higher layered drawables properly show up above those on 
	 * lower layers.
	 * 
	 * @param layer The layer to put the drawable in
	 * @param drawable The drawable to add to the MultiMap
	 */
	protected final void addDrawable(int layer, Drawable drawable){
		drawables.put(layer, drawable);
	}
	
	/**
	 * Add the given {@link Clickable} to the clickables MultiMap for the given layer, 
	 * so that the View knows about it.
	 * <br>
	 * 0 is the lowest layer, and there's no set limit as to highest layer.
	 * Also skipping layers should be fine too.
	 * <br><br>
	 * The {@link #handleClick} method will prioritize buttons on higher layers over 
	 * those on lower layers.
	 * 
	 * @param layer The layer to put the clickable in
	 * @param clickable The clickable to add to the MultiMap
	 */
	protected final void addClickable(int layer, Clickable clickable){
		addDrawable(layer, clickable);
		clickables.put(layer, clickable);
	}
	
	public final void handleKeyPressed(KeyEvent e){
		// TODO: Use for key combos
	}
	
	/**
	 * Handles a key typed event. Usually this is for when a user is typing in a text box.
	 * 
	 * @param e The KeyEvent to handle
	 */
	public final boolean handleKeyTyped(KeyEvent e){
		if(focus != null && focus instanceof TextInput){
			TextInput input = (TextInput) focus;
			boolean removeFocus = false;
			switch(e.getKeyChar()){
				case KeyEvent.VK_BACK_SPACE:
					input.handleBackspace();
					break;
				case KeyEvent.VK_DELETE:
					input.handleDelete();
					break;
				case KeyEvent.VK_ESCAPE:
					removeFocus = input.handleEscape();
					break;
				case KeyEvent.VK_TAB:
					removeFocus = input.handleTab();
					break;
				case KeyEvent.VK_ENTER:
					removeFocus = input.handleEnter();
					break;
				default:
					input.handleKeyType(e.getKeyChar());
					break;
			}
			if(removeFocus){
				focus = null;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Handles a key released event. This is the meat and potatoes of users pressing keys 
	 * to play the game.
	 * 
	 * @param e The KeyEvent to handle
	 */
	public final boolean handleKeyReleased(KeyEvent e){
		if(focus != null && focus instanceof TextInput){
			TextInput input = (TextInput) focus;
			boolean removeFocus = false;
			switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					input.handleLeft();
					break;
				case KeyEvent.VK_RIGHT:
					input.handleRight();
					break;
				case KeyEvent.VK_UP:
					input.handleUp();
					break;
				case KeyEvent.VK_DOWN:
					input.handleDown();
					break;
			}
			
			if(removeFocus){
				focus = null;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Handles a mouse released event. Usually this is for when a user clicks a button.
	 * <br>
	 * Currently calls {@link #handleClick} if the event is for a left click and 
	 * returns false otherwise
	 * 
	 * @param e The MouseEvent to handle
	 * @return Whether an action happened so the {@link ViewBase} knows to repaint
	 */
	public final boolean handleMouseReleased(MouseEvent e){
		switch(e.getButton()){
			case MouseEvent.BUTTON1:
				return handleClick(e.getX(), e.getY());
			default:
				return false;
		}
	}
	
	/**
	 * Handles a left click mouse event.
	 * <br>
	 * Will find the topmost {@link Clickable} in the location the user 
	 * clicked and run the on-click action of that Clickable and then 
	 * return.
	 * 
	 * @param x The x position of the mouse
	 * @param y The y position of the mouse
	 * @return Whether an action happened, so the {@link ViewBase} knows to repaint
	 */
	private boolean handleClick(int x, int y){
		// Get all the layers specified in the MultiMap and sort them in descending order
		List<Integer> layers = new ArrayList<>(clickables.keySet());
		layers.sort(Collections.reverseOrder());
		
		// Whether a Clickable action happened or not
		boolean success = false;
		
		// Will be changing focus with this click to either remove it or change it to a new one
		Clickable newFocus = null;
		
		// Find the topmost clickable in the location the user clicked
		for(Clickable clickable: clickables.values()){
			if(x >= clickable.getX() && x <= clickable.getX() + clickable.getWidth() &&
					y >= clickable.getY() && y <= clickable.getY() + clickable.getHeight()){
				// If Clickable is focusable, set it as the new focus
				if(clickable.isFocusable()){
					newFocus = clickable;
				}
				// Handle the user clicking on the found clickable
				clickable.onClick(context);
				success = true;
				break;
			}
		}
		// Change focus to new focus
		focus = newFocus;
		return success;
	}
	
	/**
	 * Draws all the {@link Drawable Drawables} currently in the drawables MultiMap.
	 * <br>
	 * Draws them in order of layer, so that higher layers overwrite lower layers.
	 * <br>
	 * Will not draw them if they aren't set as visible.
	 * <br>
	 * Calls their {@link Drawable#conditionalDraw conditionalDraw} method.
	 * 
	 * @param g The Graphics to use for drawing
	 */
	public final void draw(Graphics g){
		// Get all the layers specified in the MultiMap and sort them in ascending order
		List<Integer> layers = new ArrayList<>(drawables.keySet());
		Collections.sort(layers);
		
		// Draw the drawables on each layer in increasing order
		for(int layer: layers){
			for(Drawable drawable: drawables.get(layer)){
				drawable.conditionalDraw(g);
			}
		}
	}
	
	/**
	 * Should be called when this View is no longer being used. 
	 * This method will unregister any listeners this View has.
	 */
	public final void close(){
		context.getViewChangeEventHandler().unregisterListener(this);
	}
}
