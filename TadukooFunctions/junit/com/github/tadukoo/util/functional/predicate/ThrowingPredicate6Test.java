package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingPredicate6Test{
	private ThrowingPredicate6<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, IllegalArgumentException> thrower;
	private ThrowingPredicate6<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, IllegalArgumentException> simpleAnd;
	private ThrowingPredicate6<Boolean, Boolean, Boolean, Boolean, Boolean,
			Boolean, IllegalArgumentException> simpleOr;
	
	@BeforeEach
	public void setup(){
		thrower = (a, b, c, d, e, f) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		simpleAnd = (a, b, c, d, e, f) -> a && b && c && d && e && f;
		simpleOr = (a , b, c, d, e, f) -> a || b || c || d || e || f;
	}
	
	@Test
	public void testThrowingPredicate4(){
		try{
			thrower.test(true, false, true, false, true, false);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(simpleAnd.and(simpleOr).test(true, true, true, true, true, true));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, false, false, false, false, false));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, true, false, false, false, true));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(simpleOr.and(simpleAnd).test(true, false, false, false, true, false));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(simpleAnd.or(simpleOr).test(true, true, true, true, true, true));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(simpleAnd.or(simpleOr).test(false, false, false, false, false, false));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(simpleAnd.or(simpleOr).test(false, true, false, false, true, false));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(simpleOr.or(simpleAnd).test(true, false, false, false, true, false));
	}
	
	@Test
	public void testNegate(){
		ThrowingPredicate6<Boolean, Boolean, Boolean, Boolean, Boolean,
				Boolean, IllegalArgumentException> negate =
				simpleAnd.negate();
		assertTrue(negate.test(false, false, false, false, false, false));
		assertFalse(negate.test(true, true, true, true, true, true));
	}
}
