package com.github.tadukoo.util.view.gradient;

import java.awt.*;

/**
 * Gradient is used to make it easier to create generic Gradient paints.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public interface Gradient{
	
	/**
	 * Creates a {@link Paint} for this Gradient appropriate for the given dimensions.
	 *
	 * @param size The dimensions of the object the Gradient will go on
	 * @return The {@link Paint} for the Gradient
	 */
	Paint getPaint(Dimension size);
	
	/**
	 * @return The fractions involved in this Gradient
	 */
	float[] getFractions();
	
	/**
	 * @return The {@link Color}s involved in this Gradient
	 */
	Color[] getColors();
}
