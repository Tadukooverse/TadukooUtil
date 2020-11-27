package com.github.tadukoo.util.view;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class OrientationTest{
	
	@Test
	public void testTopCenter(){
		assertEquals(Orientation.Vertical.TOP, Orientation.TOP_CENTER.vertical());
		assertEquals(Orientation.Horizontal.CENTER, Orientation.TOP_CENTER.horizontal());
	}
	
	@Test
	public void testLeftCenter(){
		assertEquals(Orientation.Vertical.CENTER, Orientation.LEFT_CENTER.vertical());
		assertEquals(Orientation.Horizontal.LEFT, Orientation.LEFT_CENTER.horizontal());
	}
	
	@Test
	public void testCenter(){
		assertEquals(Orientation.Vertical.CENTER, Orientation.CENTER.vertical());
		assertEquals(Orientation.Horizontal.CENTER, Orientation.CENTER.horizontal());
	}
	
	@Test
	public void testRightCenter(){
		assertEquals(Orientation.Vertical.CENTER, Orientation.RIGHT_CENTER.vertical());
		assertEquals(Orientation.Horizontal.RIGHT, Orientation.RIGHT_CENTER.horizontal());
	}
	
	@Test
	public void testBottomCenter(){
		assertEquals(Orientation.Vertical.BOTTOM, Orientation.BOTTOM_CENTER.vertical());
		assertEquals(Orientation.Horizontal.CENTER, Orientation.BOTTOM_CENTER.horizontal());
	}
	
	@Test
	public void testTopLeft(){
		assertEquals(Orientation.Vertical.TOP, Orientation.TOP_LEFT.vertical());
		assertEquals(Orientation.Horizontal.LEFT, Orientation.TOP_LEFT.horizontal());
	}
	
	@Test
	public void testTopRight(){
		assertEquals(Orientation.Vertical.TOP, Orientation.TOP_RIGHT.vertical());
		assertEquals(Orientation.Horizontal.RIGHT, Orientation.TOP_RIGHT.horizontal());
	}
	
	@Test
	public void testBottomLeft(){
		assertEquals(Orientation.Vertical.BOTTOM, Orientation.BOTTOM_LEFT.vertical());
		assertEquals(Orientation.Horizontal.LEFT, Orientation.BOTTOM_LEFT.horizontal());
	}
	
	@Test
	public void testBottomRight(){
		assertEquals(Orientation.Vertical.BOTTOM, Orientation.BOTTOM_RIGHT.vertical());
		assertEquals(Orientation.Horizontal.RIGHT, Orientation.BOTTOM_RIGHT.horizontal());
	}
}
