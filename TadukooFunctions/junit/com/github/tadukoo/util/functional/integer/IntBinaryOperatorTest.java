package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IntBinaryOperatorTest{
	private IntBinaryOperator success;
	private Function<Integer, Integer> add2;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> simpleThrower;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		success = Integer::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testThrowingFunction2(){
		assertEquals(10, success.apply(5, 5));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(simpleThrower).apply(5, 5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(12, success.andThen(add2).apply(5, 5));
	}
	
	@Test
	public void testAndThenRegularFunction(){
		assertEquals(12, success.andThen(throwingSuccess).apply(5, 5));
	}
	
	@Test
	public void testMinByA(){
		assertEquals(1, IntBinaryOperator.minBy(Comparator.naturalOrder()).apply(1, 42));
	}
	
	@Test
	public void testMinByB(){
		assertEquals(1, IntBinaryOperator.minBy(Comparator.naturalOrder()).apply(42, 1));
	}
	
	@Test
	public void testMaxByA(){
		assertEquals(42, IntBinaryOperator.maxBy(Comparator.naturalOrder()).apply(1, 42));
	}
	
	@Test
	public void testMaxByB(){
		assertEquals(42, IntBinaryOperator.maxBy(Comparator.naturalOrder()).apply(42, 1));
	}
}
