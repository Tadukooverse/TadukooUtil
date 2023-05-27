package com.github.tadukoo.util.logger;

import com.github.tadukoo.util.stack.StackUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EasyLogger is a wrapper around {@link Logger} that provides methods to simplify logging operations
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Alpha v.0.2
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
		logger.logp(Level.INFO, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), info);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as {@link Level#INFO info}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logInfo(Throwable t){
		logger.logp(Level.INFO, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#INFO info} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param info The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logInfo(String info, Throwable t){
		logger.logp(Level.INFO, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), info, t);
	}
	
	/**
	 * Logs the given {@link Level#WARNING warning} message to the {@link Logger}
	 *
	 * @param warning The message to be logged
	 */
	public void logWarning(String warning){
		logger.logp(Level.WARNING, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), warning);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as a {@link Level#WARNING warning}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logWarning(Throwable t){
		logger.logp(Level.WARNING, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#WARNING warning} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param warning The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logWarning(String warning, Throwable t){
		logger.logp(Level.WARNING, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), warning, t);
	}
	
	/**
	 * Logs the given {@link Level#SEVERE error} message to the {@link Logger}
	 *
	 * @param error The message to be logged
	 */
	public void logError(String error){
		logger.logp(Level.SEVERE, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), error);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as an {@link Level#SEVERE error}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logError(Throwable t){
		logger.logp(Level.SEVERE, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#SEVERE error} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param error The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logError(String error, Throwable t){
		logger.logp(Level.SEVERE, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), error, t);
	}
	
	/**
	 * Logs the given {@link Level#CONFIG config} message to the {@link Logger}
	 *
	 * @param config The message to be logged
	 */
	public void logConfig(String config){
		logger.logp(Level.CONFIG, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), config);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged as a {@link Level#CONFIG config} message
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logConfig(Throwable t){
		logger.logp(Level.CONFIG, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), t.getMessage(), t);
	}
	
	/**
	 * Logs the given {@link Level#CONFIG config} message with the given {@link Throwable} to the {@link Logger}
	 *
	 * @param config The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logConfig(String config, Throwable t){
		logger.logp(Level.CONFIG, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), config, t);
	}
	
	/**
	 * Logs the given debug message to the {@link Logger} at {@link Level#FINE}
	 *
	 * @param debug The message to be logged
	 */
	public void logDebugFine(String debug){
		logger.logp(Level.FINE, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), debug);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged at {@link Level#FINE}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFine(Throwable t){
		logger.logp(Level.FINE, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), t.getMessage(), t);
	}
	
	/**
	 * Logs the given debug message with the given {@link Throwable} to the {@link Logger} at {@link Level#FINE}
	 *
	 * @param debug The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFine(String debug, Throwable t){
		logger.logp(Level.FINE, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), debug, t);
	}
	
	/**
	 * Logs the given debug message to the {@link Logger} at {@link Level#FINER}
	 *
	 * @param debug The message to be logged
	 */
	public void logDebugFiner(String debug){
		logger.logp(Level.FINER, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), debug);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged at {@link Level#FINER}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFiner(Throwable t){
		logger.logp(Level.FINER, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), t.getMessage(), t);
	}
	
	/**
	 * Logs the given debug message with the given {@link Throwable} to the {@link Logger} at {@link Level#FINER}
	 *
	 * @param debug The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFiner(String debug, Throwable t){
		logger.logp(Level.FINER, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), debug, t);
	}
	
	/**
	 * Logs the given debug message to the {@link Logger} at {@link Level#FINEST}
	 *
	 * @param debug The message to be logged
	 */
	public void logDebugFinest(String debug){
		logger.logp(Level.FINEST, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), debug);
	}
	
	/**
	 * Logs the given {@link Throwable} to the {@link Logger}. Uses {@link Throwable#getMessage()} as
	 * the message on the log entry. This entry is logged at {@link Level#FINEST}
	 *
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFinest(Throwable t){
		logger.logp(Level.FINEST, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), t.getMessage(), t);
	}
	
	/**
	 * Logs the given debug message with the given {@link Throwable} to the {@link Logger} at {@link Level#FINEST}
	 *
	 * @param debug The message to be logged
	 * @param t The {@link Throwable} to be logged
	 */
	public void logDebugFinest(String debug, Throwable t){
		logger.logp(Level.FINEST, StackUtil.getCallingClassName(), StackUtil.getCallingMethodName(), debug, t);
	}
}
