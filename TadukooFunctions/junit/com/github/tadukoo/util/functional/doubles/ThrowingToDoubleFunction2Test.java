package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingToDoubleFunction2Test{
	private ThrowingToDoubleFunction2<Double, Double, IllegalArgumentException> thrower;
	private ThrowingToDoubleFunction2<Double, Double, IllegalArgumentException> success;
	private ThrowingFunction<Double, Double, IllegalArgumentException> add2;
	private ThrowingFunction<Double, Double, IllegalArgumentException> simpleThrower;
	private Function<Double, Double> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = (i, j) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Double::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		regularFunction = i -> i + 2;
	}
	
	@Test
	public void testThrowingToDoubleFunction2(){
		try{
			thrower.apply(5.0, 5.0);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(simpleThrower).apply(5.0, 5.0);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(12.0, success.andThen(add2).apply(5.0, 5.0));
	}
	
	@Test
	public void testAndThenRegularFunction(){
		assertEquals(12.0, success.andThen(regularFunction).apply(5.0, 5.0));
	}
}
