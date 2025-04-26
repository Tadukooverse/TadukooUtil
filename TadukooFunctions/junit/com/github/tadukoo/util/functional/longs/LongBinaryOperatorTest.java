package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LongBinaryOperatorTest{
	private LongBinaryOperator success;
	private Function<Long, Long> add2;
	private ThrowingFunction<Long, Long, IllegalArgumentException> simpleThrower;
	private ThrowingFunction<Long, Long, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		success = Long::sum;
		add2 = i -> i + 2;
		simpleThrower = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testThrowingFunction2(){
		assertEquals(10L, success.apply(5L, 5L));
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
		assertEquals(12L, success.andThen(throwingSuccess).apply(5L, 5L));
	}
	
	@Test
	public void testMinByA(){
		assertEquals(1L, LongBinaryOperator.minBy(Comparator.naturalOrder()).apply(1L, 42L));
	}
	
	@Test
	public void testMinByB(){
		assertEquals(1L, LongBinaryOperator.minBy(Comparator.naturalOrder()).apply(42L, 1L));
	}
	
	@Test
	public void testMaxByA(){
		assertEquals(42L, LongBinaryOperator.maxBy(Comparator.naturalOrder()).apply(1L, 42L));
	}
	
	@Test
	public void testMaxByB(){
		assertEquals(42L, LongBinaryOperator.maxBy(Comparator.naturalOrder()).apply(42L, 1L));
	}
}
