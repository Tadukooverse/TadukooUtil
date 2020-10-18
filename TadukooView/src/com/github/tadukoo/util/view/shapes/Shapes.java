package com.github.tadukoo.util.view.shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * A collection of {@link ShapeInfo}s that can be used
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public enum Shapes{
	/**
	 * Creates an ellipse
	 */
	ELLIPSE(new ShapeInfo(Ellipse2D.Float::new, ShapeInfo.noInsets)),
	
	/**
	 * Creates a circle centered in the space
	 */
	CIRCLE(new ShapeInfo((x, y, width, height) -> {
		// Figure out minimum dimension
		int size = Math.min(width, height);
		// Recalculate x and y to center the circle
		int newX = x + ((width - size)/2);
		int newY = y + ((height - size)/2);
		return new Ellipse2D.Float(newX, newY, size, size);
	}, ShapeInfo.noInsets)),
	
	/**
	 * Creates a Rectangle
	 */
	RECTANGLE(new ShapeInfo(Rectangle::new, ShapeInfo.noInsets)),
	
	// TODO: Make a better way so they can specify the arc params
	/**
	 * Creates a rounded rectangle
	 */
	ROUND_RECTANGLE(new ShapeInfo((x, y, width, height) -> new RoundRectangle2D.Float(x, y, width, height,
			width/4, height/4), ShapeInfo.noInsets)),
	
	/**
	 * Creates a Rectangle with the top right and bottom left corners cut out
	 */
	RECTANGLE_WITH_CUT_CORNERS_TR_BL(new ShapeInfo((x, y, width, height) -> {
		Polygon polygon = new Polygon();
		polygon.addPoint(x, y);
		polygon.addPoint(x + width - 10, y);
		polygon.addPoint(x + width, y + 10);
		polygon.addPoint(x + width, y + height);
		polygon.addPoint(x + 10, y + height);
		polygon.addPoint(x, y + height - 10);
		return polygon;
	}, 	(x, y, width, height) -> new Insets(10, 10, 10, 10))),
	
	/**
	 * Creates a Rectangle with the top left and bottom right corners cut out
	 */
	RECTANGLE_WITH_CUT_CORNERS_TL_BR(new ShapeInfo((x, y, width, height) -> {
		Polygon polygon = new Polygon();
		polygon.addPoint(x + 10, y);
		polygon.addPoint(x + width, y);
		polygon.addPoint(x + width, y + height - 10);
		polygon.addPoint(x + width - 10, y + height);
		polygon.addPoint(x, y + height);
		polygon.addPoint(x, y + 10);
		return polygon;
	}, (x, y, width, height) -> new Insets(10, 10, 10, 10)))
	;
	
	/** The {@link ShapeInfo} */
	private final ShapeInfo shapeInfo;
	
	/**
	 * Creates a Shapes enumeration with the given {@link ShapeInfo}.
	 *
	 * @param shapeInfo The {@link ShapeInfo}
	 */
	Shapes(ShapeInfo shapeInfo){
		this.shapeInfo = shapeInfo;
	};
	
	/**
	 * @return The {@link ShapeInfo}
	 */
	public ShapeInfo getShapeInfo(){
		return shapeInfo;
	}
}
