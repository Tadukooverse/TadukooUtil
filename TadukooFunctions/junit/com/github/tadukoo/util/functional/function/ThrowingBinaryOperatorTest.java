package com.github.tadukoo.util.functional.function;

import com.github.tadukoo.util.functional.NoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingBinaryOperatorTest{
	private ThrowingBinaryOperator<Integer, IllegalArgumentException> thrower;
	private ThrowingBinaryOperator<Integer, IllegalArgumentException> success;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> add2;
	private ThrowingFunction<Integer, Integer, IllegalArgumentException> simpleThrower;
	private Function<Integer, Integer> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = (i, j) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Integer::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		regularFunction = i -> i + 2;
	}
	
	@Test
	public void testThrowingFunction2(){
		try{
			thrower.apply(5, 5);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
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
		assertEquals(12, success.andThen(regularFunction).apply(5, 5));
	}
	
	@Test
	public void testMinByA(){
		assertEquals(1, ThrowingBinaryOperator.<Integer, NoException>minBy(Comparator.naturalOrder()).apply(1, 42));
	}
	
	@Test
	public void testMinByB(){
		assertEquals(1, ThrowingBinaryOperator.<Integer, NoException>minBy(Comparator.naturalOrder()).apply(42, 1));
	}
	
	@Test
	public void testMaxByA(){
		assertEquals(42, ThrowingBinaryOperator.<Integer, NoException>maxBy(Comparator.naturalOrder()).apply(1, 42));
	}
	
	@Test
	public void testMaxByB(){
		assertEquals(42, ThrowingBinaryOperator.<Integer, NoException>maxBy(Comparator.naturalOrder()).apply(42, 1));
	}
}
