package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.longs.ThrowingLongToIntFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingIntToLongFunctionTest{
	private ThrowingIntToLongFunction<IllegalArgumentException> thrower;
	private ThrowingIntToLongFunction<IllegalArgumentException> success;
	private ThrowingIntToLongFunction<IllegalArgumentException> add2;
	private ThrowingLongToIntFunction<IllegalArgumentException> throwerReverse;
	private ThrowingLongToIntFunction<IllegalArgumentException> successReverse;
	private ThrowingLongToIntFunction<IllegalArgumentException> add2Reverse;
	private Function<Long, Integer> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> (long) i;
		add2 = i -> (long) i + 2;
		throwerReverse = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		successReverse = Long::intValue;
		add2Reverse = i -> (int) (i + 2);
		regularFunction = i -> (int) (i + 2);
	}
	
	@Test
	public void testThrowingFunction(){
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
			thrower.compose(successReverse).apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testComposeSuccess(){
		assertEquals(7, add2.compose(successReverse).apply(5L));
	}
	
	@Test
	public void testComposeRegularFunction(){
		assertEquals(7, regularFunction.compose(success).apply(5));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwerReverse).apply(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(7, success.andThen(add2Reverse).apply(5));
	}
	
	@Test
	public void testAndThenRegularFunction(){
		assertEquals(7, success.andThen(regularFunction).apply(5));
	}
}
