package com.github.tadukoo.util.lookandfeel;

import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.plaf.metal.MetalButtonUI;

public class TadukooThemeFactory{
	
	public static TadukooTheme.TadukooThemeBuilder defaultThemeBuilder(){
		return TadukooTheme.builder();
	}
	
	public static TadukooTheme createDefaultTheme(){
		return defaultThemeBuilder().build();
	}
	
	public static TadukooTheme.TadukooThemeBuilder metalThemeBuilder(){
		return TadukooTheme.builder()
							.buttonUI(MetalButtonUI.class)
							.buttonBorder(MetalBorders.getButtonBorder());
	}
	
	public static TadukooTheme createMetalTheme(){
		return metalThemeBuilder().build();
	}
	
	public static TadukooTheme.TadukooThemeBuilder basicThemeBuilder(){
		return TadukooTheme.builder()
							.buttonUI(BasicButtonUI.class)
							.buttonBorder(BasicBorders.getButtonBorder());
	}
	
	public static TadukooTheme createBasicTheme(){
		return basicThemeBuilder().build();
	}
}
