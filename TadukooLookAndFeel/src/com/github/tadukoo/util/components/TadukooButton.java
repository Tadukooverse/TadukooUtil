package com.github.tadukoo.util.components;

import com.github.tadukoo.util.view.shapes.ShapeInfo;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;

public class TadukooButton extends JButton implements Shaped{
	
	public static class TadukooButtonBuilder{
		private String text;
		private Icon icon;
		private Action action;
		private ShapeInfo shapeInfo = null;
		
		// Can't create outside of Tadukoo Button
		private TadukooButtonBuilder(){ }
		
		public TadukooButtonBuilder text(String text){
			this.text = text;
			return this;
		}
		
		public TadukooButtonBuilder icon(Icon icon){
			this.icon = icon;
			return this;
		}
		
		public TadukooButtonBuilder action(Action action){
			this.action = action;
			return this;
		}
		
		public TadukooButtonBuilder shapeInfo(ShapeInfo shapeInfo){
			this.shapeInfo = shapeInfo;
			return this;
		}
		
		public TadukooButton build(){
			// TODO: Check action (non-null changes text + potentially icon)
			return new TadukooButton(text, icon, action, shapeInfo);
		}
	}
	
	private ShapeInfo shapeInfo;
	
	private TadukooButton(String text, Icon icon, Action action, ShapeInfo shapeInfo){
		super(text, icon);
		if(action != null){
			setAction(action);
		}
		// We do it this way because in the parent constructor, this is set via installDefaults in the Tadukoo button UI
		if(shapeInfo != null){
			setShapeInfo(shapeInfo);
		}
	}
	
	public static TadukooButtonBuilder builder(){
		return new TadukooButtonBuilder();
	}
	
	@Override
	public ShapeInfo getShapeInfo(){
		return shapeInfo;
	}
	
	@Override
	public void setShapeInfo(ShapeInfo shapeInfo){
		this.shapeInfo = shapeInfo;
	}
	
	@Override
	public Insets getInsets(){
		Insets insets;
		if(getBorder() != null || shapeInfo != null){
			insets = new Insets(0, 0, 0, 0);
			
			// Use border insets if we have one
			if(getBorder() != null){
				insets = getBorder().getBorderInsets(this);
			}
			
			// Use Shape insets if we have any
			if(shapeInfo != null){
				Insets shapeInsets = shapeInfo.getShapeInsetsFunc().apply(getX(), getY(), getWidth(), getHeight());
				insets.set(insets.top + shapeInsets.top, insets.left + shapeInsets.left,
						insets.bottom + shapeInsets.bottom, insets.right + shapeInsets.right);
			}
		}else{
			insets = super.getInsets();
		}
		return insets;
	}
	
	public Insets getInsets(Insets insets){
		if(insets == null){
			insets = new Insets(0, 0, 0, 0);
		}else{
			insets.left = insets.top = insets.right = insets.bottom = 0;
		}
		
		Border border = getBorder();
		if(border != null) {
			if(border instanceof AbstractBorder){
				insets = ((AbstractBorder)border).getBorderInsets(this, insets);
			}else{
				insets = border.getBorderInsets(this);
			}
		}
		
		if(shapeInfo != null){
			Insets shapeInsets = shapeInfo.getShapeInsetsFunc().apply(getX(), getY(), getWidth(), getHeight());
			insets.set(insets.top + shapeInsets.top, insets.left + shapeInsets.left,
					insets.bottom + shapeInsets.bottom, insets.right + shapeInsets.right);
		}
		
		return insets;
	}
}
