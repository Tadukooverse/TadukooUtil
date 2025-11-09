package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LongFunctionTest{
	private ThrowingFunction<Long, Long, IllegalArgumentException> thrower;
	private LongFunction<Long> success;
	private LongFunction<Long> add2;
	private ThrowingFunction<Long, Long, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> i;
		add2 = i -> i + 2;
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testFunction(){
		assertEquals(5L, success.apply(5L));
	}
	
	@Test
	public void testComposeThrowing(){
		try{
			thrower.compose(success).apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testComposeSuccess(){
		assertEquals(7L, add2.compose(success).apply(5L));
	}
	
	@Test
	public void testComposeThrowingSuccess(){
		assertEquals(7L, throwingSuccess.compose(success).apply(5L));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(thrower).apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(7L, success.andThen(add2).apply(5L));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(7L, success.andThen(throwingSuccess).apply(5L));
	}
	
	@Test
	public void testIdentity(){
		LongFunction<Long> identity = LongFunction.identity();
		assertEquals(5L, identity.apply(5L));
	}
}
