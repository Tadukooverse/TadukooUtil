package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterUtilTest{
	
	@Test
	public void testIsUpperCase(){
		assertTrue(CharacterUtil.isUpperCase('H'));
	}
	
	@Test
	public void testIsUpperCaseA(){
		assertTrue(CharacterUtil.isUpperCase('A'));
	}
	
	@Test
	public void testIsUpperCaseZ(){
		assertTrue(CharacterUtil.isUpperCase('Z'));
	}
	
	@Test
	public void testIsNotUpperCase(){
		assertFalse(CharacterUtil.isUpperCase('t'));
	}
	
	@Test
	public void testIsNotUpperCaseNotLetter(){
		assertFalse(CharacterUtil.isUpperCase('?'));
	}
	
	@Test
	public void testIsNotUpperCaseBeforeA(){
		assertFalse(CharacterUtil.isUpperCase('@'));
	}
	
	@Test
	public void testIsNotUpperCaseAfterZ(){
		assertFalse(CharacterUtil.isUpperCase('['));
	}
	
	@Test
	public void testIsLowerCase(){
		assertTrue(CharacterUtil.isLowerCase('h'));
	}
	
	@Test
	public void testIsLowerCaseA(){
		assertTrue(CharacterUtil.isLowerCase('a'));
	}
	
	@Test
	public void testIsLowerCaseZ(){
		assertTrue(CharacterUtil.isLowerCase('z'));
	}
	
	@Test
	public void testIsNotLowerCase(){
		assertFalse(CharacterUtil.isLowerCase('T'));
	}
	
	@Test
	public void testIsNotLowerCaseNotLetter(){
		assertFalse(CharacterUtil.isLowerCase('?'));
	}
	
	@Test
	public void testIsNotLowerCaseBeforeA(){
		assertFalse(CharacterUtil.isLowerCase('`'));
	}
	
	@Test
	public void testIsNotLowerCaseAfterZ(){
		assertFalse(CharacterUtil.isLowerCase('{'));
	}
	
	@Test
	public void testIsLetterCapital(){
		assertTrue(CharacterUtil.isLetter('T'));
	}
	
	@Test
	public void testIsLetterLowerCase(){
		assertTrue(CharacterUtil.isLetter('l'));
	}
	
	@Test
	public void testIsNotLetter(){
		assertFalse(CharacterUtil.isLetter('_'));
	}
	
	@Test
	public void testIsNumber(){
		assertTrue(CharacterUtil.isNumber('5'));
	}
	
	@Test
	public void testIsNumber0(){
		assertTrue(CharacterUtil.isNumber('0'));
	}
	
	@Test
	public void testIsNumber9(){
		assertTrue(CharacterUtil.isNumber('9'));
	}
	
	@Test
	public void testIsNotNumberLetter(){
		assertFalse(CharacterUtil.isNumber('j'));
	}
	
	@Test
	public void testIsNotNumberBefore0(){
		assertFalse(CharacterUtil.isNumber('/'));
	}
	
	@Test
	public void testIsNotNumberAfter9(){
		assertFalse(CharacterUtil.isNumber(':'));
	}
	
	@Test
	public void testToUpperCase(){
		assertEquals('T', CharacterUtil.toUpperCase('t'));
	}
	
	@Test
	public void testToUpperCaseAlreadyCapital(){
		assertEquals('T', CharacterUtil.toUpperCase('T'));
	}
	
	@Test
	public void testToUpperCaseNotALetter(){
		assertEquals('?', CharacterUtil.toUpperCase('?'));
	}
	
	@Test
	public void testToLowerCase(){
		assertEquals('g', CharacterUtil.toLowerCase('G'));
	}
	
	@Test
	public void testToLowerCaseAlreadyLowerCase(){
		assertEquals('g', CharacterUtil.toLowerCase('g'));
	}
	
	@Test
	public void testToLowerCaseNotALetter(){
		assertEquals('!', CharacterUtil.toLowerCase('!'));
	}
}
