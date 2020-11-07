package com.github.tadukoo.util.view.components;

import com.github.tadukoo.util.pojo.AbstractOrderedMappedPojo;
import com.github.tadukoo.util.pojo.OrderedMappedPojo;
import com.github.tadukoo.util.pojo.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Tadukoo Table is an extension of {@link JScrollPane} that contains a {@link JTable}. It provides methods for
 * using {@link Table}s of {@link OrderedMappedPojo}s to populate the {@link JTable} and to make it easier to update
 * the table.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TadukooTable extends JScrollPane{
	
	/**
	 * Builder to be used to create a {@link TadukooTable}. It has the following parameters:
	 *
	 * <table>
	 *     <tr>
	 *         <th>Name</th>
	 *         <th>Description</th>
	 *         <th>Default Value or Required</th>
	 *     </tr>
	 *     <tr>
	 *         <td>keyOrder</td>
	 *         <td>The order of the keys in the table - can be null to use the pojos in the data</td>
	 *         <td>Defaults to null (to use the pojos in the data)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>data</td>
	 *         <td>The data to be put in the table</td>
	 *         <td>Defaults to null</td>
	 *     </tr>
	 * </table>
	 */
	public static class TadukooTableBuilder{
		/** The order of the keys in the table - can be null to use the pojos in the data */
		private List<String> keyOrder = null;
		/** The data to be put in the table */
		private Table<OrderedMappedPojo> data = null;
		
		// Can't create outside of Tadukoo Table
		private TadukooTableBuilder(){ }
		
		/**
		 * @param keyOrder The order of the keys in the table - can be null to use the pojos in the data
		 * @return this, to continue building
		 */
		public TadukooTableBuilder keyOrder(List<String> keyOrder){
			this.keyOrder = keyOrder;
			return this;
		}
		
		/**
		 * @param data The data to be put in the table
		 * @return this, to continue building
		 */
		public TadukooTableBuilder data(Table<OrderedMappedPojo> data){
			this.data = data;
			return this;
		}
		
		/**
		 * Builds the {@link TadukooTable}
		 *
		 * @return A newly created {@link TadukooTable}
		 */
		public TadukooTable build(){
			return new TadukooTable(keyOrder, data);
		}
	}
	
	/** The order of the keys in the table - can be null to use the pojos in the data */
	private final List<String> keyOrder;
	
	/**
	 * Creates a new Tadukoo Table using the given parameters
	 *
	 * @param data The data to be put in the table
	 */
	private TadukooTable(List<String> keyOrder, Table<OrderedMappedPojo> data){
		super(new JTable(new DefaultTableModel()));
		this.keyOrder = keyOrder;
		setTableData(data);
	}
	
	/**
	 * @return A new {@link TadukooTableBuilder} to use to make a {@link TadukooTable}
	 */
	public static TadukooTableBuilder builder(){
		return new TadukooTableBuilder();
	}
	
	/**
	 * @return The order of the keys in the table - can be null to use the pojos in the data
	 */
	public List<String> getKeyOrder(){
		return keyOrder;
	}
	
	/**
	 * @return The {@link JTable} behind this Tadukoo Table
	 */
	public JTable getTable(){
		return (JTable) getViewport().getView();
	}
	
	/**
	 * @return The {@link DefaultTableModel} behind this Tadukoo Table
	 */
	public DefaultTableModel getTableModel(){
		return (DefaultTableModel) getTable().getModel();
	}
	
	/**
	 * Sets the columns and rows of the table based on the passed in data. If the key order was set on the
	 * Tadukoo Table, that key order will be used, otherwise the key order of the first pojo in the data will
	 * be used.
	 *
	 * @param data The pojos to use to populate the table
	 */
	public void setTableData(Table<OrderedMappedPojo> data){
		// Grab that table model
		DefaultTableModel model = getTableModel();
		
		// Reset the columns based on either the key order we have or grab it off a pojo
		List<String> keys = keyOrder != null?keyOrder:(data != null?data.getRow(0).getKeyOrder():null);
		model.setColumnCount(0);
		if(keys != null){
			for(String key: keys){
				model.addColumn(key);
			}
		}
		
		// Reset the row data of the table based on the passed in pojos
		model.setRowCount(0);
		if(data != null && keys != null){
			List<OrderedMappedPojo> pojos = data.getAllRows();
			for(OrderedMappedPojo pojo: pojos){
				Object[] row = new Object[keys.size()];
				for(int i = 0; i < keys.size(); i++){
					row[i] = pojo.getItem(keys.get(i));
				}
				model.addRow(row);
			}
		}
	}
	
	/**
	 * Update the passed in data based on the current values in the table
	 *
	 * @param data The {@link Table} of pojos to be updated
	 * @return The newly updated pojos {@link Table}
	 */
	public Table<OrderedMappedPojo> updatePojos(Table<OrderedMappedPojo> data){
		// If the data is null, create a new table
		if(data == null){
			data = new Table<>();
		}
		
		// Grab the table model and the size of the data
		int dataSize = data.getNumRows();
		DefaultTableModel model = getTableModel();
		
		// Get the key order from the model
		List<String> keys = new ArrayList<>();
		for(int col = 0; col < model.getColumnCount(); col++){
			keys.add(model.getColumnName(col));
		}
		
		// Iterate over all the table rows
		for(int row = 0; row < model.getRowCount(); row++){
			OrderedMappedPojo pojo;
			if(row < dataSize){
				// Grab the pojo from the data if there's enough rows
				pojo = data.getRow(row);
			}else{
				// Make a new pojo if the data is too small
				pojo = new AbstractOrderedMappedPojo(){
					@Override
					public List<String> getKeyOrder(){
						return keys;
					}
				};
				data.addRow(pojo);
			}
			// Set the values on the pojo from the table
			for(int col = 0; col < keys.size(); col++){
				String key = keys.get(col);
				Object value = model.getValueAt(row, col);
				pojo.setItem(key, value);
			}
		}
		
		// Return the updated data
		return data;
	}
	
	/**
	 * Adds a new row to the end of the table with empty data
	 */
	public void addEmptyRow(){
		DefaultTableModel model = getTableModel();
		Object[] row = new Object[model.getColumnCount()];
		model.addRow(row);
	}
	
	/**
	 * Adds a new row to the end of the table using values from the passed in pojo
	 *
	 * @param row The {@link OrderedMappedPojo} to use to add data to the table
	 */
	public void addRow(OrderedMappedPojo row){
		// Grab the table model
		DefaultTableModel model = getTableModel();
		
		// Create an Object array based on values in the pojo
		Object[] rowData = new Object[model.getColumnCount()];
		for(int i = 0; i < model.getColumnCount(); i++){
			String key = model.getColumnName(i);
			rowData[i] = row.getItem(key);
		}
		
		// Add the data to the table
		model.addRow(rowData);
	}
}
