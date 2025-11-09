package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingPredicate5Test{
	private ThrowingPredicate5<Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> thrower;
	private ThrowingPredicate5<Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> simpleAnd;
	private ThrowingPredicate5<Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> simpleOr;
	private Predicate5<Boolean, Boolean, Boolean, Boolean, Boolean> simpleAndRegular, simpleOrRegular;
	
	@BeforeEach
	public void setup(){
		thrower = (a, b, c, d, e) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		simpleAnd = (a, b, c, d, e) -> a && b && c && d && e;
		simpleOr = (a , b, c, d, e) -> a || b || c || d || e;
		simpleAndRegular = (a, b, c, d, e) -> a && b && c && d && e;
		simpleOrRegular = (a , b, c, d, e) -> a || b || c || d || e;
	}
	
	@Test
	public void testThrowingPredicate5(){
		try{
			thrower.test(true, false, true, false, true);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
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
		ThrowingPredicate5<Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> negate =
				simpleAnd.negate();
		assertTrue(negate.test(false, false, false, false, false));
		assertFalse(negate.test(true, true, true, true, true));
	}
}
