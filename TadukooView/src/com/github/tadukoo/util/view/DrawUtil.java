package com.github.tadukoo.util.view;

import java.awt.*;

/**
 * This class provides utility methods for drawing things to the screen.
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha
 */
public class DrawUtil{
	
	// Not allowed to create a DrawUtil
	private DrawUtil(){ }
	
	/**
	 * Draws the given text aligned to the given coordinates according to the orientation.
	 *
	 * @param g The Graphics to use to draw
	 * @param text The text to be drawn to the screen
	 * @param x The x position to align to
	 * @param y The y position to align to
	 * @param orientation The orientation to use in aligning the text
	 */
	public static void drawText(Graphics g, String text, int x, int y, Orientation orientation){
		FontMetrics metrics = g.getFontMetrics();
		
		// Calculate the correct x for alignment purposes
		int realX = switch(orientation.horizontal()){
			case LEFT -> x;
			case CENTER -> x - metrics.stringWidth(text)/2;
			case RIGHT -> x - metrics.stringWidth(text);
		};
		
		// Calculate the correct y for alignment purposes
		int realY = switch(orientation.vertical()){
			case TOP -> y + metrics.getAscent();
			case CENTER -> y - ((metrics.getAscent() + metrics.getDescent())/2) + metrics.getAscent();
			case BOTTOM -> y - metrics.getHeight() + metrics.getAscent() + metrics.getDescent();
		};
		
		// Draw the actual string
		g.drawString(text, realX, realY);
	}
}
