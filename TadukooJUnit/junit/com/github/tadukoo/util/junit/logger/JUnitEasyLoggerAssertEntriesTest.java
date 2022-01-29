package com.github.tadukoo.util.junit.logger;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.logging.Level;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitEasyLoggerAssertEntriesTest{
	private final JUnitEasyLogger logger = new JUnitEasyLogger();
	
	@Test
	public void testAssertEntriesSingleEntry(){
		JUnitEasyLogger.JUnitEasyLoggerEntry entry =
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
		logger.logInfo("test message");
		JUnitEasyLogger.assertEntries(expectedEntries, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesMultiEntry(){
		List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null),
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
		logger.logInfo("test message");
		logger.logDebugFinest("debug test");
		JUnitEasyLogger.assertEntries(expectedEntries, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesExtraInExpected(){
		try{
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null),
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(2, 1), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesExtraInActual(){
		try{
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null));
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			JUnitEasyLogger.assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevel(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessage(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntryLogger(){
		JUnitEasyLogger.JUnitEasyLoggerEntry entry =
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
		logger.logInfo("test message");
		JUnitEasyLogger.assertEntries(expectedEntries, logger);
	}
	
	@Test
	public void testAssertEntriesMultiEntryLogger(){
		List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null),
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
		logger.logInfo("test message");
		logger.logDebugFinest("debug test");
		JUnitEasyLogger.assertEntries(expectedEntries, logger);
	}
	
	@Test
	public void testAssertEntriesExtraInExpectedLogger(){
		try{
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null),
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.FINEST, "debug test", null));
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(2, 1), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesExtraInActualLogger(){
		try{
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null));
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			JUnitEasyLogger.assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelLogger(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageLogger(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableLogger(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			List<JUnitEasyLogger.JUnitEasyLoggerEntry> expectedEntries = ListUtil.createList(entry);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(expectedEntries, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntry(){
		JUnitEasyLogger.JUnitEasyLoggerEntry entry =
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		logger.logInfo("test message");
		JUnitEasyLogger.assertEntries(entry, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntry(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null);
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			JUnitEasyLogger.assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntry(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntry(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntry(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(entry, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntryLogger(){
		JUnitEasyLogger.JUnitEasyLoggerEntry entry =
				new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null);
		logger.logInfo("test message");
		JUnitEasyLogger.assertEntries(entry, logger);
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntryLogger(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", null);
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			JUnitEasyLogger.assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryLogger(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.SEVERE, "test message", null);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryLogger(){
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message 2", null);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntryLogger(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			JUnitEasyLogger.JUnitEasyLoggerEntry entry =
					new JUnitEasyLogger.JUnitEasyLoggerEntry(Level.INFO, "test message", t);
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(entry, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntryParams(){
		logger.logInfo("test message");
		JUnitEasyLogger.assertEntries(Level.INFO, "test message", null, logger.getEntries());
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntryParams(){
		try{
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			JUnitEasyLogger.assertEntries(Level.INFO, "test message", null, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryParams(){
		try{
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(Level.SEVERE, "test message", null, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryParams(){
		try{
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(Level.INFO, "test message 2", null, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntryParams(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(Level.INFO, "test message", t, logger.getEntries());
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesSingleEntrySingleEntryParamsLogger(){
		logger.logInfo("test message");
		JUnitEasyLogger.assertEntries(Level.INFO, "test message", null, logger);
	}
	
	@Test
	public void testAssertEntriesExtraInActualSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			logger.logDebugFinest("debug test");
			JUnitEasyLogger.assertEntries(Level.INFO, "test message", null, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(Level.SEVERE, "test message", null, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(Level.INFO, "test message 2", null, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError("test message 2", "test message"), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongThrowableSingleEntryParamsLogger(){
		Throwable t = new IllegalArgumentException("something wrong");
		try{
			logger.logInfo("test message");
			JUnitEasyLogger.assertEntries(Level.INFO, "test message", t, logger);
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
}
