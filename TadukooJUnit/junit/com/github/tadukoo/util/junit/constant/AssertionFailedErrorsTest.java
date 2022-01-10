package com.github.tadukoo.util.junit.constant;

import org.junit.jupiter.api.Test;

import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.ASSERT_FALSE_ERROR;
import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.ASSERT_NOT_NULL_ERROR;
import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.ASSERT_TRUE_ERROR;
import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.buildAssertErrorNot;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionFailedErrorsTest{
	
	@Test
	public void testASSERT_TRUE_ERROR(){
		assertEquals("expected: <true> but was: <false>", ASSERT_TRUE_ERROR.toString());
	}
	
	@Test
	public void testASSERT_FALSE_ERROR(){
		assertEquals("expected: <false> but was: <true>", ASSERT_FALSE_ERROR.toString());
	}
	
	@Test
	public void testASSERT_NOT_NULL_ERROR(){
		assertEquals("expected: not <null>", ASSERT_NOT_NULL_ERROR.toString());
	}
	
	@Test
	public void testBuildAssertErrorNot(){
		assertEquals("expected: not <plop>", buildAssertErrorNot("plop"));
	}
	
	@Test
	public void testBuildAssertError(){
		assertEquals("expected: <derp> but was: <test>", buildAssertError("derp", "test"));
	}
}
