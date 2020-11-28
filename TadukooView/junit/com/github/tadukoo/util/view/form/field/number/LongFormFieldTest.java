package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.LabelType;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LongFormFieldTest{
	private LongFormField field = LongFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.LONG, field.getType());
		assertEquals(Long.valueOf(0), field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertNull(field.getMinValue());
		assertNull(field.getMaxValue());
		assertEquals(Long.valueOf(1), field.getStepSize());
	}
	
	@Test
	public void testSettings(){
		field = LongFormField.builder().key("Test").defaultValue(25L).labelType(LabelType.TITLED_BORDER)
				.rowPos(5).colPos(25).rowSpan(5).colSpan(3)
				.minValue(1L).maxValue(125L).stepSize(5L).build();
		
		assertEquals("Test", field.getKey());
		assertEquals(Long.valueOf(25), field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(5, field.getRowPos());
		assertEquals(25, field.getColPos());
		assertEquals(5, field.getRowSpan());
		assertEquals(3, field.getColSpan());
		assertEquals(Long.valueOf(1), field.getMinValue());
		assertEquals(Long.valueOf(125), field.getMaxValue());
		assertEquals(Long.valueOf(5), field.getStepSize());
	}
	
	@Test
	public void testGetValue(){
		assertEquals(Long.valueOf(65),
				field.getValue(new JSpinner(new SpinnerNumberModel(65L, null, null, 1L))));
	}
}
