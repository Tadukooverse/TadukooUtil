package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.LabelType;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DoubleFormFieldTest{
	private DoubleFormField field = DoubleFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.DOUBLE, field.getType());
		assertEquals(Double.valueOf(0), field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertNull(field.getMinValue());
		assertNull(field.getMaxValue());
		assertEquals(Double.valueOf(1), field.getStepSize());
	}
	
	@Test
	public void testSettings(){
		field = DoubleFormField.builder().key("Test").defaultValue(25.0).labelType(LabelType.TITLED_BORDER)
				.rowPos(5).colPos(25).rowSpan(5).colSpan(3)
				.minValue(1.0).maxValue(125.0).stepSize(5.0).build();
		
		assertEquals("Test", field.getKey());
		assertEquals(Double.valueOf(25), field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(5, field.getRowPos());
		assertEquals(25, field.getColPos());
		assertEquals(5, field.getRowSpan());
		assertEquals(3, field.getColSpan());
		assertEquals(Double.valueOf(1), field.getMinValue());
		assertEquals(Double.valueOf(125), field.getMaxValue());
		assertEquals(Double.valueOf(5), field.getStepSize());
	}
	
	@Test
	public void testGetValue(){
		assertEquals(Double.valueOf(65),
				field.getValue(new JSpinner(new SpinnerNumberModel(65.0, null, null, 1.0))));
	}
}
