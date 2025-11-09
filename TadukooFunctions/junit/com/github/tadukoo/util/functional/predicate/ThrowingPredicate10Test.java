package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingPredicate10Test{
	private ThrowingPredicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> thrower;
	private ThrowingPredicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> simpleAnd;
	private ThrowingPredicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> simpleOr;
	private Predicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, Boolean, Boolean, Boolean, Boolean> simpleAndRegular, simpleOrRegular;
	
	@BeforeEach
	public void setup(){
		thrower = (a, b, c, d, e, f, g, h, i, j) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		simpleAnd = (a, b, c, d, e, f, g, h, i, j) -> a && b && c && d && e && f && g && h && i && j;
		simpleOr = (a , b, c, d, e, f, g, h, i, j) -> a || b || c || d || e || f || g || h || i || j;
		simpleAndRegular = (a, b, c, d, e, f, g, h, i, j) -> a && b && c && d && e && f && g && h && i && j;
		simpleOrRegular = (a , b, c, d, e, f, g, h, i, j) -> a || b || c || d || e || f || g || h || i || j;
	}
	
	@Test
	public void testThrowingPredicate10(){
		try{
			thrower.test(true, false, true, false, true, false, true, false, false, true);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
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
		ThrowingPredicate10<Boolean, Boolean, Boolean, Boolean, Boolean,
				Boolean, Boolean, Boolean, Boolean, Boolean, IllegalArgumentException> negate =
				simpleAnd.negate();
		assertTrue(negate.test(false, false, false, false, false,
				false, false, false, false, false));
		assertFalse(negate.test(true, true, true, true, true,
				true, true, true, true, true));
	}
}
