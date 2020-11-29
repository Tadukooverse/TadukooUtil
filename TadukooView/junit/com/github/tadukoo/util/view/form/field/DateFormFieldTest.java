package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.time.DateUtil;
import com.github.tadukoo.util.view.components.DateForm;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.time.Month;
import java.util.Date;

import static org.junit.Assert.*;

public class DateFormFieldTest{
	private DateFormField field = DateFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertNull(field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertEquals(1900, field.getMinYear());
		assertEquals(2100, field.getMaxYear());
	}
	
	@Test
	public void testSettings(){
		Date date = DateUtil.createDate(Month.JULY, 4, 1776);
		field = DateFormField.builder().key("Derp").defaultValue(date).labelType(LabelType.TITLED_BORDER)
				.rowPos(5).colPos(3).rowSpan(2).colSpan(4).minYear(120).maxYear(1920).build();
		
		assertEquals(FieldType.DATE, field.getType());
		assertEquals("Derp", field.getKey());
		assertEquals(date, field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(5, field.getRowPos());
		assertEquals(3, field.getColPos());
		assertEquals(2, field.getRowSpan());
		assertEquals(4, field.getColSpan());
		assertEquals(120, field.getMinYear());
		assertEquals(1920, field.getMaxYear());
	}
	
	@Test
	public void testGetComponent(){
		Date date = DateUtil.createDate(Month.JULY, 4, 1776);
		field = DateFormField.builder().defaultValue(date).minYear(120).maxYear(1920).build();
		
		JComponent component = field.getComponent();
		assertTrue(component instanceof DateForm);
		DateForm dateForm = (DateForm) component;
		assertEquals(date, dateForm.getDate());
		assertEquals(Month.JULY, dateForm.getMonth());
		assertEquals(4, dateForm.getDay());
		assertEquals(1776, dateForm.getYear());
		assertEquals(120, dateForm.getMinYear());
		assertEquals(1920, dateForm.getMaxYear());
	}
	
	@Test
	public void testGetValue(){
		Date date = DateUtil.createDate(Month.JULY, 4, 1776);
		Date otherDate = field.getValue(new DateForm(date, 120, 1920));
		assertEquals(date, otherDate);
	}
	
	@Test
	public void testGetValueNotDateForm(){
		assertNull(field.getValue(new JLabel("Testing Stuff")));
	}
}
