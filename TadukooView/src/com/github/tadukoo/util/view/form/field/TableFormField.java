package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.pojo.OrderedMappedPojo;
import com.github.tadukoo.util.pojo.Table;
import com.github.tadukoo.util.view.components.TadukooTable;

import javax.swing.*;

/**
 * Table Form Field is a {@link FormField} used to show a {@link Table} of {@link OrderedMappedPojo}s in a form
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TableFormField extends FormField<Table<OrderedMappedPojo>>{
	
	/**
	 * Builder to be used to create a {@link TableFormField}. It has the following parameters:
	 *
	 * <table>
	 *     <tr>
	 *         <th>Name</th>
	 *         <th>Description</th>
	 *         <th>Default Value or Required</th>
	 *     </tr>
	 *     <tr>
	 *         <td>key</td>
	 *         <td>The name of the field (used as a key in {@link com.github.tadukoo.util.view.form.Form Forms})</td>
	 *         <td>Required</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultValue</td>
	 *         <td>The starting value of the field</td>
	 *         <td>Defaults to null</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelType</td>
	 *         <td>The {@link LabelType} to use for the field</td>
	 *         <td>Defaults to {@link LabelType#NONE}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>rowPos</td>
	 *         <td>The row position of the field</td>
	 *         <td>Required</td>
	 *     </tr>
	 *     <tr>
	 *         <td>colPos</td>
	 *         <td>The column position of the field</td>
	 *         <td>Required</td>
	 *     </tr>
	 *     <tr>
	 *         <td>rowSpan</td>
	 *         <td>The row span of the field</td>
	 *         <td>Defaults to 1</td>
	 *     </tr>
	 *     <tr>
	 *         <td>colSpan</td>
	 *         <td>The column span of the field</td>
	 *         <td>Defaults to 1</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class TableFormFieldBuilder extends FormFieldBuilder<Table<OrderedMappedPojo>>{
		
		// Not allowed to create a TableFormFieldBuilder outside of TableFormField
		private TableFormFieldBuilder(){
			super();
			labelType = LabelType.NONE;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormFieldBuilder defaultValue(Table<OrderedMappedPojo> defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public TableFormField build(){
			return new TableFormField(key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan);
		}
	}
	
	/**
	 * Creates a new TableFormField with the given parameters
	 *
	 * @param key The name of this field (used as a key in Forms)
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 */
	private TableFormField(String key, Table<OrderedMappedPojo> defaultValue, LabelType labelType,
	                       int rowPos, int colPos, int rowSpan, int colSpan){
		super(FieldType.TABLE, key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan);
	}
	
	/**
	 * @return A new {@link TableFormFieldBuilder} to use to make a {@link TableFormField}
	 */
	public static TableFormFieldBuilder builder(){
		return new TableFormFieldBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponent(){
		return TadukooTable.builder()
				.data(getDefaultValue())
				.build();
	}
	
	/** {@inheritDoc} */
	@Override
	public Table<OrderedMappedPojo> getValue(JComponent component){
		if(component instanceof TadukooTable){
			Table<OrderedMappedPojo> data = getDefaultValue();
			return ((TadukooTable) component).updatePojos(data);
		}else{
			return null;
		}
	}
}
