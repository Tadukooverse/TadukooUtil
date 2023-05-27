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
		private String className = null;
		private String methodName = null;
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
		
		@Override
		public void logp(Level level, String className, String methodName, String message){
			this.level = level;
			this.className = className;
			this.methodName = methodName;
			this.message = message;
		}
		
		@Override
		public void logp(Level level, String className, String methodName, String message, Throwable t){
			this.level = level;
			this.className = className;
			this.methodName = methodName;
			this.message = message;
			this.t = t;
		}
		
		public Level getLevel(){
			return level;
		}
		
		public String getMessage(){
			return message;
		}
		
		public String getClassName(){
			return className;
		}
		
		public String getMethodName(){
			return methodName;
		}
		
		public Throwable getT(){
			return t;
		}
	}
	
	private String className;
	private EasyLogger logger;
	private DummyLogger actualLogger;
	
	@BeforeEach
	public void setup(){
		className = EasyLoggerTest.class.getCanonicalName();
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
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogInfo", actualLogger.getMethodName());
		assertEquals("Some info", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogInfoErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logInfo(e);
		assertEquals(Level.INFO, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogInfoErrorOnly", actualLogger.getMethodName());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogInfoBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logInfo("The info", e);
		assertEquals(Level.INFO, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogInfoBoth", actualLogger.getMethodName());
		assertEquals("The info", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogWarning(){
		logger.logWarning("Some warning");
		assertEquals(Level.WARNING, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogWarning", actualLogger.getMethodName());
		assertEquals("Some warning", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogWarningErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logWarning(e);
		assertEquals(Level.WARNING, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogWarningErrorOnly", actualLogger.getMethodName());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogWarningBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logWarning("The warning", e);
		assertEquals(Level.WARNING, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogWarningBoth", actualLogger.getMethodName());
		assertEquals("The warning", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogError(){
		logger.logError("Some error");
		assertEquals(Level.SEVERE, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogError", actualLogger.getMethodName());
		assertEquals("Some error", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogErrorErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logError(e);
		assertEquals(Level.SEVERE, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogErrorErrorOnly", actualLogger.getMethodName());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogErrorBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logError("The error", e);
		assertEquals(Level.SEVERE, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogErrorBoth", actualLogger.getMethodName());
		assertEquals("The error", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogConfig(){
		logger.logConfig("Some config");
		assertEquals(Level.CONFIG, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogConfig", actualLogger.getMethodName());
		assertEquals("Some config", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogConfigErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logConfig(e);
		assertEquals(Level.CONFIG, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogConfigErrorOnly", actualLogger.getMethodName());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogConfigBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logConfig("The config", e);
		assertEquals(Level.CONFIG, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogConfigBoth", actualLogger.getMethodName());
		assertEquals("The config", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFine(){
		logger.logDebugFine("Some debug");
		assertEquals(Level.FINE, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFine", actualLogger.getMethodName());
		assertEquals("Some debug", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFineErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFine(e);
		assertEquals(Level.FINE, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFineErrorOnly", actualLogger.getMethodName());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFineBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFine("The debug", e);
		assertEquals(Level.FINE, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFineBoth", actualLogger.getMethodName());
		assertEquals("The debug", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFiner(){
		logger.logDebugFiner("Some debug");
		assertEquals(Level.FINER, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFiner", actualLogger.getMethodName());
		assertEquals("Some debug", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinerErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFiner(e);
		assertEquals(Level.FINER, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFinerErrorOnly", actualLogger.getMethodName());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinerBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFiner("The debug", e);
		assertEquals(Level.FINER, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFinerBoth", actualLogger.getMethodName());
		assertEquals("The debug", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinest(){
		logger.logDebugFinest("Some debug");
		assertEquals(Level.FINEST, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFinest", actualLogger.getMethodName());
		assertEquals("Some debug", actualLogger.getMessage());
		assertNull(actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinestErrorOnly(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFinest(e);
		assertEquals(Level.FINEST, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFinestErrorOnly", actualLogger.getMethodName());
		assertEquals("Derp", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
	
	@Test
	public void testLogDebugFinestBoth(){
		IllegalArgumentException e = new IllegalArgumentException("Derp");
		logger.logDebugFinest("The debug", e);
		assertEquals(Level.FINEST, actualLogger.getLevel());
		assertEquals(className, actualLogger.getClassName());
		assertEquals("testLogDebugFinestBoth", actualLogger.getMethodName());
		assertEquals("The debug", actualLogger.getMessage());
		assertEquals(e, actualLogger.getT());
	}
}
