package com.github.tadukoo.util.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EasyLogger is a wrapper around {@link Logger} that provides methods to simplify logging operations
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class EasyLogger{
	/** The {@link Logger} wrapped in this EasyLogger */
	private final Logger logger;
	
	/**
	 * Wraps the given {@link Logger} as an EasyLogger.
	 *
	 * @param logger The {@link Logger} to be wrapped
	 */
	public EasyLogger(Logger logger){
		this.logger = logger;
	}
	
	/**
	 * @return The {@link Logger} wrapped in this class if you want to call other methods
	 */
	public Logger getLogger(){
		return logger;
	}
	
	/**
	 * Logs the given {@link Level#INFO info} message to the {@link Logger}
	 *
	 * @param info The message to be logged
	 */
	public void logInfo(String info){
		logger.log(Level.INFO, info);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as {@link Level#INFO info}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logInfo(Throwable t){
		logger.log(Level.INFO, t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#INFO info} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param info The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logInfo(String info, Throwable t){
		logger.log(Level.INFO, info, t);
	}
	
	/**
	 * Logs the given {@link Level#WARNING warning} message to the {@link Logger}
	 *
	 * @param warning The message to be logged
	 */
	public void logWarning(String warning){
		logger.log(Level.WARNING, warning);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as a {@link Level#WARNING warning}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logWarning(Throwable t){
		logger.log(Level.WARNING, t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#WARNING warning} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param warning The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logWarning(String warning, Throwable t){
		logger.log(Level.WARNING, warning, t);
	}
	
	/**
	 * Logs the given {@link Level#SEVERE error} message to the {@link Logger}
	 *
	 * @param error The message to be logged
	 */
	public void logError(String error){
		logger.log(Level.SEVERE, error);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as an {@link Level#SEVERE error}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logError(Throwable t){
		logger.log(Level.SEVERE, t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#SEVERE error} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param error The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logError(String error, Throwable t){
		logger.log(Level.SEVERE, error, t);
	}
	
	/**
	 * Logs the given {@link Level#CONFIG config} message to the {@link Logger}
	 *
	 * @param config The message to be logged
	 */
	public void logConfig(String config){
		logger.log(Level.CONFIG, config);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as a {@link Level#CONFIG config} message
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logConfig(Throwable t){
		logger.log(Level.CONFIG, t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#CONFIG config} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param config The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logConfig(String config, Throwable t){
		logger.log(Level.CONFIG, config, t);
	}
	
	/**
	 * Logs the given debug message to the {@link Logger} at {@link Level#FINE}
	 *
	 * @param debug The message to be logged
	 */
	public void logDebugFine(String debug){
		logger.log(Level.FINE, debug);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged at {@link Level#FINE}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFine(Throwable t){
		logger.log(Level.FINE, t.getMessage(), t);
	}
	
	/**
	 * Logs the given debug message with the given {@link Throwable} to the {@link Logger} at {@link Level#FINE}
	 *
	 * @param debug The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFine(String debug, Throwable t){
		logger.log(Level.FINE, debug, t);
	}
	
	/**
	 * Logs the given debug message to the {@link Logger} at {@link Level#FINER}
	 *
	 * @param debug The message to be logged
	 */
	public void logDebugFiner(String debug){
		logger.log(Level.FINER, debug);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged at {@link Level#FINER}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFiner(Throwable t){
		logger.log(Level.FINER, t.getMessage(), t);
	}
	
	/**
	 * Logs the given debug message with the given {@link Throwable} to the {@link Logger} at {@link Level#FINER}
	 *
	 * @param debug The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFiner(String debug, Throwable t){
		logger.log(Level.FINER, debug, t);
	}
	
	/**
	 * Logs the given debug message to the {@link Logger} at {@link Level#FINEST}
	 *
	 * @param debug The message to be logged
	 */
	public void logDebugFinest(String debug){
		logger.log(Level.FINEST, debug);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged at {@link Level#FINEST}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFinest(Throwable t){
		logger.log(Level.FINEST, t.getMessage(), t);
	}
	
	/**
	 * Logs the given debug message with the given {@link Throwable} to the {@link Logger} at {@link Level#FINEST}
	 *
	 * @param debug The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFinest(String debug, Throwable t){
		logger.log(Level.FINEST, debug, t);
	}
}
