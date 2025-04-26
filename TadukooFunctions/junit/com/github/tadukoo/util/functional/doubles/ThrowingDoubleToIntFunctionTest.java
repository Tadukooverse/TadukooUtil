package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.integer.ThrowingIntToDoubleFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingDoubleToIntFunctionTest{
	private ThrowingDoubleToIntFunction<IllegalArgumentException> thrower;
	private ThrowingDoubleToIntFunction<IllegalArgumentException> success;
	private ThrowingDoubleToIntFunction<IllegalArgumentException> add2;
	private ThrowingIntToDoubleFunction<IllegalArgumentException> throwerReverse;
	private ThrowingIntToDoubleFunction<IllegalArgumentException> successReverse;
	private ThrowingIntToDoubleFunction<IllegalArgumentException> add2Reverse;
	private Function<Integer, Double> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Double::intValue;
		add2 = i -> (int) (i + 2);
		throwerReverse = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		successReverse = Integer::doubleValue;
		add2Reverse = i -> (double) (i + 2);
		regularFunction = i -> (double) (i + 2);
	}
	
	@Test
	public void testThrowingFunction(){
		try{
			thrower.apply(5.0);
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
		assertEquals(7, regularFunction.compose(success).apply(5.0));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwerReverse).apply(5.0);
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
	public void testAndThenRegularFunction(){
		assertEquals(7, success.andThen(regularFunction).apply(5.0));
	}
}
