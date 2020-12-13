package com.github.tadukoo.util.lookandfeel;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.logger.EasyLogger;
import com.github.tadukoo.util.view.border.ShapedLineBorder;
import com.github.tadukoo.util.lookandfeel.componentui.TadukooButtonUI;
import com.github.tadukoo.util.lookandfeel.paintui.ColorPaintUIResource;
import com.github.tadukoo.util.lookandfeel.paintui.PaintUIResource;
import com.github.tadukoo.util.view.shapes.ShapeInfo;
import com.github.tadukoo.util.view.shapes.Shapes;
import com.github.tadukoo.util.view.font.FontFamilies;
import com.github.tadukoo.util.view.font.FontFamily;
import com.github.tadukoo.util.view.font.FontResourceLoader;

import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Theme class for {@link TadukooLookAndFeel}. You can use the {@link TadukooThemeBuilder builder} via the
 * {@link #builder()} method to construct it and specify whatever customizations you want.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TadukooTheme{
	
	/**
	 * An enum used for Title Position (for Titled Borders)
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public enum TitlePosition{
		/** Position the title above the border's top line. */
		ABOVE_TOP(1),
		/** Position the title in the middle of the border's top line. */
		TOP(2),
		/** Position the title below the border's top line. */
		BELOW_TOP(3),
		/** Position the title above the border's bottom line. */
		ABOVE_BOTTOM(4),
		/** Position the title in the middle of the border's bottom line. */
		BOTTOM(5),
		/** Position the title below the border's bottom line. */
		BELOW_BOTTOM(6);
		
		/** The value of the Title Position (used in Titled Border class) */
		private final int value;
		
		/**
		 * Constructs a new Title Position with the given value
		 *
		 * @param value The value for this Title Position
		 */
		TitlePosition(int value){
			this.value = value;
		}
		
		/**
		 * @return The value of the Title Position (used in Titled Border class)
		 */
		public int getValue(){
			return value;
		}
	}
	
	/**
	 * Builder for {@link TadukooTheme}. There are no required fields - all of them will be
	 * defaulted based on the default Tadukoo Theme. The following fields may be specified:
	 * <br><br>
	 *
	 * <table>
	 *     <caption><b>Component UI Classes</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonUI</td>
	 *         <td>The {@link ButtonUI} class to use</td>
	 *         <td>TadukooButtonUI.class</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Paint Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
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
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Font Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultFont</td>
	 *         <td>The font to use for unspecified fonts - specified as a {@link FontFamily}, font style, and
	 *         font size</td>
	 *         <td>{@link FontFamilies#CALIBRI}, style {@link Font#PLAIN}, and size 12</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonFont</td>
	 *         <td>The font to use for Buttons - specified as a {@link FontFamily}, font style, and font size</td>
	 *         <td>null (defaults to the {@code defaultFont} value</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Font Resource Loading Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *        <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>logFontResourceLoaderWarnings</td>
	 *         <td>Whether to log warnings generated by the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>false</td>
	 *     </tr>
	 *     <tr>
	 *         <td>logger</td>
	 *         <td>An {@link EasyLogger} that will be sent to the FontResourceLoader by default
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>null (since logging warnings is set to false by default)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>graphEnv</td>
	 *         <td>The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>{@link GraphicsEnvironment#getLocalGraphicsEnvironment()}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>fontFolder</td>
	 *         <td>The path to the fonts folder to find font files in if needed in the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>"fonts/"</td>
	 *     </tr>
	 *     <tr>
	 *         <td>fontResourceLoader</td>
	 *        <td>The {@link FontResourceLoader} to use in loading fonts and/or ensuring they're in the system</td>
	 *        <td>a new FontResourceLoader with the specified values for {@link #logFontResourceLoaderWarnings},
	 *         {@link #logger}, {@link #graphEnv}, and {@link #fontFolder}</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Shape Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultShapeInfo</td>
	 *         <td>The {@link ShapeInfo} to use for all unspecified shapes</td>
	 *         <td>{@link Shapes#RECTANGLE_WITH_CUT_CORNERS_TR_BL}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonShapeInfo</td>
	 *         <td>The {@link ShapeInfo} to use for Buttons</td>
	 *         <td>null (defaults to the {@code defaultShapeInfo} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Border Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultBorder</td>
	 *         <td>The {@link BorderUIResource} to use for all unspecified borders</td>
	 *         <td>new TadukooBorder()</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonBorder</td>
	 *         <td>The {@link BorderUIResource} to use on Buttons</td>
	 *         <td>null (defaults to the {@code defaultBorder} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Titled Border Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderBorder</td>
	 *         <td>The default border to use in Titled Borders</td>
	 *         <td>null (defaults to the {@code defaultBorder} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderFont</td>
	 *         <td>The default font to use in Titled Borders</td>
	 *         <td>null (defaults to the {@code defaultFont} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderColor</td>
	 *         <td>The default color to use in Titled Borders</td>
	 *         <td>{@link Color#BLACK}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderPosition</td>
	 *         <td>The default position for the title in Titled Borders</td>
	 *         <td>{@link TitlePosition#TOP}</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Other Customizations</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>classDefaults</td>
	 *         <td>Class defaults beyond those specified in the "Component UI Classes" section</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 *     <tr>
	 *         <td>systemColorDefaults</td>
	 *         <td>System Color defaults</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 *     <tr>
	 *         <td>componentDefaults</td>
	 *         <td>Component defaults beyond those specified in the above sections</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class TadukooThemeBuilder{
		/*
		 * Component UIs
		 */
		
		/** The {@link ButtonUI} class to use */
		private Class<? extends ButtonUI> buttonUI = TadukooButtonUI.class;
		
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
		
		/*
		 * Fonts
		 */
		
		// Default Font
		/** The {@link FontFamily} to use for unspecified fonts */
		private FontFamily defaultFontFamily = FontFamilies.CALIBRI.getFamily();
		/** The font style to use for unspecified fonts */
		private int defaultFontStyle = Font.PLAIN;
		/** The font size to use for unspecified fonts */
		private int defaultFontSize = 12;
		
		// Button Font
		/** The {@link FontFamily} to use for Buttons */
		private FontFamily buttonFontFamily = null;
		/** The Font style to use for Buttons */
		private int buttonFontStyle = -1;
		/** The font size to use for Buttons */
		private int buttonFontSize = -1;
		
		/*
		 * Font Resource Loading
		 */
		
		/** Whether to log warnings generated by the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private boolean logFontResourceLoaderWarnings = false;
		/** An {@link EasyLogger} that will be sent to the FontResourceLoader by default
		 *  - can be ignored if you specify your own FontResourceLoader */
		private EasyLogger logger = null;
		/** The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		/** The path to the fonts folder to find font files in if needed in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private String fontFolder = "fonts/";
		/** The {@link FontResourceLoader} to use in loading fonts and/or ensuring they're in the system */
		private FontResourceLoader fontResourceLoader = null;
		
		/*
		 * Shapes
		 */
		
		/** The {@link ShapeInfo} to use for unspecified shapes */
		private ShapeInfo defaultShapeInfo = Shapes.RECTANGLE_WITH_CUT_CORNERS_TR_BL.getShapeInfo();
		/** The {@link ShapeInfo} to use for Buttons */
		private ShapeInfo buttonShapeInfo = null;
		
		/*
		 * Borders
		 */
		/** The {@link BorderUIResource} to use for all unspecified borders */
		private BorderUIResource defaultBorder = new BorderUIResource(ShapedLineBorder.builder().build());
		/** The {@link BorderUIResource} to use on Buttons */
		private BorderUIResource buttonBorder = null;
		
		/*
		 * Titled Border Parameters
		 */
		
		/** The default {@link BorderUIResource} to use in Titled Borders */
		private BorderUIResource titledBorderBorder = null;
		/** The default {@link FontFamily} to use in Titled Borders */
		private FontFamily titledBorderFontFamily = null;
		/** The default font style to use in Titled Borders */
		private int titledBorderFontStyle = -1;
		/** The default font size to use in Titled Borders */
		private int titledBorderFontSize = -1;
		/** The default color to use in Titled Borders */
		private ColorUIResource titledBorderColor = new ColorUIResource(Color.BLACK);
		/** The default position for the title in Titled Borders */
		private TitlePosition titledBorderPosition = TitlePosition.TOP;
		
		/*
		 * Other Customizations
		 */
		
		/** Class defaults beyond those specified in the "Component UI Classes" section */
		private Map<String, Class<?>> classDefaults = new HashMap<>();
		 /** System Color defaults */
		private Map<String, ColorUIResource> systemColorDefaults = new HashMap<>();
		/** Component defaults beyond those specified in the above sections */
		private Map<String, Object> componentDefaults = new HashMap<>();
		
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
		 * Specifies the font to use for unspecified fonts
		 *
		 * @param defaultFontFamily The {@link FontFamily} to use
		 * @param defaultFontStyle The font style to use
		 * @param defaultFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultFont(FontFamily defaultFontFamily, int defaultFontStyle, int defaultFontSize){
			this.defaultFontFamily = defaultFontFamily;
			this.defaultFontStyle = defaultFontStyle;
			this.defaultFontSize = defaultFontSize;
			return this;
		}
		
		/**
		 * Specifies the font to use for Buttons
		 *
		 * @param buttonFontFamily The {@link FontFamily} to use
		 * @param buttonFontStyle The font style to use
		 * @param buttonFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonFont(FontFamily buttonFontFamily, int buttonFontStyle, int buttonFontSize){
			this.buttonFontFamily = buttonFontFamily;
			this.buttonFontStyle = buttonFontStyle;
			this.buttonFontSize = buttonFontSize;
			return this;
		}
		
		/*
		 * Font Resource Loading Methods
		 */
		
		/**
		 * @param logFontResourceLoaderWarnings Whether to log warnings generated by the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder logFontResourceLoaderWarnings(boolean logFontResourceLoaderWarnings){
			this.logFontResourceLoaderWarnings = logFontResourceLoaderWarnings;
			return this;
		}
		
		/**
		 * @param logger An {@link EasyLogger} that will be sent to the FontResourceLoader by default
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder logger(EasyLogger logger){
			this.logger = logger;
			return this;
		}
		
		/**
		 * @param graphEnv The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder graphEnv(GraphicsEnvironment graphEnv){
			this.graphEnv = graphEnv;
			return this;
		}
		
		/**
		 * @param fontFolder The path to the fonts folder to find font files in if needed in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder fontFolder(String fontFolder){
			this.fontFolder = fontFolder;
			return this;
		}
		
		/**
		 * @param fontResourceLoader The {@link FontResourceLoader} to use in loading fonts and/or ensuring
		 *  they're in the system
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder fontResourceLoader(FontResourceLoader fontResourceLoader){
			this.fontResourceLoader = fontResourceLoader;
			return this;
		}
		
		/*
		 * Shape Methods
		 */
		
		/**
		 * @param defaultShapeInfo The {@link ShapeInfo} to use for unspecified shapes
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultShapeInfo(ShapeInfo defaultShapeInfo){
			this.defaultShapeInfo = defaultShapeInfo;
			return this;
		}
		
		/**
		 * @param buttonShapeInfo The {@link ShapeInfo} to use for Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonShapeInfo(ShapeInfo buttonShapeInfo){
			this.buttonShapeInfo = buttonShapeInfo;
			return this;
		}
		
		/*
		 * Border Methods
		 */
		
		/**
		 * @param defaultBorder The {@link Border} to use for all unspecified borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultBorder(BorderUIResource defaultBorder){
			this.defaultBorder = defaultBorder;
			return this;
		}
		
		/**
		 * @param buttonBorder The {@link Border} to use on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonBorder(BorderUIResource buttonBorder){
			this.buttonBorder = buttonBorder;
			return this;
		}
		
		/*
		 * Titled Border Parameters
		 */
		
		/**
		 * @param titledBorderBorder The default {@link BorderUIResource} to use in Titled Borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderBorder(BorderUIResource titledBorderBorder){
			this.titledBorderBorder = titledBorderBorder;
			return this;
		}
		
		/**
		 * Specifies the default font to use for Titled Borders
		 *
		 * @param titledBorderFontFamily The {@link FontFamily} to use
		 * @param titledBorderFontStyle The font style to use
		 * @param titledBorderFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderFont(FontFamily titledBorderFontFamily, int titledBorderFontStyle,
		                                      int titledBorderFontSize){
			this.titledBorderFontFamily = titledBorderFontFamily;
			this.titledBorderFontStyle = titledBorderFontStyle;
			this.titledBorderFontSize = titledBorderFontSize;
			return this;
		}
		
		/**
		 * @param titledBorderColor The default color to use in Titled Borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderColor(ColorUIResource titledBorderColor){
			this.titledBorderColor = titledBorderColor;
			return this;
		}
		
		/**
		 * @param titledBorderPosition The default position for the title in Titled Borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderPosition(TitlePosition titledBorderPosition){
			this.titledBorderPosition = titledBorderPosition;
			return this;
		}
		
		/*
		 * Other Customizations
		 */
		
		/**
		 * @param classDefaults Class defaults beyond those specified in the "Component UI Classes" section
		 *                      - this overwrites the map
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder classDefaults(Map<String, Class<?>> classDefaults){
			this.classDefaults = classDefaults;
			return this;
		}
		
		/**
		 * Adds a class default beyond those specified in the "Component UI Classes" section - this
		 * adds to the map instead of overwriting it
		 *
		 * @param key The key for the class default
		 * @param clazz The actual Class for the class default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder classDefault(String key, Class<?> clazz){
			classDefaults.put(key, clazz);
			return this;
		}
		
		/**
		 * @param systemColorDefaults System Color defaults
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder systemColorDefaults(Map<String, ColorUIResource> systemColorDefaults){
			this.systemColorDefaults = systemColorDefaults;
			return this;
		}
		
		/**
		 * Adds a system color default - this adds to the map instead of overwriting it
		 *
		 * @param key The key for the system color default
		 * @param color The actual color for the system color default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder systemColorDefault(String key, ColorUIResource color){
			systemColorDefaults.put(key, color);
			return this;
		}
		
		/**
		 * @param componentDefaults Component defaults beyond those specified in the above sections
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder componentDefaults(Map<String, Object> componentDefaults){
			this.componentDefaults = componentDefaults;
			return this;
		}
		
		/**
		 * Adds a component default beyond those specified in the above sections - this adds to the map instead
		 * of overwriting it
		 *
		 * @param key The key for the component default
		 * @param value The actual value for the component default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder componentDefault(String key, Object value){
			componentDefaults.put(key, value);
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
		 * @throws IOException If something goes wrong in loading fonts
		 * @throws FontFormatException If something goes wrong in loading fonts
		 */
		public TadukooTheme build() throws IOException, FontFormatException{
			checkForErrors();
			
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
			if(buttonFontFamily == null){
				buttonFontFamily = defaultFontFamily;
				buttonFontStyle = defaultFontStyle;
				buttonFontSize = defaultFontSize;
			}
			
			if(titledBorderFontFamily == null){
				titledBorderFontFamily = defaultFontFamily;
				titledBorderFontStyle = defaultFontStyle;
				titledBorderFontSize = defaultFontSize;
			}
			
			// Handle font resource loading
			if(fontResourceLoader == null){
				fontResourceLoader = new FontResourceLoader(logFontResourceLoaderWarnings, logger, graphEnv,
						fontFolder);
			}
			
			// Load fonts
			List<FontFamily> fontFamilies = ListUtil.createList(buttonFontFamily, titledBorderFontFamily);
			List<String> foundFonts = fontResourceLoader.loadFonts(fontFamilies, true);
			
			// Create the FontUIResources
			FontUIResource buttonFont = new FontUIResource(foundFonts.get(0), buttonFontStyle, buttonFontSize);
			FontUIResource titledBorderFont = new FontUIResource(foundFonts.get(1),
					titledBorderFontStyle, titledBorderFontSize);
			
			/*
			 * Handle Default Shapes
			 */
			if(buttonShapeInfo == null){
				buttonShapeInfo = defaultShapeInfo;
			}
			
			/*
			 * Handle Default Borders
			 */
			if(buttonBorder == null){
				buttonBorder = defaultBorder;
			}
			
			if(titledBorderBorder == null){
				titledBorderBorder = defaultBorder;
			}
			
			/*
			 * Handle Other Customizations
			 */
			
			// Handle Class Defaults
			int classDefaultsNum = classDefaults.keySet().size();
			Object[] classDefaultsArray = new Object[classDefaultsNum*2];
			Iterator<String> classDefaultsIt = classDefaults.keySet().iterator();
			for(int i = 0; i < classDefaultsNum; i++){
				String key = classDefaultsIt.next();
				classDefaultsArray[i*2] = key;
				classDefaultsArray[i*2+1] = classDefaults.get(key).getCanonicalName();
			}
			
			// Handle System Color Defaults
			int systemColorDefaultsNum = systemColorDefaults.keySet().size();
			Object[] systemColorDefaultsArray = new Object[systemColorDefaultsNum*2];
			Iterator<String> systemColorDefaultsIt = systemColorDefaults.keySet().iterator();
			for(int i = 0; i < systemColorDefaultsNum; i++){
				String key = systemColorDefaultsIt.next();
				systemColorDefaultsArray[i*2] = key;
				systemColorDefaultsArray[i*2+1] = systemColorDefaults.get(key);
			}
			
			// Handle Component Defaults
			int componentDefaultsNum = componentDefaults.keySet().size();
			Object[] componentDefaultsArray = new Object[componentDefaultsNum*2];
			Iterator<String> componentDefaultsIt = componentDefaults.keySet().iterator();
			for(int i = 0; i < componentDefaultsNum; i++){
				String key = componentDefaultsIt.next();
				componentDefaultsArray[i*2] = key;
				componentDefaultsArray[i*2+1] = componentDefaults.get(key);
			}
			
			return new TadukooTheme(buttonUI.getCanonicalName(),
					buttonFocusPaint, buttonSelectPaint, buttonFont,
					buttonShapeInfo, buttonBorder,
					titledBorderBorder, titledBorderFont, titledBorderColor, titledBorderPosition.getValue(),
					classDefaultsArray, systemColorDefaultsArray, componentDefaultsArray);
		}
	}
	
	/** The {@link ButtonUI} class to use */
	private final String buttonUI;
	/** The {@link PaintUIResource} to use for focus on Buttons */
	private final PaintUIResource buttonFocusPaint;
	/** The {@link PaintUIResource} to use for select on Buttons */
	private final PaintUIResource buttonSelectPaint;
	/** The {@link FontUIResource} to use for Buttons */
	private final FontUIResource buttonFont;
	/** The {@link ShapeInfo} to use on Buttons */
	private final ShapeInfo buttonShapeInfo;
	/** The {@link Border} to use on Buttons */
	private final BorderUIResource buttonBorder;
	/** The default {@link BorderUIResource} to use in Titled Borders */
	private final BorderUIResource titledBorderBorder;
	/** The default {@link FontUIResource} to use in Titled Borders */
	private final FontUIResource titledBorderFont;
	/** The default color to use in Titled Borders */
	private final ColorUIResource titledBorderColor;
	/** The default position for the title in Titled Borders */
	private final int titledBorderPosition;
	/** Class defaults beyond those specified in the "Component UI Classes" section */
	private final Object[] classDefaults;
	/** System Color defaults */
	private final Object[] systemColorDefaults;
	/** Component defaults beyond those specified in the other sections */
	private final Object[] componentDefaults;
	
	/**
	 * Constructs a new TadukooTheme with the given customizations.
	 *
	 * @param buttonUI The {@link ButtonUI} class to use
	 * @param buttonFocusPaint The {@link PaintUIResource} to use for focus on Buttons
	 * @param buttonSelectPaint The {@link PaintUIResource} to use for select on Buttons
	 * @param buttonFont The {@link FontUIResource} to use for Buttons
	 * @param buttonShapeInfo The {@link ShapeInfo} to use on Buttons
	 * @param buttonBorder The {@link Border} to use on Buttons
	 * @param titledBorderBorder The default {@link BorderUIResource} to use in Titled Borders
	 * @param titledBorderFont The default {@link FontUIResource} to use in Titled Borders
	 * @param titledBorderColor The default color to use in Titled Borders
	 * @param titledBorderPosition The default position for the title in Titled Borders
	 * @param classDefaults Class defaults beyond those specified in the "Component UI Classes" section
	 * @param systemColorDefaults System Color defaults
	 * @param componentDefaults Component defaults beyond those specified in the other sections
	 */
	private TadukooTheme(String buttonUI,
	                     PaintUIResource buttonFocusPaint, PaintUIResource buttonSelectPaint, FontUIResource buttonFont,
	                     ShapeInfo buttonShapeInfo, BorderUIResource buttonBorder,
	                     BorderUIResource titledBorderBorder, FontUIResource titledBorderFont,
	                     ColorUIResource titledBorderColor, int titledBorderPosition,
	                     Object[] classDefaults, Object[] systemColorDefaults, Object[] componentDefaults){
		this.buttonUI = buttonUI;
		this.buttonFocusPaint = buttonFocusPaint;
		this.buttonSelectPaint = buttonSelectPaint;
		this.buttonFont = buttonFont;
		this.buttonShapeInfo = buttonShapeInfo;
		this.buttonBorder = buttonBorder;
		this.titledBorderBorder = titledBorderBorder;
		this.titledBorderFont = titledBorderFont;
		this.titledBorderColor = titledBorderColor;
		this.titledBorderPosition = titledBorderPosition;
		this.classDefaults = classDefaults;
		this.systemColorDefaults = systemColorDefaults;
		this.componentDefaults = componentDefaults;
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
	
	/**
	 * @return The {@link ShapeInfo} to use on Buttons
	 */
	public ShapeInfo getButtonShapeInfo(){
		return buttonShapeInfo;
	}
	
	/**
	 * @return The {@link BorderUIResource} to use on Buttons
	 */
	public BorderUIResource getButtonBorder(){
		return buttonBorder;
	}
	
	/**
	 * @return The default {@link BorderUIResource} to use in Titled Borders
	 */
	public BorderUIResource getTitledBorderBorder(){
		return titledBorderBorder;
	}
	
	/**
	 * @return The default {@link FontUIResource} to use in Titled Borders
	 */
	public FontUIResource getTitledBorderFont(){
		return titledBorderFont;
	}
	
	/**
	 * @return The default color to use in Titled Borders
	 */
	public ColorUIResource getTitledBorderColor(){
		return titledBorderColor;
	}
	
	/**
	 * @return The default position for the title in Titled Borders
	 */
	public int getTitledBorderPosition(){
		return titledBorderPosition;
	}
	
	/**
	 * @return Class defaults beyond those specified in the "Component UI Classes" section
	 */
	public Object[] getClassDefaults(){
		return classDefaults;
	}
	
	/**
	 * @return System Color defaults
	 */
	public Object[] getSystemColorDefaults(){
		return systemColorDefaults;
	}
	
	/**
	 * @return Component defaults beyond those specified in the other sections
	 */
	public Object[] getComponentDefaults(){
		return componentDefaults;
	}
}
