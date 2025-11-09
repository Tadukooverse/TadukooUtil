package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoubleBinaryOperatorTest{
	private DoubleBinaryOperator success;
	private Function<Double, Double> add2;
	private ThrowingFunction<Double, Double, IllegalArgumentException> simpleThrower;
	private ThrowingFunction<Double, Double, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		success = Double::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testThrowingFunction2(){
		assertEquals(10.0, success.apply(5.0, 5.0));
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
		assertEquals(12.0, success.andThen(throwingSuccess).apply(5.0, 5.0));
	}
	
	@Test
	public void testMinByA(){
		assertEquals(1.0, DoubleBinaryOperator.minBy(Comparator.naturalOrder()).apply(1.0, 42.0));
	}
	
	@Test
	public void testMinByB(){
		assertEquals(1.0, DoubleBinaryOperator.minBy(Comparator.naturalOrder()).apply(42.0, 1.0));
	}
	
	@Test
	public void testMaxByA(){
		assertEquals(42.0, DoubleBinaryOperator.maxBy(Comparator.naturalOrder()).apply(1.0, 42.0));
	}
	
	@Test
	public void testMaxByB(){
		assertEquals(42.0, DoubleBinaryOperator.maxBy(Comparator.naturalOrder()).apply(42.0, 1.0));
	}
}
