package com.gmail.realtadukoo.util;

/**
 * Util functions for dealing with Booleans.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public final class BooleanUtil{
	
	// Not allowed to create a BooleanUtil
	private BooleanUtil(){ }
	
	/**
	 * Checks that the given Boolean is true, properly handling cases of 
	 * null as false (meaning returning a false result).
	 * 
	 * @param bool The Boolean to check
	 * @return If the Boolean is true
	 */
	public static final boolean isTrue(Boolean bool){
		return bool != null && bool;
	}
	
	/**
	 * Checks that the given Boolean is false, properly handling cases of 
	 * null as false (meaning returning a false result).
	 * 
	 * @param bool The Boolean to check
	 * @return If the Boolean is false
	 */
	public static final boolean isFalse(Boolean bool){
		return bool != null && !bool;
	}
	
	/**
	 * Checks that the given Boolean is not true, meaning that a Boolean 
	 * that's false or a null Boolean will both return a true result.
	 * 
	 * @param bool The Boolean to check
	 * @return If the Boolean is not true
	 */
	public static final boolean isNotTrue(Boolean bool){
		return bool == null || !bool;
	}
	
	/**
	 * Checks that the given Boolean is not false, meaning that a Boolean 
	 * that's true or a null Boolean will both return a true result.
	 * 
	 * @param bool The Boolean to check
	 * @return If the Boolean is not false
	 */
	public static final boolean isNotFalse(Boolean bool){
		return bool == null || bool;
	}
}
