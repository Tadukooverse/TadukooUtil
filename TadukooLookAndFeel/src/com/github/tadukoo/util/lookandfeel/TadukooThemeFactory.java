package com.github.tadukoo.util.lookandfeel;

import com.github.tadukoo.util.lookandfeel.paintui.ColorPaintUIResource;
import com.github.tadukoo.util.view.font.FontFamilies;
import com.github.tadukoo.util.view.font.FontFamily;

import javax.swing.plaf.metal.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Tadukoo Theme Factory provides some standard {@link TadukooTheme}s and
 * {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilders}, along with some util methods for making your own
 * {@link TadukooTheme}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TadukooThemeFactory{
	
	/*
	 * Themes and Theme Builders
	 */
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that has the default customizations
	 */
	public static TadukooTheme.TadukooThemeBuilder defaultThemeBuilder(){
		return TadukooTheme.builder();
	}
	
	/**
	 * @return A {@link TadukooTheme} that has the default customizations
	 */
	public static TadukooTheme createDefaultTheme() throws IOException, FontFormatException{
		return defaultThemeBuilder().build();
	}
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that uses the
	 * {@link javax.swing.plaf.metal.MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders, but uses the
	 * regular {@link TadukooLookAndFeel} colors and fonts.
	 */
	public static TadukooTheme.TadukooThemeBuilder metalThemeBuilder(){
		return TadukooTheme.builder()
							.buttonUI(MetalButtonUI.class)
							.buttonBorder(MetalBorders.getButtonBorder());
	}
	
	/**
	 * @return A {@link TadukooTheme} that uses the
	 * {@link javax.swing.plaf.metal.MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders, but uses the
	 * regular {@link TadukooLookAndFeel} colors and fonts.
	 */
	public static TadukooTheme createMetalTheme() throws IOException, FontFormatException{
		return metalThemeBuilder().build();
	}
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that uses the
	 * {@link javax.swing.plaf.metal.MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link DefaultMetalTheme} for colors and fonts.
	 */
	public static TadukooTheme.TadukooThemeBuilder defaultMetalThemeBuilder(){
		return copyMetalTheme(metalThemeBuilder(), new DefaultMetalTheme());
	}
	
	/**
	 * @return A {@link TadukooTheme} that uses the
	 * {@link javax.swing.plaf.metal.MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link DefaultMetalTheme} for colors and fonts.
	 */
	public static TadukooTheme createDefaultMetalTheme() throws IOException, FontFormatException{
		return defaultMetalThemeBuilder().build();
	}
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that uses the
	 * {@link javax.swing.plaf.metal.MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link OceanTheme} for colors and fonts.
	 */
	public static TadukooTheme.TadukooThemeBuilder oceanThemeBuilder(){
		return copyMetalTheme(metalThemeBuilder(), new OceanTheme());
	}
	
	/**
	 * @return A {@link TadukooTheme} that uses the
	 * {@link javax.swing.plaf.metal.MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link OceanTheme} for colors and fonts.
	 */
	public static TadukooTheme createOceanTheme() throws IOException, FontFormatException{
		return oceanThemeBuilder().build();
	}
	
	/*
	 * Util Functions
	 */
	
	/**
	 * Copies the colors and fonts from the given {@link MetalTheme} to the given
	 * {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} and returns the TadukooThemeBuilder to easily
	 * continue building the {@link TadukooTheme}.
	 *
	 * @param themeBuilder The {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} to add parameters to
	 * @param metalTheme The {@link MetalTheme} to grab colors and fonts from
	 * @return The {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder}, to continue building a theme
	 */
	public static TadukooTheme.TadukooThemeBuilder copyMetalTheme(TadukooTheme.TadukooThemeBuilder themeBuilder,
	                                                              MetalTheme metalTheme){
		// Sort out fonts
		Font controlTextFont = metalTheme.getControlTextFont();
		FontFamily controlTextFontFamily = Objects.requireNonNull(
				FontFamilies.fromName(controlTextFont.getFontName())).getFamily();
		
		return themeBuilder.buttonFocusPaint(new ColorPaintUIResource(metalTheme.getFocusColor()))
					.buttonSelectPaint(new ColorPaintUIResource(metalTheme.getControlShadow()))
					.buttonFont(controlTextFontFamily, controlTextFont.getStyle(), controlTextFont.getSize());
	}
}
