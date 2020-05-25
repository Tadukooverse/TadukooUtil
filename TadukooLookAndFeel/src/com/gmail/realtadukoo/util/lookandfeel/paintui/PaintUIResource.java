package com.gmail.realtadukoo.util.lookandfeel.paintui;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.UIResource;
import java.awt.*;

// TODO: Do something with this?
public class PaintUIResource{
	
	public static ColorUIResource createColorUIResource(Color color){
		return new ColorUIResource(color);
	}
	
	public static ColorUIResource createColorUIResource(int rgb){
		return new ColorUIResource(rgb);
	}
	
	public static ColorUIResource createColorUIResource(int r, int g, int b){
		return new ColorUIResource(r, g, b);
	}
	
	public static ColorUIResource createColorUIResource(float r, float g, float b){
		return new ColorUIResource(r, g, b);
	}
}
