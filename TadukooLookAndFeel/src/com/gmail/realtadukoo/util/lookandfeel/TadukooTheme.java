package com.gmail.realtadukoo.util.lookandfeel;

import com.gmail.realtadukoo.util.lookandfeel.componentui.TadukooButtonUI;
import com.gmail.realtadukoo.util.lookandfeel.paintui.ColorPaintUIResource;
import com.gmail.realtadukoo.util.lookandfeel.paintui.PaintUIResource;

import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
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
		private PaintUIResource defaultFocusPaint = new ColorPaintUIResource(Color.YELLOW);
		private PaintUIResource buttonFocusPaint = null;
		
		// Select Colors
		private PaintUIResource defaultSelectPaint = new ColorPaintUIResource(Color.RED);
		private PaintUIResource buttonSelectPaint = null;
		
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
		 * Focus Color Methods
		 */
		
		public TadukooThemeBuilder defaultFocusPaint(PaintUIResource defaultFocusPaint){
			this.defaultFocusPaint = defaultFocusPaint;
			return this;
		}
		
		public TadukooThemeBuilder buttonFocusPaint(PaintUIResource buttonFocusPaint){
			this.buttonFocusPaint = buttonFocusPaint;
			return this;
		}
		
		/*
		 * Select Color Methods
		 */
		
		public TadukooThemeBuilder defaultSelectPaint(PaintUIResource defaultSelectPaint){
			this.defaultSelectPaint = defaultSelectPaint;
			return this;
		}
		
		public TadukooThemeBuilder buttonSelectPaint(PaintUIResource buttonSelectPaint){
			this.buttonSelectPaint = buttonSelectPaint;
			return this;
		}
		
		/*
		 * Font Methods
		 */
		
		public TadukooThemeBuilder defaultFont(FontUIResource defaultFont){
			this.defaultFont = defaultFont;
			return this;
		}
		
		public TadukooThemeBuilder buttonFont(FontUIResource buttonFont){
			this.buttonFont = buttonFont;
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
			if(buttonFocusPaint == null){
				buttonFocusPaint = defaultFocusPaint;
			}
			
			if(buttonSelectPaint == null){
				buttonSelectPaint = defaultSelectPaint;
			}
			
			/*
			 * Handle Default Fonts
			 */
			if(buttonFont == null){
				buttonFont = defaultFont;
			}
			
			return new TadukooTheme(buttonUI.getCanonicalName(), buttonBorder,
					buttonFocusPaint, buttonSelectPaint, buttonFont);
		}
	}
	
	private String buttonUI;
	private Border buttonBorder;
	private PaintUIResource buttonFocusPaint;
	private PaintUIResource buttonSelectPaint;
	private FontUIResource buttonFont;
	
	private TadukooTheme(String buttonUI, Border buttonBorder,
	                     PaintUIResource buttonFocusPaint, PaintUIResource buttonSelectPaint, FontUIResource buttonFont){
		this.buttonUI = buttonUI;
		this.buttonBorder = buttonBorder;
		this.buttonFocusPaint = buttonFocusPaint;
		this.buttonSelectPaint = buttonSelectPaint;
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
	
	public PaintUIResource getButtonFocusPaint(){
		return buttonFocusPaint;
	}
	
	public PaintUIResource getButtonSelectPaint(){
		return buttonSelectPaint;
	}
	
	public FontUIResource getButtonFont(){
		return buttonFont;
	}
}
