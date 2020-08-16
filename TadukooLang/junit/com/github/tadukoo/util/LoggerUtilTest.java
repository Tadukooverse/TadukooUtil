package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerUtilTest{
	
	@Test
	public void testCreateFileLogger() throws IOException{
		String filepath = "target/test-files/test.txt";
		Logger logger = LoggerUtil.createFileLogger(filepath, Level.FINE);
		
		assertEquals(Level.FINE, logger.getLevel());
		File file = new File(filepath);
		assertTrue(file.exists());
	}
}
