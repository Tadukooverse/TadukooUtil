package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.predicate.ThrowingPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntPredicateTest{
	private IntPredicate aboveOrEqual5;
	private IntPredicate belowOrEqual5;
	private IntPredicate equal5;
	private ThrowingPredicate<Integer, IllegalArgumentException> equal5Throwing, belowOrEqual5Throwing;
	
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
		assertTrue(equal5.test(5));
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(aboveOrEqual5.and(belowOrEqual5).test(5));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(aboveOrEqual5.and(equal5).test(4));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5).test(4));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5).test(6));
	}
	
	@Test
	public void testAndBothTrueThrowing(){
		assertTrue(aboveOrEqual5.and(belowOrEqual5Throwing).test(5));
	}
	
	@Test
	public void testAndBothFalseThrowing(){
		assertFalse(aboveOrEqual5.and(equal5Throwing).test(4));
	}
	
	@Test
	public void testAndFirstFalseThrowing(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Throwing).test(4));
	}
	
	@Test
	public void testAndLastFalseThrowing(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Throwing).test(6));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5).test(5));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(aboveOrEqual5.or(equal5).test(4));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5).test(4));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5).test(6));
	}
	
	@Test
	public void testOrBothTrueThrowing(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Throwing).test(5));
	}
	
	@Test
	public void testOrBothFalseThrowing(){
		assertFalse(aboveOrEqual5.or(equal5Throwing).test(4));
	}
	
	@Test
	public void testOrFirstFalseThrowing(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Throwing).test(4));
	}
	
	@Test
	public void testOrLastFalseThrowing(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Throwing).test(6));
	}
	
	@Test
	public void testNegate(){
		IntPredicate negate = aboveOrEqual5.negate();
		assertTrue(negate.test(4));
		assertFalse(negate.test(5));
	}
	
	@Test
	public void testIsEqual(){
		IntPredicate isEqual = IntPredicate.isEqual(5);
		assertTrue(isEqual.test(5));
		assertFalse(isEqual.test(1));
	}
}
