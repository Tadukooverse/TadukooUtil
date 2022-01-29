package com.github.tadukoo.util.junit;

import org.junit.jupiter.api.Test;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_FALSE_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_NOT_NULL_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_TRUE_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertErrorNot;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
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
	
	@Test
	public void testBuildTwoPartErrorEnum(){
		assertEquals("something bad happened ==> expected: <true> but was: <false>",
				buildTwoPartError("something bad happened", ASSERT_TRUE_ERROR));
	}
	
	@Test
	public void testBuildTwoPartErrorString(){
		assertEquals("something bad happened ==> blah blah blah",
				buildTwoPartError("something bad happened", "blah blah blah"));
	}
	
	@Test
	public void testBuildMultiPartError(){
		assertEquals("bad thing ==> something bad ==> yep",
				buildMultiPartError("bad thing", "something bad", "yep"));
	}
}
