package com.github.tadukoo.util.junit.logger;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.logging.Level;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_TRUE_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static com.github.tadukoo.util.junit.logger.JUnitEasyLoggerAssertEntries.assertEntries;
import static com.github.tadukoo.util.junit.logger.JUnitEasyLoggerAssertEntries.assertLastEntries;
import static com.github.tadukoo.util.junit.logger.JUnitEasyLoggerAssertEntries.assertLastEntry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
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
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryParams(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.SEVERE, "test message", null, logger.getEntries());
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryParams(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.INFO, "test message 2", null, logger.getEntries());
			fail();
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
			fail();
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
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongLevelSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.SEVERE, "test message", null, logger);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEntriesWrongMessageSingleEntryParamsLogger(){
		try{
			logger.logInfo("test message");
			assertEntries(Level.INFO, "test message 2", null, logger);
			fail();
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
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(t, null), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntryPiecesAndLoggerSuccess(){
		Throwable t = new IllegalArgumentException("yep");
		logger.logError("something");
		logger.logInfo("some info", t);
		assertLastEntry(Level.INFO, "some info", t, logger);
	}
	
	@Test
	public void testAssertLastEntryPiecesAndLoggerFail(){
		Throwable t = new IllegalArgumentException("yep");
		try{
			logger.logError("something");
			logger.logInfo("some info", t);
			assertLastEntry(Level.SEVERE, "some info", t, logger);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntryPiecesAndEntryListSuccess(){
		Throwable t = new IllegalArgumentException("yep");
		logger.logError("something");
		logger.logInfo("some info", t);
		assertLastEntry(Level.INFO, "some info", t, logger.getEntries());
	}
	
	@Test
	public void testAssertLastEntryPiecesAndEntryListFail(){
		Throwable t = new IllegalArgumentException("yep");
		try{
			logger.logError("something");
			logger.logInfo("some info", t);
			assertLastEntry(Level.SEVERE, "some info", t, logger.getEntries());
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntryEntryAndLoggerSuccess(){
		Throwable t = new IllegalArgumentException("yep");
		logger.logError("something");
		logger.logInfo("some info", t);
		assertLastEntry(new JUnitEasyLoggerEntry(Level.INFO, "some info", t), logger);
	}
	
	@Test
	public void testAssertLastEntryEntryAndLoggerFail(){
		Throwable t = new IllegalArgumentException("yep");
		try{
			logger.logError("something");
			logger.logInfo("some info", t);
			assertLastEntry(new JUnitEasyLoggerEntry(Level.SEVERE, "some info", t), logger);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntryEntryAndEntryListSuccess(){
		Throwable t = new IllegalArgumentException("yep");
		logger.logError("something");
		logger.logInfo("some info", t);
		assertLastEntry(new JUnitEasyLoggerEntry(Level.INFO, "some info", t), logger.getEntries());
	}
	
	@Test
	public void testAssertLastEntryEntryAndEntryListFail(){
		Throwable t = new IllegalArgumentException("yep");
		try{
			logger.logError("something");
			logger.logInfo("some info", t);
			assertLastEntry(new JUnitEasyLoggerEntry(Level.SEVERE, "some info", t), logger.getEntries());
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.SEVERE, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntriesLoggerSuccess(){
		Throwable t = new IllegalArgumentException("yep");
		logger.logConfig("nope");
		logger.logError("something");
		logger.logInfo("some info", t);
		assertLastEntries(ListUtil.createList(
				new JUnitEasyLoggerEntry(Level.SEVERE, "something", null),
				new JUnitEasyLoggerEntry(Level.INFO, "some info", t)), logger);
	}
	
	@Test
	public void testAssertLastEntriesLoggerFail(){
		try{
			Throwable t = new IllegalArgumentException("yep");
			logger.logConfig("nope");
			logger.logError("something");
			logger.logInfo("some info", t);
			assertLastEntries(ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.SEVERE, "something", null),
					new JUnitEasyLoggerEntry(Level.CONFIG, "some info", t)), logger);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.CONFIG, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntriesLoggerFailNotEvenEntries(){
		try{
			Throwable t = new IllegalArgumentException("yep");
			logger.logInfo("some info", t);
			assertLastEntries(ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.SEVERE, "something", null),
					new JUnitEasyLoggerEntry(Level.CONFIG, "some info", t)), logger);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError(
					"Found 1 actual entries when expecting at least 2 entries.", ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntriesEntryListSuccess(){
		Throwable t = new IllegalArgumentException("yep");
		logger.logConfig("nope");
		logger.logError("something");
		logger.logInfo("some info", t);
		assertLastEntries(ListUtil.createList(
				new JUnitEasyLoggerEntry(Level.SEVERE, "something", null),
				new JUnitEasyLoggerEntry(Level.INFO, "some info", t)), logger.getEntries());
	}
	
	@Test
	public void testAssertLastEntriesEntryListFail(){
		try{
			Throwable t = new IllegalArgumentException("yep");
			logger.logConfig("nope");
			logger.logError("something");
			logger.logInfo("some info", t);
			assertLastEntries(ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.SEVERE, "something", null),
					new JUnitEasyLoggerEntry(Level.CONFIG, "some info", t)), logger.getEntries());
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(Level.CONFIG, Level.INFO), e.getMessage());
		}
	}
	
	@Test
	public void testAssertLastEntriesEntryListFailNotEvenEntries(){
		try{
			Throwable t = new IllegalArgumentException("yep");
			logger.logInfo("some info", t);
			assertLastEntries(ListUtil.createList(
					new JUnitEasyLoggerEntry(Level.SEVERE, "something", null),
					new JUnitEasyLoggerEntry(Level.CONFIG, "some info", t)), logger.getEntries());
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError(
					"Found 1 actual entries when expecting at least 2 entries.", ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
}
