package com.github.tadukoo.util.view.form.field;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class StringFormFieldTest{
	private StringFormField field = StringFormField.builder().build();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.STRING, field.getType());
		assertNull(field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
		assertEquals(StringFormField.StringFieldType.NORMAL, field.getStringFieldType());
		assertTrue(field.isEditable());
		assertEquals(-1, field.getColumns());
	}
	
	@Test
	public void testSettings(){
		field = StringFormField.builder().key("Test").defaultValue("Yes").labelType(LabelType.NONE)
				.rowPos(2).colPos(5).rowSpan(3).colSpan(7)
				.stringFieldType(StringFormField.StringFieldType.PASSWORD)
				.editable(false).columns(27).build();
		assertEquals("Test", field.getKey());
		assertEquals("Yes", field.getDefaultValue());
		assertEquals(LabelType.NONE, field.getLabelType());
		assertEquals(2, field.getRowPos());
		assertEquals(5, field.getColPos());
		assertEquals(3, field.getRowSpan());
		assertEquals(7, field.getColSpan());
		assertEquals(StringFormField.StringFieldType.PASSWORD, field.getStringFieldType());
		assertFalse(field.isEditable());
		assertEquals(27, field.getColumns());
	}
	
	@Test
	public void testGetComponentNormal(){
		field = StringFormField.builder().defaultValue("Derp")
				.stringFieldType(StringFormField.StringFieldType.NORMAL).build();
		JComponent component = field.getComponent();
		assertTrue(component instanceof JTextField);
		assertEquals("Derp", ((JTextField) component).getText());
	}
	
	@Test
	public void testGetComponentTitle(){
		field = StringFormField.builder().defaultValue("Test")
				.stringFieldType(StringFormField.StringFieldType.TITLE).build();
		JComponent component = field.getComponent();
		assertTrue(component instanceof JLabel);
		assertEquals("Test", ((JLabel) component).getText());
	}
	
	@Test
	public void testGetComponentPassword(){
		field = StringFormField.builder().defaultValue("Testy")
				.stringFieldType(StringFormField.StringFieldType.PASSWORD).build();
		JComponent component = field.getComponent();
		assertTrue(component instanceof JPasswordField);
		assertArrayEquals(new char[]{'T', 'e', 's', 't', 'y'}, ((JPasswordField) component).getPassword());
	}
	
	@Test
	public void testGetComponentDefaultColumns(){
		assertEquals(25, ((JTextField) field.getComponent()).getColumns());
	}
	
	@Test
	public void testGetComponentSetColumns(){
		field = StringFormField.builder().columns(19).build();
		assertEquals(19, ((JTextField) field.getComponent()).getColumns());
	}
	
	@Test
	public void testGetValueNormal(){
		assertEquals("Testy", field.getValue(new JTextField("Testy")));
	}
	
	@Test
	public void testGetValueTitle(){
		assertEquals("Yep", field.getValue(new JLabel("Yep")));
	}
	
	@Test
	public void testGetValuePassword(){
		assertEquals("A Password", field.getValue(new JPasswordField("A Password")));
	}
	
	@Test
	public void testGetValueRandomComponent(){
		assertNull(field.getValue(new JButton("Testing")));
	}
}
