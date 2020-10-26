package com.github.tadukoo.util.view.shapes;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer5;

import java.awt.*;

/**
 * Shape Drawing Function is generally used to draw a shape or part of one using the given {@link Graphics} and the
 * given x, y, width, and height, to support drawing generally sized shapes.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public interface ShapeDrawingFunction extends ThrowingConsumer5<Graphics, Integer, Integer, Integer, Integer, NoException>{
}
