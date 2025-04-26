package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingDoubleBinaryOperatorTest{
	private ThrowingDoubleBinaryOperator<IllegalArgumentException> thrower;
	private ThrowingDoubleBinaryOperator<IllegalArgumentException> success;
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
	public void testThrowingFunction2(){
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
	
	@Test
	public void testMinByA(){
		assertEquals(1.0, ThrowingDoubleBinaryOperator.<NoException>minBy(Comparator.naturalOrder()).apply(1.0, 42.0));
	}
	
	@Test
	public void testMinByB(){
		assertEquals(1.0, ThrowingDoubleBinaryOperator.<NoException>minBy(Comparator.naturalOrder()).apply(42.0, 1.0));
	}
	
	@Test
	public void testMaxByA(){
		assertEquals(42.0, ThrowingDoubleBinaryOperator.<NoException>maxBy(Comparator.naturalOrder()).apply(1.0, 42.0));
	}
	
	@Test
	public void testMaxByB(){
		assertEquals(42.0, ThrowingDoubleBinaryOperator.<NoException>maxBy(Comparator.naturalOrder()).apply(42.0, 1.0));
	}
}
