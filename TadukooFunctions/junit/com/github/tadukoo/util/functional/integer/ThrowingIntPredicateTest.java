package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.predicate.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingIntPredicateTest{
	private ThrowingIntPredicate<IllegalArgumentException> thrower;
	private ThrowingIntPredicate<IllegalArgumentException> aboveOrEqual5;
	private ThrowingIntPredicate<IllegalArgumentException> belowOrEqual5;
	private ThrowingIntPredicate<IllegalArgumentException> equal5;
	private Predicate<Integer> equal5Regular, belowOrEqual5Regular;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		aboveOrEqual5 = i -> i >= 5;
		belowOrEqual5 = i -> i <= 5;
		equal5 = i -> i == 5;
		equal5Regular = i -> i == 5;
		belowOrEqual5Regular = i -> i <= 5;
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
	public void testAndBothTrueRegular(){
		assertTrue(aboveOrEqual5.and(belowOrEqual5Regular).test(5));
	}
	
	@Test
	public void testAndBothFalseRegular(){
		assertFalse(aboveOrEqual5.and(equal5Regular).test(4));
	}
	
	@Test
	public void testAndFirstFalseRegular(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Regular).test(4));
	}
	
	@Test
	public void testAndLastFalseRegular(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Regular).test(6));
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
	public void testOrBothTrueRegular(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Regular).test(5));
	}
	
	@Test
	public void testOrBothFalseRegular(){
		assertFalse(aboveOrEqual5.or(equal5Regular).test(4));
	}
	
	@Test
	public void testOrFirstFalseRegular(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Regular).test(4));
	}
	
	@Test
	public void testOrLastFalseRegular(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Regular).test(6));
	}
	
	@Test
	public void testNegate(){
		ThrowingIntPredicate<IllegalArgumentException> negate = aboveOrEqual5.negate();
		assertTrue(negate.test(4));
		assertFalse(negate.test(5));
	}
	
	@Test
	public void testIsEqual(){
		ThrowingIntPredicate<IllegalArgumentException> isEqual = ThrowingIntPredicate.isEqual(5);
		assertTrue(isEqual.test(5));
		assertFalse(isEqual.test(1));
	}
}
