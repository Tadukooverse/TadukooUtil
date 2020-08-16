package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionUtilTest{
	
	private void throwException(){
		throw new IllegalArgumentException("No");
	}
	
	@Test
	public void testGetStackTraceAsString(){
		try{
			throwException();
		}catch(IllegalArgumentException e){
			// Manually build the stack trace string to use for verification
			String expectedString = e.getClass().getCanonicalName() + ": " +  e.getMessage() +
					"\r\n\tat " +
					StringUtil.buildStringWithSeparator(
							Arrays.stream(e.getStackTrace())
									.map(StackTraceElement::toString)
									.collect(Collectors.toList()),
							"\r\n\tat ");
			
			// We trim to remove some whitespace that causes issues
			String asString = ExceptionUtil.getStackTraceAsString(e).trim();
			
			assertEquals(expectedString, asString);
		}
	}
}
