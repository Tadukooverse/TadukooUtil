package com.github.tadukoo.util.lookandfeel.paintui;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * ColorPaintUIResource is a {@link PaintUIResource} to be used in
 * {@link com.github.tadukoo.util.lookandfeel.TadukooLookAndFeel} for custom paints if you want a solid color.
 * Using this class allows supporting other component UI classes that may not allow a solid color. It extends
 * {@link ColorUIResource}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class ColorPaintUIResource extends ColorUIResource implements PaintUIResource{
	
	/**
	 * Create a ColorPaintUIResource using a {@link ColorUIResource}.
	 *
	 * @param colorUIResource The {@link ColorUIResource} to use for this ColorPaintUIResource
	 */
	public ColorPaintUIResource(ColorUIResource colorUIResource){
		super(colorUIResource);
	}
	
	/**
	 * Create a ColorPaintUIResource using a {@link Color}.
	 *
	 * @param color The {@link Color} to use for this ColorPaintUIResource
	 */
	public ColorPaintUIResource(Color color){
		super(color);
	}
	
	/**
	 * Create a ColorPaintUIResource using the combined RGB components.
	 *
	 * @param rgb The combined RGB components to use for this ColorPaintUIResource
	 */
	public ColorPaintUIResource(int rgb){
		super(rgb);
	}
	
	/**
	 * Create a ColorPaintUIResource using the separate RGB components.
	 *
	 * @param r The red component of the color
	 * @param g The green component of the color
	 * @param b The blue component of the color
	 */
	public ColorPaintUIResource(int r, int g, int b){
		super(r, g, b);
	}
	
	/**
	 * Create a ColorPaintUIResource using the separate RGB components as floats.
	 *
	 * @param r The red component of the color
	 * @param g The green component of the color
	 * @param b The blue component of the color
	 */
	public ColorPaintUIResource(float r, float g, float b){
		super(r, g, b);
	}
	
	/**
	 * Returns this as the Paint, since it's an extension of {@link Color}.
	 *
	 * @param size The Dimensions of the object to be painted
	 * @return this
	 */
	@Override
	public Paint getPaint(Dimension size){
		return this;
	}
	
	/**
	 * @return this
	 */
	@Override
	public ColorUIResource getColorUIResource(){
		return this;
	}
	
	/**
	 * @return A {@code List<Object>} for use in Metal gradients that will produce a solid color
	 */
	@Override
	public List<Object> getMetalGradientList(){
		return Arrays.asList(
				new Object[]{0.33, 0,
							this, this, this});
	}
}
