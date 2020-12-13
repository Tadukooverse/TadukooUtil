package com.github.tadukoo.util.view.shapes;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import static org.junit.jupiter.api.Assertions.*;

public class ShapesTest{
	
	@Test
	public void testEllipse(){
		ShapeInfo ellipseInfo = Shapes.ELLIPSE.getShapeInfo();
		assertTrue(ellipseInfo.getShapeFunc().apply(5, 5, 5, 5) instanceof Ellipse2D);
		assertEquals(ShapeInfo.noInsets, ellipseInfo.getShapeInsetsFunc());
		assertEquals(ShapeInfo.noDrawFunc, ellipseInfo.getTopLeftDrawFunc());
		assertEquals(ShapeInfo.noDrawFunc, ellipseInfo.getBottomRightDrawFunc());
	}
	
	@Test
	public void testCircle(){
		ShapeInfo circleInfo = Shapes.CIRCLE.getShapeInfo();
		assertTrue(circleInfo.getShapeFunc().apply(5, 5, 5, 5) instanceof Ellipse2D);
		assertEquals(ShapeInfo.noInsets, circleInfo.getShapeInsetsFunc());
		assertEquals(ShapeInfo.noDrawFunc, circleInfo.getTopLeftDrawFunc());
		assertEquals(ShapeInfo.noDrawFunc, circleInfo.getBottomRightDrawFunc());
	}
	
	@Test
	public void testRectangle(){
		ShapeInfo rectangleInfo = Shapes.RECTANGLE.getShapeInfo();
		assertTrue(rectangleInfo.getShapeFunc().apply(5, 5, 5, 5) instanceof Rectangle);
		assertEquals(ShapeInfo.noInsets, rectangleInfo.getShapeInsetsFunc());
		assertNotNull(rectangleInfo.getTopLeftDrawFunc());
		assertNotNull(rectangleInfo.getBottomRightDrawFunc());
	}
	
	@Test
	public void testRoundRectangle(){
		ShapeInfo roundRectInfo = Shapes.ROUND_RECTANGLE.getShapeInfo();
		assertTrue(roundRectInfo.getShapeFunc().apply(5, 5, 5, 5) instanceof RoundRectangle2D);
		assertEquals(ShapeInfo.noInsets, roundRectInfo.getShapeInsetsFunc());
		assertEquals(ShapeInfo.noDrawFunc, roundRectInfo.getTopLeftDrawFunc());
		assertEquals(ShapeInfo.noDrawFunc, roundRectInfo.getBottomRightDrawFunc());
	}
	
	@Test
	public void testRectangleWithCutCornersTLBR(){
		ShapeInfo cutRectInfo = Shapes.RECTANGLE_WITH_CUT_CORNERS_TL_BR.getShapeInfo();
		assertTrue(cutRectInfo.getShapeFunc().apply(25, 25, 25, 25) instanceof Polygon);
		Insets insets = cutRectInfo.getShapeInsetsFunc().apply(25, 25, 25, 25);
		assertEquals(10, insets.top);
		assertEquals(10, insets.left);
		assertEquals(10, insets.right);
		assertEquals(10, insets.bottom);
		assertNotNull(cutRectInfo.getTopLeftDrawFunc());
		assertNotNull(cutRectInfo.getBottomRightDrawFunc());
	}
	
	@Test
	public void testRectangleWithCutCornersTRBL(){
		ShapeInfo cutRectInfo = Shapes.RECTANGLE_WITH_CUT_CORNERS_TR_BL.getShapeInfo();
		assertTrue(cutRectInfo.getShapeFunc().apply(25, 25, 25, 25) instanceof Polygon);
		Insets insets = cutRectInfo.getShapeInsetsFunc().apply(25, 25, 25, 25);
		assertEquals(10, insets.top);
		assertEquals(10, insets.left);
		assertEquals(10, insets.right);
		assertEquals(10, insets.bottom);
		assertNotNull(cutRectInfo.getTopLeftDrawFunc());
		assertNotNull(cutRectInfo.getBottomRightDrawFunc());
	}
}
