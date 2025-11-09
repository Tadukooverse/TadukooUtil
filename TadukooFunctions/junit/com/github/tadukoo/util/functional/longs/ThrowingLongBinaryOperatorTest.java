package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingLongBinaryOperatorTest{
	private ThrowingLongBinaryOperator<IllegalArgumentException> thrower;
	private ThrowingLongBinaryOperator<IllegalArgumentException> success;
	private ThrowingFunction<Long, Long, IllegalArgumentException> add2;
	private ThrowingFunction<Long, Long, IllegalArgumentException> simpleThrower;
	private Function<Long, Long> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = (i, j) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = Long::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		regularFunction = i -> i + 2;
	}
	
	@Test
	public void testThrowingFunction2(){
		try{
			thrower.apply(5L, 5L);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(simpleThrower).apply(5L, 5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(12L, success.andThen(add2).apply(5L, 5L));
	}
	
	@Test
	public void testAndThenRegularFunction(){
		assertEquals(12L, success.andThen(regularFunction).apply(5L, 5L));
	}
	
	@Test
	public void testMinByA(){
		assertEquals(1L, ThrowingLongBinaryOperator.<NoException>minBy(Comparator.naturalOrder()).apply(1L, 42L));
	}
	
	@Test
	public void testMinByB(){
		assertEquals(1L, ThrowingLongBinaryOperator.<NoException>minBy(Comparator.naturalOrder()).apply(42L, 1L));
	}
	
	@Test
	public void testMaxByA(){
		assertEquals(42L, ThrowingLongBinaryOperator.<NoException>maxBy(Comparator.naturalOrder()).apply(1L, 42L));
	}
	
	@Test
	public void testMaxByB(){
		assertEquals(42L, ThrowingLongBinaryOperator.<NoException>maxBy(Comparator.naturalOrder()).apply(42L, 1L));
	}
}
