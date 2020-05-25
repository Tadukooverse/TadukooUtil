package com.gmail.realtadukoo.util.lookandfeel;

import com.gmail.realtadukoo.util.lookandfeel.componentui.TadukooButtonUI;

import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;

public class TadukooTheme{
	
	public static class TadukooThemeBuilder{
		// Component UIs
		private Class<? extends ButtonUI> buttonUI = TadukooButtonUI.class;
		
		// Borders
		private Border defaultBorder = new TadukooBorder();
		private Border buttonBorder = null;
		
		/*
		 * Colors
		 */
		
		// TODO: Set defaults for colors
		
		// Focus Colors
		private ColorUIResource defaultFocusColor = new ColorUIResource(Color.YELLOW);
		private ColorUIResource buttonFocusColor = null;
		
		// Select Colors
		private ColorUIResource defaultSelectColor = new ColorUIResource(Color.RED);
		private ColorUIResource buttonSelectColor = null;
		
		/// Fonts
		private FontUIResource defaultFont = new FontUIResource("Calibri", Font.PLAIN, 12);
		private FontUIResource buttonFont = null;
		
		private TadukooThemeBuilder(){ }
		
		public TadukooThemeBuilder buttonUI(Class<? extends ButtonUI> buttonUI){
			this.buttonUI = buttonUI;
			OceanTheme t;
			return this;
		}
		
		public TadukooThemeBuilder defaultBorder(Border defaultBorder){
			this.defaultBorder = defaultBorder;
			return this;
		}
		
		public TadukooThemeBuilder buttonBorder(Border buttonBorder){
			this.buttonBorder = buttonBorder;
			return this;
		}
		
		/*
		 * Default Focus Color Methods
		 */
		
		public TadukooThemeBuilder defaultFocusColor(ColorUIResource defaultFocusColor){
			this.defaultFocusColor = defaultFocusColor;
			return this;
		}
		
		public TadukooThemeBuilder defaultFocusColor(Color defaultFocusColor){
			this.defaultFocusColor = new ColorUIResource(defaultFocusColor);
			return this;
		}
		
		public TadukooThemeBuilder defaultFocusColorRGB(int defaultFocusColorRGB){
			this.defaultFocusColor = new ColorUIResource(defaultFocusColorRGB);
			return this;
		}
		
