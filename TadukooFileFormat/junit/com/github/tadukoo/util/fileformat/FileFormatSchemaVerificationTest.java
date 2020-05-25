package com.github.tadukoo.util.fileformat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tadukoo.util.fileformat.ghdr.GHDRFileFormat;

public class FileFormatSchemaVerificationTest{
	private static final String subfolder = "FileFormatSchemaVerificationTest";
	private String filepath;
	private FileFormat fileFormat;
	
	@BeforeEach
	public void setup() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "setup");
		filepath = "1959.ghdr";
		fileFormat = new GHDRFileFormat(logger);
	}
	
	@Test
	public void testVerifyFileFormat() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "testVerifyFileFormat");
		assertTrue(FileFormatSchemaVerification.verifyFileFormat(logger, fileFormat, fileFormat.getSchema("Version 1.0"), filepath));
	}
}
