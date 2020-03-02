package com.gmail.realtadukoo.util.view.textinput;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.gmail.realtadukoo.util.StringUtil;
import com.gmail.realtadukoo.util.view.draw.Draw;
import com.gmail.realtadukoo.util.view.draw.Draw.ORIENTATION;
import com.gmail.realtadukoo.util.view.draw.Text;
import com.gmail.realtadukoo.util.view.draw.Text.TextBuilder;

public class TextInputPrompt extends FocusableTextInput{
	
	/**
	 * Builder for constructing a {@link TextInputPrompt} object.
	 * <br><br>
	 * <b>MUST</b> specify the following:
	 * <ul>
	 * <li>x coordinate (of prompt)</li>
	 * <li>y coordinate (of prompt)</li>
	 * <li>width</li>
	 * <li>height</li>
	 * </ul>
	 * Defaults are:
	 * <ul>
	 * <li>orientation - {@link ORIENTATION#TOP_LEFT TOP_LEFT}</li>
	 * <li>text x coordinate (within box, as in 0 is the left edge of the box) - 0</li>
	 * <li>text y coordinate (within box, as in 0 is the top edge of the box) - box height/2</li>
	 * <li>text orientation - {@link ORIENTATION#LEFT_CENTER LEFT_CENTER}</li>
	 * <li>fontName - The default in {@link Text}</li>
	 * <li>fontStyle - The default in {@link Text}</li>
	 * <li>text color - The default in {@link Text}</li>
	 * </ul>
	 * 
	 * @author Logan Ferree (Tadukoo)
	 * @version 0.1-Alpha-SNAPSHOT
	 */
	public static class TextInputPromptBuilder{
		/** The left-most edge of the TextInputPrompt - must be specified */
		private int x = -1;
		/** The top-most edge of the TextInputPrompt - must be specified */
		private int y = -1;
		/** The width of the TextInputPrompt - must be specified */
		private int width = -1;
		/** The height of the TextInputPrompt - must be specified */
		private int height = -1;
		/** The orientation of the TextInputPrompt - default TOP_LEFT */
		private ORIENTATION orientation = ORIENTATION.TOP_LEFT;
		/** The x coordinate of the Text within the prompt box - default 0 */
		private int textX = 0;
		/** The y coordinate of the Text within the prompt box - default height/2 */
		private int textY = -1;
		/** The orientation of the Text - default LEFT_CENTER */
		private ORIENTATION textOrientation = ORIENTATION.LEFT_CENTER;
		/** The font name of the Text - default to {@link Text Text's} default */
		private String fontName = null;
		/** The font style of the Text - default to {@link Text Text's} default */
		private int fontStyle = -1;
		/** The {@link Color} of the Text - default to {@link Text Text's} default */
		private Color textColor = null;
		
		// Cannot create TextInputPromptBuilder outside of TextInputPrompt
		private TextInputPromptBuilder(){ }
		
		/**
		 * Sets the x coordinate to be used on the TextInputPrompt object
		 * 
		 * @param x The x coordinate for the TextInputPrompt object
		 */
		public TextInputPromptBuilder x(int x){
			this.x = x;
			return this;
		}
		
		/**
		 * Sets the y coordinate to be used on the TextInputPrompt object
		 * 
		 * @param y The y coordinate for the TextInputPrompt object
		 */
		public TextInputPromptBuilder y(int y){
			this.y = y;
			return this;
		}
		
		/**
		 * Sets the width to be used on the TextInputPrompt object
		 * 
		 * @param width The width for the TextInputPrompt object
		 */
		public TextInputPromptBuilder width(int width){
			this.width = width;
			return this;
		}
		
		/**
		 * Sets the height to be used on the TextInputPrompt object
		 * 
		 * @param height The height for the TextInputPrompt object
		 */
		public TextInputPromptBuilder height(int height){
			this.height = height;
			return this;
		}
		
