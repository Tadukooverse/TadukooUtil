package com.github.tadukoo.util.pojo;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderedMappedPojoTest{
	
	private OrderedMappedPojo pojo = new AbstractOrderedMappedPojo(){
		@Override
		public List<String> getKeyOrder(){
			return ListUtil.createList("Derp", "Test");
		}
	};
	
	@Test
	public void testConstructor(){
		assertTrue(pojo.getMap().isEmpty());
	}
	
	@Test
	public void testPojoConstructor(){
		OrderedMappedPojo otherPojo = new AbstractOrderedMappedPojo(){
			@Override
			public List<String> getKeyOrder(){
				return ListUtil.createList("Derp", "Test");
			}
			
			@Override
			public Map<String, Object> getMap(){
				Map<String, Object> aMap = new HashMap<>();
				aMap.put("Test", 50);
				aMap.put("Derp", "Yep");
				return aMap;
			}
		};
		pojo = new AbstractOrderedMappedPojo(otherPojo){
			@Override
			public List<String> getKeyOrder(){
				return ListUtil.createList("Derp", "Test");
			}
		};
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
	public void testGetKeyOrder(){
		List<String> keyOrder = pojo.getKeyOrder();
		assertEquals(2, keyOrder.size());
		assertEquals("Derp", keyOrder.get(0));
		assertEquals("Test", keyOrder.get(1));
	}
}
