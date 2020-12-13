package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.LabelType;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShortFormFieldTest{
	private ShortFormField field = ShortFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.SHORT, field.getType());
		assertEquals(Short.valueOf((short) 0), field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertNull(field.getMinValue());
		assertNull(field.getMaxValue());
		assertEquals(Short.valueOf((short) 1), field.getStepSize());
	}
	
	@Test
	public void testSettings(){
		field = ShortFormField.builder().key("Test").defaultValue((short) 25).labelType(LabelType.TITLED_BORDER)
				.rowPos(5).colPos(25).rowSpan(5).colSpan(3)
				.minValue((short) 1).maxValue((short) 125).stepSize((short) 5).build();
		
		assertEquals("Test", field.getKey());
		assertEquals(Short.valueOf((short) 25), field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(5, field.getRowPos());
		assertEquals(25, field.getColPos());
		assertEquals(5, field.getRowSpan());
		assertEquals(3, field.getColSpan());
		assertEquals(Short.valueOf((short) 1), field.getMinValue());
		assertEquals(Short.valueOf((short) 125), field.getMaxValue());
		assertEquals(Short.valueOf((short) 5), field.getStepSize());
	}
	
	@Test
	public void testGetValue(){
		assertEquals(Short.valueOf((short) 65),
				field.getValue(new JSpinner(new SpinnerNumberModel((short) 65, null, null, (short) 1))));
	}
}
