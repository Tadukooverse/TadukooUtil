package com.github.tadukoo.util.view.shapes;

import java.awt.*;

/**
 * Shape Info contains a method for calculating the {@link Shape} and one for calculating the {@link Insets} for that
 * Shape. Both methods take in the x, y, width, and height of the space the Shape will be drawn in to support
 * Shapes of different sizes more easily.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class ShapeInfo{
	
	/**
	 * A simple {@link ShapeInsetsFunction} to be used to return an {@link Insets} object with 0 for all sides.
	 */
	public static ShapeInsetsFunction noInsets = (x, y, width, height) -> new Insets(0, 0, 0, 0);
	
	/** The {@link ShapeFunction} for constructing a {@link Shape} */
	private final ShapeFunction shapeFunc;
	/** The {@link ShapeInsetsFunction} for determining {@link Insets} for the shape */
	private final ShapeInsetsFunction shapeInsetsFunc;
	
	/**
	 * Constructs a new ShapeInfo with the given functions
	 *
	 * @param shapeFunc The {@link ShapeFunction} for constructing a {@link Shape}
	 * @param shapeInsetsFunc The {@link ShapeInsetsFunction} for determining {@link Insets} for the shape
	 */
	public ShapeInfo(ShapeFunction shapeFunc, ShapeInsetsFunction shapeInsetsFunc){
		this.shapeFunc = shapeFunc;
		this.shapeInsetsFunc = shapeInsetsFunc;
	}
	
	/**
	 * @return The {@link ShapeFunction} for constructing a {@link Shape}
	 */
	public ShapeFunction getShapeFunc(){
		return shapeFunc;
	}
	
	/**
	 * @return The {@link ShapeInsetsFunction} for determining {@link Insets} for the shape
	 */
	public ShapeInsetsFunction getShapeInsetsFunc(){
		return shapeInsetsFunc;
	}
}
