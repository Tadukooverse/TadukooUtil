package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Predicate2Test{
	private Predicate2<Boolean, Boolean> simpleAnd;
	private Predicate2<Boolean, Boolean> simpleOr;
	private ThrowingPredicate2<Boolean, Boolean, IllegalArgumentException> simpleAndThrowing;
	private ThrowingPredicate2<Boolean, Boolean, IllegalArgumentException> simpleOrThrowing;
	
	@BeforeEach
	public void setup(){
		simpleAnd = (a, b) -> a && b;
		simpleOr = (a , b) -> a || b;
		simpleAndThrowing = (a, b) -> a && b;
		simpleOrThrowing = (a , b) -> a || b;
	}
	
	@Test
	public void testPredicate2(){
		assertFalse(simpleAnd.test(true, false));
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(simpleAnd.and(simpleOr).test(true, true));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, false));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, true));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(simpleOr.and(simpleAnd).test(true, false));
	}
	
	@Test
	public void testAndBothTrueThrowing(){
		assertTrue(simpleAnd.and(simpleOrThrowing).test(true, true));
	}
	
	@Test
	public void testAndBothFalseThrowing(){
		assertFalse(simpleAnd.and(simpleOrThrowing).test(false, false));
	}
	
	@Test
	public void testAndFirstFalseThrowing(){
		assertFalse(simpleAnd.and(simpleOrThrowing).test(false, true));
	}
	
	@Test
	public void testAndLastFalseThrowing(){
		assertFalse(simpleOr.and(simpleAndThrowing).test(true, false));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(simpleAnd.or(simpleOr).test(true, true));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(simpleAnd.or(simpleOr).test(false, false));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(simpleAnd.or(simpleOr).test(false, true));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(simpleOr.or(simpleAnd).test(true, false));
	}
	
	@Test
	public void testOrBothTrueThrowing(){
		assertTrue(simpleAnd.or(simpleOrThrowing).test(true, true));
	}
	
	@Test
	public void testOrBothFalseThrowing(){
		assertFalse(simpleAnd.or(simpleOrThrowing).test(false, false));
	}
	
	@Test
	public void testOrFirstFalseThrowing(){
		assertTrue(simpleAnd.or(simpleOrThrowing).test(false, true));
	}
	
	@Test
	public void testOrLastFalseThrowing(){
		assertTrue(simpleOr.or(simpleAndThrowing).test(true, false));
	}
	
	@Test
	public void testNegate(){
		Predicate2<Boolean, Boolean> negate = simpleAnd.negate();
		assertTrue(negate.test(false, false));
		assertFalse(negate.test(true, true));
	}
}
