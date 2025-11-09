package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Predicate10Test{
	private Predicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, Boolean, Boolean> simpleAnd;
	private Predicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, Boolean, Boolean> simpleOr;
	private ThrowingPredicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> simpleAndRegular, simpleOrRegular;
	
	@BeforeEach
	public void setup(){
		simpleAnd = (a, b, c, d, e, f, g, h, i, j) -> a && b && c && d && e && f && g && h && i && j;
		simpleOr = (a , b, c, d, e, f, g, h, i, j) -> a || b || c || d || e || f || g || h || i || j;
		simpleAndRegular = (a, b, c, d, e, f, g, h, i, j) -> a && b && c && d && e && f && g && h && i && j;
		simpleOrRegular = (a , b, c, d, e, f, g, h, i, j) -> a || b || c || d || e || f || g || h || i || j;
	}
	
	@Test
	public void testPredicate10(){
		assertFalse(simpleAnd.test(true, false, true, false, true, false, true, false, false, true));
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(simpleAnd.and(simpleOr).test(true, true, true, true, true,
				true, true, true, true, true));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, false, false, false, false,
				false, false, false, false, false));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, true, false, false, true,
				true, false, true, true, false));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(simpleOr.and(simpleAnd).test(true, false, false, true, false,
				false, true, true, false, false));
	}
	
	@Test
	public void testAndBothTrueRegular(){
		assertTrue(simpleAnd.and(simpleOrRegular).test(true, true, true, true, true,
				true, true, true, true, true));
	}
	
	@Test
	public void testAndBothFalseRegular(){
		assertFalse(simpleAnd.and(simpleOrRegular).test(false, false, false, false, false,
				false, false, false, false, false));
	}
	
	@Test
	public void testAndFirstFalseRegular(){
		assertFalse(simpleAnd.and(simpleOrRegular).test(false, true, false, false, true,
				true, false, true, true, false));
	}
	
	@Test
	public void testAndLastFalseRegular(){
		assertFalse(simpleOr.and(simpleAndRegular).test(true, false, false, true, false,
				false, true, true, false, false));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(simpleAnd.or(simpleOr).test(true, true, true, true, true,
				true, true, true, true, true));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(simpleAnd.or(simpleOr).test(false, false, false, false, false,
				false, false, false, false, false));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(simpleAnd.or(simpleOr).test(false, true, false, true, false,
				false, true, true, true, false));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(simpleOr.or(simpleAnd).test(true, true, false, false, false,
				false, true, true, false, false));
	}
	
	@Test
	public void testOrBothTrueRegular(){
		assertTrue(simpleAnd.or(simpleOrRegular).test(true, true, true, true, true,
				true, true, true, true, true));
	}
	
	@Test
	public void testOrBothFalseRegular(){
		assertFalse(simpleAnd.or(simpleOrRegular).test(false, false, false, false, false,
				false, false, false, false, false));
	}
	
	@Test
	public void testOrFirstFalseRegular(){
		assertTrue(simpleAnd.or(simpleOrRegular).test(false, true, false, true, false,
				false, true, true, true, false));
	}
	
	@Test
	public void testOrLastFalseRegular(){
		assertTrue(simpleOr.or(simpleAndRegular).test(true, true, false, false, false,
				false, true, true, false, false));
	}
	
	@Test
	public void testNegate(){
		Predicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
				Boolean, Boolean, Boolean, Boolean, Boolean> negate =
				simpleAnd.negate();
		assertTrue(negate.test(false, false, false, false, false,
				false, false, false, false, false));
		assertFalse(negate.test(true, true, true, true, true,
				true, true, true, true, true));
	}
}
