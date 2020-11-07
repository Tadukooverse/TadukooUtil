package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.view.form.AbstractForm;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class FormFormFieldTest{
	private FormFormField field = FormFormField.builder().build();
	private boolean savedValues = false;
	private final AbstractForm form = new AbstractForm(new HashMap<>()){
		
		@Override
		public void saveValues(){
			super.saveValues();
			savedValues = true;
		}
		
		@Override
		public void setDefaultFields(){
		
		}
	};
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.FORM, field.getType());
		assertNull(field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
	}
	
	@Test
	public void testSettings(){
		field = FormFormField.builder().key("Test").defaultValue(form).labelType(LabelType.TITLED_BORDER)
				.rowPos(2).colPos(5).rowSpan(3).colSpan(7).build();
		assertEquals("Test", field.getKey());
		assertEquals(form, field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(2, field.getRowPos());
		assertEquals(5, field.getColPos());
		assertEquals(3, field.getRowSpan());
		assertEquals(7, field.getColSpan());
	}
	
	@Test
	public void testGetComponentNoForm(){
		JComponent component = field.getComponent();
		assertTrue(component instanceof JLabel);
		assertEquals("No value", ((JLabel) component).getText());
	}
	
	@Test
	public void testGetComponentForm(){
		field = FormFormField.builder().defaultValue(form).build();
		JComponent component = field.getComponent();
		assertTrue(component instanceof AbstractForm);
		assertEquals(form, component);
	}
	
	@Test
	public void testGetValueNotForm(){
		assertNull(field.getValue(new JLabel("Test")));
	}
	
	@Test
	public void testGetValueForm(){
		assertEquals(form, field.getValue(form));
		assertTrue(savedValues);
	}
}
