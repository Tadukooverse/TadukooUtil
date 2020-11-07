package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.view.components.TadukooButton;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Button Form Field is a {@link FormField} that is just a button
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class ButtonFormField extends FormField<String>{
	
	/**
	 * Builder to be used to create a {@link ButtonFormField}. It has the following parameters:
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
	 *     <tr>
	 *         <td>actionListener</td>
	 *         <td>The action to perform on click of the Button</td>
	 *         <td>Defaults to null (no action)</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class ButtonFormFieldBuilder extends FormFieldBuilder<String>{
		/** The action to perform on click of the Button */
		private ActionListener actionListener = null;
		
		// Not allowed to create ButtonFormFieldBuilder outside of ButtonFormField
		private ButtonFormFieldBuilder(){
			super();
			labelType = LabelType.NONE;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormFieldBuilder defaultValue(String defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/**
		 * @param actionListener The action to perform on click of the Button
		 * @return this, to continue building
		 */
		public ButtonFormFieldBuilder actionListener(ActionListener actionListener){
			this.actionListener = actionListener;
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public ButtonFormField build(){
			return new ButtonFormField(key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan,
					actionListener);
		}
	}
	
	/** The action to perform on click of the Button */
	private final ActionListener actionListener;
	
	/**
	 * Creates a new ButtonFormField with the given parameters
	 *
	 * @param key The name of this field (used as a key in Forms)
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 * @param actionListener The action to perform on click of the Button
	 */
	private ButtonFormField(String key, String defaultValue, LabelType labelType,
	                        int rowPos, int colPos, int rowSpan, int colSpan,
	                        ActionListener actionListener){
		super(FieldType.BUTTON, key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan);
		this.actionListener = actionListener;
	}
	
	/**
	 * @return A new {@link ButtonFormFieldBuilder} to use to make a {@link ButtonFormField}
	 */
	public static ButtonFormFieldBuilder builder(){
		return new ButtonFormFieldBuilder();
	}
	
	/**
	 * @return The action to perform on click of the Button
	 */
	public ActionListener getActionListener(){
		return actionListener;
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponent(){
		return TadukooButton.builder()
				.text(getKey())
				.actionListener(actionListener)
				.build();
	}
	
	/** {@inheritDoc} */
	@Override
	public String getValue(JComponent component){
		return null;
	}
}
