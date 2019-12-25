package com.gmail.realtadukoo.util.fileformat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to convert between TFormatting and Java regexes.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class TFormatRegexConverter{
	
	// Not allowed to instantiate TFormatRegexConverter
	private TFormatRegexConverter(){ }
	
	/**
	 * Convert the given TFormatting into a Java regex.
	 * 
	 * @param logger The Logger to log any messages to
	 * @param tFormat The TFormatting string to convert to a regex
	 * @return The regex resulting from the TFormatting conversion
	 */
	public static String convertTFormatToRegex(Logger logger, String tFormat){
		// Log the original TFormatting
		logger.log(Level.FINER, "TFormat: " + tFormat);
		
		// If TFormatting contains a period, escape it in the regex
		// TFormatting treats period as a normal character
		if(tFormat.contains(".")){
			tFormat = tFormat.replaceAll("\\.", "\\\\.");
			logger.log(Level.FINEST, "* Found .\n"
					+ "* TFormat changed to " + tFormat);
		}
		
		// If TFormatting contains "<text>", change it to ".*"
		// <text> is TFormat's "any amount of any characters"
		if(tFormat.contains("<text>")){
			tFormat = tFormat.replaceAll("<text>", ".*");
			logger.log(Level.FINEST, "* Found <text>\n"
					+ "* TFormat changed to " + tFormat);
		}
		
		// If TFormatting contains <imagefile>, change it to look for common image file extensions
		// Currently just .jpg (TODO: Add other image file extensions)
		if(tFormat.contains("<imagefile>")){
			tFormat = tFormat.replaceAll("<imagefile>", ".*\\\\.jpg");
			logger.log(Level.FINEST, "* Found <imagefile>\n"
					+ "* TFormat changed to " + tFormat);
		}
		
		// If TFormatting contains <#>, change it to (\d)*
		// <#> is TFormat's "number" requirement
		if(tFormat.contains("<#>")){
			tFormat = tFormat.replaceAll("<#>", "(\\\\d)*");
			logger.log(Level.FINEST, "* Found <#>\n"
					+ "* TFormat changed to " + tFormat);
		}
		
		// If TFormatting contains a $, escape it
		// TFormatting treats dollar sign as a normal character
		if(tFormat.contains("$")){
			tFormat = tFormat.replaceAll("\\$", "\\\\\\$");
			logger.log(Level.FINEST, "* Found $\n"
					+ "* TFormat changed to " + tFormat);
		}
		
		// If TFormatting contains brackets, change from [<whatever>] to (<whatever>)?
		// Brackets represent optionals in TFormatting
		if(tFormat.contains("[")){
			tFormat = tFormat.replaceAll("\\[", "(");
			tFormat = tFormat.replaceAll("\\]", ")?");
			logger.log(Level.FINEST, "* Found [\n"
					+ "* TFormat changed to " + tFormat);
		}
		
		// Log the resulting regex
		logger.log(Level.FINER, "Regex: " + tFormat);
		
		return tFormat;
	}
	
	/**
	 * Convert the given Java regex into TFormatting.
	 * 
	 * @param logger The Logger to log any messages to
	 * @param regex The Java regex to convert to TFormatting
	 * @return The TFormatting resulting from the regex conversion
	 */
	public static String convertRegexToTFormat(Logger logger, String regex){
		// Log the original regex
		logger.log(Level.FINER, "Regex: " + regex);
		
		// Change Java number matching to <#>
		// <#> is TFormatting's number checking
		if(regex.contains("(\\d)*")){
			regex = regex.replaceAll("\\(\\\\d\\)\\*", "<#>");
			logger.log(Level.FINEST, "* Found (\\d)*\n"
					+ "* Regex changed to " + regex);
		}
		
		// Change (<whatever>)? to [<whatever>]
		// Bracketed items are how TFormatting does optionals
		if(regex.contains("(")){
			regex = regex.replaceAll("\\(", "[");
			regex = regex.replaceAll("\\)\\?", "]");
			logger.log(Level.FINEST, "* Found (\n"
					+ "* Regex changed to " + regex);
		}
		
		// Change escaped dollar signs to unescaped ones
		// TFormatting treats dollar signs as regular characters to match on
		if(regex.contains("\\$")){
			// TODO: Handle regular $'s from regexes
			regex = regex.replaceAll("\\\\\\$", "\\$");
			logger.log(Level.FINEST, "* Found \\$\n"
					+ "* Regex changed to " + regex);
		}
		
		// Replace image file check with <imageFile>
		// TFormatting checks for image files with <imageFile>
		if(regex.contains(".*\\.jpg")){
			// TODO: Add other image file types
			regex = regex.replaceAll("\\.\\*\\\\.jpg", "<imagefile>");
			logger.log(Level.FINEST, "* Found .*\\.jpg\n"
					+ "* Regex changed to " + regex);
		}
		
		// Change escaped periods to unescaped ones
		// TFormatting treat periods as regular characters to match on
		if(regex.contains("\\.")){
			regex = regex.replaceAll("\\\\.", ".");
			logger.log(Level.FINEST, "* Found \\.\n"
					+ "* Regex changed to " + regex);
		}
		
		// Change any character matches to <text>
		// TFormatting checks for an arbitrary length any characters using <text>
		if(regex.contains(".*")){
			regex = regex.replaceAll("\\.\\*", "<text>");
			logger.log(Level.FINEST, "* Found .*\n"
					+ "* Regex changed to " + regex);
		}
		
		// Log the resulting TFormatting
		logger.log(Level.FINER, "TFormat: " + regex);
		
		return regex;
	}
}
