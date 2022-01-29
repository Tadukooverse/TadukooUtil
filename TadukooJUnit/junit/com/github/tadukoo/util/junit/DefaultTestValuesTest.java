package com.github.tadukoo.util.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultTestValuesTest implements DefaultTestValues{
	
	@Test
	public void testDEFAULT_TEST_STRING(){
		assertEquals("some_test_string", DEFAULT_TEST_STRING);
	}
	
	@Test
	public void testDEFAULT_TEST_STRING_2(){
		assertEquals("another_test_string", DEFAULT_TEST_STRING_2);
	}
	
	@Test
	public void testDEFAULT_WRONG_STRING(){
		assertEquals("something_wrong", DEFAULT_WRONG_STRING);
	}
	
	@Test
	public void testDEFAULT_TEST_DOUBLE(){
		assertEquals(42.0, DEFAULT_TEST_DOUBLE);
	}
	
	@Test
	public void testDEFAULT_TEST_DOUBLE_2(){
		assertEquals(35.4, DEFAULT_TEST_DOUBLE_2);
	}
	
	@Test
	public void testDEFAULT_WRONG_DOUBLE(){
		assertEquals(27.3, DEFAULT_WRONG_DOUBLE);
	}
	
	@Test
	public void testDEFAULT_TEST_KEY(){
		assertEquals("Test", DEFAULT_TEST_KEY);
	}
	
	@Test
	public void testDEFAULT_TEST_KEY_2(){
		assertEquals("Derp", DEFAULT_TEST_KEY_2);
	}
	
	@Test
	public void testDEFAULT_WRONG_KEY(){
		assertEquals("Wrong", DEFAULT_WRONG_KEY);
	}
}
