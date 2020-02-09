package com.gmail.realtadukoo.util.view.draw;

import java.awt.Graphics;

/**
 * Drawable represents an object that can be drawn to the screen. 
 * This could be a simple {@link Image}, simply {@link Text}, or something more complex. 
 * Basically the point of this class is so that it's easier to draw more complex stuff 
 * to the screen and not have to copy a bunch of code over and over.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public abstract class Drawable{
	/** Represents whether this object's orientation has been calculated or not */
	private boolean oriented = true;
	/**  Represents whether this object should be drawn to the screen or not */
	private boolean visible = true;
	
	/**
	 * Returns whether this object's orientation has been calculated or not.
	 * Used in {@link #conditionalDraw} before determining whether to draw or not.
	 * 
	 * @return Whether this object's orientation has been calculated or not
	 */
	private final boolean isOriented(){
		return oriented;
	}
	
	/**
	 * Set whether this object's orientation has been calculated or not.
	 * 
	 * @param oriented Whether this object's orientation has been calculated or not
	 */
	public final void setOriented(boolean oriented){
		this.oriented = oriented;
	}
	
	/**
	 * Returns whether this object should be drawn to the screen or not.
	 * Used in {@link #conditionalDraw}
	 * 
	 * @return Whether this object should be drawn to the screen or not
	 */
	private final boolean isVisible(){
		return visible;
	}
	
	/**
	 * Set whether this object should be drawn to the screen or not.
	 * 
	 * @param visible Whether to have this object drawn to the screen or not
	 */
	public final void setVisible(boolean visible){
		this.visible = visible;
	}
	
	/**
	 * Draws the object using the given Graphics if the object is visible.
	 * 
	 * @param g The Graphics to use in drawing
	 */
	public final void conditionalDraw(Graphics g){
		if(!isOriented()){
			orient(g);
		}
		if(isVisible()){
			draw(g);
		}
	}
	
	/**
	 * Calculates the orientation of the object using the given Graphics.
	 * 
	 * @param g The Graphics to use in orienting
	 */
	protected abstract void orient(Graphics g);
	
	/**
	 * Draws the object using the given Graphics.
	 * 
	 * @param g The Graphics to use in drawing
	 */
	protected abstract void draw(Graphics g);
}
