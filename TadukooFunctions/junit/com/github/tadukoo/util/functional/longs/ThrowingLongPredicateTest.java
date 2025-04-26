package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.predicate.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingLongPredicateTest{
	private ThrowingLongPredicate<IllegalArgumentException> thrower;
	private ThrowingLongPredicate<IllegalArgumentException> aboveOrEqual5;
	private ThrowingLongPredicate<IllegalArgumentException> belowOrEqual5;
	private ThrowingLongPredicate<IllegalArgumentException> equal5;
	private Predicate<Long> equal5Regular, belowOrEqual5Regular;
	
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
			thrower.test(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
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
	public void testAndBothTrueRegular(){
		assertTrue(aboveOrEqual5.and(belowOrEqual5Regular).test(5L));
	}
	
	@Test
	public void testAndBothFalseRegular(){
		assertFalse(aboveOrEqual5.and(equal5Regular).test(4L));
	}
	
	@Test
	public void testAndFirstFalseRegular(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Regular).test(4L));
	}
	
	@Test
	public void testAndLastFalseRegular(){
		assertFalse(aboveOrEqual5.and(belowOrEqual5Regular).test(6L));
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
	public void testOrBothTrueRegular(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Regular).test(5L));
	}
	
	@Test
	public void testOrBothFalseRegular(){
		assertFalse(aboveOrEqual5.or(equal5Regular).test(4L));
	}
	
	@Test
	public void testOrFirstFalseRegular(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Regular).test(4L));
	}
	
	@Test
	public void testOrLastFalseRegular(){
		assertTrue(aboveOrEqual5.or(belowOrEqual5Regular).test(6L));
	}
	
	@Test
	public void testNegate(){
		ThrowingLongPredicate<IllegalArgumentException> negate = aboveOrEqual5.negate();
		assertTrue(negate.test(4L));
		assertFalse(negate.test(5L));
	}
	
	@Test
	public void testIsEqual(){
		ThrowingLongPredicate<IllegalArgumentException> isEqual = ThrowingLongPredicate.isEqual(5);
		assertTrue(isEqual.test(5L));
		assertFalse(isEqual.test(1L));
	}
}
