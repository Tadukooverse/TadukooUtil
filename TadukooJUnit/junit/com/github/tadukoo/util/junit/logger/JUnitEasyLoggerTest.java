package com.github.tadukoo.util.junit.logger;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static com.github.tadukoo.util.junit.logger.JUnitEasyLoggerAssertEntries.assertEntries;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitEasyLoggerTest{
	private final JUnitEasyLogger logger = new JUnitEasyLogger();
	
	@Test
	public void testNullLogger(){
		assertNull(logger.getLogger());
	}
	
	@Test
	public void testInitialEmptyEntries(){
		assertTrue(logger.getEntries().isEmpty());
	}
	
	@Test
	public void testClear(){
		logger.logInfo("Yep");
		assertFalse(logger.getEntries().isEmpty());
		logger.clear();
		assertTrue(logger.getEntries().isEmpty());
	}
	
	@Test
	public void testLogInfoString(){
		logger.logInfo("test info");
		assertEntries(Level.INFO, "test info", null, logger);
	}
	
	@Test
	public void testLogInfoThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logInfo(t);
		assertEntries(Level.INFO, null, t, logger);
	}
	
	@Test
	public void testLogInfoBoth(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logInfo("test info", t);
		assertEntries(Level.INFO, "test info", t, logger);
	}
	
	@Test
	public void testLogWarningString(){
		logger.logWarning("test info");
		assertEntries(Level.WARNING, "test info", null, logger);
	}
	
	@Test
	public void testLogWarningThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logWarning(t);
		assertEntries(Level.WARNING, null, t, logger);
	}
	
	@Test
	public void testLogWarningBoth(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logWarning("test info", t);
		assertEntries(Level.WARNING, "test info", t, logger);
	}
	
	@Test
	public void testLogErrorString(){
		logger.logError("test info");
		assertEntries(Level.SEVERE, "test info", null, logger);
	}
	
	@Test
	public void testLogErrorThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logError(t);
		assertEntries(Level.SEVERE, null, t, logger);
	}
	
	@Test
	public void testLogErrorBoth(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logError("test info", t);
		assertEntries(Level.SEVERE, "test info", t, logger);
	}
	
	@Test
	public void testLogConfigString(){
		logger.logConfig("test info");
		assertEntries(Level.CONFIG, "test info", null, logger);
	}
	
	@Test
	public void testLogConfigThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logConfig(t);
		assertEntries(Level.CONFIG, null, t, logger);
	}
	
	@Test
	public void testLogConfigBoth(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logConfig("test info", t);
		assertEntries(Level.CONFIG, "test info", t, logger);
	}
	
	@Test
	public void testLogDebugFineString(){
		logger.logDebugFine("test info");
		assertEntries(Level.FINE, "test info", null, logger);
	}
	
	@Test
	public void testLogDebugFineThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logDebugFine(t);
		assertEntries(Level.FINE, null, t, logger);
	}
	
	@Test
	public void testLogDebugFineBoth(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logDebugFine("test info", t);
		assertEntries(Level.FINE, "test info", t, logger);
	}
	
	@Test
	public void testLogDebugFinerString(){
		logger.logDebugFiner("test info");
		assertEntries(Level.FINER, "test info", null, logger);
	}
	
	@Test
	public void testLogDebugFinerThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logDebugFiner(t);
		assertEntries(Level.FINER, null, t, logger);
	}
	
	@Test
	public void testLogDebugFinerBoth(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logDebugFiner("test info", t);
		assertEntries(Level.FINER, "test info", t, logger);
	}
	
	@Test
	public void testLogDebugFinestString(){
		logger.logDebugFinest("test info");
		assertEntries(Level.FINEST, "test info", null, logger);
	}
	
	@Test
	public void testLogDebugFinestThrowable(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logDebugFinest(t);
		assertEntries(Level.FINEST, null, t, logger);
	}
	
	@Test
	public void testLogDebugFinestBoth(){
		Throwable t = new IllegalArgumentException("something wrong");
		logger.logDebugFinest("test info", t);
		assertEntries(Level.FINEST, "test info", t, logger);
	}
}
