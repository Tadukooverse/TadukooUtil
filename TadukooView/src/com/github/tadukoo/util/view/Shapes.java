package com.github.tadukoo.util.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * A collection of Shape functions that can be used
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public enum Shapes{
	/**
	 * Creates an ellipse
	 */
	ELLIPSE(Ellipse2D.Float::new),
	
	/**
	 * Creates a circle centered in the space
	 */
	CIRCLE((x, y, width, height) -> {
		// Figure out minimum dimension
		int size = Math.min(width, height);
		// Recalculate x and y to center the circle
		int newX = x + ((width - size)/2);
		int newY = y + ((height - size)/2);
		return new Ellipse2D.Float(newX, newY, size, size);
	}),
	
	/**
	 * Creates a Rectangle
	 */
	RECTANGLE(Rectangle::new),
	
	// TODO: Make a better way so they can specify the arc params
	/**
	 * Creates a rounded rectangle
	 */
	ROUND_RECTANGLE((x, y, width, height) -> new RoundRectangle2D.Float(x, y, width, height,
			width/4, height/4)),
	
	/**
	 * Creates a Rectangle with the top right and bottom left corners cut out
	 */
	RECTANGLE_WITH_CUT_CORNERS_TR_BL((x, y, width, height) -> {
		Polygon polygon = new Polygon();
		polygon.addPoint(x + 1, y + 1);
		polygon.addPoint(x + width - 10, y + 1);
		polygon.addPoint(x + width - 1, y + 10);
		polygon.addPoint(x + width - 1, y + height - 1);
		polygon.addPoint(x + 10, y + height - 1);
		polygon.addPoint(x + 1, y + height - 10);
		return polygon;
	}),
	
	/**
	 * Creates a Rectangle with the top left and bottom right corners cut out
	 */
	RECTANGLE_WITH_CUT_CORNERS_TL_BR((x, y, width, height) -> {
		Polygon polygon = new Polygon();
		polygon.addPoint(x + 10, y + 1);
		polygon.addPoint(x + width - 1, y + 1);
		polygon.addPoint(x + width - 1, y + height - 10);
		polygon.addPoint(x + width - 10, y + height - 1);
		polygon.addPoint(x + 1, y + height - 1);
		polygon.addPoint(x + 1, y + 10);
		return polygon;
	})
	;
	
	/** The {@link ShapeFunction} */
	private final ShapeFunction shapeFunc;
	
	/**
	 * Creates a Shapes enumeration with the given {@link ShapeFunction}.
	 *
	 * @param shapeFunc The {@link ShapeFunction}
	 */
	Shapes(ShapeFunction shapeFunc){
		this.shapeFunc = shapeFunc;
	}
	
	/**
	 * @return The {@link ShapeFunction}
	 */
	public ShapeFunction getShapeFunc(){
		return shapeFunc;
	}
}
