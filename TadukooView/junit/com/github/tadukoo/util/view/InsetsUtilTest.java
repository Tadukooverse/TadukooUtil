package com.github.tadukoo.util.view;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsetsUtilTest{
	
	@Test
	public void testZeroInsetsNull(){
		Insets insets = InsetsUtil.zeroInsets(null);
		assertEquals(0, insets.top);
		assertEquals(0, insets.left);
		assertEquals(0, insets.right);
		assertEquals(0, insets.bottom);
	}
	
	@Test
	public void testZeroInsetsNotNull(){
		Insets insets = InsetsUtil.zeroInsets(new Insets(5, 2, 7, 19));
		assertEquals(0, insets.top);
		assertEquals(0, insets.left);
		assertEquals(0, insets.right);
		assertEquals(0, insets.bottom);
	}
	
	@Test
	public void testAddInsets(){
		Insets insets = InsetsUtil.addInsets(new Insets(2, 5, 8, 17),
				new Insets(3, 5, 12, 13));
		assertEquals(5, insets.top);
		assertEquals(10, insets.left);
		assertEquals(20, insets.bottom);
		assertEquals(30, insets.right);
	}
	
	@Test
	public void testAddInsetsNullSource(){
		Insets insets = InsetsUtil.addInsets(null, new Insets(3, 5, 12, 13));
		assertEquals(3, insets.top);
		assertEquals(5, insets.left);
		assertEquals(12, insets.bottom);
		assertEquals(13, insets.right);
	}
	
	@Test
	public void testAddInsetsNullAddition(){
		Insets insets = InsetsUtil.addInsets(new Insets(2, 5, 8, 17), null);
		assertEquals(2, insets.top);
		assertEquals(5, insets.left);
		assertEquals(8, insets.bottom);
		assertEquals(17, insets.right);
	}
}