		/**
		 * Sets the orientation to be used on the TextInputPrompt object
		 * 
		 * @param orientation The orientation for the TextInputPrompt object
		 */
		public TextInputPromptBuilder orientation(ORIENTATION orientation){
			this.orientation = orientation;
			return this;
		}
		
		/**
		 * Sets the x coordinate of the Text within the prompt box
		 * <br><br>
		 * 0 = the left edge of the prompt box
		 * 
		 * @param textX The x coordinate of the Text within the prompt box
		 */
		public TextInputPromptBuilder textX(int textX){
			this.textX = textX;
			return this;
		}
		
		/**
		 * Sets the y coordinate of the Text within the prompt box
		 * <br><br>
		 * 0 = the top edge of the prompt box
		 * 
		 * @param textY The y coordinate of the Text within the prompt box
		 */
		public TextInputPromptBuilder textY(int textY){
			this.textY = textY;
			return this;
		}
		
		/**
		 * Sets the orientation to be used on the Text
		 * 
		 * @param textOrientation The orientation for the Text
		 */
		public TextInputPromptBuilder textOrientation(ORIENTATION textOrientation){
			this.textOrientation = textOrientation;
			return this;
		}
		
		/**
		 * Sets the font name of the {@link Font} to be used on the Text object
		 * 
		 * @param fontName The font name of the {@link Font} for the Text object
		 */
		public TextInputPromptBuilder fontName(String fontName){
			this.fontName = fontName;
			return this;
		}
		
		/**
		 * Sets the font style of the {@link Font} to be used on the Text object
		 * 
		 * @param fontStyle The font style of the {@link Font} for the Text object
		 */
		public TextInputPromptBuilder fontStyle(int fontStyle){
			this.fontStyle = fontStyle;
			return this;
		}
		
		/**
		 * Sets the Color to be used for the Text object
		 * 
		 * @param textColor The Color to be used for the Text object
		 */
		public TextInputPromptBuilder textColor(Color textColor){
			this.textColor = textColor;
			return this;
		}
		
		/**
		 * Checks for errors with the current settings for the TextInputPrompt object
		 */
		private void checkForErrors(){
			List<String> errors = new ArrayList<String>();
			
			if(x == -1){
				errors.add("Must specify x coordinate");
			}
			if(y == -1){
				errors.add("Must specify y coordinate");
			}
			if(width == -1){
				errors.add("Must specify width");
			}
			if(height == -1){
				errors.add("Must specify height");
			}
			
			if(!errors.isEmpty()){
				throw new IllegalStateException("Failed to create Text Input Prompt object: " + errors.toString());
			}
		}
		
		/**
		 * Builds the TextInputPrompt object based on the input data if it's valid.
		 * 
		 * @return The built TextInputPrompt object
		 */
		public TextInputPrompt build(){
			// Check for any issues
			checkForErrors();
			
			// Set the text y coordinate to the default if none was set
			if(textY == -1){
				textY = height/2;
			}
			
			// Actually create the Text Input Prompt
			return new TextInputPrompt(x, y, width, height, orientation, 
					textX, textY, textOrientation, fontName, fontStyle, textColor);
		}
	}
	
	/** The x coordinate of the text input prompt (meaning determined by {@link ORIENTATION}) */
	private int originalX;
	/** The y coordinate of the text input prompt (meaning determined by {@link ORIENTATION}) */
	private int originalY;
	/** The {@link ORIENTATION} of the text input prompt in relation to the given coordinate */
	private ORIENTATION orientation;
	/** The x coordinate of the Text object (within the prompt box, meaning determined by {@link ORIENTATION}) */
	private int textX;
	/** The y coordinate of the Text object (within the prompt box, meaning determined by {@link ORIENTATION}) */
	private int textY;
	/** The {@link ORIENTATION} of the Text object */
	private ORIENTATION textOrientation;
	/** The {@link Text} object for the text in the prompt */
	private Text text;
	/** The font name of the {@link Font} for the Text object */
	private String fontName;
	/** The font style of the {@link Font} for the Text object */
	private int fontStyle;
	/** The {@link Color} for the Text object */
	private Color textColor;
	
