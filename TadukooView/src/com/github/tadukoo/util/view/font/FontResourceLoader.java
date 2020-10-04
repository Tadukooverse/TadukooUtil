package com.github.tadukoo.util.view.font;

import com.github.tadukoo.util.FileUtil;
import com.github.tadukoo.util.StringUtil;
import com.github.tadukoo.util.logger.EasyLogger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Font Resource Loader is used to load {@link FontFamily Font Families}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class FontResourceLoader implements FontConstants{
	/** The {@link EasyLogger} to use in logging any problems */
	private final EasyLogger logger;
	/** The {@link GraphicsEnvironment} to use for the {@link Font}s */
	private final GraphicsEnvironment graphEnv;
	/** The path to the directory where the fonts are stored */
	private final String fontDirectoryPath;
	
	/**
	 * Constructs a new FontResourceLoader with the given {@link EasyLogger}, {@link GraphicsEnvironment}, and
	 * fonts directory
	 *
	 * @param logger The {@link EasyLogger} to use in logging any problems
	 * @param graphEnv The {@link GraphicsEnvironment} to use for the {@link Font}s
	 * @param fontDirectoryPath The path to the directory where the fonts are stored
	 */
	public FontResourceLoader(EasyLogger logger, GraphicsEnvironment graphEnv, String fontDirectoryPath){
		this.logger = logger;
		this.graphEnv = graphEnv;
		this.fontDirectoryPath = fontDirectoryPath;
	}
	
	/**
	 * Loads a collection of {@link FontFamily Font Families}. Just iterates over the list calling
	 * {@link #loadFont(FontFamily, boolean)}.
	 *
	 * @param fonts The list of {@link FontFamily Font Families} to be loaded
	 * @param requireLoadAll Whether all variants need loaded for each Font Family or not
	 * @return A List of fonts to be used (will choose alternates if applicable, and return nulls for issues)
	 * @throws IOException For file loading problems
	 * @throws FontFormatException If the Fonts themselves have issues
	 */
	public List<String> loadFonts(List<FontFamily> fonts, boolean requireLoadAll) throws IOException, FontFormatException{
		// Load each font and grab the resulting font names
		List<String> results = new ArrayList<>();
		for(FontFamily font: fonts){
			results.add(loadFont(font, requireLoadAll));
		}
		return results;
	}
	
	/**
	 * Loads a single {@link FontFamily}. If an alternate is specified and we can't find the given font, the
	 * alternate will be loaded and its name returned as a string. If we fail to load the font, a null will be
	 * returned instead.
	 *
	 * @param font The {@link FontFamily} to be loaded
	 * @param requireLoadAll Whether all variants need loaded for the Font Family or not
	 * @return The font to be used (will choose alternate if applicable, and return null for issues)
	 * @throws IOException For file loading problems
	 * @throws FontFormatException If the Font itself has issues
	 */
	public String loadFont(FontFamily font, boolean requireLoadAll) throws IOException, FontFormatException{
		// Grab alternate font name if it exists
		boolean hasAlternate = font.getAlternate() != null;
		String alternate = hasAlternate?font.getAlternate().getName():null;
		boolean alternateFound = false;
		
		// Check if font family already exists
		String[] fontFamilies = graphEnv.getAvailableFontFamilyNames();
		for(String fontFamily: fontFamilies){
			if(StringUtil.equalsIgnoreCase(font.getName(), fontFamily)){
				return font.getName();
			}else if(hasAlternate && StringUtil.equalsIgnoreCase(alternate, fontFamily)){
				// If alternate exists (and not the main font), we will be using it instead
				alternateFound = true;
			}
		}
		
		// If we found the alternate, return it (will not be hit if alternate is null)
		if(alternateFound){
			return alternate;
		}
		
		// Load actual or alternate font and return its name
		boolean loadedAFontFile = false;
		boolean loadedAllFontFiles = true;
		FontFamily fontToUse = hasAlternate?font.getAlternate():font;
		String fontFamilyPath = fontDirectoryPath + fontToUse.getDirectory();
		for(String fontFileName: fontToUse.getFiles()){
			// Determine the font format based on the extension
			String extension = FileUtil.getFileExtension(fontFileName);
			int fontFormat;
			if(StringUtil.equalsIgnoreCase(extension, TTF)){
				fontFormat = Font.TRUETYPE_FONT;
			}else if(StringUtil.equalsIgnoreCase(extension, OTF)){
				// TODO: Find a way to support OpenType format
				logger.logWarning("Failed to load font at: " + fontFamilyPath + fontFileName +
						"\nFont Format: OpenType Format not supported");
				loadedAllFontFiles = false;
				continue;
			}else{
				logger.logWarning("Failed to load font at: " + fontFamilyPath + fontFileName +
						"\nFont Format: Unknown Format not supported");
				loadedAllFontFiles = false;
				continue;
			}
			
			// Load and register the font
			File fontFile = new File(fontFamilyPath + fontFileName);
			if(fontFile.exists()){
				graphEnv.registerFont(Font.createFont(fontFormat, fontFile));
				loadedAFontFile = true;
			}else{
				loadedAllFontFiles = false;
			}
		}
		
		// Return the name of the font we loaded if we loaded all or at least one if not all are required
		if(loadedAllFontFiles || (!requireLoadAll && loadedAFontFile)){
			return fontToUse.getName();
		}else{
			// Hitting this happens from getting unsupported font formats (which are logged above)
			return null;
		}
	}
}
