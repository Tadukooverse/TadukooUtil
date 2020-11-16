package com.github.tadukoo.util.view.paint.gradient;

import com.github.tadukoo.util.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.function.Function;

import static org.junit.Assert.*;

public class RadialGradientTest{
	private RadialGradient gradient;
	
	@BeforeEach
	public void setup(){
		gradient = RadialGradient.builder()
				.colorPoint(0.5f, Color.BLACK)
				.colorPoint(0.75f, Color.YELLOW)
				.build();
	}
	
	@Test
	public void testDefaults(){
		assertNotNull(gradient);
		assertNotNull(gradient.getCircleFunc());
		assertEquals(MultipleGradientPaint.CycleMethod.NO_CYCLE, gradient.getCycleMethod());
		assertEquals(MultipleGradientPaint.ColorSpaceType.SRGB, gradient.getColorSpace());
		assertEquals(new AffineTransform(), gradient.getGradientTransform());
	}
	
	@Test
	public void testSettings(){
		Function<Dimension, Triple<Point2D, Float, Point2D>> aFunc =
				dim -> Triple.of(new Point2D.Float(0.0f, 0.0f), 0.0f, new Point2D.Float(0.0f, 0.0f));
		gradient = RadialGradient.builder()
				.circleFunc(aFunc)
				.colorPoint(0.5f, Color.BLACK)
				.colorPoint(0.75f, Color.YELLOW)
				.cycleMethod(MultipleGradientPaint.CycleMethod.REFLECT)
				.colorSpace(MultipleGradientPaint.ColorSpaceType.LINEAR_RGB)
				.gradientTransform(new AffineTransform(0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f))
				.build();
		assertNotNull(gradient);
		assertNotNull(gradient.getCircleFunc());
		assertEquals(aFunc, gradient.getCircleFunc());
		assertEquals(MultipleGradientPaint.CycleMethod.REFLECT, gradient.getCycleMethod());
		assertEquals(MultipleGradientPaint.ColorSpaceType.LINEAR_RGB, gradient.getColorSpace());
		assertEquals(new AffineTransform(0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f),
				gradient.getGradientTransform());
		Color[] colors = gradient.getColors();
		assertEquals(2, colors.length);
		assertEquals(Color.BLACK, colors[0]);
		assertEquals(Color.YELLOW, colors[1]);
		float[] fractions = gradient.getFractions();
		assertEquals(2, fractions.length);
		assertEquals(0.5f, fractions[0], 0.01);
		assertEquals(0.75f, fractions[1], 0.01);
	}
	
	@Test
	public void testNotEnoughColorPoints(){
		try{
			gradient = RadialGradient.builder().build();
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Must have 2 or more colors/fractions, otherwise it's just a Color!", e.getMessage());
		}
	}
	
	@Test
	public void testNullCircleFunc(){
		try{
			gradient = RadialGradient.builder()
					.circleFunc(null)
					.colorPoint(0.5f, Color.BLACK)
					.colorPoint(0.75f, Color.YELLOW)
					.build();
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Can't have a null function for circle's center, radius, and focus!", e.getMessage());
		}
	}
}
