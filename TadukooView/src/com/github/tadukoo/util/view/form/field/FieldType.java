package com.github.tadukoo.util.view.form.field;

/**
 * Field Type is used in {@link FormField} for what type of field it is.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public enum FieldType{
	/** A string field */
	STRING,
	/** A button (not really a field) */
	BUTTON,
	/** A {@link com.github.tadukoo.util.view.form.Form Form} field */
	FORM,
	/** A {@link com.github.tadukoo.util.pojo.Table Table} field */
	TABLE
}
