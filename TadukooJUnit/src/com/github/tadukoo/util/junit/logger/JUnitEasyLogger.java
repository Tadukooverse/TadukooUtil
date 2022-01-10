package com.github.tadukoo.util.junit.logger;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.logger.EasyLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit Easy Logger is used when we don't want to create an actual log file during a JUnit test, but
 * want to verify that logging occurred properly. It contains a List of
 * {@link JUnitEasyLoggerEntry JUnit Easy Logger Entries} that can be checked to verify log entries
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public class JUnitEasyLogger extends EasyLogger{
	/** The List of Logger entries for this logger */
	private final List<JUnitEasyLoggerEntry> entries;
	
	/**
	 * JUnit Easy Logger Entry represents a single logged entry in the logger
	 * <br><br>
	 * Contains: {@link Level}, message, and {@link Throwable}
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.1
	 */
	public static record JUnitEasyLoggerEntry(Level level, String message, Throwable throwable){ }
	
	/**
	 * Constructs a new JUnit Easy Logger with a null Logger and empty entries List
	 */
	public JUnitEasyLogger(){
		super(null);
		entries = new ArrayList<>();
	}
	
	/**
	 * @return The List of logger entries for this logger
	 */
	public List<JUnitEasyLoggerEntry> getEntries(){
		return entries;
	}
	
	/**
	 * Will verify that the entries in the given logger contains one element with the given {@link Level},
	 * message, and {@link Throwable}
	 *
	 * @param level The {@link Level} of the entry we're expecting
	 * @param message The message of the entry we're expecting
	 * @param t The {@link Throwable} of the entry we're expecting
	 * @param logger The {@link JUnitEasyLogger} to check entries we actually have
	 */
	public static void assertEntries(
			Level level, String message, Throwable t,
			JUnitEasyLogger logger){
		assertEntries(level, message, t, logger.getEntries());
	}
	
	/**
	 * Will verify that the given actual entries contains one element with the given {@link Level}, message, and
	 * {@link Throwable}
	 *
	 * @param level The {@link Level} of the entry we're expecting
	 * @param message The message of the entry we're expecting
	 * @param t The {@link Throwable} of the entry we're expecting
	 * @param actualEntries The entries we actually have
	 */
	public static void assertEntries(
			Level level, String message, Throwable t,
			List<JUnitEasyLoggerEntry> actualEntries){
		assertEntries(new JUnitEasyLoggerEntry(level, message, t), actualEntries);
	}
	
	/**
	 * Will verify that the entries in the given logger contains one element and matches the expected entry
	 *
	 * @param expectedEntry The entry we're expecting
	 * @param logger The {@link JUnitEasyLogger} to check entries we actually have
	 */
	public static void assertEntries(
			JUnitEasyLoggerEntry expectedEntry,
			JUnitEasyLogger logger){
		assertEntries(expectedEntry, logger.getEntries());
	}
	
	/**
	 * Will verify that the given actual entries contains one element and matches the expected entry
	 *
	 * @param expectedEntry The entry we're expecting
	 * @param actualEntries The entries we actually have
	 */
	public static void assertEntries(
			JUnitEasyLoggerEntry expectedEntry,
			List<JUnitEasyLoggerEntry> actualEntries){
		assertEntries(ListUtil.createList(expectedEntry), actualEntries);
	}
	
	/**
	 * Will verify that the entries in the given logger match the expected entries.
	 *
	 * @param expectedEntries The entries we're expecting
	 * @param logger The {@link JUnitEasyLogger} to check entries we actually have
	 */
	public static void assertEntries(
			List<JUnitEasyLoggerEntry> expectedEntries,
			JUnitEasyLogger logger){
		assertEntries(expectedEntries, logger.getEntries());
	}
	
	/**
	 * Will verify that the given actual entries match the expected entries.
	 *
	 * @param expectedEntries The entries we're expecting
	 * @param actualEntries The entries we actually have
	 */
	public static void assertEntries(
			List<JUnitEasyLoggerEntry> expectedEntries,
			List<JUnitEasyLoggerEntry> actualEntries){
		assertEquals(expectedEntries.size(), actualEntries.size());
		for(int i = 0; i < expectedEntries.size(); i++){
			JUnitEasyLoggerEntry expectedEntry = expectedEntries.get(i);
			JUnitEasyLoggerEntry actualEntry = actualEntries.get(i);
			assertEquals(expectedEntry.level(), actualEntry.level());
			assertEquals(expectedEntry.message(), actualEntry.message());
			assertEquals(expectedEntry.throwable(), actualEntry.throwable());
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public void logInfo(String info){
		entries.add(new JUnitEasyLoggerEntry(Level.INFO, info, null));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logInfo(Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.INFO, null, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logInfo(String info, Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.INFO, info, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logWarning(String warning){
		entries.add(new JUnitEasyLoggerEntry(Level.WARNING, warning, null));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logWarning(Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.WARNING, null, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logWarning(String warning, Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.WARNING, warning, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logError(String error){
		entries.add(new JUnitEasyLoggerEntry(Level.SEVERE, error, null));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logError(Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.SEVERE, null, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logError(String error, Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.SEVERE, error, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logConfig(String config){
		entries.add(new JUnitEasyLoggerEntry(Level.CONFIG, config, null));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logConfig(Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.CONFIG, null, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logConfig(String config, Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.CONFIG, config, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFine(String debug){
		entries.add(new JUnitEasyLoggerEntry(Level.FINE, debug, null));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFine(Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.FINE, null, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFine(String debug, Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.FINE, debug, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFiner(String debug){
		entries.add(new JUnitEasyLoggerEntry(Level.FINER, debug, null));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFiner(Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.FINER, null, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFiner(String debug, Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.FINER, debug, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFinest(String debug){
		entries.add(new JUnitEasyLoggerEntry(Level.FINEST, debug, null));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFinest(Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.FINEST, null, t));
	}
	
	/** {@inheritDoc} */
	@Override
	public void logDebugFinest(String debug, Throwable t){
		entries.add(new JUnitEasyLoggerEntry(Level.FINEST, debug, t));
	}
}
