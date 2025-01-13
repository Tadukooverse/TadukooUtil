package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Predicate5Test{
	private Predicate5<Boolean, Boolean, Boolean, Boolean, Boolean> simpleAnd;
	private Predicate5<Boolean, Boolean, Boolean, Boolean, Boolean> simpleOr;
	private ThrowingPredicate5<Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> simpleAndRegular, simpleOrRegular;
	
	@BeforeEach
	public void setup(){
		simpleAnd = (a, b, c, d, e) -> a && b && c && d && e;
		simpleOr = (a , b, c, d, e) -> a || b || c || d || e;
		simpleAndRegular = (a, b, c, d, e) -> a && b && c && d && e;
		simpleOrRegular = (a , b, c, d, e) -> a || b || c || d || e;
	}
	
	@Test
	public void testPredicate5(){
		assertFalse(simpleAnd.test(true, false, true, false, true));
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(simpleAnd.and(simpleOr).test(true, true, true, true, true));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, false, false, false, false));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, true, false, false, false));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(simpleOr.and(simpleAnd).test(true, false, false, false, false));
	}
	
	@Test
	public void testAndBothTrueRegular(){
		assertTrue(simpleAnd.and(simpleOrRegular).test(true, true, true, true, true));
	}
	
	@Test
	public void testAndBothFalseRegular(){
		assertFalse(simpleAnd.and(simpleOrRegular).test(false, false, false, false, false));
	}
	
	@Test
	public void testAndFirstFalseRegular(){
		assertFalse(simpleAnd.and(simpleOrRegular).test(false, true, false, false, false));
	}
	
	@Test
	public void testAndLastFalseRegular(){
		assertFalse(simpleOr.and(simpleAndRegular).test(true, false, false, false, false));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(simpleAnd.or(simpleOr).test(true, true, true, true, true));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(simpleAnd.or(simpleOr).test(false, false, false, false, false));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(simpleAnd.or(simpleOr).test(false, true, false, false, false));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(simpleOr.or(simpleAnd).test(true, false, false, false, false));
	}
	
	@Test
	public void testOrBothTrueRegular(){
		assertTrue(simpleAnd.or(simpleOrRegular).test(true, true, true, true, true));
	}
	
	@Test
	public void testOrBothFalseRegular(){
		assertFalse(simpleAnd.or(simpleOrRegular).test(false, false, false, false, false));
	}
	
	@Test
	public void testOrFirstFalseRegular(){
		assertTrue(simpleAnd.or(simpleOrRegular).test(false, true, false, false, false));
	}
	
	@Test
	public void testOrLastFalseRegular(){
		assertTrue(simpleOr.or(simpleAndRegular).test(true, false, false, false, false));
	}
	
	@Test
	public void testNegate(){
		Predicate5<Boolean, Boolean, Boolean, Boolean, Boolean> negate =
				simpleAnd.negate();
		assertTrue(negate.test(false, false, false, false, false));
		assertFalse(negate.test(true, true, true, true, true));
	}
}
