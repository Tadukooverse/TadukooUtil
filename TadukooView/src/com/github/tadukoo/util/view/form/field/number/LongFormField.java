package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.Form;
import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.FormField;
import com.github.tadukoo.util.view.form.field.LabelType;

/**
 * A Long Form Field is a {@link FormField} used to store longs.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public class LongFormField extends NumberFormField<Long>{
	
	/**
	 * Builder to be used to create an {@link LongFormField}. It has the following parameters:
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
	 *         <td>The name of the field (used as a key in {@link Form Forms})</td>
	 *         <td>Required</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultValue</td>
	 *         <td>The starting value of the field</td>
	 *         <td>Defaults to 0</td>
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
	 *         <td>Defaults to 1.0</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2.1
	 */
	public static class LongFormFieldBuilder extends NumberFormFieldBuilder<Long>{
		
		// Can't create outside of LongFormField
		private LongFormFieldBuilder(){
			super();
			defaultValue = 0L;
			stepSize = 1L;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder defaultValue(Long defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder minValue(Long minValue){
			super.minValue(minValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder maxValue(Long maxValue){
			super.maxValue(maxValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormFieldBuilder stepSize(Long stepSize){
			super.stepSize(stepSize);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LongFormField build(){
			return new LongFormField(key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan,
					minValue, maxValue, stepSize);
		}
	}
	
	/**
	 * Creates a new LongFormField with the given parameters.
	 *
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
	private LongFormField(String key, Long defaultValue, LabelType labelType,
	                       int rowPos, int colPos, int rowSpan, int colSpan,
	                       Long minValue, Long maxValue, Long stepSize){
		super(FieldType.LONG, key, defaultValue, labelType,
				rowPos, colPos, rowSpan, colSpan,
				minValue, maxValue, stepSize);
	}
	
	/**
	 * @return A new {@link LongFormFieldBuilder} to use to make a {@link LongFormField}
	 */
	public static LongFormFieldBuilder builder(){
		return new LongFormFieldBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	protected Long convertToType(Number number){
		return number.longValue();
	}
}
