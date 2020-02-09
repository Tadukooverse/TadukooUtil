package com.gmail.realtadukoo.util.view.draw.clickable;

import java.util.function.Consumer;

import com.gmail.realtadukoo.util.view.Context;
import com.gmail.realtadukoo.util.view.draw.Drawable;

/**
 * Clickable represents an object that can be clicked on the screen. 
 * This could be a simple {@link Button} or something more complex. 
 * Basically this class is to make it easier to create Clickable objects 
 * without having to copy a bunch of code over and over.
 * <br>
 * Clickable extends {@link Drawable} for the drawing to the screen portion.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public abstract class Clickable extends Drawable{
	/** The x coordinate (left edge) of the Clickable */
	private int x;
	/** The y coordinate (top edge) of the Clickable */
	private int y;
	/** The width of the Clickable */
	private int width;
	/** The height of the Clickable */
	private int height;
	/** The action to perform when this is clicked */
	private Consumer<Context> action;
	/** Whether this Clickable is able to be focused on or not */
	private boolean focusable = false;
	
	/**
	 * Grabs the x coordinate (left edge) of the {@link Clickable}.
	 * <br>
	 * Used for checking if the Clickable is being clicked
	 * 
	 * @return The x coordinate (left edge) of the Clickable
	 */
	public final int getX(){
		return x;
	}
	
	/**
	 * Set the x coordinate (left edge) of the {@link Clickable}.
	 * <br>
	 * Used by subclasses to change the position (by default, you can't).
	 * 
	 * @param x The x coordinate (left edge) of the Clickable
	 */
	protected final void setX(int x){
		this.x = x;
	}
	
	/**
	 * Grabs the y coordinate (top edge) of the {@link Clickable}.
	 * <br>
	 * Used for checking if the Clickable is being clicked
	 * 
	 * @return The y coordinate (top edge) of the Clickable
	 */
	public final int getY(){
		return y;
	}
	
	/**
	 * Set the y coordinate (top edge) of the {@link Clickable}.
	 * <br>
	 * Used by subclasses to change the position (by default, you can't).
	 * 
	 * @param y The y coordinate (top edge) of the Clickable
	 */
	protected final void setY(int y){
		this.y = y;
	}
	
	/**
	 * Grabs the width of the {@link Clickable}.
	 * <br>
	 * Used for checking if the Clickable is being clicked
	 * 
	 * @return The width of the Clickable
	 */
	public final int getWidth(){
		return width;
	}
	
	/**
	 * Set the width of the {@link Clickable}.
	 * <br>
	 * Used by subclasses to change the size (by default, you can't).
	 * 
	 * @param width The width of the Clickable
	 */
	protected final void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * Grabs the height of the {@link Clickable}.
	 * <br>
	 * Used for checking if the Clickable is being clicked
	 * 
	 * @return The height of the Clickable
	 */
	public final int getHeight(){
		return height;
	}
	
	/**
	 * Set the height of the {@link Clickable}.
	 * <br>
	 * Used by subclasses to change the size (by default, you can't).
	 * 
	 * @param height The height of the Clickable
	 */
	protected final void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * Performs the on-click action using the given {@link Context}
	 * 
	 * @param context The Context to perform the action using
	 */
	public final void onClick(Context context){
		// TODO: Don't allow clicking on it if it's not visible (so stuff can simply be hidden to be "removed")
		if(action != null){
			action.accept(context);
		}
	}
	
	/**
	 * Sets the on-click action to be performed when this {@link Clickable} 
	 * is clicked.
	 * 
	 * @param action The on-click action to perform when clicked
	 */
	protected final void setOnClick(Consumer<Context> action){
		this.action = action;
	}
	
	/**
	 * Grabs whether this {@link Clickable} is able to be focused on or not.
	 * 
	 * @return Whether this is focusable or not
	 */
	public final boolean isFocusable(){
		return focusable;
	}
	
	/**
	 * Set whether this {@link Clickable} is focusable or not.
	 * 
	 * @param focusable Whether this is focusable or not
	 */
	protected final void setFocusable(boolean focusable){
		this.focusable = focusable;
	}
}
