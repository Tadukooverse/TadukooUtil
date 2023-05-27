package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	public void testAllBlankNulls(){
		assertTrue(StringUtil.allBlank(null, null, null));
	}
	
	@Test
	public void testAllBlankFalseLeftNonEmpty(){
		assertFalse(StringUtil.allBlank("yes", null, null));
	}
	
	@Test
	public void testAllBlankFalseMiddleEmpty(){
		assertFalse(StringUtil.allBlank(null, "yes", null));
	}
	
	@Test
	public void testAllBlankFalseRightEmpty(){
		assertFalse(StringUtil.allBlank(null, null, "yes"));
	}
	
	@Test
	public void testAllBlankEmpties(){
		assertTrue(StringUtil.allBlank("", "", ""));
	}
	
	@Test
	public void testAllBlankFalseOnWhitespace(){
		assertFalse(StringUtil.allBlank(" ", "", null));
	}
	
	@Test
	public void testAllBlankMixture(){
		assertTrue(StringUtil.allBlank("", null, ""));
	}
	
	@Test
	public void testNoneBlank(){
		assertTrue(StringUtil.noneBlank("yes", "no", "maybe"));
	}
	
	@Test
	public void testNoneBlankFalseLeftNull(){
		assertFalse(StringUtil.noneBlank(null, "no", "maybe"));
	}
	
	@Test
	public void testNoneBlankFalseMiddleNull(){
		assertFalse(StringUtil.noneBlank("yes", null, "maybe"));
	}
	
	@Test
	public void testNoneBlankFalseRightNull(){
		assertFalse(StringUtil.noneBlank("yes", "no", null));
	}
	
	@Test
	public void testNoneBlankFalseAllNull(){
		assertFalse(StringUtil.noneBlank(null, null, null));
	}
	
	@Test
	public void testNoneBlankFalseLeftEmpty(){
		assertFalse(StringUtil.noneBlank("", "no", "maybe"));
	}
	
	@Test
	public void testNoneBlankFalseMiddleEmpty(){
		assertFalse(StringUtil.noneBlank("yes", "", "maybe"));
	}
	
	@Test
	public void testNoneBlankFalseRightEmpty(){
		assertFalse(StringUtil.noneBlank("yes", "no", ""));
	}
	
	@Test
	public void testNoneBlankFalseAllEmpty(){
		assertFalse(StringUtil.noneBlank("", "", ""));
	}
	
	@Test
	public void testNoneBlankOnWhitespace(){
		assertTrue(StringUtil.noneBlank(" ", " ", " "));
	}
	
	@Test
	public void testAnyBlank(){
		assertFalse(StringUtil.anyBlank("yes", "no", "maybe"));
	}
	
	@Test
	public void testAnyBlankTrueLeftNull(){
		assertTrue(StringUtil.anyBlank(null, "no", "maybe"));
	}
	
	@Test
	public void testAnyBlankTrueMiddleNull(){
		assertTrue(StringUtil.anyBlank("yes", null, "maybe"));
	}
	
	@Test
	public void testAnyBlankTrueRightNull(){
		assertTrue(StringUtil.anyBlank("yes", "no", null));
	}
	
	@Test
	public void testAnyBlankTrueAllNull(){
		assertTrue(StringUtil.anyBlank(null, null, null));
	}
	
	@Test
	public void testAnyBlankTrueLeftEmpty(){
		assertTrue(StringUtil.anyBlank("", "no", "maybe"));
	}
	
	@Test
	public void testAnyBlankTrueMiddleEmpty(){
		assertTrue(StringUtil.anyBlank("yes", "", "maybe"));
	}
	
	@Test
	public void testAnyBlankTrueRightEmpty(){
		assertTrue(StringUtil.anyBlank("yes", "no", ""));
	}
	
	@Test
	public void testAnyBlankTrueAllEmpty(){
		assertTrue(StringUtil.anyBlank("", "", ""));
	}
	
	@Test
	public void testAnyBlankOnWhitespace(){
		assertFalse(StringUtil.anyBlank(" ", " ", " "));
	}
	
	@Test
	public void testAnyNotBlankNulls(){
		assertFalse(StringUtil.anyNotBlank(null, null, null));
	}
	
	@Test
	public void testAnyNotBlankTrueLeftNonEmpty(){
		assertTrue(StringUtil.anyNotBlank("yes", null, null));
	}
	
	@Test
	public void testAnyNotBlankTrueMiddleEmpty(){
		assertTrue(StringUtil.anyNotBlank(null, "yes", null));
	}
	
	@Test
	public void testAnyNotBlankTrueRightEmpty(){
		assertTrue(StringUtil.anyNotBlank(null, null, "yes"));
	}
	
	@Test
	public void testAnyNotBlankEmpties(){
		assertFalse(StringUtil.anyNotBlank("", "", ""));
	}
	
	@Test
	public void testAnyNotBlankTrueOnWhitespace(){
		assertTrue(StringUtil.anyNotBlank(" ", "", null));
	}
	
	@Test
	public void testAnyNotBlankMixture(){
		assertFalse(StringUtil.anyNotBlank("", null, ""));
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
	public void testTrimOnNull(){
		assertNull(StringUtil.trim(null));
	}
	
	@Test
	public void testTrimOnEmpty(){
		assertEquals("", StringUtil.trim(""));
	}
	
	@Test
	public void testTrimOnString(){
		assertEquals("Test", StringUtil.trim("   \t  Test  \t  \t"));
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
	public void testParseListFromStringWithSeparatorUsingRegex(){
		List<String> result = StringUtil.parseListFromStringWithSeparator("package com.example;", "\\s+", false);
		
		assertEquals(1, result.size());
		assertEquals("package com.example;", result.get(0));
	}
	
	@Test
	public void testParseListFromStringWithRegexTrimMissingSeparator(){
		List<String> result = StringUtil.parseListFromStringWithRegex("  test  ", "f1", true);
		assertEquals(1, result.size());
		assertEquals("test", result.get(0));
	}
	
	@Test
	public void testParseListFromStringWithRegexNoTrimMissingSeparator(){
		List<String> result = StringUtil.parseListFromStringWithRegex("  test  ", "e3", false);
		assertEquals(1, result.size());
		assertEquals("  test  ", result.get(0));
	}
	
	@Test
	public void testParseListFromStringWithRegexTrim(){
		List<String> result = StringUtil.parseListFromStringWithRegex("  test  -  derp  -  plop  ",
				"-", true);
		assertEquals(3, result.size());
		assertEquals("test", result.get(0));
		assertEquals("derp", result.get(1));
		assertEquals("plop", result.get(2));
	}
	
	@Test
	public void testParseListFromStringWithRegexNoTrim(){
		List<String> result = StringUtil.parseListFromStringWithRegex("  test  -  derp  -  plop  ",
				"-", false);
		assertEquals(3, result.size());
		assertEquals("  test  ", result.get(0));
		assertEquals("  derp  ", result.get(1));
		assertEquals("  plop  ", result.get(2));
	}
	
	@Test
	public void testParseListFromStringWithRegexUsingRegex(){
		List<String> result = StringUtil.parseListFromStringWithRegex("package com.example;", "\\s+", false);
		
		assertEquals(2, result.size());
		assertEquals("package", result.get(0));
		assertEquals("com.example;", result.get(1));
	}
	
	@Test
	public void testParseCommaSeparatedListFromString(){
		List<String> result = StringUtil.parseCommaSeparatedListFromString("  test ,  plop,  derp  ");
		assertEquals(3, result.size());
		assertEquals("test", result.get(0));
		assertEquals("plop", result.get(1));
		assertEquals("derp", result.get(2));
	}
	
	@Test
	public void testParseListFromStringWithPattern(){
		List<String> result = StringUtil.parseListFromStringWithPattern("umyumumyum", "(um)+", false);
		assertEquals(3, result.size());
		assertEquals("um", result.get(0));
		assertEquals("umum", result.get(1));
		assertEquals("um", result.get(2));
	}
	
	@Test
	public void testParseListFromStringWithPatternPatternDoesntAppear(){
		List<String> result = StringUtil.parseListFromStringWithPattern("umyumumyum", "derp", false);
		assertEquals(0, result.size());
	}
	
	@Test
	public void testParseListFromStringWithPatternAndTrim(){
		List<String> result = StringUtil.parseListFromStringWithPattern("derp plop derp", "\\s*plop\\s*", true);
		assertEquals(1, result.size());
		assertEquals("plop", result.get(0));
	}
	
	@Test
	public void testIndentAllLinesSingleLine(){
		assertEquals("\tThe line", StringUtil.indentAllLines("The line"));
	}
	
	@Test
	public void testIndentAllLinesMultiLines(){
		assertEquals("""
				\tThe line
				\tThe second line
				\tThe third line""", StringUtil.indentAllLines("The line\nThe second line\nThe third line"));
	}
	
	/*
	 * Handling String Case
	 */
	
	@Test
	public void testCapitalizeFirstLetter(){
		assertEquals("First", StringUtil.capitalizeFirstLetter("first"));
	}
	
	@Test
	public void testCapitalizeFirstLetterAlreadyCapital(){
		assertEquals("First", StringUtil.capitalizeFirstLetter("First"));
	}
	
	@Test
	public void testCapitalizeFirstLetterNotLetter(){
		assertEquals("!yep", StringUtil.capitalizeFirstLetter("!yep"));
	}
	
	@Test
	public void testCapitalizeFirstLetterSingleLetter(){
		assertEquals("Y", StringUtil.capitalizeFirstLetter("y"));
	}
	
	@Test
	public void testCapitalizeFirstLetterBlank(){
		assertEquals("", StringUtil.capitalizeFirstLetter(""));
	}
	
	@Test
	public void testIsPascalCase(){
		assertTrue(StringUtil.isPascalCase("TestStringHere"));
	}
	
	@Test
	public void testIsPascalCaseFirstWordNotCapital(){
		assertFalse(StringUtil.isPascalCase("testStringHere"));
	}
	
	@Test
	public void testIsPascalCaseContainsSpaces(){
		assertFalse(StringUtil.isPascalCase("Test String Here"));
	}
	
	@Test
	public void testIsPascalCaseContainsUnderscores(){
		assertFalse(StringUtil.isPascalCase("Test_String_Here"));
	}
	
	@Test
	public void testIsPascalCaseAllConditionsFail(){
		assertFalse(StringUtil.isPascalCase("test_String Here"));
	}
	
	@Test
	public void testToPascalCaseAlreadyIsPascalCase(){
		assertEquals("TestStringHere", StringUtil.toPascalCase("TestStringHere"));
	}
	
	@Test
	public void testToPascalCaseLowerCaseFirstLetter(){
		assertEquals("TestStringHere", StringUtil.toPascalCase("testStringHere"));
	}
	
	@Test
	public void testToPascalCaseContainsSpaces(){
		assertEquals("TestStringHere", StringUtil.toPascalCase("Test String here"));
	}
	
	@Test
	public void testToPascalCaseContainsUnderscores(){
		assertEquals("TestStringHere", StringUtil.toPascalCase("Test_String_here"));
	}
	
	@Test
	public void testToPascalCaseAllConditionsFail(){
		assertEquals("TestStringHere", StringUtil.toPascalCase("test_String here"));
	}
	
	@Test
	public void testIsCamelCase(){
		assertTrue(StringUtil.isCamelCase("testStringHere"));
	}
	
	@Test
	public void testIsCamelCaseFirstWordCapital(){
		assertFalse(StringUtil.isCamelCase("TestStringHere"));
	}
	
	@Test
	public void testIsCamelCaseContainsSpaces(){
		assertFalse(StringUtil.isCamelCase("test String Here"));
	}
	
	@Test
	public void testIsCamelCaseContainsUnderscores(){
		assertFalse(StringUtil.isCamelCase("test_String_Here"));
	}
	
	@Test
	public void testIsCamelCaseAllConditionsFail(){
		assertFalse(StringUtil.isCamelCase("Test_String Here"));
	}
	
	@Test
	public void testToCamelCaseAlreadyIsCamelCase(){
		assertEquals("testStringHere", StringUtil.toCamelCase("testStringHere"));
	}
	
	@Test
	public void testToCamelCaseCapitalFirstLetter(){
		assertEquals("testStringHere", StringUtil.toCamelCase("TestStringHere"));
	}
	
	@Test
	public void testToCamelCaseContainsSpaces(){
		assertEquals("testStringHere", StringUtil.toCamelCase("test String here"));
	}
	
	@Test
	public void testToCamelCaseContainsUnderscores(){
		assertEquals("testStringHere", StringUtil.toCamelCase("test_String_here"));
	}
	
	@Test
	public void testToCamelCaseAllConditionsFail(){
		assertEquals("testStringHere", StringUtil.toCamelCase("Test_string here"));
	}
	
	@Test
	public void testIsSnakeCase(){
		assertTrue(StringUtil.isSnakeCase("test_string_here"));
	}
	
	@Test
	public void testIsSnakeCaseContainsSpaces(){
		assertFalse(StringUtil.isSnakeCase("test_string here"));
	}
	
	@Test
	public void testIsSnakeCaseNoUnderscores(){
		assertFalse(StringUtil.isSnakeCase("TestStringHere"));
	}
	
	@Test
	public void testIsSnakeCaseAllConditionsFail(){
		assertFalse(StringUtil.isSnakeCase("Test String Here"));
	}
	
	@Test
	public void testToSnakeCaseIsAlreadySnakeCase(){
		assertEquals("test_string_here", StringUtil.toSnakeCase("test_string_here"));
	}
	
	@Test
	public void testToSnakeCaseContainsSpaces(){
		assertEquals("test_string_here", StringUtil.toSnakeCase("test string here"));
	}
	
	@Test
	public void testToSnakeCaseIsPascalCase(){
		assertEquals("test_string_here", StringUtil.toSnakeCase("TestStringHere"));
	}
	
	@Test
	public void testToSnakeCaseIsCamelCase(){
		assertEquals("test_string_here", StringUtil.toSnakeCase("testStringHere"));
	}
	
	@Test
	public void testToSnakeCaseDifferent(){
		assertEquals("test_string_here", StringUtil.toSnakeCase("Test String_here"));
	}
}
