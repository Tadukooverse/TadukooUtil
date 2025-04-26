package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoubleFunctionTest{
	private ThrowingFunction<Double, Double, IllegalArgumentException> thrower;
	private ToDoubleFunction<Double> success;
	private ToDoubleFunction<Double> add2;
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
		ToDoubleFunction<Double> identity = ToDoubleFunction.identity();
		assertEquals(5.0, identity.apply(5.0));
	}
}
