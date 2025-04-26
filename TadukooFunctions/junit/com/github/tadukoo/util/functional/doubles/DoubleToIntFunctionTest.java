package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.integer.IntToDoubleFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoubleToIntFunctionTest{
	private ThrowingFunction<Integer, Double, IllegalArgumentException> thrower;
	private DoubleToIntFunction success;
	private DoubleToIntFunction add2;
	private IntToDoubleFunction successReverse;
	private IntToDoubleFunction add2Reverse;
	private ThrowingFunction<Integer, Double, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Double::intValue;
		successReverse = Integer::doubleValue;
		add2 = i -> (int) (i + 2);
		add2Reverse = i -> (double) (i + 2);
		throwingSuccess = i -> (double) (i + 2);
	}
	
	@Test
	public void testFunction(){
		assertEquals(5.0, (double) success.apply(5.0));
	}
	
	@Test
	public void testComposeThrowing(){
		try{
			thrower.compose(success).apply(5.0);
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
	public void testComposeThrowingSuccess(){
		assertEquals(7, throwingSuccess.compose(success).apply(5.0));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(thrower).apply(5.0);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(7, success.andThen(add2Reverse).apply(5.0));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(7, success.andThen(throwingSuccess).apply(5.0));
	}
}
