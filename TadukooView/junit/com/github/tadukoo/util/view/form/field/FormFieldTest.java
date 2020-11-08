package com.github.tadukoo.util.view.form.field;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FormFieldTest{
	private final JLabel label = new JLabel("Derp");
	
	private class TestFormField extends FormField<String>{
		
		private TestFormField(FieldType type, String key, String defaultValue, LabelType labelType,
		                        int rowPos, int colPos, int rowSpan, int colSpan){
			super(type, key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan);
		}
		
		@Override
		public JComponent getComponent(){
			return label;
		}
		
		@Override
		public String getValue(JComponent component){
			return label.getText();
		}
	}
	
	private class TestFormFieldBuilder extends FormField.FormFieldBuilder<String>{
		
		public TestFormFieldBuilder(){ }
		
		@Override
		public FormField<String> build(){
			return new TestFormField(FieldType.STRING, key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan);
		}
	}
	
	private FormField<String> field = new TestFormFieldBuilder().key("Test").rowPos(2).colPos(5).build();
	
	@Test
	public void testDefaults(){
		assertNull(field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
	}
	
	@Test
	public void testSettings(){
		field = new TestFormFieldBuilder().key("Test").defaultValue("Yes").labelType(LabelType.NONE)
				.rowPos(2).colPos(5).rowSpan(3).colSpan(7).build();
		assertEquals(FieldType.STRING, field.getType());
		assertEquals("Test", field.getKey());
		assertEquals("Yes", field.getDefaultValue());
		assertEquals(LabelType.NONE, field.getLabelType());
		assertEquals(2, field.getRowPos());
		assertEquals(5, field.getColPos());
		assertEquals(3, field.getRowSpan());
		assertEquals(7, field.getColSpan());
	}
	
	@Test
	public void testGetComponent(){
		assertEquals(label, field.getComponent());
	}
	
	@Test
	public void testGetValue(){
		assertEquals("Derp", field.getValue(label));
	}
}
