package com.github.tadukoo.util.functional.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingPredicate2Test{
	private ThrowingPredicate2<Boolean, Boolean, IllegalArgumentException> thrower;
	private ThrowingPredicate2<Boolean, Boolean, IllegalArgumentException> simpleAnd;
	private ThrowingPredicate2<Boolean, Boolean, IllegalArgumentException> simpleOr;
	
	@BeforeEach
	public void setup(){
		thrower = (a, b) -> {
			throw new IllegalArgumentException("Unsupported");
		};
		simpleAnd = (a, b) -> a && b;
		simpleOr = (a , b) -> a || b;
	}
	
	@Test
	public void testThrowingPredicate2(){
		try{
			thrower.test(true, false);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
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
	public void testNegate(){
		ThrowingPredicate2<Boolean, Boolean, IllegalArgumentException> negate = simpleAnd.negate();
		assertTrue(negate.test(false, false));
		assertFalse(negate.test(true, true));
	}
}
