package com.github.tadukoo.util.view.form.field;

import com.github.tadukoo.util.view.components.DateForm;
import com.github.tadukoo.util.view.form.Form;

import javax.swing.*;
import java.util.Date;

/**
 * An Int Form Field is a {@link FormField} used to store Dates.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public class DateFormField extends FormField<Date>{
	
	/**
	 * Builder to be used to create an {@link DateFormField}. It has the following parameters:
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
	 *         <td>Defaults to null</td>
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
	 *         <td>minYear</td>
	 *         <td>The minimum year to allow on the field</td>
	 *         <td>Defaults to 1900</td>
	 *     </tr>
	 *     <tr>
	 *         <td>maxYear</td>
	 *         <td>The maximum year to allow on the field</td>
	 *         <td>Defaults to 2100</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2.1
	 */
	public static class DateFormFieldBuilder extends FormFieldBuilder<Date>{
		/** The minimum year to allow on the field */
		private int minYear = 1900;
		/** The maximum year to allow on the field */
		private int maxYear = 2100;
		
		// Not allowed to create outside of DateFormField
		private DateFormFieldBuilder(){
			super();
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormFieldBuilder key(String key){
			super.key(key);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormFieldBuilder defaultValue(Date defaultValue){
			super.defaultValue(defaultValue);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormFieldBuilder labelType(LabelType labelType){
			super.labelType(labelType);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormFieldBuilder rowPos(int rowPos){
			super.rowPos(rowPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormFieldBuilder colPos(int colPos){
			super.colPos(colPos);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormFieldBuilder rowSpan(int rowSpan){
			super.rowSpan(rowSpan);
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormFieldBuilder colSpan(int colSpan){
			super.colSpan(colSpan);
			return this;
		}
		
		/**
		 * @param minYear The minimum year to allow on the field
		 */
		public DateFormFieldBuilder minYear(int minYear){
			this.minYear = minYear;
			return this;
		}
		
		/**
		 * @param maxYear The maximum year to allow on the field
		 */
		public DateFormFieldBuilder maxYear(int maxYear){
			this.maxYear = maxYear;
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public DateFormField build(){
			return new DateFormField(key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan,
					minYear, maxYear);
		}
	}
	
	/** The minimum year to allow on this field */
	private final int minYear;
	/** The maximum year to allow on this field */
	private final int maxYear;
	
	/**
	 * Creates a new DateFormField with the given parameters.
	 *
	 * @param key The name of this field (used as a key in {@link Form Forms})
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 * @param minYear The minimum year to allow on this field
	 * @param maxYear The maximum year to allow on this field
	 */
	private DateFormField(String key, Date defaultValue, LabelType labelType,
	                        int rowPos, int colPos, int rowSpan, int colSpan,
	                        int minYear, int maxYear){
		super(FieldType.DATE, key, defaultValue, labelType,
				rowPos, colPos, rowSpan, colSpan);
		this.minYear = minYear;
		this.maxYear = maxYear;
	}
	
	/**
	 * @return A new {@link DateFormFieldBuilder} to use to make a {@link DateFormField}
	 */
	public static DateFormFieldBuilder builder(){
		return new DateFormFieldBuilder();
	}
	
	/**
	 * @return The minimum year to allow on this field
	 */
	public int getMinYear(){
		return minYear;
	}
	
	/**
	 * @return The maximum year to allow on this field
	 */
	public int getMaxYear(){
		return maxYear;
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponent(){
		return new DateForm(getDefaultValue(), minYear, maxYear);
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getValue(JComponent component){
		if(component instanceof DateForm){
			return ((DateForm) component).getDate();
		}
		return null;
	}
}
