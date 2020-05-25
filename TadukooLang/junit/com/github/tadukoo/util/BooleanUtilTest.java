package com.github.tadukoo.util;

import com.github.tadukoo.util.BooleanUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanUtilTest{
	
	@Test
	public void testIsTrueOnTrue(){
		assertTrue(BooleanUtil.isTrue(true));
	}
	
	@Test
	public void testIsTrueOnFalse(){
		assertFalse(BooleanUtil.isTrue(false));
	}
	
	@Test
	public void testIsTrueOnNull(){
		assertFalse(BooleanUtil.isTrue(null));
	}
	
	@Test
	public void testIsFalseOnTrue(){
		assertFalse(BooleanUtil.isFalse(true));
	}
	
	@Test
	public void testIsFalseOnFalse(){
		assertTrue(BooleanUtil.isFalse(false));
	}
	
	@Test
	public void testIsFalseOnNull(){
		assertFalse(BooleanUtil.isFalse(null));
	}
	
	@Test
	public void testIsNotTrueOnTrue(){
		assertFalse(BooleanUtil.isNotTrue(true));
	}
	
	@Test
	public void testIsNotTrueOnFalse(){
		assertTrue(BooleanUtil.isNotTrue(false));
	}
	
	@Test
	public void testIsNotTrueOnNull(){
		assertTrue(BooleanUtil.isNotTrue(null));
	}
	
	@Test
	public void testIsNotFalseOnTrue(){
		assertTrue(BooleanUtil.isNotFalse(true));
	}
	
	@Test
	public void testIsNotFalseOnFalse(){
		assertFalse(BooleanUtil.isNotFalse(false));
	}
	
	@Test
	public void testIsNotFalseOnNull(){
		assertTrue(BooleanUtil.isNotFalse(null));
	}
}
