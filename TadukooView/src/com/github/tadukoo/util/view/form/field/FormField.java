package com.github.tadukoo.util.view.form.field;

import javax.swing.*;

/**
 * Form Fields are fields used on {@link com.github.tadukoo.util.view.form.Form Forms}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 *
 * @param <Type> The type of value being stored in the field (used for default value)
 */
public abstract class FormField<Type>{
	
	/**
	 * Builder to be used to create a {@link FormField}. This is the abstract version to be extended
	 * in subclasses of FormField. It has the following parameters:
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
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 *
	 * @param <Type> The type of value being stored in the field (used for default value)
	 */
	public static abstract class FormFieldBuilder<Type>{
		/** The name of the field (used as a key in {@link com.github.tadukoo.util.view.form.Form Forms}) */
		protected String key;
		/** The starting value of the field */
		protected Type defaultValue = null;
		/** The {@link LabelType} to use for the field */
		protected LabelType labelType = LabelType.LABEL;
		/** The row position of the field */
		protected int rowPos;
		/** The column position of the field */
		protected int colPos;
		/** The row span of the field */
		protected int rowSpan = 1;
		/** The column span of the field */
		protected int colSpan = 1;
		
		/**
		 * Constructs a new FormFieldBuilder (to be called in subclasses)
		 */
		protected FormFieldBuilder(){ }
		
		/**
		 * @param key The name of the field (used as a key in {@link com.github.tadukoo.util.view.form.Form Forms})
		 * @return this, to continue building
		 */
		public FormFieldBuilder<Type> key(String key){
			this.key = key;
			return this;
		}
		
		/**
		 * @param defaultValue The starting value of the field
		 * @return this, to continue building
		 */
		public FormFieldBuilder<Type> defaultValue(Type defaultValue){
			this.defaultValue = defaultValue;
			return this;
		}
		
		/**
		 * @param labelType The {@link LabelType} to use for the field
		 * @return this, to continue building
		 */
		public FormFieldBuilder<Type> labelType(LabelType labelType){
			this.labelType = labelType;
			return this;
		}
		
		/**
		 * @param rowPos The row position of the field
		 * @return this, to continue building
		 */
		public FormFieldBuilder<Type> rowPos(int rowPos){
			this.rowPos = rowPos;
			return this;
		}
		
		/**
		 * @param colPos The column position of the field
		 * @return this, to continue building
		 */
		public FormFieldBuilder<Type> colPos(int colPos){
			this.colPos = colPos;
			return this;
		}
		
		/**
		 * @param rowSpan The row span of the field
		 * @return this, to continue building
		 */
		public FormFieldBuilder<Type> rowSpan(int rowSpan){
			this.rowSpan = rowSpan;
			return this;
		}
		
		/**
		 * @param colSpan The column span of the field
		 * @return this, to continue building
		 */
		public FormFieldBuilder<Type> colSpan(int colSpan){
			this.colSpan = colSpan;
			return this;
		}
		
		/**
		 * Builds the {@link FormField}
		 *
		 * @return A newly created {@link FormField}
		 */
		public abstract FormField<Type> build();
	}
	
	/** The {@link FieldType} of this field */
	private final FieldType type;
	/** The name of this field (used as a key in {@link com.github.tadukoo.util.view.form.Form Forms}) */
	private final String key;
	/** The starting value of the field */
	private final Type defaultValue;
	/** The {@link LabelType} to use for this field */
	private final LabelType labelType;
	/** The row position of this field */
	private final int rowPos;
	/** The column position of this field */
	private final int colPos;
	/** The row span of this field */
	private final int rowSpan;
	/** The column span of this field */
	private final int colSpan;
	
	/**
	 * Creates a new FormField with the given parameters.
	 *
	 * @param type The {@link FieldType} of this field
	 * @param key The name of this field (used as a key in {@link com.github.tadukoo.util.view.form.Form Forms})
	 * @param defaultValue The starting value of the field
	 * @param labelType The {@link LabelType} to use for this field
	 * @param rowPos The row position of this field
	 * @param colPos The column position of this field
	 * @param rowSpan The row span of this field
	 * @param colSpan The column span of this field
	 */
	protected FormField(FieldType type, String key, Type defaultValue, LabelType labelType,
	                    int rowPos, int colPos, int rowSpan, int colSpan){
		this.type = type;
		this.key = key;
		this.defaultValue = defaultValue;
		this.labelType = labelType;
		this.rowPos = rowPos;
		this.colPos = colPos;
		this.rowSpan = rowSpan;
		this.colSpan = colSpan;
	}
	
	/**
	 * @return The {@link FieldType} of this field
	 */
	public FieldType getType(){
		return type;
	}
	
	/**
	 * @return The name of this field (used as a key in {@link com.github.tadukoo.util.view.form.Form Forms})
	 */
	public String getKey(){
		return key;
	}
	
	/**
	 * @return The starting value of this field
	 */
	public Type getDefaultValue(){
		return defaultValue;
	}
	
	/**
	 * @return The {@link LabelType} to use for this field
	 */
	public LabelType getLabelType(){
		return labelType;
	}
	
	/**
	 * @return The row position of this field
	 */
	public int getRowPos(){
		return rowPos;
	}
	
	/**
	 * @return The column position of this field
	 */
	public int getColPos(){
		return colPos;
	}
	
	/**
	 * @return The row span of this field
	 */
	public int getRowSpan(){
		return rowSpan;
	}
	
	/**
	 * @return The column span of this field
	 */
	public int getColSpan(){
		return colSpan;
	}
	
	/**
	 * Creates the {@link JComponent} to be used for this field.
	 *
	 * @return A newly created {@link JComponent} to use on the form
	 */
	public abstract JComponent getComponent();
	
	/**
	 * Takes in the {@link JComponent} for this field and grabs the data off of it, returning it in the proper
	 * format for the field to be repopulated on the form.
	 *
	 * @param component The {@link JComponent} associated with this field
	 * @return The data extracted from the {@link JComponent}
	 */
	public abstract Type getValue(JComponent component);
}
