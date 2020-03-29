package com.gmail.realtadukoo.util;

/**
 * Util functions for dealing with AutoCloseables.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public final class AutoCloseableUtil{
	
	// Not allowed to create an AutoCloseableUtil
	private AutoCloseableUtil(){ }
	
	/**
	 * Closes the given AutoCloseable while suppressing any error messages
	 * 
	 * @param autoCloseable The AutoCloseable to close
	 */
	public static void closeQuietly(AutoCloseable autoCloseable){
		if(autoCloseable != null){
			try{
				autoCloseable.close();
			}catch(Exception e){
				
			}
		}
	}
}
