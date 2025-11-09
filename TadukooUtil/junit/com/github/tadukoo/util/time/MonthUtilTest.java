package com.github.tadukoo.util.time;

import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MonthUtilTest{
	
	@Test
	public void testParseJanuary(){
		assertEquals(Month.JANUARY, MonthUtil.parseFromString("JanUaRy"));
	}
	
	@Test
	public void testParseFebruary(){
		assertEquals(Month.FEBRUARY, MonthUtil.parseFromString("fEbRUary"));
	}
	
	@Test
	public void testParseMarch(){
		assertEquals(Month.MARCH, MonthUtil.parseFromString("MARcH"));
	}
	
	@Test
	public void testParseApril(){
		assertEquals(Month.APRIL, MonthUtil.parseFromString("aPRIl"));
	}
	
	@Test
	public void testParseMay(){
		assertEquals(Month.MAY, MonthUtil.parseFromString("mAY"));
	}
	
	@Test
	public void testParseJune(){
		assertEquals(Month.JUNE, MonthUtil.parseFromString("jUnE"));
	}
	
	@Test
	public void testParseJuly(){
		assertEquals(Month.JULY, MonthUtil.parseFromString("JUlY"));
	}
	
	@Test
	public void testParseAugust(){
		assertEquals(Month.AUGUST, MonthUtil.parseFromString("AUGuSt"));
	}
	
	@Test
	public void testParseSeptember(){
		assertEquals(Month.SEPTEMBER, MonthUtil.parseFromString("September"));
	}
	
	@Test
	public void testParseOctober(){
		assertEquals(Month.OCTOBER, MonthUtil.parseFromString("OCTOBER"));
	}
	
	@Test
	public void testParseNovember(){
		assertEquals(Month.NOVEMBER, MonthUtil.parseFromString("november"));
	}
	
	@Test
	public void testParseDecember(){
		assertEquals(Month.DECEMBER, MonthUtil.parseFromString("DECemBEr"));
	}
	
	@Test
	public void testParseGarbage(){
		assertNull(MonthUtil.parseFromString("gobbledeegook"));
	}
	
	@Test
	public void testAsStringJanuary(){
		assertEquals("January", MonthUtil.asString(Month.JANUARY));
	}
	
	@Test
	public void testAsStringFebruary(){
		assertEquals("February", MonthUtil.asString(Month.FEBRUARY));
	}
	
	@Test
	public void testAsStringMarch(){
		assertEquals("March", MonthUtil.asString(Month.MARCH));
	}
	
	@Test
	public void testAsStringApril(){
		assertEquals("April", MonthUtil.asString(Month.APRIL));
	}
	
	@Test
	public void testAsStringMay(){
		assertEquals("May", MonthUtil.asString(Month.MAY));
	}
	
	@Test
	public void testAsStringJune(){
		assertEquals("June", MonthUtil.asString(Month.JUNE));
	}
	
	@Test
	public void testAsStringJuly(){
		assertEquals("July", MonthUtil.asString(Month.JULY));
	}
	
	@Test
	public void testAsStringAugust(){
		assertEquals("August", MonthUtil.asString(Month.AUGUST));
	}
	
	@Test
	public void testAsStringSeptember(){
		assertEquals("September", MonthUtil.asString(Month.SEPTEMBER));
	}
	
	@Test
	public void testAsStringOctober(){
		assertEquals("October", MonthUtil.asString(Month.OCTOBER));
	}
	
	@Test
	public void testAsStringNovember(){
		assertEquals("November", MonthUtil.asString(Month.NOVEMBER));
	}
	
	@Test
	public void testAsStringDecember(){
		assertEquals("December", MonthUtil.asString(Month.DECEMBER));
	}
	
	@Test
	public void testAsStringArray(){
		String[] monthStrings = MonthUtil.asStringArray();
		assertEquals(12, monthStrings.length);
		assertEquals("January", monthStrings[0]);
		assertEquals("February", monthStrings[1]);
		assertEquals("March", monthStrings[2]);
		assertEquals("April", monthStrings[3]);
		assertEquals("May", monthStrings[4]);
		assertEquals("June", monthStrings[5]);
		assertEquals("July", monthStrings[6]);
		assertEquals("August", monthStrings[7]);
		assertEquals("September", monthStrings[8]);
		assertEquals("October", monthStrings[9]);
		assertEquals("November", monthStrings[10]);
		assertEquals("December", monthStrings[11]);
	}
}
