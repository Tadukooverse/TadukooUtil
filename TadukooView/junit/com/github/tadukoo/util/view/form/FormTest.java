package com.github.tadukoo.util.view.form;

import com.github.tadukoo.util.view.form.field.StringFormField;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest{
	private boolean weSetThoseFields = false;
	
	private final Form form = new AbstractForm(new HashMap<>()){
		
		@Override
		public void setDefaultFields(){
			weSetThoseFields = true;
			addField(StringFormField.builder()
					.key("Derp").defaultValue("No")
					.build());
		}
	};
	
	@Test
	public void testConstructor(){
		Map<String, Object> map = form.getMap();
		assertFalse(map.isEmpty());
		assertTrue(map.containsKey("Derp"));
		assertEquals("No", map.get("Derp"));
	}
	
	@Test
	public void testHasKeyFalse(){
		assertFalse(form.hasKey("Test"));
	}
	
	@Test
	public void testHasKeyTrue(){
		form.setItem("Derp", 5);
		assertTrue(form.hasKey("Derp"));
	}
	
	@Test
	public void testGetKeysPopulated(){
		form.setItem("Derp", 5);
		form.setItem("Test", "Yes");
		Set<String> keys = form.getKeys();
		assertFalse(keys.isEmpty());
		assertTrue(keys.contains("Derp"));
		assertTrue(keys.contains("Test"));
	}
	
	@Test
	public void testHasItemNotSet(){
		assertFalse(form.hasItem("Test"));
	}
	
	@Test
	public void testHasItemSetToNull(){
		form.setItem("Test", null);
		assertFalse(form.hasItem("Test"));
	}
	
	@Test
	public void testHasItemTrue(){
		form.setItem("Derp", 5);
		assertTrue(form.hasItem("Derp"));
	}
	
	@Test
	public void testGetItemNotSet(){
		assertNull(form.getItem("Test"));
	}
	
	@Test
	public void testGetItem(){
		form.setItem("Derp", 5);
		assertEquals(5, form.getItem("Derp"));
	}
	
	@Test
	public void testSetItem(){
		form.setItem("Derp", 5);
		assertTrue(form.hasKey("Derp"));
		assertTrue(form.hasItem("Derp"));
		assertEquals(5, form.getItem("Derp"));
	}
	
	@Test
	public void testRemoveItem(){
		form.setItem("Derp", 5);
		assertTrue(form.hasKey("Derp"));
		assertTrue(form.hasItem("Derp"));
		assertEquals(5, form.getItem("Derp"));
		
		form.removeItem("Derp");
		assertFalse(form.hasKey("Derp"));
		assertFalse(form.hasItem("Derp"));
		assertNull(form.getItem("Derp"));
	}
	
	@Test
	public void testGetMap(){
		form.setItem("Derp", 5);
		form.setItem("Test", "Yes");
		
		Map<String, Object> map = form.getMap();
		assertFalse(map.isEmpty());
		assertEquals(2, map.keySet().size());
		assertTrue(map.containsKey("Derp"));
		assertEquals(5, map.get("Derp"));
		assertTrue(map.containsKey("Test"));
		assertEquals("Yes", map.get("Test"));
	}
	
	@Test
	public void testLabelsOnTop(){
		assertTrue(form.labelsOnTop());
	}
	
	@Test
	public void testAddField(){
		form.addField(StringFormField.builder()
				.key("Test").defaultValue("Yes")
				.build());
		assertTrue(form.hasItem("Test"));
		assertEquals("Yes", form.getItem("Test"));
	}
	
	@Test
	public void testSetDefaultFields(){
		assertTrue(weSetThoseFields);
	}
	
	@Test
	public void testCreateComponentsAndGetComponentByKey(){
		assertTrue(form.hasItem("Derp"));
		assertEquals("No", form.getItem("Derp"));
		JComponent comp = form.getComponentByKey("Derp");
		assertTrue(comp instanceof JTextField);
		assertEquals("No", ((JTextField) comp).getText());
	}
	
	@Test
	public void testSaveValues(){
		assertTrue(form.hasItem("Derp"));
		assertEquals("No", form.getItem("Derp"));
		((JTextField) form.getComponentByKey("Derp")).setText("Yeppers");
		form.saveValues();
		assertEquals("Yeppers", form.getItem("Derp"));
	}
}
