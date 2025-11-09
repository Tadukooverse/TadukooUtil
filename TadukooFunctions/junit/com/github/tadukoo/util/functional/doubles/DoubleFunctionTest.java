package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoubleFunctionTest{
	private ThrowingFunction<Double, Double, IllegalArgumentException> thrower;
	private DoubleFunction<Double> success;
	private DoubleFunction<Double> add2;
	private ThrowingFunction<Double, Double, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> i;
		add2 = i -> i + 2;
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testFunction(){
		assertEquals(5.0, success.apply(5.0));
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
		assertEquals(7.0, add2.compose(success).apply(5.0));
	}
	
	@Test
	public void testComposeThrowingSuccess(){
		assertEquals(7.0, throwingSuccess.compose(success).apply(5.0));
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
		assertEquals(7.0, success.andThen(add2).apply(5.0));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(7.0, success.andThen(throwingSuccess).apply(5.0));
	}
	
	@Test
	public void testIdentity(){
		DoubleFunction<Double> identity = DoubleFunction.identity();
		assertEquals(5.0, identity.apply(5.0));
	}
}
