package com.github.tadukoo.util.lookandfeel;

import com.github.tadukoo.util.lookandfeel.componentui.TadukooButtonUI;
import com.github.tadukoo.util.lookandfeel.paintui.ColorPaintUIResource;
import com.github.tadukoo.util.lookandfeel.paintui.PaintUIResource;

import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

/**
 * Theme class for {@link TadukooLookAndFeel}. You can use the {@link TadukooThemeBuilder builder} via the
 * {@link #builder()} method to construct it and specify whatever customizations you want.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TadukooTheme{
	
	/**
	 * Builder for {@link TadukooTheme}. There are no required fields - all of them will be
	 * defaulted based on the default Tadukoo Theme. The following fields may be specified:
	 *
	 * <table>
	 *     <th>
	 *         <td>Field</td>
	 *         <td>Description</td>
	 *         <td>Default Value</td>
	 *     </th>
	 *     <tr>
	 *         <td>buttonUI</td>
	 *         <td>The {@link ButtonUI} class to use</td>
	 *         <td>TadukooButtonUI.class</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultBorder</td>
	 *         <td>The {@link Border} to use for all unspecified borders</td>
	 *         <td>new TadukooBorder()</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonBorder</td>
	 *         <td>The {@link Border} to use on Buttons</td>
	 *         <td>null (defaults to the {@code defaultBorder} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultFocusPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified focus paints</td>
	 *         <td>new ColorPaintUIResource(Color.YELLOW)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonFocusPaint</td>
	 *         <td>The {@link PaintUIResource} to use for focus on Buttons</td>
	 *         <td>null (defaults to the {@code defaultFocusPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultSelectPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified select paints</td>
	 *         <td>new ColorPaintUIResource(Color.RED)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonSelectPaint</td>
	 *         <td>The {@link PaintUIResource} to use for select on Buttons</td>
	 *         <td>null (defaults to the {@code defaultSelectPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultFont</td>
	 *         <td>The {@link FontUIResource} to use for unspecified fonts</td>
	 *         <td>new FontUIResource("Calibri", Font.PLAIN, 12)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonFont</td>
	 *         <td>The {@link FontUIResource} to use for Buttons</td>
	 *         <td>null (defaults to the {@code defaultFont} value</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class TadukooThemeBuilder{
		// Component UIs
		/** The {@link ButtonUI} class to use */
		private Class<? extends ButtonUI> buttonUI = TadukooButtonUI.class;
		
		// Borders
		/** The {@link Border} to use for all unspecified borders */
		private Border defaultBorder = new TadukooBorder();
		/** The {@link Border} to use on Buttons */
		private Border buttonBorder = null;
		
		/*
		 * Colors
		 */
		
		// TODO: Set (actual) defaults for colors
		
		// Focus Colors
		/** The {@link PaintUIResource} to use for all unspecified focus paints */
		private PaintUIResource defaultFocusPaint = new ColorPaintUIResource(Color.YELLOW);
		/** The {@link PaintUIResource} to use for focus on Buttons */
		private PaintUIResource buttonFocusPaint = null;
		
		// Select Colors
		/** The {@link PaintUIResource} to use for all unspecified select paints */
		private PaintUIResource defaultSelectPaint = new ColorPaintUIResource(Color.RED);
		/** The {@link PaintUIResource} to use for select on Buttons */
		private PaintUIResource buttonSelectPaint = null;
		
		// Fonts
		/** The {@link FontUIResource} to use for unspecified fonts */
		private FontUIResource defaultFont = new FontUIResource("Calibri", Font.PLAIN, 12);
		/** The {@link FontUIResource} to use for Buttons */
		private FontUIResource buttonFont = null;
		
		// Cannot create TadukooThemeBuilder outside of TadukooTheme
		private TadukooThemeBuilder(){ }
		
		/**
		 * @param buttonUI The {@link ButtonUI} class to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonUI(Class<? extends ButtonUI> buttonUI){
			this.buttonUI = buttonUI;
			return this;
		}
		
		/**
		 * @param defaultBorder The {@link Border} to use for all unspecified borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultBorder(Border defaultBorder){
			this.defaultBorder = defaultBorder;
			return this;
		}
		
		/**
		 * @param buttonBorder The {@link Border} to use on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonBorder(Border buttonBorder){
			this.buttonBorder = buttonBorder;
			return this;
		}
		
		/*
		 * Focus Color Methods
		 */
		
		/**
		 * @param defaultFocusPaint The {@link PaintUIResource} to use for all unspecified focus paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultFocusPaint(PaintUIResource defaultFocusPaint){
			this.defaultFocusPaint = defaultFocusPaint;
			return this;
		}
		
		/**
		 * @param buttonFocusPaint The {@link PaintUIResource} to use for focus on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonFocusPaint(PaintUIResource buttonFocusPaint){
			this.buttonFocusPaint = buttonFocusPaint;
			return this;
		}
		
		/*
		 * Select Color Methods
		 */
		
		/**
		 * @param defaultSelectPaint The {@link PaintUIResource} to use for all unspecified select paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultSelectPaint(PaintUIResource defaultSelectPaint){
			this.defaultSelectPaint = defaultSelectPaint;
			return this;
		}
		
		/**
		 * @param buttonSelectPaint The {@link PaintUIResource} to use for select on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonSelectPaint(PaintUIResource buttonSelectPaint){
			this.buttonSelectPaint = buttonSelectPaint;
			return this;
		}
		
		/*
		 * Font Methods
		 */
		
		/**
		 * @param defaultFont The {@link FontUIResource} to use for unspecified fonts
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultFont(FontUIResource defaultFont){
			this.defaultFont = defaultFont;
			return this;
		}
		
		/**
		 * @param buttonFont The {@link FontUIResource} to use for Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonFont(FontUIResource buttonFont){
			this.buttonFont = buttonFont;
			return this;
		}
		
		/**
		 * Checks for any errors in the parameters that were set
		 */
		public void checkForErrors(){
			// TODO: Check for errors
		}
		
		/**
		 * Builds a {@link TadukooTheme} using the given customizations (or default customizations for unspecified
		 * parameters).
		 *
		 * @return A new {@link TadukooTheme}
		 */
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
	
	/** The {@link ButtonUI} class to use */
	private final String buttonUI;
	/** The {@link Border} to use on Buttons */
	private final Border buttonBorder;
	/** The {@link PaintUIResource} to use for focus on Buttons */
	private final PaintUIResource buttonFocusPaint;
	/** The {@link PaintUIResource} to use for select on Buttons */
	private final PaintUIResource buttonSelectPaint;
	/** The {@link FontUIResource} to use for Buttons */
	private final FontUIResource buttonFont;
	
	/**
	 * Constructs a new TadukooTheme with the given customizations.
	 *
	 * @param buttonUI The {@link ButtonUI} class to use
	 * @param buttonBorder The {@link Border} to use on Buttons
	 * @param buttonFocusPaint The {@link PaintUIResource} to use for focus on Buttons
	 * @param buttonSelectPaint The {@link PaintUIResource} to use for select on Buttons
	 * @param buttonFont The {@link FontUIResource} to use for Buttons
	 */
	private TadukooTheme(String buttonUI, Border buttonBorder,
	                     PaintUIResource buttonFocusPaint, PaintUIResource buttonSelectPaint, FontUIResource buttonFont){
		this.buttonUI = buttonUI;
		this.buttonBorder = buttonBorder;
		this.buttonFocusPaint = buttonFocusPaint;
		this.buttonSelectPaint = buttonSelectPaint;
		this.buttonFont = buttonFont;
	}
	
	/**
	 * @return A {@link TadukooThemeBuilder} to use in building a TadukooTheme
	 */
	public static TadukooThemeBuilder builder(){
		return new TadukooThemeBuilder();
	}
	
	/**
	 * @return The {@link ButtonUI} class to use
	 */
	public String getButtonUI(){
		return buttonUI;
	}
	
	/**
	 * @return The {@link Border} to use on Buttons
	 */
	public Border getButtonBorder(){
		return buttonBorder;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for focus on Buttons
	 */
	public PaintUIResource getButtonFocusPaint(){
		return buttonFocusPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for select on Buttons
	 */
	public PaintUIResource getButtonSelectPaint(){
		return buttonSelectPaint;
	}
	
	/**
	 * @return The {@link FontUIResource} to use for Buttons
	 */
	public FontUIResource getButtonFont(){
		return buttonFont;
	}
}
