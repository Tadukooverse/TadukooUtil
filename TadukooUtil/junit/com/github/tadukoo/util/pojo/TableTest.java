package com.github.tadukoo.util.pojo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableTest{
	
	private static class TestMappedPojo extends AbstractMappedPojo{
		public TestMappedPojo(){ }
	}
	
	private final Table<TestMappedPojo> table = new Table<>();
	
	@Test
	public void testConstructor(){
		assertTrue(table.getAllRows().isEmpty());
	}
	
	@Test
	public void testGetAllRowsEmpty(){
		assertTrue(table.getAllRows().isEmpty());
	}
	
	@Test
	public void testGetAllRows(){
		TestMappedPojo pojo1 = new TestMappedPojo();
		TestMappedPojo pojo2 = new TestMappedPojo();
		table.addRow(pojo1);
		table.addRow(pojo2);
		
		List<TestMappedPojo> rows = table.getAllRows();
		assertEquals(2, rows.size());
		assertEquals(pojo1, rows.get(0));
		assertEquals(pojo2, rows.get(1));
	}
	
	@Test
	public void testGetRow(){
		TestMappedPojo pojo1 = new TestMappedPojo();
		TestMappedPojo pojo2 = new TestMappedPojo();
		table.addRow(pojo1);
		table.addRow(pojo2);
		
		assertEquals(pojo1, table.getRow(0));
		assertEquals(pojo2, table.getRow(1));
	}
	
	@Test
	public void testGetNumRowsEmpty(){
		assertEquals(0, table.getNumRows());
	}
	
	@Test
	public void testGetNumRows(){
		table.addRow(new TestMappedPojo());
		table.addRow(new TestMappedPojo());
		
		assertEquals(2, table.getNumRows());
	}
	
	@Test
	public void testAddRow(){
		TestMappedPojo pojo1 = new TestMappedPojo();
		TestMappedPojo pojo2 = new TestMappedPojo();
		
		table.addRow(pojo1);
		assertEquals(1, table.getNumRows());
		assertEquals(pojo1, table.getRow(0));
		
		table.addRow(pojo2);
		assertEquals(2, table.getNumRows());
		assertEquals(pojo2, table.getRow(1));
	}
	
	@Test
	public void testAddRowIndex(){
		TestMappedPojo pojo1 = new TestMappedPojo();
		TestMappedPojo pojo2 = new TestMappedPojo();
		
		table.addRow(pojo1);
		assertEquals(1, table.getNumRows());
		assertEquals(pojo1, table.getRow(0));
		
		table.addRow(0, pojo2);
		assertEquals(2, table.getNumRows());
		assertEquals(pojo2, table.getRow(0));
		assertEquals(pojo1, table.getRow(1));
	}
	
	@Test
	public void testRemoveRow(){
		TestMappedPojo pojo1 = new TestMappedPojo();
		TestMappedPojo pojo2 = new TestMappedPojo();
		table.addRow(pojo1);
		table.addRow(pojo2);
		assertEquals(2, table.getNumRows());
		assertEquals(pojo1, table.getRow(0));
		assertEquals(pojo2, table.getRow(1));
		
		assertEquals(pojo1, table.removeRow(0));
		assertEquals(1, table.getNumRows());
		assertEquals(pojo2, table.getRow(0));
	}
}
