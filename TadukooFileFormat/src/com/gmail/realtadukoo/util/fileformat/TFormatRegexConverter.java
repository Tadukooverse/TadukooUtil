package com.gmail.realtadukoo.util.fileformat;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface TFormatRegexConverter{
	
	public static String convertTFormatToRegex(Logger logger, String tFormat){
		logger.log(Level.FINER, "TFormat: " + tFormat);
		if(tFormat.contains(".")){
			tFormat = tFormat.replaceAll("\\.", "\\\\.");
			logger.log(Level.FINEST, "* Found .\n"
					+ "* TFormat changed to " + tFormat);
		}
		if(tFormat.contains("<text>")){
			tFormat = tFormat.replaceAll("<text>", ".*");
			logger.log(Level.FINEST, "* Found <text>\n"
					+ "* TFormat changed to " + tFormat);
		}
		if(tFormat.contains("<imagefile>")){
			// TODO: Add other image file types
			tFormat = tFormat.replaceAll("<imagefile>", ".*\\\\.jpg");
			logger.log(Level.FINEST, "* Found <imagefile>\n"
					+ "* TFormat changed to " + tFormat);
		}
		if(tFormat.contains("<#>")){
			tFormat = tFormat.replaceAll("<#>", "(\\\\d)*");
			logger.log(Level.FINEST, "* Found <#>\n"
					+ "* TFormat changed to " + tFormat);
		}
		if(tFormat.contains("$")){
			tFormat = tFormat.replaceAll("\\$", "\\\\\\$");
			logger.log(Level.FINEST, "* Found $\n"
					+ "* TFormat changed to " + tFormat);
		}
		if(tFormat.contains("[")){
			tFormat = tFormat.replaceAll("\\[", "(");
			tFormat = tFormat.replaceAll("\\]", ")?");
			logger.log(Level.FINEST, "* Found [\n"
					+ "* TFormat changed to " + tFormat);
		}
		logger.log(Level.FINER, "Regex: " + tFormat);
		return tFormat;
	}
	
	public static String convertRegexToTFormat(Logger logger, String regex){
		logger.log(Level.FINER, "Regex: " + regex);
		if(regex.contains("(\\d)*")){
			regex = regex.replaceAll("\\(\\\\d\\)\\*", "<#>");
			logger.log(Level.FINEST, "* Found (\\d)*\n"
					+ "* Regex changed to " + regex);
		}
		if(regex.contains("(")){
			regex = regex.replaceAll("\\(", "[");
			regex = regex.replaceAll("\\)\\?", "]");
			logger.log(Level.FINEST, "* Found (\n"
					+ "* Regex changed to " + regex);
		}
		if(regex.contains("\\$")){
			regex = regex.replaceAll("\\\\\\$", "\\$");
			logger.log(Level.FINEST, "* Found \\$\n"
					+ "* Regex changed to " + regex);
		}
		if(regex.contains(".*\\.jpg")){
			// TODO: Add other image file types
			regex = regex.replaceAll("\\.\\*\\\\.jpg", "<imagefile>");
			logger.log(Level.FINEST, "* Found .*\\.jpg\n"
					+ "* Regex changed to " + regex);
		}
		if(regex.contains("\\.")){
			regex = regex.replaceAll("\\\\.", ".");
			logger.log(Level.FINEST, "* Found \\.\n"
					+ "* Regex changed to " + regex);
		}
		if(regex.contains(".*")){
			regex = regex.replaceAll("\\.\\*", "<text>");
			logger.log(Level.FINEST, "* Found .*\n"
					+ "* Regex changed to " + regex);
		}
		logger.log(Level.FINER, "TFormat: " + regex);
		return regex;
	}
}
