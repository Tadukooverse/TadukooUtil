package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.LabelType;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntFormFieldTest{
	private IntFormField field = IntFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.INT, field.getType());
		assertEquals(Integer.valueOf(0), field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertNull(field.getMinValue());
		assertNull(field.getMaxValue());
		assertEquals(Integer.valueOf(1), field.getStepSize());
	}
	
	@Test
	public void testSettings(){
		field = IntFormField.builder().key("Test").defaultValue(25).labelType(LabelType.TITLED_BORDER)
				.rowPos(5).colPos(25).rowSpan(5).colSpan(3)
				.minValue(1).maxValue(125).stepSize(5).build();
		
		assertEquals("Test", field.getKey());
		assertEquals(Integer.valueOf(25), field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(5, field.getRowPos());
		assertEquals(25, field.getColPos());
		assertEquals(5, field.getRowSpan());
		assertEquals(3, field.getColSpan());
		assertEquals(Integer.valueOf(1), field.getMinValue());
		assertEquals(Integer.valueOf(125), field.getMaxValue());
		assertEquals(Integer.valueOf(5), field.getStepSize());
	}
	
	@Test
	public void testGetValue(){
		assertEquals(Integer.valueOf(65),
				field.getValue(new JSpinner(new SpinnerNumberModel(65, null, null, 1))));
	}
}
