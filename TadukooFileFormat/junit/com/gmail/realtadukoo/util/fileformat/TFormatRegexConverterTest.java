package com.gmail.realtadukoo.util.fileformat;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gmail.realtadukoo.util.fileformat.TFormatRegexConverter;

public class TFormatRegexConverterTest{
	private static final String subfolder = "TFormatRegexConverterTest";
	private String formatString;
	private String regexString;
	
	@BeforeEach
	public void setup(){
		formatString = "<filename>/<fileTitle>.<fileExtension>/<text>/<imagefile>[$<#>,<#>,<#>,<#>]";
		regexString = "<filename>/<fileTitle>\\.<fileExtension>/.*/.*\\.jpg(\\$(\\d)*,(\\d)*,(\\d)*,(\\d)*)?";
	}
	
	@Test
	public void testConvertFromTFormatToRegexAndBack() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "testConvertFromTFormatToRegexAndBack");
		String regexTest = TFormatRegexConverter.convertTFormatToRegex(logger, formatString);
		assertEquals(regexString, regexTest);
		String tFormatTest = TFormatRegexConverter.convertRegexToTFormat(logger, regexTest);
		assertEquals(formatString, tFormatTest);
	}
	
	@Test
	public void testConvertFromRegexToTFormatAndBack() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "testConvertFromRegexToTFormatAndBack");
		String tFormatTest = TFormatRegexConverter.convertRegexToTFormat(logger, regexString);
		assertEquals(formatString, tFormatTest);
		String regexTest = TFormatRegexConverter.convertTFormatToRegex(logger, tFormatTest);
		assertEquals(regexString, regexTest);
	}
}
