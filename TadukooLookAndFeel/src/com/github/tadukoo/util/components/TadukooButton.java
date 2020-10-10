package com.github.tadukoo.util.components;

import com.github.tadukoo.util.view.ShapeFunction;

import javax.swing.*;

public class TadukooButton extends JButton implements Shaped{
	
	public static class TadukooButtonBuilder{
		private String text;
		private Icon icon;
		private Action action;
		private ShapeFunction shapeFunc = null;
		
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
		
		public TadukooButtonBuilder shapeFunc(ShapeFunction shapeFunc){
			this.shapeFunc = shapeFunc;
			return this;
		}
		
		public TadukooButton build(){
			// TODO: Check action (non-null changes text + potentially icon)
			return new TadukooButton(text, icon, action, shapeFunc);
		}
	}
	
	private ShapeFunction shapeFunc;
	
	private TadukooButton(String text, Icon icon, Action action, ShapeFunction shapeFunc){
		super(text, icon);
		if(action != null){
			setAction(action);
		}
		this.shapeFunc = shapeFunc;
	}
	
	public static TadukooButtonBuilder builder(){
		return new TadukooButtonBuilder();
	}
	
	@Override
	public ShapeFunction getShapeFunc(){
		return shapeFunc;
	}
	
	@Override
	public void setShapeFunc(ShapeFunction shapeFunc){
		this.shapeFunc = shapeFunc;
	}
}
