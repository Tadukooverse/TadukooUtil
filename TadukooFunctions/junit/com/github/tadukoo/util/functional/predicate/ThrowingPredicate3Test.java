package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingPredicate3Test{
	private ThrowingPredicate3<Boolean, Boolean, Boolean, IllegalArgumentException> thrower;
	private ThrowingPredicate3<Boolean, Boolean, Boolean, IllegalArgumentException> simpleAnd;
	private ThrowingPredicate3<Boolean, Boolean, Boolean, IllegalArgumentException> simpleOr;
	
	@BeforeEach
	public void setup(){
		thrower = (a, b, c) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		simpleAnd = (a, b, c) -> a && b && c;
		simpleOr = (a , b, c) -> a || b || c;
	}
	
	@Test
	public void testThrowingPredicate3(){
		try{
			thrower.test(true, false, true);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndBothTrue(){
		assertTrue(simpleAnd.and(simpleOr).test(true, true, true));
	}
	
	@Test
	public void testAndBothFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, false, false));
	}
	
	@Test
	public void testAndFirstFalse(){
		assertFalse(simpleAnd.and(simpleOr).test(false, true, false));
	}
	
	@Test
	public void testAndLastFalse(){
		assertFalse(simpleOr.and(simpleAnd).test(true, false, false));
	}
	
	@Test
	public void testOrBothTrue(){
		assertTrue(simpleAnd.or(simpleOr).test(true, true, true));
	}
	
	@Test
	public void testOrBothFalse(){
		assertFalse(simpleAnd.or(simpleOr).test(false, false, false));
	}
	
	@Test
	public void testOrFirstFalse(){
		assertTrue(simpleAnd.or(simpleOr).test(false, true, false));
	}
	
	@Test
	public void testOrLastFalse(){
		assertTrue(simpleOr.or(simpleAnd).test(true, false, false));
	}
	
	@Test
	public void testNegate(){
		ThrowingPredicate3<Boolean, Boolean, Boolean, IllegalArgumentException> negate = simpleAnd.negate();
		assertTrue(negate.test(false, false, false));
		assertFalse(negate.test(true, true, true));
	}
}
