package com.github.tadukoo.util.view.paint.gradient;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.function.BiFunction;

import static org.junit.Assert.*;

public class LinearGradientTest{
	private LinearGradient gradient;
	
	@BeforeEach
	public void setup(){
		gradient = LinearGradient.builder()
				.colorPoint(0.5f, Color.BLACK)
				.colorPoint(0.75f, Color.YELLOW)
				.build();
	}
	
	@Test
	public void testDefaults(){
		assertNotNull(gradient);
		assertEquals(LinearGradient.GradientDirection.HORIZONTAL, gradient.getDirection());
		assertNotNull(gradient.getStartAndEndPointsFunc());
		assertEquals(MultipleGradientPaint.CycleMethod.NO_CYCLE, gradient.getCycleMethod());
		assertEquals(MultipleGradientPaint.ColorSpaceType.SRGB, gradient.getColorSpace());
		assertEquals(new AffineTransform(), gradient.getGradientTransform());
	}
	
	@Test
	public void testSettings(){
		BiFunction<Dimension, LinearGradient.GradientDirection, Pair<Point2D, Point2D>> aFunc =
				(dim, dir) -> Pair.of(new Point2D.Float(0.0f, 0.0f), new Point2D.Float(0.0f, 0.0f));
		gradient = LinearGradient.builder()
				.colorPoint(0.5f, Color.BLACK)
				.colorPoint(0.75f, Color.YELLOW)
				.direction(LinearGradient.GradientDirection.VERTICAL)
				.startAndEndPointsFunc(aFunc)
				.cycleMethod(MultipleGradientPaint.CycleMethod.REFLECT)
				.colorSpace(MultipleGradientPaint.ColorSpaceType.LINEAR_RGB)
				.gradientTransform(new AffineTransform(0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f))
				.build();
		assertNotNull(gradient);
		assertEquals(LinearGradient.GradientDirection.VERTICAL, gradient.getDirection());
		assertNotNull(gradient.getStartAndEndPointsFunc());
		assertEquals(aFunc, gradient.getStartAndEndPointsFunc());
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
			gradient = LinearGradient.builder().build();
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Must have 2 or more colors/fractions, otherwise it's just a Color!", e.getMessage());
		}
	}
	
	@Test
	public void testNullStartAndEndPointFunc(){
		try{
			gradient = LinearGradient.builder()
					.startAndEndPointsFunc(null)
					.colorPoint(0.5f, Color.BLACK)
					.colorPoint(0.75f, Color.YELLOW)
					.build();
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Can't have a null function for start and end points!", e.getMessage());
		}
	}
}
