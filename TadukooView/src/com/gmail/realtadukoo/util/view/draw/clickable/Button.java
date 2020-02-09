package com.gmail.realtadukoo.util.view.draw.clickable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.gmail.realtadukoo.util.view.Context;
import com.gmail.realtadukoo.util.view.draw.Draw;
import com.gmail.realtadukoo.util.view.draw.Draw.ORIENTATION;
import com.gmail.realtadukoo.util.view.draw.Text;

/**
 * Clickable button that is drawn to the screen with 1 or 2 lines of text on it.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class Button extends Clickable{
	
	/**
	 * Builder for constructing a {@link Button} object.
	 * <br><br>
	 * <b>MUST</b> specify the following:
	 * <ul>
	 * <li>line1 - the 1st (or only) line of text on the button</li>
	 * <li>x coordinate</li>
	 * <li>y coordinate</li>
	 * </ul>
	 * Defaults are:
	 * <ul>
	 * <li>orientation - {@link Draw.ORIENTATION#TOP_LEFT TOP_LEFT}</li>
	 * <li>size - {@link SIZE#NORMAL}</li>
	 * <li>color - {@link COLOR#GREEN}</li>
	 * <li>onClick - nothing (though not recommended to leave as doing nothing)</li>
	 * </ul>
	 * 
	 * @author Logan Ferree (Tadukoo)
	 * @version 0.1-Alpha-SNAPSHOT
	 */
	public static class ButtonBuilder{
		/** The first (or only) line of text on the Button - must be specified */
		private String line1 = null;
		/** The second line of text on the Button - optional */
		private String line2 = null;
		/** The x coordinate for the Button - must be specified */
		private int x = -1;
		/** The y coordinate for the Button - must be specified */
		private int y = -1;
		/** The orientation of the Button - default TOP_LEFT */
		private Draw.ORIENTATION orientation = Draw.ORIENTATION.TOP_LEFT;
		/** The size of the Button - default NORMAL */
		private SIZE size = SIZE.NORMAL;
		/** The color of the Button - default GREEN */
		private COLOR color = COLOR.GREEN;
		/** The on-click action of the Button - default nothing */
		private Consumer<Context> onClick = null;
		
		// Cannot create ButtonBuilder outside of Button
		private ButtonBuilder(){ }
		
		/**
		 * Sets the line 1 text to be used on the Button object
		 * 
		 * @param line1 The line 1 text for the Button object
		 */
		public ButtonBuilder line1(String line1){
			this.line1 = line1;
			return this;
		}
		
		/**
		 * Sets the line 2 text to be used on the Button object
		 * 
		 * @param line2 The line 2 text for the Button object
		 */
		public ButtonBuilder line2(String line2){
			this.line2 = line2;
			return this;
		}
		
		/**
		 * Sets the x coordinate to be used on the Button object
		 * 
		 * @param x The x coordinate for the Button object
		 */
		public ButtonBuilder x(int x){
			this.x = x;
			return this;
		}
		
		/**
		 * Sets the y coordinate to be used on the Button object
		 * 
		 * @param y The y coordinate for the Button object
		 */
		public ButtonBuilder y(int y){
			this.y = y;
			return this;
		}
		
		/**
		 * Sets the orientation to be used on the Button object
		 * 
		 * @param orientation The orientation for the Button object
		 */
		public ButtonBuilder orientation(Draw.ORIENTATION orientation){
			this.orientation = orientation;
			return this;
		}
		
		/**
		 * Sets the size to be used on the Button object
		 * 
		 * @param size The size for the Button object
		 */
		public ButtonBuilder size(SIZE size){
			this.size = size;
			return this;
		}
		
		/**
		 * Sets the color to be used on the Button object
		 * 
		 * @param color The color for the Button object
		 */
		public ButtonBuilder color(COLOR color){
			this.color = color;
			return this;
		}
		
		/**
		 * Sets the on-click action to be used on the Button object
		 * 
		 * @param onClick The on-click action for the Button object
		 */
		public ButtonBuilder onClick(Consumer<Context> onClick){
			this.onClick = onClick;
			return this;
		}
		
		/**
		 * Checks for errors with the current settings for the Button object
		 */
		private void checkForErrors(){
			List<String> errors = new ArrayList<String>();
			
			if(line1 == null){
				errors.add("Must specify at least 1 line of text");
			}
			if(x == -1){
				errors.add("Must specify x coordinate");
			}
			if(y == -1){
				errors.add("Must specify y coordinate");
			}
			
			if(!errors.isEmpty()){
				throw new IllegalStateException("Failed to create Button object: " + errors.toString());
			}
		}
		
		/**
		 * Builds the Button object based on the input data if it's 
		 * valid.
		 * 
		 * @return The built Button object
		 */
		public Button build(){
			checkForErrors();
			
			return new Button(line1, line2, x, y, orientation, size, color, onClick);
		}
	}
	
	/**
	 * Represents the size of the button to be drawn.
	 * Contains dimensions and Font to use.
	 * 
	 * @author Logan Ferree (Tadukoo)
	 * @version 0.1-Alpha-SNAPSHOT
	 */
	public enum SIZE{
		/** Represents a "normal" 300x200 button */
		NORMAL(300, 200),
		/** Represents a "medium" 300x70 button */
		MEDIUM(300, 70), 
		/** Represents a "small" 145x70 button */
		SMALL(145, 70), 
		/** Represents a "tiny" 100x30 button */
		TINY(100, 30);
		
		/** The width to use for the button */
		private int width;
		/** The height to use for the button */
		private int height;
		
		/**
		 * Constructor for a SIZE. Sets the width and height.
		 * 
		 * @param width The width to use for the button
		 * @param height The height to use for the button
		 */
		private SIZE(int width, int height){
			this.width = width;
			this.height = height;
		}
		
		/**
		 * @return The width to use for the button
		 */
		public int getWidth(){
			return width;
		}
		
		/** @return The height to use for the button */
		public int getHeight(){
			return height;
		}
	}
	
	/** Represents the Color of the button */
	public enum COLOR{
		GREEN, RED, YELLOW
	}
	
	/** A Map of the different button images by their size and color */
	private static final Map<String, BufferedImage> buttons = new HashMap<String, BufferedImage>();
	static{
		// Create the buttons Map
		// Base path where all the buttons are stored
		String basePath = "/img/button/";
		// Load the normal buttons
		buttons.put(SIZE.NORMAL.toString() + COLOR.GREEN.toString(), Draw.loadImage(basePath + "normalGreen.png"));
		buttons.put(SIZE.NORMAL.toString() + COLOR.RED.toString(), Draw.loadImage(basePath + "normalRed.png"));
		buttons.put(SIZE.NORMAL.toString() + COLOR.YELLOW.toString(), Draw.loadImage(basePath + "normalYellow.png"));
		// Load the medium buttons
		buttons.put(SIZE.MEDIUM.toString() + COLOR.GREEN.toString(), Draw.loadImage(basePath + "mediumGreen.png"));
		buttons.put(SIZE.MEDIUM.toString() + COLOR.RED.toString(), Draw.loadImage(basePath + "mediumRed.png"));
		buttons.put(SIZE.MEDIUM.toString() + COLOR.YELLOW.toString(), Draw.loadImage(basePath + "mediumYellow.png"));
		// Load the small buttons
		buttons.put(SIZE.SMALL.toString() + COLOR.GREEN.toString(), Draw.loadImage(basePath + "smallGreen.png"));
		buttons.put(SIZE.SMALL.toString() + COLOR.RED.toString(), Draw.loadImage(basePath + "smallRed.png"));
		buttons.put(SIZE.SMALL.toString() + COLOR.YELLOW.toString(), Draw.loadImage(basePath + "smallYellow.png"));
		// Load the tiny buttons
		buttons.put(SIZE.TINY.toString() +  COLOR.GREEN.toString(), Draw.loadImage(basePath + "tinyGreen.png"));
		buttons.put(SIZE.TINY.toString() +  COLOR.RED.toString(), Draw.loadImage(basePath + "tinyRed.png"));
		buttons.put(SIZE.TINY.toString() +  COLOR.YELLOW.toString(), Draw.loadImage(basePath + "tinyYellow.png"));
	}
	
	/** The first line of text on the button */
	private String line1;
	/** The second line of text on the button */
	private String line2;
	/** The x coordinate of the button (meaning determined by {@link ORIENTATION}) */
	private int originalX;
	/** The y coordinate of the button (meaning determined by {@link ORIENTATION}) */
	private int originalY;
	/** The {@link ORIENTATION} of the button in relation to the given coordinate */
	private Draw.ORIENTATION orientation;
	/** The {@link SIZE} of the button */
	private SIZE size;
	/** The {@link COLOR} of the button */
	private COLOR color;
	/** The {@link Text} object for the first line of text on the button */
	private Text line1Text;
	/** The {@link Text} object for the second line of text on the button */
	private Text line2Text;
	
	/**
	 * Creates a button to be drawn to the screen at the given coordinates with the 
	 * given orientation, size, color, text, and on-click action.
	 * 
	 * @param line1 The first line of text on the button
	 * @param line2 The second line of text on the button
	 * @param x The x coordinate of the button (meaning determined by orientation)
	 * @param y The y coordinate of the button (meaning determined by orientation)
	 * @param orientation The {@link ORIENTATION} of the button
	 * @param size The {@link SIZE} of the button
	 * @param color The {@link COLOR} of the button
	 * @param onClick The on-click action for the button
	 */
	private Button(String line1, String line2, int x, int y, Draw.ORIENTATION orientation, SIZE size, 
			COLOR color, Consumer<Context> onClick){
		this.line1 = line1;
		this.line2 = line2;
		this.originalX = x;
		this.originalY = y;
		this.orientation = orientation;
		this.size = size;
		setWidth(size.getWidth());
		setHeight(size.getHeight());
		this.color = color;
		setOnClick(onClick);
		setOriented(false);
	}
	
	/**
	 * Constructs a {@link ButtonBuilder} to create a Button object.
	 * 
	 * @return A new ButtonBuilder
	 */
	public static ButtonBuilder builder(){
		return new ButtonBuilder();
	}
	
	/**
	 * Grabs the image from the Map of button images based on the {@link SIZE} 
	 * and {@link COLOR}.
	 * 
	 * @param size The {@link SIZE} of the button
	 * @param color The {@link COLOR} of the button
	 * @return The Image for the button of the given size and color
	 */
	private BufferedImage getButton(SIZE size, COLOR color){
		return buttons.get(size.toString() + color.toString());
	}
	
	/**
	 * Orients the button using {@link Draw#orient} and then creates the 
	 * two {@link Text} objects for the button text, orienting them and 
	 * adjusting their size to fit to the button (or 1/2 of the button in the 
	 * case of both lines being used).
	 * 
	 * @param g The Graphics to use in orienting and fitting the text to boxes
	 */
	protected void orient(Graphics g){
		// Orient the button itself
		Point newCoord = Draw.orient(originalX, originalY, getWidth(), getHeight(), orientation);
		setX((int) newCoord.getX());
		setY((int) newCoord.getY());
		
		if(line2 == null){
			// Create line1 text centered in the button
			line1Text = Text.builder()
									.text(line1)
									.x(getX() + getWidth()/2).y(getY() + getHeight()/2)
									.orientation(ORIENTATION.CENTER)
									// Fit the text to the button
									.g(g).orient(true).fitToBox(getWidth(), getHeight())
									.build();
		}else{
			// Create the lines centered in the 2 halves of the button
			line1Text = Text.builder()
									.text(line1)
									.x(getX() + getWidth()/2).y(getY() + getHeight()/4)
									.orientation(ORIENTATION.CENTER)
									// Fit the text to top 1/2 of the button
									.g(g).orient(true).fitToBox(getWidth(), getHeight()/2)
									.build();
			line2Text = Text.builder()
									.text(line2)
									.x(getX() + getWidth()/2).y(getY() + getHeight()*3/4)
									.orientation(ORIENTATION.CENTER)
									// Fit the text to bottom 1/2 of the button
									.g(g).orient(true).fitToBox(getWidth(), getHeight()/2)
									.build();
		}
		
		setOriented(true);
	}
	
	/**
	 * Draws the button image using {@link Draw#drawImage} and then the 
	 * text to go on top of it by calling the Texts' {@link Text#conditionalDraw}.
	 * 
	 * @param g The Graphics to draw using
	 */
	@Override
	public void draw(Graphics g){
		// Draw Button Base
		Draw.drawImage(g, getButton(size, color), getX(), getY(), getWidth(), getHeight());
		
		// Draw Text
		line1Text.conditionalDraw(g);
		if(line2Text != null){
			// line 2 may be null
			line2Text.conditionalDraw(g);
		}
	}
}
