package com.github.tadukoo.util.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest{
	
	@Test
	public void testCreateDateMonthEnum(){
		Date date = DateUtil.createDate(Month.JULY, 4, 1776);
		Date otherDate = DateUtil.convertToDate(LocalDate.of(1776, Month.JULY, 4));
		assertEquals(date, otherDate);
	}
	
	@Test
	public void testCreateDateMonthString(){
		Date date = DateUtil.createDate("August", 18, 2005);
		Date otherDate = DateUtil.convertToDate(LocalDate.of(2005, Month.AUGUST, 18));
		assertEquals(date, otherDate);
	}
	
	@Test
	public void testCreateDateMonthInt(){
		Date date = DateUtil.createDate(5, 12, 1984);
		Date otherDate = DateUtil.convertToDate(LocalDate.of(1984, Month.MAY, 12));
		assertEquals(date, otherDate);
	}
	
	@Test
	public void testConvertToDateAndBack(){
		LocalDate localDate = LocalDate.of(2020, Month.MARCH, 20);
		Date date = DateUtil.convertToDate(localDate);
		LocalDate otherLocalDate = DateUtil.convertToLocalDate(date);
		assertEquals(localDate, otherLocalDate);
	}
}
