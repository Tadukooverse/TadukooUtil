package com.github.tadukoo.util.junit;

import com.github.tadukoo.util.junit.constant.DefaultTestValues;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.tadukoo.util.junit.constant.AssertionFailedErrors.*;
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
		PojoTest.assertValueGetSet(pojo::getString, pojo::setString, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
	}
	
	@Test
	public void testVAssertalueGetSetFailedAssertion(){
		try{
			PojoTest.assertValueGetSet(badPojo::getString, badPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetFailedAssertion2(){
		try{
			PojoTest.assertValueGetSet(trickyPojo::getString, trickyPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2, DEFAULT_TEST_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetterThrows(){
		try{
			PojoTest.assertValueGetSet(getThrowPojo::getString, getThrowPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad getter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetSetterThrows(){
		try{
			PojoTest.assertValueGetSet(setThrowPojo::getString, setThrowPojo::setString, DEFAULT_TEST_STRING,
					DEFAULT_TEST_STRING_2);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad setter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetPass(){
		PojoTest.assertStringGetSet(pojo::getString, pojo::setString);
	}
	
	@Test
	public void testAssertStringGetSetFailedAssertion(){
		try{
			PojoTest.assertStringGetSet(badPojo::getString, badPojo::setString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetFailedAssertion2(){
		try{
			PojoTest.assertStringGetSet(trickyPojo::getString, trickyPojo::setString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING_2,DEFAULT_TEST_STRING), e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetGetterThrows(){
		try{
			PojoTest.assertStringGetSet(getThrowPojo::getString, getThrowPojo::setString);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad getter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertStringGetSetSetterThrows(){
		try{
			PojoTest.assertStringGetSet(setThrowPojo::getString, setThrowPojo::setString);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad setter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetPass(){
		PojoTest.assertDoubleGetSet(pojo::getDouble, pojo::setDouble);
	}
	
	@Test
	public void testAssertDoubleGetSetFailedAssertion(){
		try{
			PojoTest.assertDoubleGetSet(badPojo::getDouble, badPojo::setDouble);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_DOUBLE, 0.0), e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetFailedAssertion2(){
		try{
			PojoTest.assertDoubleGetSet(trickyPojo::getDouble, trickyPojo::setDouble);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_DOUBLE_2, DEFAULT_TEST_DOUBLE), e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetGetterThrows(){
		try{
			PojoTest.assertDoubleGetSet(getThrowPojo::getDouble, getThrowPojo::setDouble);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad getter", e.getMessage());
		}
	}
	
	@Test
	public void testAssertDoubleGetSetSetterThrows(){
		try{
			PojoTest.assertDoubleGetSet(setThrowPojo::getDouble, setThrowPojo::setDouble);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("I'm a bad setter", e.getMessage());
		}
	}
}
