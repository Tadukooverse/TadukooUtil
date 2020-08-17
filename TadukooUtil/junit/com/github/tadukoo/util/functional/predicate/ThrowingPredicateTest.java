package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowingPredicateTest{
	private ThrowingPredicate<Integer, IllegalArgumentException> thrower;
	private ThrowingPredicate<Integer, IllegalArgumentException> aboveOrEqual5;
	private ThrowingPredicate<Integer, IllegalArgumentException> belowOrEqual5;
	private ThrowingPredicate<Integer, IllegalArgumentException> equal5;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		aboveOrEqual5 = i -> i >= 5;
		belowOrEqual5 = i -> i <= 5;
		equal5 = i -> i == 5;
	}
	
	@Test
	public void testThrowingPredicate(){
		try{
			thrower.test(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
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
	public void testNegate(){
		ThrowingPredicate<Integer, IllegalArgumentException> negate = aboveOrEqual5.negate();
		assertTrue(negate.test(4));
		assertFalse(negate.test(5));
	}
	
	@Test
	public void testIsEqual(){
		ThrowingPredicate<Integer, IllegalArgumentException> isEqual = ThrowingPredicate.isEqual(5);
		assertTrue(isEqual.test(5));
		assertFalse(isEqual.test(1));
	}
}
