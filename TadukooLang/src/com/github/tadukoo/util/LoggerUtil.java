package com.github.tadukoo.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Util functions for dealing with Loggers.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public final class LoggerUtil{
	
	// Not allowed to create a LoggerUtil
	private LoggerUtil(){ }
	
	/**
	 * Creates a new {@link Logger} for the given file with the given {@link Level} 
	 * of logging.
	 * 
	 * @param filepath The path to the File to be used as a log
	 * @param level The Level to log messages at
	 * @return The created Logger
	 * @throws IOException If something goes wrong in creating the file logger
	 */
	public static Logger createFileLogger(String filepath, Level level) throws IOException{
		// Create the file
		FileUtil.createFile(filepath);
		
		// Setup a FileHandler for the File with a SimpleFormatter
		FileHandler fh = new FileHandler(filepath, true);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		
		// Create the Logger with the given level and the created FileHandler
		Logger logger = Logger.getLogger(filepath);
		logger.setLevel(level);
		logger.addHandler(fh);
		
		// Return the newly created Logger
		return logger;
	}
}
