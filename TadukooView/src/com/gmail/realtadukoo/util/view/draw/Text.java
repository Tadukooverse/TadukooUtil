package com.gmail.realtadukoo.util.view.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.gmail.realtadukoo.util.view.draw.Draw.ORIENTATION;

/**
 * Drawable class used for drawing text to the screen.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class Text extends Drawable{
	
	/**
	 * Builder for constructing a {@link Text} object.
	 * <br><br>
	 * <b>MUST</b> specify the following:
	 * <ul>
	 * <li>text</li>
	 * <li>x coordinate</li>
	 * <li>y coordinate</li>
	 * </ul>
	 * Defaults are:
	 * <ul>
	 * <li>orientation - {@link ORIENTATION#LEFT_CENTER LEFT_CENTER}</li>
	 * <li>fontName - Calibri</li>
	 * <li>fontStyle - {@link Font#PLAIN}</li>
	 * <li>fontSize - 25</li>
	 * <li>color - {@link Color#BLACK}</li>
	 * <li>Graphics - null - must be set if orienting or fitting to box on 
	 * initialization</li>
	 * <li>orient - false - if Graphics is set, can orient on creation, 
	 * otherwise will be oriented before drawing the first time.</li>
	 * <li>width + height - empty - can be set to fit the Text to a box 
	 * if the Graphics is set.</li>
	 * </ul>
	 * 
	 * @author Logan Ferree (Tadukoo)
	 * @version 0.1-Alpha-SNAPSHOT
	 */
	public static class TextBuilder{
		/** The text for the Text - must be specified */
		private String text = null;
		/** The x coordinate for the Text - must be specified */
		private int x = -1;
		/** The y coordinate for the Text - must be specified */
		private int y = -1;
		/** The orientation of the Text - default CENTER */
		private ORIENTATION orientation = ORIENTATION.LEFT_CENTER;
		/** The font name of the Text - default Calibri */
		private String fontName = "Calibri";
		/** The font style of the Text - default PLAIN */
		private int fontStyle = Font.PLAIN;
		/** The font size of the Text - default 25 */
		private int fontSize = 25;
		/** The Color of the Text - default BLACK */
		private Color color = Color.BLACK;
		/** Graphics to use if orienting or fitting the Text object to a box in the build */
		private Graphics g = null;
		/** Whether or not to orient the Text object immediately */
		private boolean orient = false;
		/** width to use for fitting to a box */
		private int width = -1;
		/** height to use for fitting to a box */
		private int height = -1;
		
		// Cannot create TextBuilder outside of Text
		private TextBuilder(){ }
		
		/**
		 * Set the text to be used on the Text object
		 * 
		 * @param text The text for the Text object
		 */
		public TextBuilder text(String text){
			this.text = text;
			return this;
		}
		
		/**
		 * Sets the x coordinate to be used on the Text object
		 * 
		 * @param x The x coordinate for the Text object
		 */
		public TextBuilder x(int x){
			this.x = x;
			return this;
		}
		
		/**
		 * Sets the y coordinate to be used on the Text object
		 * 
		 * @param y The y coordinate for the Text object
		 */
		public TextBuilder y(int y){
			this.y = y;
			return this;
		}
		
		/**
		 * Sets the orientation to be used on the Text object
		 * 
		 * @param orientation The orientation for the Text object
		 */
		public TextBuilder orientation(ORIENTATION orientation){
			this.orientation = orientation;
			return this;
		}
		
		/**
		 * Sets the font name of the {@link Font} to be used on the Text object
		 * 
		 * @param fontName The font name of the {@link Font} for the Text object
		 */
		public TextBuilder fontName(String fontName){
			this.fontName = fontName;
			return this;
		}
		
		/**
		 * Sets the font style of the {@link Font} to be used on the Text object
		 * 
		 * @param fontStyle The font style of the {@link Font} for the Text object
		 */
		public TextBuilder fontStyle(int fontStyle){
			this.fontStyle = fontStyle;
			return this;
		}
		
		/**
		 * Sets the font size of the {@link Font} to be used on the Text object
		 * 
		 * @param fontSize The font size of the {@link Font} for the Text object
		 */
		public TextBuilder fontSize(int fontSize){
			this.fontSize = fontSize;
			return this;
		}
		
		/**
		 * Sets the Color to be used for the Text object
		 * 
		 * @param color The Color to be used for the Text object
		 */
		public TextBuilder color(Color color){
			this.color = color;
			return this;
		}
		
		/**
		 * Sets Graphics to use to orient this object. To be used by objects using 
		 * Text as part of their more complex object during orientation.
		 * 
		 * @param g The Graphics to use in orienting
		 */
		public TextBuilder g(Graphics g){
			this.g = g;
			return this;
		}
		
		/**
		 * Can set orient to true to orient the Text on creation - otherwise will be 
		 * oriented before drawing it for the first time.
		 * <br><br>
		 * <b>Note:</b> If orienting, must specify the Graphics using {@link #g(Graphics)}.
		 * 
		 * @param orient Whether or not to orient the Text on creation
		 */
		public TextBuilder orient(boolean orient){
			this.orient = orient;
			return this;
		}
		
		/**
		 * Sets a width and height to use to fit the Text to a box (altering font size) 
		 * during creation of the Text.
		 * <br><br>
		 * <b>Note:</b> If fitting to a box, must specify the Graphics using {@link #g(Graphics)}.
		 * 
		 * @param width The width of the box to fit the Text in
		 * @param height The height of the box to fit the Text in
		 */
		public TextBuilder fitToBox(int width, int height){
			this.width = width;
			this.height = height;
			return this;
		}
		
		/**
		 * Checks for errors with the current settings for the Text object
		 */
		private void checkForErrors(){
			List<String> errors = new ArrayList<>();
			// Text is required
			if(text == null){
				errors.add("Text must be specified");
			}
			
			// x and y coordinates required
			if(x == -1){
				errors.add("x must be specified");
			}
			if(y == -1){
				errors.add("y must be specified");
			}
			
			// If orienting, need Graphics
			if(orient && g == null){
				errors.add("Cannot orient without Graphics");
			}
			
			// If fitting to box, need Graphics
			if(width != -1 && height != -1 && g == null){
				errors.add("Cannot fit to box without Graphics");
			}
			
			if(!errors.isEmpty()){
				throw new IllegalStateException("Failed to create Text object: " + errors.toString());
			}
		}
		
		/**
		 * Builds the Text object based on the input data if it's 
		 * valid.
		 * 
		 * @return The built Text object
		 */
		public Text build(){
			checkForErrors();
			
			// Fit the text to a box if specified
			if(width != -1 && height != -1){
				fontSize = Draw.fontSizeFitToBox(g, new Font(fontName, fontStyle, fontSize), text, width, height);
			}
			
			// Created the Text object
			Text newText = new Text(new Font(fontName, fontStyle, fontSize), color, text, x, y, orientation);
			
			// Orient the Text if specified
			if(orient){
				newText.orient(g);
			}
			return newText;
		}
	}
	
	/** The {@link Font} to use */
	private final Font font;
	/** The {@link Color} to use */
	private final Color color;
	/** The text to draw to the screen */
	private String text;
	/** The x coordinate of the text (meaning determined by {@link ORIENTATION}) */
	private final int originalX;
	/** The y coordinate of the text (meaning determined by {@link ORIENTATION}) */
	private final int originalY;
	/** The {@link ORIENTATION} of the text in relation to the given coordinate */
	private final ORIENTATION orientation;
	/** The actual x coordinate of the text, after being oriented */
	private int x;
	/** The actual y coordinate of the text, after being oriented */
	private int y;
	
	/**
	 * Constructs a {@link TextBuilder} to create a Text object.
	 * 
	 * @return A new TextBuilder
	 */
	public static TextBuilder builder(){
		return new TextBuilder();
	}
	
	/**
	 * Create a text object to be drawn to the screen at the given location using 
	 * the given {@link Color}, {@link ORIENTATION} and {@link Font} information.
	 * 
	 * @param font The {@link Font} to use
	 * @param color The {@link Color} to use
	 * @param text The text to draw to the screen
	 * @param x The x coordinate of the text
	 * @param y The y coordinate of the text
	 * @param orientation The {@link ORIENTATION} of the text in relation to the given coordinate
	 */
	private Text(Font font, Color color, String text, int x, int y, ORIENTATION orientation){
		this.font = font;
		this.color = color;
		this.text = text;
		originalX = x;
		originalY = y;
		this.orientation = orientation;
		setOriented(false);
	}
	
	/**
	 * Change the text to draw to the screen.
	 * <br>
	 * Will set oriented to false so that it can be re-oriented for the 
	 * new text.
	 * 
	 * @param text The text to draw to the screen
	 */
	public void changeText(String text){
		this.text = text;
		setOriented(false);
	}
	
	/**
	 * Calls {@link Draw#orientText} to orient the text around its original 
	 * coordinates.
	 * 
	 * @param g The Graphics to use in orienting
	 */
	@Override
	protected void orient(Graphics g){
		Point newPoint = Draw.orientText(g, font, text, originalX, originalY, orientation);
		x = (int) newPoint.getX();
		y = (int) newPoint.getY();
		setOriented(true);
	}
	
	/**
	 * Draws the text to the screen using its coordinates, orientation, font, and color.
	 * <br><br>
	 * Calls {@link Draw#drawText} to actually draw the text.
	 * 
	 * @param g The Graphics to draw using
	 */
	@Override
	protected void draw(Graphics g){
		// Draw the text to the screen
		Draw.drawText(g, text, x, y, font, color);
	}
}
