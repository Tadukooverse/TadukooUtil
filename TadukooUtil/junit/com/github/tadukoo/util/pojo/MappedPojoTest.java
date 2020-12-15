package com.github.tadukoo.util.pojo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MappedPojoTest{
	
	private MappedPojo pojo = new AbstractMappedPojo(){ };
	
	private static class TestClass extends AbstractMappedPojo{
		public TestClass(){ }
		
		public TestClass(MappedPojo pojo){
			super(pojo);
		}
		
		public String getDerp(){
			return (String) getItem("Derp");
		}
		
		public int getPlop(){
			return (int) getItem("Plop");
		}
	}
	
	@Test
	public void testConstructor(){
		assertTrue(pojo.getMap().isEmpty());
	}
	
	@Test
	public void testPojoConstructor(){
		MappedPojo otherPojo = new AbstractMappedPojo(){
			@Override
			public Map<String, Object> getMap(){
				Map<String, Object> aMap = new HashMap<>();
				aMap.put("Test", 50);
				aMap.put("Derp", "Yep");
				return aMap;
			}
		};
		pojo = new AbstractMappedPojo(otherPojo){ };
		Map<String, Object> theMap = pojo.getMap();
		assertFalse(theMap.isEmpty());
		assertTrue(theMap.containsKey("Test"));
		assertEquals(50, theMap.get("Test"));
		assertTrue(theMap.containsKey("Derp"));
		assertEquals("Yep", theMap.get("Derp"));
	}
	
	@Test
	public void testHasKeyFalse(){
		assertFalse(pojo.hasKey("Derp"));
	}
	
	@Test
	public void testHasKeyTrue(){
		pojo.setItem("Derp", 5);
		assertTrue(pojo.hasKey("Derp"));
	}
	
	@Test
	public void testGetKeysEmpty(){
		assertTrue(pojo.getKeys().isEmpty());
	}
	
	@Test
	public void testGetKeysPopulated(){
		pojo.setItem("Derp", 5);
		pojo.setItem("Test", "Yes");
		Set<String> keys = pojo.getKeys();
		assertFalse(keys.isEmpty());
		assertTrue(keys.contains("Derp"));
		assertTrue(keys.contains("Test"));
	}
	
	@Test
	public void testHasItemNotSet(){
		assertFalse(pojo.hasItem("Test"));
	}
	
	@Test
	public void testHasItemSetToNull(){
		pojo.setItem("Test", null);
		assertFalse(pojo.hasItem("Test"));
	}
	
	@Test
	public void testHasItemTrue(){
		pojo.setItem("Derp", 5);
		assertTrue(pojo.hasItem("Derp"));
	}
	
	@Test
	public void testGetItemNotSet(){
		assertNull(pojo.getItem("Derp"));
	}
	
	@Test
	public void testGetItem(){
		pojo.setItem("Derp", 5);
		assertEquals(5, pojo.getItem("Derp"));
	}
	
	@Test
	public void testSetItem(){
		pojo.setItem("Derp", 5);
		assertTrue(pojo.hasKey("Derp"));
		assertTrue(pojo.hasItem("Derp"));
		assertEquals(5, pojo.getItem("Derp"));
	}
	
	@Test
	public void testRemoveItem(){
		pojo.setItem("Derp", 5);
		assertTrue(pojo.hasKey("Derp"));
		assertTrue(pojo.hasItem("Derp"));
		assertEquals(5, pojo.getItem("Derp"));
		
		pojo.removeItem("Derp");
		assertFalse(pojo.hasKey("Derp"));
		assertFalse(pojo.hasItem("Derp"));
		assertNull(pojo.getItem("Derp"));
	}
	
	@Test
	public void testGetMapEmpty(){
		Map<String, Object> map = pojo.getMap();
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void testGetMap(){
		pojo.setItem("Derp", 5);
		pojo.setItem("Test", "Yes");
		
		Map<String, Object> map = pojo.getMap();
		assertFalse(map.isEmpty());
		assertEquals(2, map.keySet().size());
		assertTrue(map.containsKey("Derp"));
		assertEquals(5, map.get("Derp"));
		assertTrue(map.containsKey("Test"));
		assertEquals("Yes", map.get("Test"));
	}
	
	@Test
	public void testGetItemAsPojo()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		MappedPojo clazz2 = new AbstractMappedPojo(){ };
		clazz2.setItem("Derp", "Yes");
		clazz2.setItem("Plop", 42);
		
		pojo.setItem("Test", clazz2);
		
		TestClass item = pojo.getPojoItem("Test", TestClass.class);
		assertEquals("Yes", item.getDerp());
		assertEquals(42, item.getPlop());
	}
	
	@Test
	public void testGetItemAsPojoNull()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		pojo.setItem("Test", null);
		
		TestClass item = pojo.getPojoItem("Test", TestClass.class);
		assertNull(item);
	}
	
	@Test
	public void testGetItemAsPojoGetsCast()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		// Test that it works the first time
		MappedPojo clazz2 = new AbstractMappedPojo(){ };
		clazz2.setItem("Derp", "Yes");
		clazz2.setItem("Plop", 42);
		
		pojo.setItem("Test", clazz2);
		
		TestClass item = pojo.getPojoItem("Test", TestClass.class);
		assertEquals("Yes", item.getDerp());
		assertEquals(42, item.getPlop());
		
		// Now it should already be cast
		item = (TestClass) pojo.getItem("Test");
		assertEquals("Yes", item.getDerp());
		assertEquals(42, item.getPlop());
	}
	
	@Test
	public void testGetItemAsPojoAlreadyCast()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		TestClass clazz2 = new TestClass();
		clazz2.setItem("Derp", "Yes");
		clazz2.setItem("Plop", 42);
		
		pojo.setItem("Test", clazz2);
		
		TestClass item = pojo.getPojoItem("Test", TestClass.class);
		assertEquals("Yes", item.getDerp());
		assertEquals(42, item.getPlop());
	}
	
	@Test
	public void testGetTableItemNull()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		pojo.setItem("Table", null);
		
		Table<TestClass> table = pojo.getTableItem("Table", TestClass.class);
		assertNull(table);
	}
	
	@Test
	public void testGetTableItem()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		Table<MappedPojo> brokenTable = new Table<>();
		TestClass testClass1 = new TestClass();
		testClass1.setItem("Derp", "nope");
		testClass1.setItem("Plop", 5);
		brokenTable.addRow(testClass1);
		MappedPojo otherPojo = new AbstractMappedPojo(){ };
		otherPojo.setItem("Derp", "yep");
		otherPojo.setItem("Plop", 42);
		brokenTable.addRow(otherPojo);
		pojo.setItem("Table", brokenTable);
		
		Table<TestClass> table = pojo.getTableItem("Table", TestClass.class);
		assertEquals(2, table.getNumRows());
		List<TestClass> rows = table.getAllRows();
		assertEquals(testClass1, rows.get(0));
		TestClass testClass2 = rows.get(1);
		assertEquals("yep", testClass2.getDerp());
		assertEquals(42, testClass2.getPlop());
	}
}
