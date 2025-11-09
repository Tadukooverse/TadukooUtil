package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.doubles.DoubleToLongFunction;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LongToDoubleFunctionTest{
	private ThrowingFunction<Double, Long, IllegalArgumentException> thrower;
	private LongToDoubleFunction success;
	private LongToDoubleFunction add2;
	private DoubleToLongFunction successReverse;
	private DoubleToLongFunction add2Reverse;
	private ThrowingFunction<Double, Long, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Long::doubleValue;
		successReverse = Double::longValue;
		add2 = i -> (double) (i + 2);
		add2Reverse = i -> (long) (i + 2);
		throwingSuccess = i -> (long) (i + 2);
	}
	
	@Test
	public void testFunction(){
		assertEquals(5, success.apply(5L));
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
		assertEquals(7, add2.compose(successReverse).apply(5.0));
	}
	
	@Test
	public void testComposeThrowingSuccess(){
		assertEquals(7, throwingSuccess.compose(success).apply(5L));
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
		assertEquals(7, success.andThen(add2Reverse).apply(5L));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(7, success.andThen(throwingSuccess).apply(5L));
	}
}
