package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.BooleanUtil;

import javax.swing.*;

/**
 * Boolean Form Field is a {@link FormField} used to show a boolean value.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class BooleanFormField extends FormField<Boolean>{
	
	/**
	 * Builder to be used to create a {@link BooleanFormField}. It has the following parameters:
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
	 *         <td>Defaults to null - which converts to false for this field</td>
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
	 *     <tr>
	 *         <td>editable</td>
	 *         <td>Whether the field can be edited or not</td>
	 *         <td>Defaults to true</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class BooleanFormFieldBuilder extends FormFieldBuilder<Boolean>{
		/** Whether the field can be edited or not */
		private boolean editable = true;
		
		// Not allowed to create a BooleanFormFieldBuilder outside of BooleanFormField
		private BooleanFormFieldBuilder(){
			super();
			labelType = LabelType.NONE;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormFieldBuilder defaultValue(Boolean defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/**
		 * @param editable Whether the field can be edited or not
		 * @return this, to continue building
		 */
		public BooleanFormFieldBuilder editable(boolean editable){
			this.editable = editable;
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public BooleanFormField build(){
			return new BooleanFormField(key, BooleanUtil.isTrue(defaultValue), labelType,
					rowPos, colPos, rowSpan, colSpan,
					editable);
		}
	}
	
	/** Whether this field can be edited or not */
	private final boolean editable;
	
	/**
	 * Creates a new BooleanFormField with the given parameters
	 *
	 * @param key The name of this field (used as a key in Forms)
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 * @param editable Whether this field can be edited or not
	 */
	private BooleanFormField(String key, Boolean defaultValue, LabelType labelType,
	                         int rowPos, int colPos, int rowSpan, int colSpan,
	                         boolean editable){
		super(FieldType.BOOLEAN, key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan);
		this.editable = editable;
	}
	
	/**
	 * @return A new {@link BooleanFormFieldBuilder} to use to make a {@link BooleanFormField}
	 */
	public static BooleanFormFieldBuilder builder(){
		return new BooleanFormFieldBuilder();
	}
	
	/**
	 * @return Whether this field can be edited or not
	 */
	public boolean isEditable(){
		return editable;
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponent(){
		JCheckBox checkbox = new JCheckBox(getKey(), getDefaultValue());
		checkbox.setEnabled(editable);
		return checkbox;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValue(JComponent component){
		if(component instanceof JCheckBox){
			return ((JCheckBox) component).isSelected();
		}else{
			return null;
		}
	}
}
