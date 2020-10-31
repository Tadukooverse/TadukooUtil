package com.github.tadukoo.util.view.shapes;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.function.ThrowingFunction4;

import java.awt.*;

/**
 * It's a {@link ThrowingFunction4} that throws {@link NoException} (just a way to avoid throwing stuff),
 * takes in 4 integers (which should be x, y, width, and height), and returns a {@link Shape}. This interface
 * exists solely as shorthand for using the long ThrowingFunction4 name.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
@FunctionalInterface
public interface ShapeFunction extends ThrowingFunction4<Integer, Integer, Integer, Integer, Shape, NoException>{
}
