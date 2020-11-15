package com.github.tadukoo.util.view.paint;

import java.awt.*;

/**
 * Sizable Paints are used to create generic {@link Paint}s that can vary according to the given dimensions they're
 * meant to be painted on.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public interface SizablePaint{
	
	/**
	 * Creates a {@link Paint} to be used based on the size of the object to be painted. The size is given
	 * because of some cases where it matters, e.g. the case of Gradients, where it determines where the
	 * points are placed.
	 *
	 * @param size The Dimensions of the object to be painted
	 * @return A {@link Paint}
	 */
	Paint getPaint(Dimension size);
}
