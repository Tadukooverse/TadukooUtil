package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.view.form.Form;

import javax.swing.*;

/**
 * Drop Down Form Field is a {@link FormField} used for drop-down menus.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class DropDownFormField extends FormField<String>{
	
	/**
	 * Builder to be used to create a {@link DropDownFormField}. It has the following parameters:
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
	 *         <td>Defaults to {@link LabelType#LABEL}</td>
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
	 *     <tr>
	 *         <td>options</td>
	 *         <td>The options to include in the drop-down list</td>
	 *         <td>Defaults to an empty array</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class DropDownFormFieldBuilder extends FormFieldBuilder<String>{
		/** Whether the field can be edited or not */
		private boolean editable = true;
		/** The options to include in the drop-down list */
		private String[] options = new String[]{};
		
		// Not allowed to create a DropDownFormFieldBuilder outside of DropDownFormField
		private DropDownFormFieldBuilder(){
			super();
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormFieldBuilder defaultValue(String defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/**
		 * @param editable Whether the field can be edited or not
		 * @return this, to continue building
		 */
		public DropDownFormFieldBuilder editable(boolean editable){
			this.editable = editable;
			return this;
		}
		
		/**
		 * @param options The options to include in the drop-down list
		 * @return this, to continue building
		 */
		public DropDownFormFieldBuilder options(String[] options){
			this.options = options;
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DropDownFormField build(){
			return new DropDownFormField(key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan,
					editable, options);
		}
	}
	
	/** Whether this field can be edited or not */
	private final boolean editable;
	/** The options to include in the drop-down list */
	private final String[] options;
	
	/**
	 * Creates a new DropDownFormField with the given parameters.
	 *
	 * @param key The name of this field (used as a key in {@link Form Forms})
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 * @param editable Whether this field can be edited or not
	 * @param options The options to include in the drop-down list
	 */
	private DropDownFormField(String key, String defaultValue, LabelType labelType,
	                          int rowPos, int colPos, int rowSpan, int colSpan,
	                          boolean editable, String[] options){
		super(FieldType.DROP_DOWN, key, defaultValue, labelType,
				rowPos, colPos, rowSpan, colSpan);
		this.editable = editable;
		this.options = options;
	}
	
	/**
	 * @return A new {@link DropDownFormFieldBuilder} to use to make a {@link DropDownFormField}
	 */
	public static DropDownFormFieldBuilder builder(){
		return new DropDownFormFieldBuilder();
	}
	
	/**
	 * @return Whether this field can be edited or not
	 */
	public boolean isEditable(){
		return editable;
	}
	
	/**
	 * @return The options to include in the drop-down list
	 */
	public String[] getOptions(){
		return options;
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponent(){
		JComboBox<String> comboBox = new JComboBox<>(options);
		comboBox.setSelectedItem(getDefaultValue());
		comboBox.setEditable(editable);
		return comboBox;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getValue(JComponent component){
		if(component instanceof JComboBox){
			return (String) ((JComboBox<?>) component).getSelectedItem();
		}else{
			return null;
		}
	}
}
