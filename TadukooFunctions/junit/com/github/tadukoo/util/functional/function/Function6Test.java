package com.github.tadukoo.util.functional.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Function6Test{
	private Function6<Integer, Integer, Integer, Integer, Integer, Integer,
			Integer> success;
	private Function<Integer, Integer> add2;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> simpleThrower;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		success = (i, j, k, l, m, n) -> i + j + k + l + m + n;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testFunction6(){
		assertEquals(30, success.apply(5, 5, 5, 5, 5, 5));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(simpleThrower).apply(5, 5, 5, 5, 5, 5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(32,
				success.andThen(add2).apply(5, 5, 5, 5, 5, 5));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(32,
				success.andThen(throwingSuccess).apply(5, 5, 5, 5, 5, 5));
	}
}
