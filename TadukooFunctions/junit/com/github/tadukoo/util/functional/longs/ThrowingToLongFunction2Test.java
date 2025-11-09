package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingToLongFunction2Test{
	private ThrowingToLongFunction2<Long, Long, IllegalArgumentException> thrower;
	private ThrowingToLongFunction2<Long, Long, IllegalArgumentException> success;
	private ThrowingFunction<Long, Long, IllegalArgumentException> add2;
	private ThrowingFunction<Long, Long, IllegalArgumentException> simpleThrower;
	private Function<Long, Long> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = (i, j) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Long::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		regularFunction = i -> i + 2;
	}
	
	@Test
	public void testThrowingToLongFunction2(){
		try{
			thrower.apply(5L, 5L);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(simpleThrower).apply(5L, 5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(12L, success.andThen(add2).apply(5L, 5L));
	}
	
	@Test
	public void testAndThenRegularFunction(){
		assertEquals(12L, success.andThen(regularFunction).apply(5L, 5L));
	}
}
