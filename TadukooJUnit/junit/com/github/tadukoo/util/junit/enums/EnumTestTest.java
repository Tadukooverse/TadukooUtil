package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static com.github.tadukoo.util.junit.DefaultTestValues.DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE;
import static com.github.tadukoo.util.junit.enums.EnumTest.assertFromStringBad;
import static com.github.tadukoo.util.junit.enums.EnumTest.assertToFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EnumTestTest{
	
	private enum TestEnum{
		YEP("Yep"), NOPE("Nope");
		
		private final String str;
		
		TestEnum(String str){
			this.str = str;
		}
		
		public static TestEnum fromString(String str){
			for(TestEnum e: values()){
				if(StringUtil.equalsIgnoreCase(e.str, str)){
					return e;
				}
			}
			return null;
		}
		
		public static TestEnum badFromString(String str){
			for(TestEnum e: values()){
				if(StringUtil.equalsIgnoreCase(e.str, str)){
					if(e == YEP){
						return NOPE;
					}else{
						return YEP;
					}
				}
			}
			return YEP;
		}
		
		@Override
		public String toString(){
			return str;
		}
		
		public String customToString(){
			return str;
		}
	}
	
	@Test
	public void testAssertFromStringBad(){
		assertFromStringBad(TestEnum::fromString);
	}
	
	@Test
	public void testAssertFromStringBadFail(){
		try{
			assertFromStringBad(TestEnum::badFromString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum returned non-null for bad string!",
							buildAssertError(null, TestEnum.YEP.toString())),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertFromStringBadCustomString(){
		assertFromStringBad(TestEnum::fromString, "garbage_string");
	}
	
	@Test
	public void testAssertFromStringBadCustomStringFail(){
		try{
			assertFromStringBad(TestEnum::badFromString, "garbage_string");
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum returned non-null for bad string!",
							buildAssertError(null, TestEnum.YEP.toString())),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertFromStringBadCustomStringCustomMessage(){
		assertFromStringBad(TestEnum::fromString, "garbage_string", DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertFromStringBadCustomStringCustomMessageFail(){
		try{
			assertFromStringBad(TestEnum::badFromString, "garbage_string",
					DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"enum returned non-null for bad string!", buildAssertError(null, TestEnum.YEP.toString())),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromString(){
		assertToFromString(TestEnum.YEP, "Yep", TestEnum::fromString);
		assertToFromString(TestEnum.NOPE, "Nope", TestEnum::fromString);
	}
	
	@Test
	public void testAssertToFromStringCustomToString(){
		assertToFromString(TestEnum.YEP, "Yep", TestEnum::fromString, TestEnum.YEP::customToString);
		assertToFromString(TestEnum.NOPE, "Nope", TestEnum::fromString, TestEnum.NOPE::customToString);
	}
	
	@Test
	public void testAssertToFromStringCustomMessage(){
		assertToFromString(TestEnum.YEP, "Yep", TestEnum::fromString, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
		assertToFromString(TestEnum.NOPE, "Nope", TestEnum::fromString, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertToFromStringCustomToStringCustomMessage(){
		assertToFromString(TestEnum.YEP, "Yep", TestEnum::fromString, TestEnum.YEP::customToString,
				DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
		assertToFromString(TestEnum.NOPE, "Nope", TestEnum::fromString, TestEnum.NOPE::customToString,
				DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertToFromStringWrongTo(){
		try{
			assertToFromString(TestEnum.YEP, "Nope", TestEnum::fromString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's toString failed!",
					buildAssertError("Nope", TestEnum.YEP.toString())), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromStringWrongToCustomToString(){
		try{
			assertToFromString(TestEnum.YEP, "Nope", TestEnum::fromString, TestEnum.YEP::customToString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's toString failed!",
					buildAssertError("Nope", TestEnum.YEP.toString())), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromStringWrongToCustomMessage(){
		try{
			assertToFromString(TestEnum.YEP, "Nope", TestEnum::fromString,
					DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "enum's toString failed!",
					buildAssertError("Nope", TestEnum.YEP.toString())), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromStringWrongToCustomToStringCustomMessage(){
		try{
			assertToFromString(TestEnum.YEP, "Nope", TestEnum::fromString, TestEnum.YEP::customToString,
					DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "enum's toString failed!",
					buildAssertError("Nope", TestEnum.YEP.toString())), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromStringWrongFrom(){
		try{
			assertToFromString(TestEnum.YEP, "Yep", TestEnum::badFromString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's fromString failed!",
					buildAssertError(TestEnum.YEP, TestEnum.NOPE)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromStringWrongFromCustomToString(){
		try{
			assertToFromString(TestEnum.YEP, "Yep", TestEnum::badFromString, TestEnum.YEP::customToString);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's fromString failed!",
					buildAssertError(TestEnum.YEP, TestEnum.NOPE)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromStringWrongFromCustomMessage(){
		try{
			assertToFromString(TestEnum.YEP, "Yep", TestEnum::badFromString,
					DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"enum's fromString failed!", buildAssertError(TestEnum.YEP, TestEnum.NOPE)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromStringWrongFromCustomToStringCustomMessage(){
		try{
			assertToFromString(TestEnum.YEP, "Yep", TestEnum::badFromString, TestEnum.YEP::customToString,
					DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "enum's fromString failed!",
					buildAssertError(TestEnum.YEP, TestEnum.NOPE)), e.getMessage());
		}
	}
}