	/**
	 * Creates a text input prompt to be drawn to the screen at the given coordinates with the 
	 * given orientation and dimensions.
	 * 
	 * @param x The x coordinate of the text input prompt (meaning determined by orientation)
	 * @param y The y coordinate of the text input prompt (meaning determined by orientation)
	 * @param width The width of the text input prompt
	 * @param height The height of the text input prompt
	 * @param orientation The {@link ORIENTATION} of the text input prompt
	 * @param textX The x coordinate of the Text object (within the prompt box, meaning determined by {@link ORIENTATION})
	 * @param textY The y coordinate of the Text object (within the prompt box, meaning determined by {@link ORIENTATION})
	 * @param textOrientation The {@link ORIENTATION} of the Text object
	 * @param fontName The font name of the {@link Font} for the Text object
	 * @param fontStyle The font style of the {@link Font} for the Text object
	 * @param textColor The {@link Color} of the Text object
	 */
	private TextInputPrompt(int originalX, int originalY, int width, int height, ORIENTATION orientation,
			int textX, int textY, ORIENTATION textOrientation, String fontName, int fontStyle, Color textColor){
		this.originalX = originalX;
		this.originalY = originalY;
		setWidth(width);
		setHeight(height);
		this.orientation = orientation;
		setOriented(false);
		this.textX = textX;
		this.textY = textY;
		this.textOrientation = textOrientation;
		this.fontName = fontName;
		this.fontStyle = fontStyle;
		this.textColor = textColor;
	}
	
	/**
	 * Constructs a {@link TextInputPromptBuilder} to create a TextInputPrompt object.
	 * 
	 * @return A new TextInputPromptBuilder
	 */
	public static TextInputPromptBuilder builder(){
		return new TextInputPromptBuilder();
	}
	
	/**
	 * Orients the prompt box using {@link Draw#orient} and then creates the 
	 * {@link Text} object for the prompt text, orienting it and adjusting 
	 * its size to fit to the prompt box.
	 * 
	 * @param g The Graphics to use in orienting and fitting the text to the box
	 */
	@Override
	protected void orient(Graphics g){
		// Orient the prompt itself
		Point newCoord = Draw.orient(originalX, originalY, getWidth(), getHeight(), orientation);
		setX((int) newCoord.getX());
		setY((int) newCoord.getY());
		
		// Create and orient the Text
		TextBuilder textBuilder = Text.builder()
										.text(getText())
										// Set coordinates relative to box x and y
										.x(getX() + textX).y(getY() + textY)
										.orientation(textOrientation)
										// Fit the text to the prompt box
										.g(g).orient(true).fitToBox(getWidth(), getHeight());
		
		// Set font name if we specified it
		if(StringUtil.isNotBlank(fontName)){
			textBuilder.fontName(fontName);
		}
		
		// Set font style if we specified it
		if(fontStyle != -1){
			textBuilder.fontStyle(fontStyle);
		}
		
		// Set text color if we specified it
		if(textColor != null){
			textBuilder.color(textColor);
		}
		
		// Actually build the Text
		text = textBuilder.build();
		
		setOriented(true);
	}
	
	/**
	 * Draws the prompt box image using {@link Draw#drawImage} and then the 
	 * text to go on top of it by calling the Text's {@link Text#conditionalDraw}.
	 * 
	 * @param g The Graphics to draw using
	 */
	@Override
	protected void draw(Graphics g){
		// TODO: Change to drawing the prompt box
		Draw.drawImage(g, Draw.loadImage("/img/button/normalGreen.png"), getX(), getY(), getWidth(), getHeight());
		
		// Set the text string and draw it on to of the prompt box
		text.changeText(getText());
		text.conditionalDraw(g);
	}
}
