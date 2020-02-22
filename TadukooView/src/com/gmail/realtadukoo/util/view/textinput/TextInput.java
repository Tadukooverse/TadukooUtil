package com.gmail.realtadukoo.util.view.textinput;

/**
 * Represents an object that text can be input into.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public interface TextInput{
	
	/**
	 * Handles a character being typed. 
	 * Basically it should be added to the current text in the input.
	 * 
	 * @param c The character that was typed
	 */
	public abstract void handleKeyType(char c);
	
	/**
	 * Handles pressing backspace on this TextInput. 
	 * Basically it should remove the character immediately before the cursor in 
	 * the current text in the input.
	 */
	public abstract void handleBackspace();
	
	/**
	 * Handles pressing delete on this TextInput. 
	 * Basically it should remove the character immediately after the cursor in 
	 * the current text in the input.
	 */
	public abstract void handleDelete();
	
	/**
	 * Handles pressing left on this TextInput. 
	 * Basically it should move the cursor back by one character in the current 
	 * text in the input.
	 */
	public abstract void handleLeft();
	
	/**
	 * Handles pressing right on this TextInput. 
	 * Basically it should move the cursor forward by one character in the current 
	 * text in the input.
	 */
	public abstract void handleRight();
	
	/**
	 * Handles pressing up on this TextInput. 
	 * Basically it should move the cursor up one line in the current text in the 
	 * input if it's multiple lines, or to the start of the text if it's just a 
	 * single line.
	 */
	public abstract void handleUp();
	
	/**
	 * Handles pressing down on this TextInput. 
	 * Basically it should move the cursor down one line in the current text in the 
	 * input if it's multiple lines, or go to the end of the text if it's just a 
	 * single line.
	 */
	public abstract void handleDown();
	
	/**
	 * Handles pressing escape on this TextInput. 
	 * It returns a boolean to determine whether to unfocus from the TextInput 
	 * object. The reason you would return false is in the case where hitting escape 
	 * moves you to another object within the TextInput context (e.g. in a form, exiting 
	 * back to a higher level).
	 * 
	 * @return Whether to unfocus from this TextInput or not
	 */
	public abstract boolean handleEscape();
	
	/**
	 * Handles pressing tab on this TextInput. 
	 * It returns a boolean to determine whether to unfocus from the TextInput 
	 * object. The reason you would return false is in the case where hitting tab 
	 * moves you to another object within the TextInput context (e.g. in a form, moving 
	 * to the next object in the form).
	 * 
	 * @return Whether to unfocus from this TextInput or not
	 */
	public abstract boolean handleTab();
	
	/**
	 * Handles pressing enter on this TextInput. 
	 * It returns a boolean to determine whether to unfocus from the TextInput 
	 * object. The reason you would return false is in the case where hitting enter 
	 * moves you to another object within the TextInput context (e.g. in a form, moving 
	 * to the next object in the form, or submitting it).
	 * 
	 * @return Whether to unfocus from this TextInput or not
	 */
	public abstract boolean handleEnter();
}
