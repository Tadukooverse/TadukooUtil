package com.github.tadukoo.util.view.form.field;

/**
 * Field Type is used in {@link FormField} for what type of field it is.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public enum FieldType{
	/** A boolean field */
	BOOLEAN,
	/** A string field */
	STRING,
	/** A drop-down field */
	DROP_DOWN,
	/** A button (not really a field) */
	BUTTON,
	/** A {@link com.github.tadukoo.util.view.form.Form Form} field */
	FORM,
	/** A {@link com.github.tadukoo.util.pojo.Table Table} field */
	TABLE
}
