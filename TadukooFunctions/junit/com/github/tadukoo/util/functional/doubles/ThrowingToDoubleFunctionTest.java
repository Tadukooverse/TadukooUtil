package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingToDoubleFunctionTest{
	private ThrowingToDoubleFunction<Double, IllegalArgumentException> thrower;
	private ThrowingToDoubleFunction<Double, IllegalArgumentException> success;
	private ThrowingToDoubleFunction<Double, IllegalArgumentException> add2;
	private Function<Double, Double> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> i;
		add2 = i -> i + 2;
		regularFunction = i -> i + 2;
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
	public void testComposeRegularFunction(){
		assertEquals(7.0, regularFunction.compose(success).apply(5.0));
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
	public void testAndThenRegularFunction(){
		assertEquals(7.0, success.andThen(regularFunction).apply(5.0));
	}
	
	@Test
	public void testIdentity() throws Throwable{
		ThrowingToDoubleFunction<Double, Throwable> identity = ThrowingToDoubleFunction.identity();
		assertEquals(5.0, identity.apply(5.0));
	}
}
