package com.github.tadukoo.util.view.form.field;


/**
 * Label Type is used in {@link FormField} for what type of label to use with the field.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public enum LabelType{
	/** Do not include a label */
	NONE,
	/** Use a plain {@link javax.swing.JLabel JLabel} */
	LABEL,
	/** Use a Titled Border */
	TITLED_BORDER
}
