package com.github.tadukoo.util.junit.logger;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.logging.Level;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.logger.JUnitEasyLoggerAssertEntries.assertEntries;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitEasyLoggerAssertEntriesTest{
	private final JUnitEasyLogger logger = new JUnitEasyLogger();
	
	@Test
	public void testAssertEntriesSingleEntry(){
		JUnitEasyLoggerEntry entry =
				new JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
		logger.logInfo("test message");
		assertEntries(expectedEntries, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesMultiEntry(){
		List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
				new JUnitEasyLoggerEntry(Level.INFO, "test message", null),
				new JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
		logger.logInfo("test message");
		logger.logDebugFinest("debug test");
		assertEntries(expectedEntries, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesExtraInExpected(){
		try{
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.INFO, "test message", null),
					new JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(2, 1), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesExtraInActual(){
		try{
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.INFO, "test message", null));
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevel(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessage(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntryLogger(){
		JUnitEasyLoggerEntry entry =
				new JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
		logger.logInfo("test message");
		assertEntries(expectedEntries, logger);
	}
	
	@Test
	public void testAssertEntriesMultiEntryLogger(){
		List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
				new JUnitEasyLoggerEntry(Level.INFO, "test message", null),
				new JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
		logger.logInfo("test message");
		logger.logDebugFinest("debug test");
		assertEntries(expectedEntries, logger);
	}
	
	@Test
	public void testAssertEntriesExtraInExpectedLogger(){
		try{
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.INFO, "test message", null),
					new JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(2, 1), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesExtraInActualLogger(){
		try{
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.INFO, "test message", null));
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelLogger(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageLogger(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableLogger(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			List<JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntry(){
		JUnitEasyLoggerEntry entry =
				new JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		logger.logInfo("test message");
		assertEntries(entry, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntry(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message", null);
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntry(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			logger.logInfo("test message");
			assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntry(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			logger.logInfo("test message");
			assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntry(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			logger.logInfo("test message");
			assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntryLogger(){
		JUnitEasyLoggerEntry entry =
				new JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		logger.logInfo("test message");
		assertEntries(entry, logger);
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntryLogger(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message", null);
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryLogger(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			logger.logInfo("test message");
			assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryLogger(){
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			logger.logInfo("test message");
			assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntryLogger(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLoggerEntry entry =
					new JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			logger.logInfo("test message");
			assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntryParams(){
		logger.logInfo("test message");
		assertEntries(Level.INFO, "test message", null, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntryParams(){
		try{
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			assertEntries(Level.INFO, "test message", null, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryParams(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.SEVERE, "test message", null, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryParams(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.INFO, "test message 2", null, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntryParams(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			logger.logInfo("test message");
			assertEntries(Level.INFO, "test message", t, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntryParamsLogger(){
		logger.logInfo("test message");
		assertEntries(Level.INFO, "test message", null, logger);
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			assertEntries(Level.INFO, "test message", null, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.SEVERE, "test message", null, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.INFO, "test message 2", null, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntryParamsLogger(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			logger.logInfo("test message");
			assertEntries(Level.INFO, "test message", t, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
}
