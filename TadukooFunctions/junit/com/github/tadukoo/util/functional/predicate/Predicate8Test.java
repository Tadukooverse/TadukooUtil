package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Predicate8Test{
	private Predicate8<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean> simpleAnd;
	private Predicate8<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean> simpleOr;
	private ThrowingPredicate8<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, IllegalArgumentException> simpleAndThrowing, simpleOrThrowing;
	
	@BeforeEach
	public void setup(){
		simpleAnd = (a, b, c, d, e, f, g, h) -> a && b && c && d && e && f && g && h;
		simpleOr = (a , b, c, d, e, f, g, h) -> a || b || c || d || e || f || g || h;
		simpleAndThrowing = (a, b, c, d, e, f, g, h) -> a && b && c && d && e && f && g && h;
		simpleOrThrowing = (a , b, c, d, e, f, g, h) -> a || b || c || d || e || f || g || h;
	}
	
	@Test
	public void testPredicate8(){
		assertFalse(simpleAnd.test(true, false, true, false, true, false, true, false));
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(simpleAnd.and(simpleOr).test(true, true, true, true, true,
				true, true, true));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, false, false, false, false,
				false, false, false));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, true, false, false, true,
				true, false, true));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(simpleOr.and(simpleAnd).test(true, false, false, true, false,
				false, true, false));
	}
	
	@Test
	public void testAndBothTrueThrowing(){
		assertTrue(simpleAnd.and(simpleOrThrowing).test(true, true, true, true, true,
				true, true, true));
	}
	
	@Test
	public void testAndBothFalseThrowing(){
		assertFalse(simpleAnd.and(simpleOrThrowing).test(false, false, false, false, false,
				false, false, false));
	}
	
	@Test
	public void testAndFirstFalseThrowing(){
		assertFalse(simpleAnd.and(simpleOrThrowing).test(false, true, false, false, true,
				true, false, true));
	}
	
	@Test
	public void testAndLastFalseThrowing(){
		assertFalse(simpleOr.and(simpleAndThrowing).test(true, false, false, true, false,
				false, true, false));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(simpleAnd.or(simpleOr).test(true, true, true, true, true,
				true, true, true));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(simpleAnd.or(simpleOr).test(false, false, false, false, false,
				false, false, false));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(simpleAnd.or(simpleOr).test(false, true, false, true, false,
				false, true, false));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(simpleOr.or(simpleAnd).test(true, true, false, false, false,
				false, true, false));
	}
	
	@Test
	public void testOrBothTrueThrowing(){
		assertTrue(simpleAnd.or(simpleOrThrowing).test(true, true, true, true, true,
				true, true, true));
	}
	
	@Test
	public void testOrBothFalseThrowing(){
		assertFalse(simpleAnd.or(simpleOrThrowing).test(false, false, false, false, false,
				false, false, false));
	}
	
	@Test
	public void testOrFirstFalseThrowing(){
		assertTrue(simpleAnd.or(simpleOrThrowing).test(false, true, false, true, false,
				false, true, false));
	}
	
	@Test
	public void testOrLastFalseThrowing(){
		assertTrue(simpleOr.or(simpleAndThrowing).test(true, true, false, false, false,
				false, true, false));
	}
	
	@Test
	public void testNegate(){
		Predicate8<Boolean, Boolean, Boolean, Boolean, Boolean,
				Boolean, Boolean, Boolean> negate =
				simpleAnd.negate();
		assertTrue(negate.test(false, false, false, false, false,
				false, false, false));
		assertFalse(negate.test(true, true, true, true, true,
				true, true, true));
	}
}
