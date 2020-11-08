package com.github.tadukoo.util.view.form;

import com.github.tadukoo.util.pojo.MappedPojo;
import com.github.tadukoo.util.view.form.field.FormField;

import javax.swing.*;

/**
 * Form represents a form used in a program that the user can fill out and interact with. It uses {@link FormField}s
 * for the fields and buttons and such to be displayed.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public interface Form extends MappedPojo{
	
	/**
	 * @return Whether to have labels for components above them (true) or to the left (false) - defaults to true
	 */
	default boolean labelsOnTop(){
		return true;
	}
	
	/**
	 * Adds the given {@link FormField} to this Form
	 *
	 * @param field The {@link FormField} to add to this Form
	 */
	void addField(FormField<?> field);
	
	/**
	 * This method should be called by the constructor to set default fields (this is where you should create new
	 * {@link FormField}s by calling {@link #addField(FormField)})
	 */
	void setDefaultFields();
	
	/**
	 * This method should be called by the constructor (after calling {@link #setDefaultFields()}), and will
	 * create the components to be used on this Form
	 */
	void createComponents();
	
	/**
	 * This method can be called by subclasses to update the values present in the form from the Component values
	 */
	void saveValues();
	
	/**
	 * Grabs the appropriate {@link JComponent} present on this form for the given key for the field
	 *
	 * @param key The key used by the field to find the {@link JComponent}
	 * @return The appropriate {@link JComponent} for the given key for its field
	 */
	JComponent getComponentByKey(String key);
}
