package com.github.tadukoo.util.functional.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingFunction2Test{
	private ThrowingFunction2<Integer, Integer, Integer, IllegalArgumentException> thrower;
	private ThrowingFunction2<Integer, Integer, Integer, IllegalArgumentException> success;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> add2;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> simpleThrower;
	
	@BeforeEach
	public void setup(){
		thrower = (i, j) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Integer::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
	}
	
	@Test
	public void testThrowingFunction2(){
		try{
			thrower.apply(5, 5);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(simpleThrower).apply(5, 5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(12, success.andThen(add2).apply(5, 5));
	}
}
