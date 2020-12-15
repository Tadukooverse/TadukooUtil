package com.github.tadukoo.util.logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EasyLoggerTest{
	// DummyLogger to test EasyLogger
	private static class DummyLogger extends Logger{
		private Level level = null;
		private String message = null;
		private Throwable t = null;
		
		public DummyLogger(String name){
			super(name, null);
		}
		
		@Override
		public void log(Level level, String message){
			this.level = level;
			this.message = message;
		}
		
		@Override
		public void log(Level level, String message, Throwable t){
			this.level = level;
			this.message = message;
			this.t = t;
		}
		
		public Level getLevel(){
			return level;
		}
		
		public String getMessage(){
			return message;
		}
		
		public Throwable getT(){
			return t;
		}
	}
	
	private EasyLogger logger;
	private DummyLogger actualLogger;
	
	@BeforeEach
	public void setup(){
		actualLogger = new DummyLogger("Logger");
		logger = new EasyLogger(actualLogger);
	}
	
	@Test
	public void testGetLogger(){
		assertEquals(actualLogger, logger.getLogger());
	}
	
	@Test
	public void testLogInfo(){
		logger.logInfo("Some info");
		assertEquals(Level.INFO, actualLogger.getLevel());
		assertEquals("Some info", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogInfoErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logInfo(e);
		assertEquals(Level.INFO, actualLogger.getLevel());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogInfoBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logInfo("The info", e);
		assertEquals(Level.INFO, actualLogger.getLevel());
		assertEquals("The info", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogWarning(){
		logger.logWarning("Some warning");
		assertEquals(Level.WARNING, actualLogger.getLevel());
		assertEquals("Some warning", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogWarningErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logWarning(e);
		assertEquals(Level.WARNING, actualLogger.getLevel());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogWarningBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logWarning("The warning", e);
		assertEquals(Level.WARNING, actualLogger.getLevel());
		assertEquals("The warning", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogError(){
		logger.logError("Some error");
		assertEquals(Level.SEVERE, actualLogger.getLevel());
		assertEquals("Some error", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogErrorErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logError(e);
		assertEquals(Level.SEVERE, actualLogger.getLevel());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogErrorBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logError("The error", e);
		assertEquals(Level.SEVERE, actualLogger.getLevel());
		assertEquals("The error", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogConfig(){
		logger.logConfig("Some config");
		assertEquals(Level.CONFIG, actualLogger.getLevel());
		assertEquals("Some config", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogConfigErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logConfig(e);
		assertEquals(Level.CONFIG, actualLogger.getLevel());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogConfigBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logConfig("The config", e);
		assertEquals(Level.CONFIG, actualLogger.getLevel());
		assertEquals("The config", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFine(){
		logger.logDebugFine("Some debug");
		assertEquals(Level.FINE, actualLogger.getLevel());
		assertEquals("Some debug", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFineErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFine(e);
		assertEquals(Level.FINE, actualLogger.getLevel());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFineBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFine("The debug", e);
		assertEquals(Level.FINE, actualLogger.getLevel());
		assertEquals("The debug", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFiner(){
		logger.logDebugFiner("Some debug");
		assertEquals(Level.FINER, actualLogger.getLevel());
		assertEquals("Some debug", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinerErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFiner(e);
		assertEquals(Level.FINER, actualLogger.getLevel());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinerBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFiner("The debug", e);
		assertEquals(Level.FINER, actualLogger.getLevel());
		assertEquals("The debug", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinest(){
		logger.logDebugFinest("Some debug");
		assertEquals(Level.FINEST, actualLogger.getLevel());
		assertEquals("Some debug", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinestErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFinest(e);
		assertEquals(Level.FINEST, actualLogger.getLevel());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinestBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFinest("The debug", e);
		assertEquals(Level.FINEST, actualLogger.getLevel());
		assertEquals("The debug", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
}
