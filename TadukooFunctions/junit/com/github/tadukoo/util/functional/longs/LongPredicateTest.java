package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.predicate.ThrowingPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongPredicateTest{
	private LongPredicate aboveOrEqual5;
	private LongPredicate belowOrEqual5;
	private LongPredicate equal5;
	private ThrowingPredicate<Long, IllegalArgumentException> equal5Throwing, belowOrEqual5Throwing;
	
	@BeforeEach
	public void setup(){
		aboveOrEqual5 = i -> i >= 5;
		belowOrEqual5 = i -> i <= 5;
		equal5 = i -> i == 5;
		equal5Throwing = i -> i == 5;
		belowOrEqual5Throwing = i -> i <= 5;
	}
	
	@Test
	public void testPredicate(){
		assertTrue(equal5.test(5L));
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(aboveOrEqual5.and(belowOrEqual5).test(5L));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(aboveOrEqual5.and(equal5).test(4L));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5).test(4L));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5).test(6L));
	}
	
	@Test
	public void testAndBothTrueThrowing(){
		assertTrue(aboveOrEqual5.and(belowOrEqual5Throwing).test(5L));
	}
	
	@Test
	public void testAndBothFalseThrowing(){
		assertFalse(aboveOrEqual5.and(equal5Throwing).test(4L));
	}
	
	@Test
	public void testAndFirstFalseThrowing(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Throwing).test(4L));
	}
	
	@Test
	public void testAndLastFalseThrowing(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Throwing).test(6L));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5).test(5L));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(aboveOrEqual5.or(equal5).test(4L));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5).test(4L));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5).test(6L));
	}
	
	@Test
	public void testOrBothTrueThrowing(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Throwing).test(5L));
	}
	
	@Test
	public void testOrBothFalseThrowing(){
		assertFalse(aboveOrEqual5.or(equal5Throwing).test(4L));
	}
	
	@Test
	public void testOrFirstFalseThrowing(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Throwing).test(4L));
	}
	
	@Test
	public void testOrLastFalseThrowing(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Throwing).test(6L));
	}
	
	@Test
	public void testNegate(){
		LongPredicate negate = aboveOrEqual5.negate();
		assertTrue(negate.test(4L));
		assertFalse(negate.test(5L));
	}
	
	@Test
	public void testIsEqual(){
		LongPredicate isEqual = LongPredicate.isEqual(5);
		assertTrue(isEqual.test(5L));
		assertFalse(isEqual.test(1L));
	}
}
