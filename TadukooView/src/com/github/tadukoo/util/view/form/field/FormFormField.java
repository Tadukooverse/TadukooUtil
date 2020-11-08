package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.view.form.AbstractForm;

import javax.swing.*;

/**
 * Form Form Field is a {@link FormField} used to have a {@link AbstractForm Form} inside of a form.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class FormFormField extends FormField<AbstractForm>{
	
	/**
	 * Builder to be used to create a {@link FormFormField}. It has the following parameters:
	 *
	 * <table>
	 *     <caption>FormFormField Parameters</caption>
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
	 *         <td>Defaults to {@link LabelType#TITLED_BORDER}</td>
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
	public static class FormFormFieldBuilder extends FormFieldBuilder<AbstractForm>{
		
		// Can't create FormFormFieldBuilder outside of FormFormField
		private FormFormFieldBuilder(){
			super();
			labelType = LabelType.TITLED_BORDER;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormFieldBuilder defaultValue(AbstractForm defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public FormFormField build(){
			return new FormFormField(key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan);
		}
	}
	
	/**
	 * Creates a new FormFormField with the given parameters
	 *
	 * @param key The name of this field (used as a key in Forms)
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 */
	private FormFormField(String key, AbstractForm defaultValue, LabelType labelType,
	                      int rowPos, int colPos, int rowSpan, int colSpan){
		super(FieldType.FORM, key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan);
	}
	
	/**
	 * @return A new {@link FormFormFieldBuilder} to use to make a {@link FormFormField}
	 */
	public static FormFormFieldBuilder builder(){
		return new FormFormFieldBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponent(){
		AbstractForm form = getDefaultValue();
		
		return form != null?form:new JLabel("No value");
	}
	
	/** {@inheritDoc} */
	@Override
	public AbstractForm getValue(JComponent component){
		if(component instanceof AbstractForm){
			AbstractForm form = (AbstractForm) component;
			form.saveValues();
			return form;
		}else{
			return null;
		}
	}
}
