package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static com.github.tadukoo.util.junit.pojo.PojoTest.assertDoubleGetSet;
import static com.github.tadukoo.util.junit.pojo.PojoTest.assertStringGetSet;
import static com.github.tadukoo.util.junit.pojo.PojoTest.assertValueGetSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PojoTestTest implements DefaultTestValues{
	private static class Pojo{
		private String str = null;
		private double doub = 0.0;
		
		public String getString(){
			return str;
		}
		
		public void setString(String str){
			this.str = str;
		}
		
		public double getDouble(){
			return doub;
		}
		
		public void setDouble(double doub){
			this.doub = doub;
		}
	}
	
	private static class BadPojo{
		
		public String getString(){
			return DEFAULT_WRONG_STRING;
		}
		
		public void setString(String str){
			// do nothing
		}
		
		public double getDouble(){
			return 0.0;
		}
		
		public void setDouble(double doub){
			// do nothing
		}
	}
	
	private static class TrickyPojo{
		
		public String getString(){
			return DEFAULT_TEST_STRING;
		}
		
		public void setString(String str){
			// do nothing
		}
		
		public double getDouble(){
			return DEFAULT_TEST_DOUBLE;
		}
		
		public void setDouble(double doub){
			// do nothing
		}
	}
	
	private static class ThrowingGetPojo{
		
		public String getString(){
			throw new IllegalArgumentException("I'm a bad getter");
		}
		
		public void setString(String str){
			// do nothing
		}
		
		public double getDouble(){
			throw new IllegalArgumentException("I'm a bad getter");
		}
		
		public void setDouble(double doub){
			// do nothing
		}
	}
	
	private static class ThrowingSetPojo{
		
		public String getString(){
			return "";
		}
		
		public void setString(String str){
			throw new IllegalArgumentException("I'm a bad setter");
		}
		
		public double getDouble(){
			return 0.0;
		}
		
		public void setDouble(double doub){
			throw new IllegalArgumentException("I'm a bad setter");
		}
	}
	
	private final Pojo pojo = new Pojo();
	private final BadPojo badPojo = new BadPojo();
	private final TrickyPojo trickyPojo = new TrickyPojo();
	private final ThrowingGetPojo getThrowPojo = new ThrowingGetPojo();
	private final ThrowingSetPojo setThrowPojo = new ThrowingSetPojo();
	
	@Test
	public void testAssertValueGetSetPass(){
		assertValueGetSet(pojo::getString, pojo::setString, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
	}
	
	@Test
	public void testAssertValueGetSetFailedAssertion(){
		try{
			assertValueGetSet(badPojo::getString, badPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getter failed on first set!",
					buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetFailedAssertion2(){
		try{
			assertValueGetSet(trickyPojo::getString, trickyPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getter failed on second set!",
					buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_TEST_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetterThrows(){
		try{
			assertValueGetSet(getThrowPojo::getString, getThrowPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad getter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetSetterThrows(){
		try{
			assertValueGetSet(setThrowPojo::getString, setThrowPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad setter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetPassCustomMessage(){
		assertValueGetSet(pojo::getString, pojo::setString, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2,
				DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertValueGetSetFailedAssertionCustomMessage(){
		try{
			assertValueGetSet(badPojo::getString, badPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "getter failed on first set!",
					buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetFailedAssertion2CustomMessage(){
		try{
			assertValueGetSet(trickyPojo::getString, trickyPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "getter failed on second set!",
					buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_TEST_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetPass(){
		assertStringGetSet(pojo::getString, pojo::setString);
	}
	
	@Test
	public void testAssertStringGetSetFailedAssertion(){
		try{
			assertStringGetSet(badPojo::getString, badPojo::setString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getter failed on first set!",
					buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetFailedAssertion2(){
		try{
			assertStringGetSet(trickyPojo::getString, trickyPojo::setString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getter failed on second set!",
					buildAssertError(DEFAULT_TEST_STRING_2,DEFAULT_TEST_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetGetterThrows(){
		try{
			assertStringGetSet(getThrowPojo::getString, getThrowPojo::setString);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad getter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetSetterThrows(){
		try{
			assertStringGetSet(setThrowPojo::getString, setThrowPojo::setString);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad setter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetPassCustomMessage(){
		assertStringGetSet(pojo::getString, pojo::setString, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertStringGetSetFailedAssertionCustomMessage(){
		try{
			assertStringGetSet(badPojo::getString, badPojo::setString, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "getter failed on first set!",
					buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetFailedAssertion2CustomMessage(){
		try{
			assertStringGetSet(trickyPojo::getString, trickyPojo::setString, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "getter failed on second set!",
					buildAssertError(DEFAULT_TEST_STRING_2,DEFAULT_TEST_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetPass(){
		assertDoubleGetSet(pojo::getDouble, pojo::setDouble);
	}
	
	@Test
	public void testAssertDoubleGetSetFailedAssertion(){
		try{
			assertDoubleGetSet(badPojo::getDouble, badPojo::setDouble);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getter failed on first set!",
					buildAssertError(DEFAULT_TEST_DOUBLE, 0.0)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetFailedAssertion2(){
		try{
			assertDoubleGetSet(trickyPojo::getDouble, trickyPojo::setDouble);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getter failed on second set!",
					buildAssertError(DEFAULT_TEST_DOUBLE_2, DEFAULT_TEST_DOUBLE)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetGetterThrows(){
		try{
			assertDoubleGetSet(getThrowPojo::getDouble, getThrowPojo::setDouble);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad getter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetSetterThrows(){
		try{
			assertDoubleGetSet(setThrowPojo::getDouble, setThrowPojo::setDouble);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad setter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetPassCustomMessage(){
		assertDoubleGetSet(pojo::getDouble, pojo::setDouble, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertDoubleGetSetFailedAssertionCustomMessage(){
		try{
			assertDoubleGetSet(badPojo::getDouble, badPojo::setDouble, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "getter failed on first set!",
					buildAssertError(DEFAULT_TEST_DOUBLE, 0.0)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetFailedAssertion2CustomMessage(){
		try{
			assertDoubleGetSet(trickyPojo::getDouble, trickyPojo::setDouble, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "getter failed on second set!",
					buildAssertError(DEFAULT_TEST_DOUBLE_2, DEFAULT_TEST_DOUBLE)), e.getMessage());
		}
	}
}
