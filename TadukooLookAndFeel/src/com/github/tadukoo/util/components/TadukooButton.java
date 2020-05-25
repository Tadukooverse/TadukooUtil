package com.github.tadukoo.util.components;

import javax.swing.*;

public class TadukooButton extends JButton implements TadukooShape{
	
	public static class TadukooButtonBuilder{
		private String text;
		private Icon icon;
		private Action action;
		
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
		
		public TadukooButton build(){
			// TODO: Check action (non-null changes text + potentially icon)
			return new TadukooButton(text, icon, action);
		}
	}
	
	private TadukooButton(String text, Icon icon, Action action){
		super(text, icon);
		if(action != null){
			setAction(action);
		}
	}
	
	public static TadukooButtonBuilder builder(){
		return new TadukooButtonBuilder();
	}
}
