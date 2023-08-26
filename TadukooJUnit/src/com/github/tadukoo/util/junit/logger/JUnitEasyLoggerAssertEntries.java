package com.github.tadukoo.util.junit.logger;

import com.github.tadukoo.util.ListUtil;

import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit Easy Logger Assert Entries is used to assert that the entries contained in a
 * {@link JUnitEasyLogger} are correct
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public class JUnitEasyLoggerAssertEntries{
	
	/** Not allowed to instantiate {@link JUnitEasyLoggerAssertEntries} */
	private JUnitEasyLoggerAssertEntries(){ }
	
	/**
	 * Asserts that the expected entry matches the actual entry
	 *
	 * @param expectedEntry The expected version of the entry
	 * @param actualEntry The actual version of the entry
	 */
	public static void assertEntryEquals(JUnitEasyLoggerEntry expectedEntry, JUnitEasyLoggerEntry actualEntry){
		assertEquals(expectedEntry.level(), actualEntry.level());
		assertEquals(expectedEntry.message(), actualEntry.message());
		assertEquals(expectedEntry.throwable(), actualEntry.throwable());
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
	public static void assertEntries(Level level, String message, Throwable t, JUnitEasyLogger logger){
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
			Level level, String message, Throwable t, List<JUnitEasyLoggerEntry> actualEntries){
		assertEntries(new JUnitEasyLoggerEntry(level, message, t), actualEntries);
	}
	
	/**
	 * Will verify that the entries in the given logger contains one element and matches the expected entry
	 *
	 * @param expectedEntry The entry we're expecting
	 * @param logger The {@link JUnitEasyLogger} to check entries we actually have
	 */
	public static void assertEntries(JUnitEasyLoggerEntry expectedEntry, JUnitEasyLogger logger){
		assertEntries(expectedEntry, logger.getEntries());
	}
	
	/**
	 * Will verify that the given actual entries contains one element and matches the expected entry
	 *
	 * @param expectedEntry The entry we're expecting
	 * @param actualEntries The entries we actually have
	 */
	public static void assertEntries(JUnitEasyLoggerEntry expectedEntry, List<JUnitEasyLoggerEntry> actualEntries){
		assertEntries(ListUtil.createList(expectedEntry), actualEntries);
	}
	
	/**
	 * Will verify that the entries in the given logger match the expected entries.
	 *
	 * @param expectedEntries The entries we're expecting
	 * @param logger The {@link JUnitEasyLogger} to check entries we actually have
	 */
	public static void assertEntries(List<JUnitEasyLoggerEntry> expectedEntries, JUnitEasyLogger logger){
		assertEntries(expectedEntries, logger.getEntries());
	}
	
	/**
	 * Will verify that the given actual entries match the expected entries.
	 *
	 * @param expectedEntries The entries we're expecting
	 * @param actualEntries The entries we actually have
	 */
	public static void assertEntries(
			List<JUnitEasyLoggerEntry> expectedEntries, List<JUnitEasyLoggerEntry> actualEntries){
		assertEquals(expectedEntries.size(), actualEntries.size());
		for(int i = 0; i < expectedEntries.size(); i++){
			JUnitEasyLoggerEntry expectedEntry = expectedEntries.get(i);
			JUnitEasyLoggerEntry actualEntry = actualEntries.get(i);
			assertEntryEquals(expectedEntry, actualEntry);
		}
	}
	
	/**
	 * Checks that the last entry in the given {@link JUnitEasyLogger} matches the given information
	 *
	 * @param level The expected {@link Level} of the last entry in the logger
	 * @param message The expected message of the last entry in the logger
	 * @param t The expected {@link Throwable} of the last entry in the logger
	 * @param logger The {@link JUnitEasyLogger} to check the last entry in
	 */
	public static void assertLastEntry(Level level, String message, Throwable t, JUnitEasyLogger logger){
		assertLastEntry(level, message, t, logger.getEntries());
	}
	
	/**
	 * Checks that the last entry in the given {@link JUnitEasyLoggerEntry entries list} matches the given information
	 *
	 * @param level The expected {@link Level} of the last entry in the list
	 * @param message The expected message of the last entry in the list
	 * @param t The expected {@link Throwable} of the last entry in the list
	 * @param actualEntries The list of {@link JUnitEasyLoggerEntry entries} to check the last entry in
	 */
	public static void assertLastEntry(
			Level level, String message, Throwable t, List<JUnitEasyLoggerEntry> actualEntries){
		assertLastEntry(new JUnitEasyLoggerEntry(level, message, t), actualEntries);
	}
	
	/**
	 * Checks that the last entry in the given {@link JUnitEasyLogger} matches the given {@link JUnitEasyLoggerEntry entry}
	 *
	 * @param expectedEntry The expected {@link JUnitEasyLoggerEntry entry} that should match the last entry of the logger
	 * @param logger The {@link JUnitEasyLogger} to check the last entry in
	 */
	public static void assertLastEntry(JUnitEasyLoggerEntry expectedEntry, JUnitEasyLogger logger){
		assertLastEntry(expectedEntry, logger.getEntries());
	}
	
	/**
	 * Checks that the last entry in the given {@link JUnitEasyLoggerEntry entry list} matches the given
	 * {@link JUnitEasyLoggerEntry entry}
	 *
	 * @param expectedEntry The expected {@link JUnitEasyLoggerEntry entry} that should match the last entry of the list
	 * @param actualEntries The list of {@link JUnitEasyLoggerEntry entries} to check the last entry in
	 */
	public static void assertLastEntry(JUnitEasyLoggerEntry expectedEntry, List<JUnitEasyLoggerEntry> actualEntries){
		assertLastEntries(ListUtil.createList(expectedEntry), actualEntries);
	}
	
	/**
	 * Checks that the last entries in the given {@link JUnitEasyLogger} match the given
	 * {@link JUnitEasyLoggerEntry entry list}
	 *
	 * @param expectedEntries The expected {@link JUnitEasyLoggerEntry entry list} that should match the last entries
	 * of the given logger
	 * @param logger The {@link JUnitEasyLogger} to check the last entries in
	 */
	public static void assertLastEntries(List<JUnitEasyLoggerEntry> expectedEntries, JUnitEasyLogger logger){
		assertLastEntries(expectedEntries, logger.getEntries());
	}
	
	/**
	 * Checks that the last entries in the given {@link JUnitEasyLogger} match the given
	 * {@link JUnitEasyLoggerEntry entry list}
	 *
	 * @param expectedEntries The expected {@link JUnitEasyLoggerEntry entry list} that should match the last entries
	 * of the given logger
	 * @param actualEntries The list of {@link JUnitEasyLoggerEntry entries} to check the last entries in
	 */
	public static void assertLastEntries(
			List<JUnitEasyLoggerEntry> expectedEntries, List<JUnitEasyLoggerEntry> actualEntries){
		assertTrue(actualEntries.size() >= expectedEntries.size(), "Found " +
				actualEntries.size() + " actual entries when expecting at least " + expectedEntries.size() + " entries.");
		int sizeDif = actualEntries.size() - expectedEntries.size();
		for(int i = expectedEntries.size() - 1; i >= 0; i--){
			JUnitEasyLoggerEntry expectedEntry = expectedEntries.get(i);
			JUnitEasyLoggerEntry actualEntry = actualEntries.get(i+sizeDif);
			assertEntryEquals(expectedEntry, actualEntry);
		}
	}
}
