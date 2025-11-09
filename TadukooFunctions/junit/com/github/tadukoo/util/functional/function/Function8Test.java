package com.github.tadukoo.util.functional.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Function8Test{
	private Function8<Integer, Integer, Integer, Integer, Integer, Integer,
			Integer, Integer, Integer> success;
	private Function<Integer, Integer> add2;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> simpleThrower;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		success = (i, j, k, l, m, n, o, p) -> i + j + k + l + m + n + o + p;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testFunction8(){
		assertEquals(40, success.apply(5, 5, 5, 5, 5, 5, 5, 5));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(simpleThrower).apply(5, 5, 5, 5, 5, 5, 5, 5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(42,
				success.andThen(add2).apply(5, 5, 5, 5, 5, 5, 5, 5));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(42,
				success.andThen(throwingSuccess).apply(5, 5, 5, 5, 5, 5, 5, 5));
	}
}
