package com.github.tadukoo.util.view.components;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.pojo.AbstractOrderedMappedPojo;
import com.github.tadukoo.util.pojo.OrderedMappedPojo;
import com.github.tadukoo.util.pojo.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TadukooTableTest{
	private TadukooTable table = TadukooTable.builder().build();
	private final List<String> keyOrder = ListUtil.createList("Test", "Derp");
	private final List<String> keyOrder2 = ListUtil.createList("Test", "Derp", "Plop");
	private final Table<OrderedMappedPojo> data = new Table<>();
	private final int testValue = 5;
	private final String derpValue = "Yep";
	private final float plopValue = 2.5f;
	
	@BeforeEach
	public void setup(){
		data.addRow(new AbstractOrderedMappedPojo(){
			@Override
			public List<String> getKeyOrder(){
				return keyOrder2;
			}
		});
		data.getRow(0).setItem("Test", testValue);
		data.getRow(0).setItem("Derp", derpValue);
		data.getRow(0).setItem("Plop", plopValue);
		data.addRow(new AbstractOrderedMappedPojo(){
			@Override
			public List<String> getKeyOrder(){
				return keyOrder2;
			}
		});
		data.getRow(1).setItem("Derp", derpValue);
	}
	
	@Test
	public void testDefaults(){
		assertNull(table.getKeyOrder());
		assertEquals(0, table.getTableModel().getColumnCount());
		assertEquals(0, table.getTable().getRowCount());
	}
	
	@Test
	public void testSettings(){
		table = TadukooTable.builder().keyOrder(keyOrder).data(data).build();
		assertEquals(keyOrder, table.getKeyOrder());
		assertEquals(data.getNumRows(), table.getTable().getRowCount());
	}
	
	@Test
	public void testSetTableDataNulls(){
		table.setTableData(null);
		assertEquals(0, table.getTable().getColumnCount());
		assertEquals(0, table.getTable().getRowCount());
	}
	
	@Test
	public void testSetTableDataNullData(){
		table = TadukooTable.builder().keyOrder(keyOrder).build();
		table.setTableData(null);
		assertEquals(keyOrder.size(), table.getTable().getColumnCount());
		assertEquals(0, table.getTable().getRowCount());
	}
	
	@Test
	public void testSetTableDataNullKeys(){
		table = TadukooTable.builder().data(data).build();
		table.setTableData(data);
		assertEquals(keyOrder2.size(), table.getTable().getColumnCount());
		assertEquals(data.getNumRows(), table.getTable().getRowCount());
	}
	
	@Test
	public void testSetTableDataNeitherNull(){
		table = TadukooTable.builder().keyOrder(keyOrder).data(data).build();
		table.setTableData(data);
		assertEquals(keyOrder.size(), table.getTable().getColumnCount());
		assertEquals(data.getNumRows(), table.getTable().getRowCount());
	}
	
	@Test
	public void testSetTableDataResets(){
		table = TadukooTable.builder().keyOrder(keyOrder).data(data).build();
		table.setTableData(data);
		assertEquals(keyOrder.size(), table.getTable().getColumnCount());
		assertEquals(data.getNumRows(), table.getTable().getRowCount());
		
		table.setTableData(data);
		assertEquals(keyOrder.size(), table.getTable().getColumnCount());
		assertEquals(data.getNumRows(), table.getTable().getRowCount());
	}
	
	@Test
	public void testUpdatePojosNull(){
		table = TadukooTable.builder().data(data).build();
		Table<OrderedMappedPojo> result = table.updatePojos(null);
		assertEquals(2, result.getNumRows());
		OrderedMappedPojo pojo1 = result.getRow(0);
		assertEquals(keyOrder2, pojo1.getKeyOrder());
		assertEquals(derpValue, pojo1.getItem("Derp"));
		assertEquals(testValue, pojo1.getItem("Test"));
		assertEquals(plopValue, pojo1.getItem("Plop"));
		OrderedMappedPojo pojo2 = result.getRow(1);
		assertEquals(keyOrder2, pojo2.getKeyOrder());
		assertEquals(derpValue, pojo2.getItem("Derp"));
		assertNull(pojo2.getItem("Test"));
		assertNull(pojo2.getItem("Plop"));
	}
	
	@Test
	public void testUpdatePojosNoChange(){
		table = TadukooTable.builder().data(data).build();
		assertEquals(data, table.updatePojos(data));
	}
	
	@Test
	public void testAddEmptyRow(){
		table.addEmptyRow();
		assertEquals(1, table.getTable().getRowCount());
	}
	
	@Test
	public void testAddRow(){
		table = TadukooTable.builder().data(data).build();
		OrderedMappedPojo newPojo = new AbstractOrderedMappedPojo(){
			@Override
			public List<String> getKeyOrder(){
				return keyOrder2;
			}
		};
		newPojo.setItem("Derp", testValue);
		newPojo.setItem("Test", derpValue);
		table.addRow(newPojo);
		assertEquals(3, table.getTable().getRowCount());
		assertEquals(derpValue, table.getTable().getValueAt(2, 0));
		assertEquals(testValue, table.getTable().getValueAt(2, 1));
		assertNull(table.getTable().getValueAt(2, 2));
	}
}
