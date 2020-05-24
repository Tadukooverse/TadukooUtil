package com.gmail.realtadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest{
	
	@Test
	public void testIsBlankOnNull(){
		assertTrue(StringUtil.isBlank(null));
	}
	
	@Test
	public void testIsBlankOnEmpty(){
		assertTrue(StringUtil.isBlank(""));
	}
	
	@Test
	public void testIsBlankOnNonEmpty(){
		assertFalse(StringUtil.isBlank("no"));
	}
	
	@Test
	public void testIsBlankOnWhitespace(){
		assertFalse(StringUtil.isBlank(" "));
	}
	
	@Test
	public void testIsNotBlankOnNull(){
		assertFalse(StringUtil.isNotBlank(null));
	}
	
	@Test
	public void testIsNotBlankOnEmpty(){
		assertFalse(StringUtil.isNotBlank(""));
	}
	
	@Test
	public void testIsNotBlankOnNonEmpty(){
		assertTrue(StringUtil.isNotBlank("yes"));
	}
	
	@Test
	public void testIsNotBlankOnWhitespace(){
		assertTrue(StringUtil.isNotBlank(" "));
	}
	
	@Test
	public void testEqualsOnBothNull(){
		assertTrue(StringUtil.equals(null, null));
	}
	
	@Test
	public void testEqualsOnLeftNull(){
		assertFalse(StringUtil.equals(null, "yes"));
	}
	
	@Test
	public void testEqualsOnRightNull(){
		assertFalse(StringUtil.equals("yes", null));
	}
	
	@Test
	public void testEqualsOnBothNonNullEqual(){
		assertTrue(StringUtil.equals("yes", "yes"));
	}
	
	@Test
	public void testEqualsOnBothNonNullUnequal(){
		assertFalse(StringUtil.equals("yes", "YES"));
	}
	
	@Test
	public void testEqualsAnyOnBothNull(){
		assertTrue(StringUtil.equalsAny(null, "yes", null));
	}
	
	@Test
	public void testEqualsAnyOnLeftNull(){
		assertFalse(StringUtil.equalsAny(null, "yes", "no"));
	}
	
	@Test
	public void testEqualsAnyOnNonNullEqual(){
		assertTrue(StringUtil.equalsAny("yes", "no", "yes"));
	}
	
	@Test
	public void testEqualsAnyOnNonNullUnequal(){
		assertFalse(StringUtil.equalsAny("yes", "YES", "YeS"));
	}
	
	@Test
	public void testEqualsIgnoreCaseOnBothNull(){
		assertTrue(StringUtil.equalsIgnoreCase(null, null));
	}
	
	@Test
	public void testEqualsIgnoreCaseOnLeftNull(){
		assertFalse(StringUtil.equalsIgnoreCase(null, "yes"));
	}
	
	@Test
	public void testEqualsIgnoreCaseOnRightNull(){
		assertFalse(StringUtil.equalsIgnoreCase("yes", null));
	}
	
	@Test
	public void testEqualsIgnoreCaseOnBothNonNullEqual(){
		assertTrue(StringUtil.equalsIgnoreCase("yes", "YEs"));
	}
	
	@Test
	public void testEqualsIgnoreCaseOnBothNonNullUnequal(){
		assertFalse(StringUtil.equalsIgnoreCase("yes", "no"));
	}
	
	@Test
	public void testEqualsAnyIgnoreCaseOnBothNull(){
		assertTrue(StringUtil.equalsAnyIgnoreCase(null, "yes", null));
	}
	
	@Test
	public void testEqualsAnyIgnoreCaseOnLeftNull(){
		assertFalse(StringUtil.equalsAnyIgnoreCase(null, "yes", "no"));
	}
	
	@Test
	public void testEqualsAnyIgnoreCaseOnNonNullEqual(){
		assertTrue(StringUtil.equalsAnyIgnoreCase("yes", "no", "YeS"));
	}
	
	@Test
	public void testEqualsAnyIgnoreCaseOnNonNullUnequal(){
		assertFalse(StringUtil.equalsAnyIgnoreCase("yes", "no", "nope"));
	}
	
	@Test
	public void testNotEqualsOnBothNull(){
		assertFalse(StringUtil.notEquals(null, null));
	}
	
	@Test
	public void testNotEqualsOnLeftNull(){
		assertTrue(StringUtil.notEquals(null, "yes"));
	}
	
	@Test
	public void testNotEqualsOnRightNull(){
		assertTrue(StringUtil.notEquals("yes", null));
	}
	
	@Test
	public void testNotEqualsOnBothNonNullEqual(){
		assertFalse(StringUtil.notEquals("yes", "yes"));
	}
	
	@Test
	public void testNotEqualsOnBothNonNullUnequal(){
		assertTrue(StringUtil.notEquals("yes", "YES"));
	}
	
	@Test
	public void testNotEqualsAnyOnBothNull(){
		assertFalse(StringUtil.notEqualsAny(null, "yes", null));
	}
	
	@Test
	public void testNotEqualsAnyOnLeftNull(){
		assertTrue(StringUtil.notEqualsAny(null, "yes", "no"));
	}
	
	@Test
	public void testNotEqualsAnyOnNonNullEqual(){
		assertFalse(StringUtil.notEqualsAny("yes", "no", "yes"));
	}
	
	@Test
	public void testNotEqualsAnyOnNonNullUnequal(){
		assertTrue(StringUtil.notEqualsAny("yes", "YES", "YeS"));
	}
	
	@Test
	public void testNotEqualsIgnoreCaseOnBothNull(){
		assertFalse(StringUtil.notEqualsIgnoreCase(null, null));
	}
	
	@Test
	public void testNotEqualsIgnoreCaseOnLeftNull(){
		assertTrue(StringUtil.notEqualsIgnoreCase(null, "yes"));
	}
	
	@Test
	public void testNotEqualsIgnoreCaseOnRightNull(){
		assertTrue(StringUtil.notEqualsIgnoreCase("yes", null));
	}
	
	@Test
	public void testNotEqualsIgnoreCaseOnBothNonNullEqual(){
		assertFalse(StringUtil.notEqualsIgnoreCase("yes", "YEs"));
	}
	
	@Test
	public void testNotEqualsIgnoreCaseOnBothNonNullUnequal(){
		assertTrue(StringUtil.notEqualsIgnoreCase("yes", "no"));
	}
	
	@Test
	public void testNotEqualsAnyIgnoreCaseOnBothNull(){
		assertFalse(StringUtil.notEqualsAnyIgnoreCase(null, "yes", null));
	}
	
	@Test
	public void testNotEqualsAnyIgnoreCaseOnLeftNull(){
		assertTrue(StringUtil.notEqualsAnyIgnoreCase(null, "yes", "no"));
	}
	
	@Test
	public void testNotEqualsAnyIgnoreCaseOnNonNullEqual(){
		assertFalse(StringUtil.notEqualsAnyIgnoreCase("yes", "no", "YeS"));
	}
	
	@Test
	public void testNotEqualsAnyIgnoreCaseOnNonNullUnequal(){
		assertTrue(StringUtil.notEqualsAnyIgnoreCase("yes", "no", "nope"));
	}
	
	@Test
	public void testConvertToStringOnNull(){
		assertNull(StringUtil.convertToString(null));
	}
	
	@Test
	public void testConvertToStringOnString(){
		assertEquals("test_string", StringUtil.convertToString("test_string"));
	}
	
	@Test
	public void testConvertToStringOnInt(){
		assertEquals("1", StringUtil.convertToString(1));
	}
	
	@Test
	public void testConvertToStringOnObject(){
		// Setup a dummy class to use
		String testString = "this is a string here";
		class Derp{
			@Override
			public String toString(){
				return testString;
			}
		}
		
		// Make sure it actually works correctly
		assertEquals(testString, StringUtil.convertToString(new Derp()));
	}
	
	@Test
	public void testConvertCollectionToStrings(){
		// Setup a dummy class to use
		String testString = "this is a string here";
		class Derp{
			@Override
			public String toString(){
				return testString;
			}
		}
		
		// Create a list of a string, int, and the Derp class
		List<Object> objs = new ArrayList<>();
		objs.add("test_string");
		objs.add(1);
		objs.add(new Derp());
		
		// Test the converter
		List<String> result = StringUtil.convertCollectionToStrings(objs);
		assertEquals(3, result.size());
		assertEquals("test_string", result.get(0));
		assertEquals("1", result.get(1));
		assertEquals(testString, result.get(2));
	}
	
	@Test
	public void testBuildStringWithSeparator(){
		assertEquals("derp - plop - can",
				StringUtil.buildStringWithSeparator(ListUtil.createList("derp", "plop", "can"), " - "));
	}
	
	@Test
	public void testBuildString(){
		assertEquals("derp", StringUtil.buildString(ListUtil.createList("d", "e", "r", "p")));
	}
	
	@Test
	public void testBuildStringWithNewLines(){
		assertEquals("derp\nplop\ncan",
				StringUtil.buildStringWithNewLines(ListUtil.createList("derp", "plop", "can")));
	}
	
	@Test
	public void testBuildCommaSeparatedString(){
		assertEquals("derp,plop,can",
				StringUtil.buildCommaSeparatedString(ListUtil.createList("derp", "plop", "can")));
	}
	
	@Test
	public void testParseListFromStringWithSeparatorTrimMissingSeparator(){
		List<String> result = StringUtil.parseListFromStringWithSeparator("  test  ", "f1", true);
		assertEquals(1, result.size());
		assertEquals("test", result.get(0));
	}
	
	@Test
	public void testParseListFromStringWithSeparatorNoTrimMissingSeparator(){
		List<String> result = StringUtil.parseListFromStringWithSeparator("  test  ", "e3", false);
		assertEquals(1, result.size());
		assertEquals("  test  ", result.get(0));
	}
	
	@Test
	public void testParseListFromStringWithSeparatorTrim(){
		List<String> result = StringUtil.parseListFromStringWithSeparator("  test  -  derp  -  plop  ",
																			"-", true);
		assertEquals(3, result.size());
		assertEquals("test", result.get(0));
		assertEquals("derp", result.get(1));
		assertEquals("plop", result.get(2));
	}
	
	@Test
	public void testParseListFromStringWithSeparatorNoTrim(){
		List<String> result = StringUtil.parseListFromStringWithSeparator("  test  -  derp  -  plop  ",
																			"-", false);
		assertEquals(3, result.size());
		assertEquals("  test  ", result.get(0));
		assertEquals("  derp  ", result.get(1));
		assertEquals("  plop  ", result.get(2));
	}
	
	@Test
	public void testParseCommaSeparatedListFromString(){
		List<String> result = StringUtil.parseCommaSeparatedListFromString("  test ,  plop,  derp  ");
		assertEquals(3, result.size());
		assertEquals("test", result.get(0));
		assertEquals("plop", result.get(1));
		assertEquals("derp", result.get(2));
	}
}
