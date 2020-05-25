package com.gmail.realtadukoo.util.lookandfeel;

import com.gmail.realtadukoo.util.lookandfeel.paintui.PaintUIResource;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class TadukooLookAndFeel extends MetalLookAndFeel{
	private final TadukooTheme theme;
	
	public TadukooLookAndFeel(){
		theme = TadukooThemeFactory.createDefaultTheme();
	}
	
	public TadukooLookAndFeel(TadukooTheme theme){
		this.theme = theme;
	}
	
	/**
	 * @return The name of this look and feel. This returns "Tadukoo"
	 */
	@Override
	public String getName(){
		return "Tadukoo";
	}
	
	/**
	 * @return The identifier of this look and feel. This returns "Tadukoo"
	 */
	@Override
	public String getID(){
		return "Tadukoo";
	}
	
	/**
	 * @return A short description of this look and feel. This returns "The Tadukoo Look and Feel"
	 */
	@Override
	public String getDescription(){
		return "The Tadukoo Look and Feel";
	}
	
	/**
	 * @return {@code false}; TadukooLookAndFeel is not a native look and feel
	 */
	@Override
	public boolean isNativeLookAndFeel(){
		return false;
	}
	
	/**
	 * @return {@code true}; TadukooLookAndFeel can be run on any platform
	 */
	@Override
	public boolean isSupportedLookAndFeel(){
		return true;
	}
	
	@Override
	protected void initClassDefaults(UIDefaults table){
		super.initClassDefaults(table);
		
		table.put("ButtonUI", theme.getButtonUI());
	}
	
	@Override
	protected void initComponentDefaults(UIDefaults table){
		super.initComponentDefaults(table);
		
		PaintUIResource buttonFocusPaint = theme.getButtonFocusPaint();
		PaintUIResource buttonSelectPaint = theme.getButtonSelectPaint();
		
		Object[] defaults = new Object[]{
				"Button.border", theme.getButtonBorder(),
				"Button.focus", buttonFocusPaint.getColorUIResource(),
				"Button.focus.paint", buttonFocusPaint,
				"Button.select", buttonSelectPaint.getColorUIResource(),
				"Button.select.paint", buttonSelectPaint,
				"Button.font", theme.getButtonFont()
		};
		
		table.putDefaults(defaults);
	}
}
