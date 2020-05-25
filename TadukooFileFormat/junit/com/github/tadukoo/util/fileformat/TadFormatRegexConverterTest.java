package com.github.tadukoo.util.fileformat;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TadFormatRegexConverterTest{
	private static final String subfolder = "TFormatRegexConverterTest";
	private String formatString;
	private String regexString;
	
	@BeforeEach
	public void setup(){
		formatString = "<filename>/<fileTitle>.<fileExtension>/<text>/<imagefile>"
				+ "[$<#>,<#>,<#>,<#>]<boolean><Boolean>";
		regexString = "<filename>/<fileTitle>\\.<fileExtension>/.*/.*\\.jpg"
				+ "(\\$(\\d)*,(\\d)*,(\\d)*,(\\d)*)?(true|false)(true|false|null)";
	}
	
	@Test
	public void testConvertFromTFormatToRegexAndBack() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "testConvertFromTFormatToRegexAndBack");
		String regexTest = TadFormatRegexConverter.convertTadFormatToRegex(logger, formatString);
		assertEquals(regexString, regexTest);
		String tFormatTest = TadFormatRegexConverter.convertRegexToTadFormat(logger, regexTest);
		assertEquals(formatString, tFormatTest);
	}
	
	@Test
	public void testConvertFromRegexToTFormatAndBack() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "testConvertFromRegexToTFormatAndBack");
		String tFormatTest = TadFormatRegexConverter.convertRegexToTadFormat(logger, regexString);
		assertEquals(formatString, tFormatTest);
		String regexTest = TadFormatRegexConverter.convertTadFormatToRegex(logger, tFormatTest);
		assertEquals(regexString, regexTest);
	}
}
