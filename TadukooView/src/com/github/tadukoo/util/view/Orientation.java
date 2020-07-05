package com.github.tadukoo.util.view;

/**
 * Represents the way to orient an object in relation to given coordinates.
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public enum Orientation{
	/** Align the object to the top, centered horizontally */
	TOP_CENTER(Vertical.TOP, Horizontal.CENTER),
	/** Left align the object, centered vertically */
	LEFT_CENTER(Vertical.CENTER, Horizontal.LEFT),
	/** Center the object (horizontally and vertically) on the specified coordinate */
	CENTER(Vertical.CENTER, Horizontal.CENTER),
	/** Right align the object, centered vertically */
	RIGHT_CENTER(Vertical.CENTER, Horizontal.RIGHT),
	/** Align the object to the bottom, centered horizontally */
	BOTTOM_CENTER(Vertical.BOTTOM, Horizontal.CENTER),
	/** Align the object to the top left */
	TOP_LEFT(Vertical.TOP, Horizontal.LEFT),
	/** Align the object to the top right */
	TOP_RIGHT(Vertical.TOP, Horizontal.RIGHT),
	/** Align the object to the bottom left */
	BOTTOM_LEFT(Vertical.BOTTOM, Horizontal.LEFT),
	/** Align the object to the bottom right */
	BOTTOM_RIGHT(Vertical.BOTTOM, Horizontal.RIGHT);
	
	/**
	 * Enum used for how to align vertically
	 */
	public enum Vertical{
		TOP, CENTER, BOTTOM
	}
	
	/**
	 * Enum used for how to align horizontally
	 */
	public enum Horizontal{
		LEFT, CENTER, RIGHT
	}
	
	/** The vertical alignment to be used */
	private final Vertical ver;
	/** The horizontal alignment to be used */
	private final Horizontal hor;
	
	/**
	 * @param ver How to align vertically
	 * @param hor How to align horizontally
	 */
	Orientation(Vertical ver, Horizontal hor){
		this.ver = ver;
		this.hor = hor;
	}
	
	/**
	 * @return The vertical alignment to be used
	 */
	public Vertical vertical(){
		return ver;
	}
	
	/**
	 * @return The horizontal alignment to be used
	 */
	public Horizontal horizontal(){
		return hor;
	}
}
