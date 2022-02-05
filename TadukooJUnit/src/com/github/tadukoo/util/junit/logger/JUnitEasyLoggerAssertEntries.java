package com.github.tadukoo.util.junit.logger;

import com.github.tadukoo.util.ListUtil;

import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit Easy Logger Assert Entries is used to assert that the entries contained in a
 * {@link JUnitEasyLogger} are correct
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public class JUnitEasyLoggerAssertEntries{
	
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
			assertEquals(expectedEntry.level(), actualEntry.level());
			assertEquals(expectedEntry.message(), actualEntry.message());
			assertEquals(expectedEntry.throwable(), actualEntry.throwable());
		}
	}
}
