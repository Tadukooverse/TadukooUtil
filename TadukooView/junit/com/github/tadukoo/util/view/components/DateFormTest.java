package com.github.tadukoo.util.view.components;

import com.github.tadukoo.util.time.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DateFormTest{
	private DateForm dateForm = new DateForm(DateUtil.createDate(Month.JULY, 4, 1776), 1600, 2100);
	
	@Test
	public void testPassedInMonth(){
		assertEquals(Month.JULY, dateForm.getMonth());
	}
	
	@Test
	public void testPassedInMonthString(){
		assertEquals("July", dateForm.getMonthString());
	}
	
	@Test
	public void testPassedInDay(){
		assertEquals(4, dateForm.getDay());
	}
	
	@Test
	public void testPassedInYear(){
		assertEquals(1776, dateForm.getYear());
	}
	
	@Test
	public void testMinYear(){
		assertEquals(1600, dateForm.getMinYear());
	}
	
	@Test
	public void testMaxYear(){
		assertEquals(2100, dateForm.getMaxYear());
	}
	
	@Test
	public void testSetMonth(){
		dateForm.setMonth(Month.DECEMBER);
		assertEquals(Month.DECEMBER, dateForm.getMonth());
	}
	
	@Test
	public void testSetMonthString(){
		dateForm.setMonth("September");
		assertEquals("September", dateForm.getMonthString());
	}
	
	@Test
	public void testSetDay(){
		dateForm.setDay(27);
		assertEquals(27, dateForm.getDay());
	}
	
	@Test
	public void testSetYear(){
		dateForm.setYear(2020);
		assertEquals(2020, dateForm.getYear());
	}
	
	@Test
	public void testGetDate(){
		Date theDate = DateUtil.createDate(Month.JULY, 4, 1776);
		assertEquals(theDate, dateForm.getDate());
	}
	
	@Test
	public void testNullDate(){
		dateForm = new DateForm(null, 100, 200);
		assertNull(dateForm.getMonthString());
		assertEquals(1, dateForm.getDay());
		assertEquals(100, dateForm.getYear());
	}
}