		public TadukooThemeBuilder defaultFocusColorRGB(int r, int g, int b){
			this.defaultFocusColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		public TadukooThemeBuilder defaultFocusColorRGB(float r, float g, float b){
			this.defaultFocusColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		/*
		 * Button Focus Color Methods
		 */
		
		public TadukooThemeBuilder buttonFocusColor(ColorUIResource buttonFocusColor){
			this.buttonFocusColor = buttonFocusColor;
			return this;
		}
		
		public TadukooThemeBuilder buttonFocusColor(Color buttonFocusColor){
			this.buttonFocusColor = new ColorUIResource(buttonFocusColor);
			return this;
		}
		
		public TadukooThemeBuilder buttonFocusColorRGB(int buttonFocusColorRGB){
			this.buttonFocusColor = new ColorUIResource(buttonFocusColorRGB);
			return this;
		}
		
		public TadukooThemeBuilder buttonFocusColorRGB(int r, int g, int b){
			this.buttonFocusColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		public TadukooThemeBuilder buttonFocusColorRGB(float r, float g, float b){
			this.buttonFocusColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		/*
		 * Default Select Color Methods
		 */
		
		public TadukooThemeBuilder defaultSelectColor(ColorUIResource defaultSelectColor){
			this.defaultSelectColor = defaultSelectColor;
			return this;
		}
		
		public TadukooThemeBuilder defaultSelectColor(Color defaultSelectColor){
			this.defaultSelectColor = new ColorUIResource(defaultSelectColor);
			return this;
		}
		
		public TadukooThemeBuilder defaultSelectColorRGB(int defaultSelectColorRGB){
			this.defaultSelectColor = new ColorUIResource(defaultSelectColorRGB);
			return this;
		}
		
		public TadukooThemeBuilder defaultSelectColorRGB(int r, int g, int b){
			this.defaultSelectColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		public TadukooThemeBuilder defaultSelectColorRGB(float r, float g, float b){
			this.defaultSelectColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		/*
		 * Button Select Color Methods
		 */
		
		public TadukooThemeBuilder buttonSelectColor(ColorUIResource buttonSelectColor){
			this.buttonSelectColor = buttonSelectColor;
			return this;
		}
		
		public TadukooThemeBuilder buttonSelectColor(Color buttonSelectColor){
			this.buttonSelectColor = new ColorUIResource(buttonSelectColor);
			return this;
		}
		
		public TadukooThemeBuilder buttonSelectColorRGB(int buttonSelectColorRGB){
			this.buttonSelectColor = new ColorUIResource(buttonSelectColorRGB);
			return this;
		}
		
		public TadukooThemeBuilder buttonSelectColorRGB(int r, int g, int b){
			this.buttonSelectColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		public TadukooThemeBuilder buttonSelectColorRGB(float r, float g, float b){
			this.buttonSelectColor = new ColorUIResource(r, g, b);
			return this;
		}
		
		/*
		 * Default Font Methods
		 */
		
		public TadukooThemeBuilder defaultFont(FontUIResource defaultFont){
			this.defaultFont = defaultFont;
			return this;
		}
		
		public TadukooThemeBuilder defaultFont(Font defaultFont){
			this.defaultFont = new FontUIResource(defaultFont);
			return this;
		}
		
		public TadukooThemeBuilder defaultFont(String fontName, int fontStyle, int fontSize){
			this.defaultFont = new FontUIResource(fontName, fontStyle, fontSize);
			return this;
		}
		
		/*
		 * Button Font Methods
		 */
		
		public TadukooThemeBuilder buttonFont(FontUIResource buttonFont){
			this.buttonFont = buttonFont;
			return this;
		}
		
		public TadukooThemeBuilder buttonFont(Font buttonFont){
			this.buttonFont = new FontUIResource(buttonFont);
			return this;
		}
		
		public TadukooThemeBuilder buttonFont(String fontName, int fontStyle, int fontSize){
			this.buttonFont = new FontUIResource(fontName, fontStyle, fontSize);
			return this;
		}
		
		public void checkForErrors(){
			// TODO: Check for errors
		}
		
		public TadukooTheme build(){
			checkForErrors();
			
			/*
			 * Handle Default Borders
			 */
			if(buttonBorder == null){
				buttonBorder = defaultBorder;
			}
			
			/*
			 * Handle Default Colors
			 */
			if(buttonFocusColor == null){
				buttonFocusColor = defaultFocusColor;
			}
			
			if(buttonSelectColor == null){
				buttonSelectColor = defaultSelectColor;
			}
			
			/*
			 * Handle Default Fonts
			 */
			if(buttonFont == null){
				buttonFont = defaultFont;
			}
			
			return new TadukooTheme(buttonUI.getCanonicalName(), buttonBorder,
					buttonFocusColor, buttonSelectColor, buttonFont);
		}
	}
	
	private String buttonUI;
	private Border buttonBorder;
	private ColorUIResource buttonFocusColor;
	private ColorUIResource buttonSelectColor;
	private FontUIResource buttonFont;
	
	private TadukooTheme(String buttonUI, Border buttonBorder,
	                     ColorUIResource buttonFocusColor, ColorUIResource buttonSelectColor, FontUIResource buttonFont){
		this.buttonUI = buttonUI;
		this.buttonBorder = buttonBorder;
		this.buttonFocusColor = buttonFocusColor;
		this.buttonSelectColor = buttonSelectColor;
		this.buttonFont = buttonFont;
	}
	
	public static TadukooThemeBuilder builder(){
		return new TadukooThemeBuilder();
	}
	
	public String getButtonUI(){
		return buttonUI;
	}
	
	public Border getButtonBorder(){
		return buttonBorder;
	}
	
	public ColorUIResource getButtonFocusColor(){
		return buttonFocusColor;
	}
	
	public ColorUIResource getButtonSelectColor(){
		return buttonSelectColor;
	}
	
	public FontUIResource getButtonFont(){
		return buttonFont;
	}
}
