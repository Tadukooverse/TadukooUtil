package com.github.tadukoo.util.view.components;

import com.github.tadukoo.util.map.MapUtil;
import com.github.tadukoo.util.time.DateUtil;
import com.github.tadukoo.util.time.MonthUtil;
import com.github.tadukoo.util.tuple.Pair;
import com.github.tadukoo.util.view.form.AbstractForm;
import com.github.tadukoo.util.view.form.field.DropDownFormField;
import com.github.tadukoo.util.view.form.field.number.IntFormField;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

/**
 * Date Form is a {@link com.github.tadukoo.util.view.form.Form Form} used to store a {@link Date}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public class DateForm extends AbstractForm{
	// Passed in default values
	/** Key used for storing default Date for passed-in values */
	private static final String DATE = "Date";
	/** Key used for storing minimum year for passed-in values */
	private static final String MIN_YEAR = "minYear";
	/** Key used for storing maximum year for passed-in values */
	private static final String MAX_YEAR = "maxYear";
	
	// Form Field Names
	/** Key used for storing Month string in Drop-Down Field */
	private static final String MONTH = "Month";
	/** Key used for storing day of the month */
	private static final String DAY = "Day";
	/** Key used for storing year */
	private static final String YEAR = "Year";
	
	/**
	 * Creates a new Date Form with the given parameters
	 *
	 * @param date The {@link Date} to have filled in, or null
	 * @param minYear The minimum year to allow
	 * @param maxYear The maximum year to allow
	 */
	public DateForm(Date date, int minYear, int maxYear){
		super(MapUtil.createMap(Pair.of(DATE, date),
				Pair.of(MIN_YEAR, minYear), Pair.of(MAX_YEAR, maxYear)));
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean labelsOnTop(){
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDefaultFields(){
		// Grab date from passed in values
		Date date = (Date) getItem(DATE);
		LocalDate localDate = date == null?null:DateUtil.convertToLocalDate(date);
		// Remove date from the map
		removeItem(DATE);
		
		// Grab min + max years from passed in values
		int minYear = (int) getItem(MIN_YEAR);
		int maxYear = (int) getItem(MAX_YEAR);
		
		// Month Field
		addField(DropDownFormField.builder()
				.key(MONTH).defaultValue(localDate == null?null:MonthUtil.asString(localDate.getMonth()))
				.rowPos(0).colPos(0)
				.options(MonthUtil.asStringArray())
				.build());
		
		// Day Field
		addField(IntFormField.builder()
				.key(DAY).defaultValue(localDate == null?1:localDate.getDayOfMonth())
				.rowPos(0).colPos(1)
				.minValue(1).maxValue(31)
				.build());
		
		// Year Field
		addField(IntFormField.builder()
				.key(YEAR).defaultValue(localDate == null?minYear:localDate.getYear())
				.rowPos(0).colPos(2)
				.minValue(minYear).maxValue(maxYear)
				.build());
	}
	
	/**
	 * @return The {@link Month}
	 */
	public Month getMonth(){
		return MonthUtil.parseFromString(getMonthString());
	}
	
	/**
	 * @return The Month as a string
	 */
	public String getMonthString(){
		return (String) getItem(MONTH);
	}
	
	/**
	 * Set the Month for this Date
	 *
	 * @param month The {@link Month} to be set
	 */
	public void setMonth(Month month){
		setItem(MONTH, MonthUtil.asString(month));
	}
	
	/**
	 * Sets the Month for this Date
	 *
	 * @param month The Month to be set
	 */
	public void setMonth(String month){
		setItem(MONTH, month);
	}
	
	/**
	 * @return The day of the month
	 */
	public int getDay(){
		return (int) getItem(DAY);
	}
	
	/**
	 * Sets the day of the month
	 *
	 * @param day The day of the month to be set
	 */
	public void setDay(int day){
		setItem(DAY, day);
	}
	
	/**
	 * @return The year
	 */
	public int getYear(){
		return (int) getItem(YEAR);
	}
	
	/**
	 * Sets the year
	 *
	 * @param year The year to be set
	 */
	public void setYear(int year){
		setItem(YEAR, year);
	}
	
	/**
	 * @return The minimum year allowed
	 */
	public int getMinYear(){
		return (int) getItem(MIN_YEAR);
	}
	
	/**
	 * @return The maximum year allowed
	 */
	public int getMaxYear(){
		return (int) getItem(MAX_YEAR);
	}
	
	/**
	 * @return The currently set {@link Date}
	 */
	public Date getDate(){
		return DateUtil.createDate(getMonth(), getDay(), getYear());
	}
}
