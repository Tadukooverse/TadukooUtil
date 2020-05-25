package com.github.tadukoo.util.lookandfeel.paintui;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ColorPaintUIResource extends ColorUIResource implements PaintUIResource{
	
	public ColorPaintUIResource(ColorUIResource colorUIResource){
		super(colorUIResource);
	}
	
	public ColorPaintUIResource(Color color){
		super(color);
	}
	
	public ColorPaintUIResource(int rgb){
		super(rgb);
	}
	
	public ColorPaintUIResource(int r, int g, int b){
		super(r, g, b);
	}
	
	public ColorPaintUIResource(float r, float g, float b){
		super(r, g, b);
	}
	
	public Paint getPaint(Dimension size){
		return this;
	}
	
	public ColorUIResource getColorUIResource(){
		return this;
	}
	
	public List<Object> getMetalGradientList(){
		return Arrays.asList(
				new Object[]{0.33, 0,
							this, this, this});
	}
}
