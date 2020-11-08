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
	public static final ShapeInsetsFunction noInsets = (x, y, width, height) -> new Insets(0, 0, 0, 0);
	
	/**
	 * {@code null} to be used in checks for a missing drawing function (which is allowed in some cases)
	 */
	public static final ShapeDrawingFunction noDrawFunc = null;
	
	/** The {@link ShapeFunction} for constructing a {@link Shape} */
	private final ShapeFunction shapeFunc;
	/** The {@link ShapeInsetsFunction} for determining {@link Insets} for the shape */
	private final ShapeInsetsFunction shapeInsetsFunc;
	/** A {@link ShapeDrawingFunction} for the top-left half of the shape */
	private final ShapeDrawingFunction topLeftDrawFunc;
	/** A {@link ShapeDrawingFunction} for the bottom-right half of the shape */
	private final ShapeDrawingFunction bottomRightDrawFunc;
	
	/**
	 * Constructs a new ShapeInfo with the given functions
	 *
	 * @param shapeFunc The {@link ShapeFunction} for constructing a {@link Shape}
	 * @param shapeInsetsFunc The {@link ShapeInsetsFunction} for determining {@link Insets} for the shape
	 * @param topLeftDrawFunc A {@link ShapeDrawingFunction} for the top-left half of the shape
	 * @param bottomRightDrawFunc A {@link ShapeDrawingFunction} for the bottom-right half of the shape
	 */
	public ShapeInfo(ShapeFunction shapeFunc, ShapeInsetsFunction shapeInsetsFunc,
	                 ShapeDrawingFunction topLeftDrawFunc, ShapeDrawingFunction bottomRightDrawFunc){
		this.shapeFunc = shapeFunc;
		this.shapeInsetsFunc = shapeInsetsFunc;
		this.topLeftDrawFunc = topLeftDrawFunc;
		this.bottomRightDrawFunc = bottomRightDrawFunc;
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
	
	/**
	 * @return A {@link ShapeDrawingFunction} for the top-left half of the shape
	 */
	public ShapeDrawingFunction getTopLeftDrawFunc(){
		return topLeftDrawFunc;
	}
	
	/**
	 * @return A {@link ShapeDrawingFunction} for the bottom-right half of the shape
	 */
	public ShapeDrawingFunction getBottomRightDrawFunc(){
		return bottomRightDrawFunc;
	}
}
