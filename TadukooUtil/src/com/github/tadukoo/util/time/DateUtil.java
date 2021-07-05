package com.github.tadukoo.util.time;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date Util provides utilities for dealing with {@link Date}s.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public final class DateUtil{
	
	/** Not allowed to make a DateUtil */
	private DateUtil(){ }
	
	/**
	 * Creates a {@link Date} with the given month, day, and year.
	 *
	 * @param month The {@link Month}
	 * @param day The day of the month
	 * @param year The year
	 * @return A {@link Date} for the given month, day, and year
	 */
	public static Date createDate(Month month, int day, int year){
		return convertToDate(LocalDate.of(year, month, day));
	}
	
	/**
	 * Creates a {@link Date} with the given month, day, and year.
	 *
	 * @param month The month as a string
	 * @param day The day of the month
	 * @param year The year
	 * @return A {@link Date} for the given month, day, and year
	 */
	public static Date createDate(String month, int day, int year){
		return createDate(MonthUtil.parseFromString(month), day, year);
	}
	
	/**
	 * Creates a {@link Date} with the given month, day, and year.
	 *
	 * @param month The month as an integer (1 = January)
	 * @param day The day of the month
	 * @param year The year
	 * @return A {@link Date} for the given month, day, and year
	 */
	public static Date createDate(int month, int day, int year){
		return convertToDate(LocalDate.of(year, month, day));
	}
	
	/**
	 * Converts the given {@link LocalDate} to a {@link Date}.
	 *
	 * @param localDate The {@link LocalDate} to be converted
	 * @return The {@link Date} created from the {@link LocalDate}
	 */
	public static Date convertToDate(LocalDate localDate){
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * Converts the given {@link Date} to a {@link LocalDate}.
	 *
	 * @param date The {@link Date} to be converted
	 * @return The {@link LocalDate} created from the {@link Date}
	 */
	public static LocalDate convertToLocalDate(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
