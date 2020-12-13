package com.github.tadukoo.util.view.shapes;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ShapeInfoTest{
	private final ShapeFunction shapeFunc = Rectangle::new;
	private final ShapeInsetsFunction shapeInsetsFunc = Insets::new;
	private final ShapeDrawingFunction topLeftDrawFunc = (g, x, y, w, h) -> {};
	private final ShapeDrawingFunction botRightDrawFunc = (g, x, y, w, h) -> {};
	private final ShapeInfo shapeInfo = new ShapeInfo(shapeFunc, shapeInsetsFunc, topLeftDrawFunc, botRightDrawFunc);
	
	@Test
	public void testNoInsets(){
		Insets insets = ShapeInfo.noInsets.apply(45, 192, 33, 39);
		assertEquals(0, insets.top);
		assertEquals(0, insets.left);
		assertEquals(0, insets.right);
		assertEquals(0, insets.bottom);
	}
	
	@Test
	public void testNoDrawFunc(){
		assertNull(ShapeInfo.noDrawFunc);
	}
	
	@Test
	public void testGetShapeFunc(){
		assertEquals(shapeFunc, shapeInfo.getShapeFunc());
	}
	
	@Test
	public void testGetShapeInsetsFunc(){
		assertEquals(shapeInsetsFunc, shapeInfo.getShapeInsetsFunc());
	}
	
	@Test
	public void testGetTopLeftDrawFunc(){
		assertEquals(topLeftDrawFunc, shapeInfo.getTopLeftDrawFunc());
	}
	
	@Test
	public void testGetBottomRightDrawFunc(){
		assertEquals(botRightDrawFunc, shapeInfo.getBottomRightDrawFunc());
	}
}
