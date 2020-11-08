package com.github.tadukoo.util.view.form.field;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanFormFieldTest{
	private BooleanFormField field = BooleanFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.BOOLEAN, field.getType());
		assertFalse(field.getDefaultValue());
		assertEquals(LabelType.NONE, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertTrue(field.isEditable());
	}
	
	@Test
	public void testSettings(){
		field = BooleanFormField.builder().key("Test").defaultValue(true).labelType(LabelType.TITLED_BORDER)
				.rowPos(2).colPos(5).rowSpan(3).colSpan(7)
				.editable(false).build();
		assertEquals("Test", field.getKey());
		assertTrue(field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(2, field.getRowPos());
		assertEquals(5, field.getColPos());
		assertEquals(3, field.getRowSpan());
		assertEquals(7, field.getColSpan());
		assertFalse(field.isEditable());
	}
	
	@Test
	public void testGetComponent(){
		field = BooleanFormField.builder().key("Test").defaultValue(true).editable(false).build();
		JComponent component = field.getComponent();
		assertTrue(component instanceof JCheckBox);
		assertEquals("Test", ((JCheckBox) component).getText());
		assertTrue(((JCheckBox) component).isSelected());
		assertFalse(component.isEnabled());
	}
	
	@Test
	public void testGetValueBadComponent(){
		assertNull(field.getValue(new JLabel("Yep")));
	}
	
	@Test
	public void testGetValue(){
		assertTrue(field.getValue(new JCheckBox("Testing", true)));
	}
}
