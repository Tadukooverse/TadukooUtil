package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.Form;
import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.FormField;
import com.github.tadukoo.util.view.form.field.LabelType;

import javax.swing.*;

/**
 * Abstract {@link FormField} used for {@link Number}s using {@link JSpinner}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 *
 * @param <Type> The type of {@link Number} being stored in the field (used for default, min, max, and step values)
 */
public abstract class NumberFormField<Type extends Number & Comparable<?>> extends FormField<Type>{
	
	/**
	 * Builder to be used to create a {@link NumberFormField}. This is the abstract version to be extended
	 * in subclasses of NumberFormField. It has the following parameters:
	 *
	 * <table>
	 *     <caption>FormField Parameters</caption>
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
	 *         <td>Defaults to 0 in implementations (defaults to null here, will cause errors)</td>
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
	 *         <td>minValue</td>
	 *         <td>The minimum value allowed for the field</td>
	 *         <td>Defaults to null (no minimum)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>maxValue</td>
	 *         <td>The maximum value allowed for the field</td>
	 *         <td>Defaults to null (no maximum)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>stepSize</td>
	 *         <td>The increment value for the spinner for the field</td>
	 *         <td>Defaults to 1 in implementations (no default here)</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2.1
	 *
	 * @param <Type> The type of {@link Number} being stored in the field (used for default value)
	 */
	public static abstract class NumberFormFieldBuilder<Type extends Number & Comparable<?>>
			extends FormFieldBuilder<Type>{
		/** The minimum value allowed for the field */
		protected Type minValue = null;
		/** The maximum value allowed for the field */
		protected Type maxValue = null;
		/** The increment value for the spinner for the field */
		protected Type stepSize;
		
		/**
		 * Constructs a new NumberFormFieldBuilder (to be called in subclasses)
		 */
		protected NumberFormFieldBuilder(){
			super();
		}
		
		/**
		 * @param minValue The minimum value allowed for the field
		 * @return this, to continue building
		 */
		public NumberFormFieldBuilder<Type> minValue(Type minValue){
			this.minValue = minValue;
			return this;
		}
		
		/**
		 * @param maxValue The maximum value allowed for the field
		 * @return this, to continue building
		 */
		public NumberFormFieldBuilder<Type> maxValue(Type maxValue){
			this.maxValue = maxValue;
			return this;
		}
		
		/**
		 * @param stepSize The increment value for the spinner for the field
		 * @return this, to continue building
		 */
		public NumberFormFieldBuilder<Type> stepSize(Type stepSize){
			this.stepSize = stepSize;
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public abstract NumberFormField<Type> build();
	}
	
	/** The minimum value allowed for this field */
	private final Type minValue;
	/** The maximum value allowed for this field */
	private final Type maxValue;
	/** The increment value for the spinner for this field */
	private final Type stepSize;
	
	/**
	 * Creates a new NumberFormField with the given parameters.
	 *
	 * @param type The {@link FieldType} of this field
	 * @param key The name of this field (used as a key in {@link Form Forms})
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 * @param minValue The minimum value allowed for this field
	 * @param maxValue The maximum value allowed for this field
	 * @param stepSize The increment value for the spinner for this field
	 */
	protected NumberFormField(FieldType type, String key, Type defaultValue, LabelType labelType,
	                          int rowPos, int colPos, int rowSpan, int colSpan,
	                          Type minValue, Type maxValue, Type stepSize){
		super(type, key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.stepSize = stepSize;
	}
	
	/**
	 * @return The minimum value allowed for this field
	 */
	public Type getMinValue(){
		return minValue;
	}
	
	/**
	 * @return The maximum value allowed for this field
	 */
	public Type getMaxValue(){
		return maxValue;
	}
	
	/**
	 * @return The increment value for the spinner for this field
	 */
	public Type getStepSize(){
		return stepSize;
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponent(){
		return new JSpinner(new SpinnerNumberModel(getDefaultValue(), minValue, maxValue, stepSize));
	}
	
	/** {@inheritDoc} */
	@Override
	public Type getValue(JComponent component){
		if(component instanceof JSpinner){
			return convertToType((Number) ((JSpinner) component).getValue());
		}
		return null;
	}
	
	/**
	 * Used in {@link #getValue(JComponent)} to convert the {@link Number} from the {@link JSpinner} into the
	 * type used in this particular FormField.
	 *
	 * @param number The {@link Number} we got from the {@link JSpinner}
	 * @return The number in its proper Type
	 */
	protected abstract Type convertToType(Number number);
}
