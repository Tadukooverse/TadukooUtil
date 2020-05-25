package com.github.tadukoo.util;

import java.util.List;

/**
 * Util functions for dealing with floats.
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public final class FloatUtil{
	
	// Not allowed to create a FileUtil
	private FloatUtil(){ }
	
	/**
	 * Converts a List of Floats into an array of floats.
	 *
	 * @param floatList The List of Floats to convert to an array
	 * @return An array containing the floats from the List
	 */
	public static float[] convertListToArray(List<Float> floatList){
		float[] floatArray = new float[floatList.size()];
		for(int i = 0; i < floatList.size(); i++){
			floatArray[i] = floatList.get(i);
		}
		return floatArray;
	}
}
