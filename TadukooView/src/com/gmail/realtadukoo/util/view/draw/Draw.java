package com.gmail.realtadukoo.util.view.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A util class for drawing objects to the screen.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class Draw{
	
	// Can't instantiate Draw
	private Draw(){ }
	
	/**
	 * Represents the way to orient an object in relation to given coordinates.
	 * 
	 * @author Logan Ferree (Tadukoo)
	 * @version 0.1-Alpha-SNAPSHOT
	 */
	public enum ORIENTATION{
		/** Align the object to the top, centered horizontally */
		TOP_CENTER(VERTICAL.TOP, HORIZONTAL.CENTER),
		/** Left align the object, centered vertically */
		LEFT_CENTER(VERTICAL.CENTER, HORIZONTAL.LEFT), 
		/** Center the object (horizontally and vertically) on the specified coordinate */
		CENTER(VERTICAL.CENTER, HORIZONTAL.CENTER), 
		/** Right align the object, centered vertically */
		RIGHT_CENTER(VERTICAL.CENTER, HORIZONTAL.RIGHT),
		/** Align the object to the bottom, centered horizontally */
		BOTTOM_CENTER(VERTICAL.BOTTOM, HORIZONTAL.CENTER),
		/** Align the object to the top left */
		TOP_LEFT(VERTICAL.TOP, HORIZONTAL.LEFT),
		/** Align the object to the top right */
		TOP_RIGHT(VERTICAL.TOP, HORIZONTAL.RIGHT),
		/** Align the object to the bottom left */
		BOTTOM_LEFT(VERTICAL.BOTTOM, HORIZONTAL.LEFT),
		/** Align the object to the bottom right */
		BOTTOM_RIGHT(VERTICAL.BOTTOM, HORIZONTAL.RIGHT);
		
		private enum VERTICAL{
			TOP, CENTER, BOTTOM;
		}
		
		private enum HORIZONTAL{
			LEFT, CENTER, RIGHT;
		}
		
		private VERTICAL ver;
		private HORIZONTAL hor;
		
		private ORIENTATION(VERTICAL ver, HORIZONTAL hor){
			this.ver = ver;
			this.hor = hor;
		}
		
		public VERTICAL vertical(){
			return ver;
		}
		
		public HORIZONTAL horizontal(){
			return hor;
		}
	}
	
	public static BufferedImage loadImage(String file){
		try{
			return ImageIO.read(Draw.class.getResourceAsStream(file));
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static int fontSizeFitToBox(Graphics g, Font font, String text, int width, int height){
		FontMetrics metrics = g.getFontMetrics(font);
		int stringWidth = metrics.stringWidth(text);
		int stringHeight = metrics.getHeight();
		float widthFitSize = (float) width / (float) stringWidth * font.getSize();
		float heightFitSize = (float) height / (float) stringHeight * font.getSize();
		return (int) Math.min(widthFitSize, heightFitSize);
	}
	
	public static Point orient(int x, int y, int width, int height, ORIENTATION orientation){
		int newX = x, newY = y;
		
		switch(orientation.vertical()){
			case TOP:
				break;
			case CENTER:
				newY = y - height/2;
				break;
			case BOTTOM:
				newY = y - height;
				break;
		}
		
		switch(orientation.horizontal()){
			case LEFT:
				break;
			case CENTER:
				newX = x - width/2;
				break;
			case RIGHT:
				newX = x - width;
				break;
		}
		
		return new Point(newX, newY);
	}
	
	public static Point orientText(Graphics g, Font font, String text, int x, int y, ORIENTATION orientation){
		// Get font metrics to calculate sizes for certain orientations
		FontMetrics metric = g.getFontMetrics(font);
		
		// Add ascent to y coordinate
		int newY = y + metric.getAscent();
		// If orientation is bottom, need to add descent to y coordinate
		if(orientation.vertical() == ORIENTATION.VERTICAL.BOTTOM){
			newY += metric.getDescent();
		}
		
		// Send info to orient method to handle actual orientation calculations
		return orient(x, newY, metric.stringWidth(text), metric.getHeight(), orientation);
	}
	
	public static void drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height){
		g.drawImage(image, x, y, width, height, null);
	}
	
	public static void drawText(Graphics g, String text, int x, int y, Font font, Color color){
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x, y);
	}
}
