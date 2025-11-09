package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingToLongFunctionTest{
	private ThrowingToLongFunction<Long, IllegalArgumentException> thrower;
	private ThrowingToLongFunction<Long, IllegalArgumentException> success;
	private ThrowingToLongFunction<Long, IllegalArgumentException> add2;
	private Function<Long, Long> regularFunction;
	
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
	public void testThrowingFunction(){
		try{
			thrower.apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
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
	public void testComposeRegularFunction(){
		assertEquals(7L, regularFunction.compose(success).apply(5L));
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
	public void testAndThenRegularFunction(){
		assertEquals(7L, success.andThen(regularFunction).apply(5L));
	}
	
	@Test
	public void testIdentity() throws Throwable{
		ThrowingToLongFunction<Long, Throwable> identity = ThrowingToLongFunction.identity();
		assertEquals(5L, identity.apply(5L));
	}
}
