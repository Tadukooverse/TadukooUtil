package com.github.tadukoo.util.view.paint.gradient;

import com.github.tadukoo.util.view.paint.SizablePaint;

import java.awt.*;

/**
 * Gradient is used to make it easier to create generic Gradient paints, as a {@link SizablePaint}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 * @since Alpha v.0.2
 */
public interface Gradient extends SizablePaint{
	
	/**
	 * @return The fractions involved in this Gradient
	 */
	float[] getFractions();
	
	/**
	 * @return The {@link Color}s involved in this Gradient
	 */
	Color[] getColors();
}
