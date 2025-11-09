package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.longs.LongToIntFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IntToLongFunctionTest{
	private ThrowingFunction<Long, Integer, IllegalArgumentException> thrower;
	private IntToLongFunction success;
	private IntToLongFunction add2;
	private LongToIntFunction successReverse;
	private LongToIntFunction add2Reverse;
	private ThrowingFunction<Long, Integer, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> (long) i;
		successReverse = Long::intValue;
		add2 = i -> (long) i + 2;
		add2Reverse = i -> (int) (i + 2);
		throwingSuccess = i -> (int) (i + 2);
	}
	
	@Test
	public void testFunction(){
		assertEquals(5, success.apply(5));
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
		assertEquals(7, add2.compose(successReverse).apply(5L));
	}
	
	@Test
	public void testComposeThrowingSuccess(){
		assertEquals(7, throwingSuccess.compose(success).apply(5));
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
		assertEquals(7, success.andThen(add2Reverse).apply(5));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(7, success.andThen(throwingSuccess).apply(5));
	}
}
