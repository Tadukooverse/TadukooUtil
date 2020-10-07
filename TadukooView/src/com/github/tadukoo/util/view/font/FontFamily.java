package com.github.tadukoo.util.view.font;

import com.github.tadukoo.util.ListUtil;

import java.util.List;

/**
 * Represents a single font family. This can be a logical font, a proprietary font for which we have no files (but
 * can specify an alternate), or an open source font where we have the actual files and can specify a local path to
 * them.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class FontFamily implements FontConstants{
	
	/**
	 * Constructs a string in the form {name}-{type}.{extension} (which appears to be a common
	 * filename for a lot of fonts - .e.g Arimo-Regular.ttf, Gelasio-Medium.ttf, etc.)
	 *
	 * @param name The name of the font
	 * @param type The type of font (e.g. Regular, Bold, Italic, etc.)
	 * @param extension The extension of the font (e.g. ttf)
	 * @return A string of the form {name}-{type}.{extension}
	 */
	public static String stdFileStr(String name, String type, String extension){
		return name + "-" + type + "." + extension;
	}
	
	/**
	 * Creates a List of font filenames that just contains {name}-Regular.{extension} (a common occurrence).
	 *
	 * @param name The name of the font
	 * @param extension The extension of the font file (e.g. ttf)
	 * @return A List containing just {name}-Regular.{extension}
	 */
	public static List<String> makeRegularList(String name, String extension){
		return ListUtil.createList(stdFileStr(name, REGULAR, extension));
	}
	
	/**
	 * Creates a list of font filenames containing the following:
	 * <ul>
	 *     <li>{name}-Regular.{extension}</li>
	 *     <li>{name}-Bold.{extension}</li>
	 *     <li>{name}-Italic.{extension}</li>
	 *     <li>{name}-BoldItalic.{extension}</li>
	 * </ul>
	 * This appears to be the most common list of filenames for fonts.
	 *
	 * @param name The name of the font
	 * @param extension The extension of the font files (e.g. ttf)
	 * @return A List of the 4 standard font filenames
	 */
	public static List<String> makeStandardList(String name, String extension){
		return ListUtil.createList(stdFileStr(name, REGULAR, extension),
				stdFileStr(name, BOLD, extension), stdFileStr(name, ITALIC, extension),
				stdFileStr(name, BOLD_ITALIC, extension));
	}
	
	/**
	 * Creates a list of font filenames containing the 4 standard ones (by calling
	 * {@link #makeStandardList(String, String) makeStandardList}), plus additional ones specified in the
	 * format {name}-{addition}.{extension}
	 *
	 * @param name The name of the font
	 * @param extension The extension of the font files (e.g. ttf)
	 * @param additions The additional types to be used (e.g. Medium, Semibold, etc.)
	 * @return A List of the standard font filenames plus the additional ones
	 */
	public static List<String> makeStandardListWithAdditions(String name, String extension, List<String> additions){
		List<String> standards = makeStandardList(name, extension);
		for(String addition: additions){
			standards.add(stdFileStr(name, addition, extension));
		}
		return standards;
	}
	
	/** The name of the font */
	private final String name;
	/** The font directory - may be null for non-open source fonts */
	private final String directory;
	/** The font files (includes all variants, such as bold, italic, etc.) - may be null for non-open source fonts */
	private final List<String> files;
	/** The open source alternative font that can be substituted - may be null for open source fonts */
	private final FontFamily alternate;
	
	/**
	 * This constructor is used for the logical fonts provided by Java.
	 *
	 * @param name The name of the logical Font
	 */
	FontFamily(String name){
		this.name = name;
		this.directory = null;
		this.files = null;
		this.alternate = null;
	}
	
	/**
	 * This constructor is used for open sources fonts included in a project. These fonts can be found
	 * in the TadukooFonts project.
	 *
	 * @param name The name of the font
	 * @param directory The font directory
	 * @param files The font files (includes all variants, such as bold, italic, etc.)
	 */
	FontFamily(String name, String directory, List<String> files){
		this.name = name;
		this.directory = directory;
		this.files = files;
		this.alternate = null;
	}
	
	/**
	 * This constructor is used for proprietary fonts, and provides an open source alternative.
	 *
	 * @param name The name of the font
	 * @param alternate The open source alternative font that can be substituted
	 */
	FontFamily(String name, FontFamily alternate){
		this.name = name;
		this.directory = null;
		this.files = null;
		this.alternate = alternate;
	}
	
	/**
	 * @return The name of the font
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return The font directory - may be null for non-open source fonts
	 */
	public String getDirectory(){
		return directory;
	}
	
	/**
	 * @return The font files (includes all variants, such as bold, italic, etc.)
	 * - may be null for non-open source fonts
	 */
	public List<String> getFiles(){
		return files;
	}
	
	/**
	 * @return The open source alternative font that can be substituted - may be null for open source fonts
	 */
	public FontFamily getAlternate(){
		return alternate;
	}
}
