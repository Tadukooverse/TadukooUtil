package com.github.tadukoo.util.view.paint;

import java.awt.*;
import java.awt.color.ColorSpace;

/**
 * Sizable Color is an extension of {@link Color} that implements {@link SizablePaint}. It simply returns the
 * {@link Color} in {@link #getPaint(Dimension)}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public class SizableColor extends Color implements SizablePaint{
	
	/** {@inheritDoc} */
	public SizableColor(int r, int g, int b){
		super(r, g, b);
	}
	
	/** {@inheritDoc} */
	public SizableColor(int r, int g, int b, int a){
		super(r, g, b, a);
	}
	
	/** {@inheritDoc} */
	public SizableColor(int rgb){
		super(rgb);
	}
	
	/** {@inheritDoc} */
	public SizableColor(int rgba, boolean hasalpha){
		super(rgba, hasalpha);
	}
	
	/** {@inheritDoc} */
	public SizableColor(float r, float g, float b){
		super(r, g, b);
	}
	
	/** {@inheritDoc} */
	public SizableColor(float r, float g, float b, float a){
		super(r, g, b, a);
	}
	
	/** {@inheritDoc} */
	public SizableColor(ColorSpace cspace, float[] components, float alpha){
		super(cspace, components, alpha);
	}
	
	public SizableColor(Color color){
		this(color.getRGB());
	}
	
	/** {@inheritDoc} */
	@Override
	public Paint getPaint(Dimension size){
		return this;
	}
}