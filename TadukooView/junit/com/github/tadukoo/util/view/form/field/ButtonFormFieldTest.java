package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.view.components.TadukooButton;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonFormFieldTest{
	private ButtonFormField field = ButtonFormField.builder().build();
	private final ActionListener testAction = e -> { };
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.BUTTON, field.getType());
		assertNull(field.getDefaultValue());
		assertEquals(LabelType.NONE, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
	}
	
	@Test
	public void testSettings(){
		field = ButtonFormField.builder().key("Test").defaultValue("Yes").labelType(LabelType.TITLED_BORDER)
				.rowPos(2).colPos(5).rowSpan(3).colSpan(7).actionListener(testAction).build();
		assertEquals("Test", field.getKey());
		assertEquals("Yes", field.getDefaultValue());
		assertEquals(LabelType.TITLED_BORDER, field.getLabelType());
		assertEquals(2, field.getRowPos());
		assertEquals(5, field.getColPos());
		assertEquals(3, field.getRowSpan());
		assertEquals(7, field.getColSpan());
		assertEquals(testAction, field.getActionListener());
	}
	
	@Test
	public void testGetComponent(){
		field = ButtonFormField.builder().key("Test Key").actionListener(testAction).build();
		JComponent component = field.getComponent();
		assertTrue(component instanceof TadukooButton);
		assertEquals("Test Key", ((TadukooButton) component).getText());
		assertEquals(testAction, ((TadukooButton) component).getActionListeners()[0]);
	}
	
	@Test
	public void testGetValue(){
		assertNull(field.getValue(new JLabel("Derp")));
	}
}
