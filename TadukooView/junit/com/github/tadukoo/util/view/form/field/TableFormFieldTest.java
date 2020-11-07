package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.pojo.AbstractOrderedMappedPojo;
import com.github.tadukoo.util.pojo.OrderedMappedPojo;
import com.github.tadukoo.util.pojo.Table;
import com.github.tadukoo.util.view.components.TadukooTable;
import com.github.tadukoo.util.view.form.AbstractForm;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TableFormFieldTest{
	private TableFormField field = TableFormField.builder().build();
	private final Table<OrderedMappedPojo> table = new Table<>();
	
	@Test
	public void testDefaults(){
		assertEquals(FieldType.TABLE, field.getType());
		assertNull(field.getDefaultValue());
		assertEquals(LabelType.NONE, field.getLabelType());
		assertEquals(1, field.getRowSpan());
		assertEquals(1, field.getColSpan());
	}
	
	@Test
	public void testSettings(){
		field = TableFormField.builder().key("Test").defaultValue(table).labelType(LabelType.LABEL)
				.rowPos(2).colPos(5).rowSpan(3).colSpan(7).build();
		assertEquals("Test", field.getKey());
		assertEquals(table, field.getDefaultValue());
		assertEquals(LabelType.LABEL, field.getLabelType());
		assertEquals(2, field.getRowPos());
		assertEquals(5, field.getColPos());
		assertEquals(3, field.getRowSpan());
		assertEquals(7, field.getColSpan());
	}
	
	@Test
	public void testGetComponent(){
		field = TableFormField.builder().defaultValue(table).build();
		JComponent component = field.getComponent();
		assertTrue(component instanceof TadukooTable);
		assertEquals(0, ((TadukooTable) component).getTable().getRowCount());
	}
	
	@Test
	public void testGetValueBadComponent(){
		assertNull(field.getValue(new JLabel("Test")));
	}
	
	@Test
	public void testGetValue(){
		TadukooTable table2 = TadukooTable.builder().keyOrder(ListUtil.createList("Derp", "Test")).build();
		OrderedMappedPojo pojo = new AbstractOrderedMappedPojo(){
			@Override
			public List<String> getKeyOrder(){
				return ListUtil.createList("Derp", "Test");
			}
		};
		pojo.setItem("Derp", 5);
		pojo.setItem("Test", "Yep");
		table2.addRow(pojo);
		
		Table<OrderedMappedPojo> result = field.getValue(table2);
		assertEquals(1, result.getNumRows());
		OrderedMappedPojo resultPojo = result.getRow(0);
		assertEquals(5, resultPojo.getItem("Derp"));
		assertEquals("Yep", resultPojo.getItem("Test"));
	}
}
