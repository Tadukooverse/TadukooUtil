package com.github.tadukoo.util.lookandfeel;

import com.github.tadukoo.util.lookandfeel.paintui.PaintUIResource;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * Tadukoo Look & Feel is a Look & Feel currently extending {@link MetalLookAndFeel} that allows for easier
 * customization of the Look & Feel. Through the use of specifying a {@link TadukooTheme}, you can customize
 * paints, fonts, shapes, and borders of components, and even swap out the Component UI classes themselves if
 * that's not custom enough for you.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TadukooLookAndFeel extends MetalLookAndFeel{
	/** The {@link TadukooTheme theme} to use in the Look & Feel */
	private final TadukooTheme theme;
	
	/**
	 * Constructs a Tadukoo Look & Feel with the default {@link TadukooTheme theme}.
	 */
	public TadukooLookAndFeel(){
		theme = TadukooThemeFactory.createDefaultTheme();
	}
	
	/**
	 * Constructs a Tadukoo Look & Feel with the given {@link TadukooTheme theme}.
	 *
	 * @param theme The {@link TadukooTheme theme} to use for the Look & Feel
	 */
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
	
	/**
	 * Populates the {@link UIDefaults} table with mappings for the Component UI classes to be used
	 * in the Look & Feel. Tadukoo Look & Feel has its own Component UI classes, but you are able
	 * to specify different ones in the {@link TadukooTheme theme} if you pass one in via the
	 * constructor.
	 *
	 * @param table The {@link UIDefaults} table to add the mappings to
	 */
	@Override
	protected void initClassDefaults(UIDefaults table){
		super.initClassDefaults(table);
		
		table.put("ButtonUI", theme.getButtonUI());
		// TODO: Add other mappings
	}
	
	/**
	 * Populates the {@link UIDefaults} table with mappings for defaults on the Components themselves.
	 * This includes anything that can be customized on the {@link TadukooTheme theme}, such as
	 * paints and fonts.
	 *
	 * @param table The {@link UIDefaults} table to add the mappings to
	 */
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
		
		// TODO: Add other mappings
		
		table.putDefaults(defaults);
	}
}
