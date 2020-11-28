package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.LabelType;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FloatFormFieldTest{
	private FloatFormField field = FloatFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.FLOAT, field.getType());
		assertEquals(Float.valueOf(0), field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertNull(field.getMinValue());
		assertNull(field.getMaxValue());
		assertEquals(Float.valueOf(1), field.getStepSize());
	}
	
	@Test
	public void testSettings(){
		field = FloatFormField.builder().key("Test").defaultValue(25.0f).labelType(LabelType.TITLED_BORDER)
				.rowPos(5).colPos(25).rowSpan(5).colSpan(3)
				.minValue(1.0f).maxValue(125.0f).stepSize(5.0f).build();
		
		assertEquals("Test", field.getKey());
		assertEquals(Float.valueOf(25), field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(5, field.getRowPos());
		assertEquals(25, field.getColPos());
		assertEquals(5, field.getRowSpan());
		assertEquals(3, field.getColSpan());
		assertEquals(Float.valueOf(1), field.getMinValue());
		assertEquals(Float.valueOf(125), field.getMaxValue());
		assertEquals(Float.valueOf(5), field.getStepSize());
	}
	
	@Test
	public void testGetValue(){
		assertEquals(Float.valueOf(65),
				field.getValue(new JSpinner(new SpinnerNumberModel(65.0f, null, null, 1.0f))));
	}
}
