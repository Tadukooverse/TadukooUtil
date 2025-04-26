package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.integer.ThrowingIntToLongFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingLongToIntFunctionTest{
	private ThrowingLongToIntFunction<IllegalArgumentException> thrower;
	private ThrowingLongToIntFunction<IllegalArgumentException> success;
	private ThrowingLongToIntFunction<IllegalArgumentException> add2;
	private ThrowingIntToLongFunction<IllegalArgumentException> throwerReverse;
	private ThrowingIntToLongFunction<IllegalArgumentException> successReverse;
	private ThrowingIntToLongFunction<IllegalArgumentException> add2Reverse;
	private Function<Integer, Long> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Long::intValue;
		add2 = i -> (int) (i + 2);
		throwerReverse = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		successReverse = Integer::longValue;
		add2Reverse = i -> (long) (i + 2);
		regularFunction = i -> (long) (i + 2);
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
			thrower.compose(successReverse).apply(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testComposeSuccess(){
		assertEquals(7, add2.compose(successReverse).apply(5));
	}
	
	@Test
	public void testComposeRegularFunction(){
		assertEquals(7, regularFunction.compose(success).apply(5L));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwerReverse).apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(7, success.andThen(add2Reverse).apply(5L));
	}
	
	@Test
	public void testAndThenRegularFunction(){
		assertEquals(7, success.andThen(regularFunction).apply(5L));
	}
}
