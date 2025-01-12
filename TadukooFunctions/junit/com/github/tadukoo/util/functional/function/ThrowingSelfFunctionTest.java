package com.github.tadukoo.util.functional.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingSelfFunctionTest{
	private ThrowingSelfFunction<Integer, IllegalArgumentException> thrower;
	private ThrowingSelfFunction<Integer, IllegalArgumentException> success;
	private ThrowingSelfFunction<Integer, IllegalArgumentException> add2;
	private SelfFunction<Integer> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> i;
		add2 = i -> i + 2;
		regularFunction = i -> i + 2;
	}
	
	@Test
	public void testThrowingSelfFunction(){
		try{
			thrower.apply(5);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testComposeThrowing(){
		try{
			thrower.compose(success).apply(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testComposeSuccess(){
		assertEquals(7, add2.compose(success).apply(5));
	}
	
	@Test
	public void testComposeRegularFunction(){
		assertEquals(9, add2.compose(regularFunction).apply(5));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(thrower).apply(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(7, success.andThen(add2).apply(5));
	}
	
	@Test
	public void testAndThenRegularFunction(){
		assertEquals(7, success.andThen(regularFunction).apply(5));
	}
	
	@Test
	public void testIdentity() throws Throwable{
		ThrowingSelfFunction<Integer, Throwable> identity = ThrowingSelfFunction.identity();
		assertEquals(5, identity.apply(5));
	}
}
