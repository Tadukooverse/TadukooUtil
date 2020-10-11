package com.github.tadukoo.util.view.gradient;

import com.github.tadukoo.util.FloatUtil;
import com.github.tadukoo.util.tuple.Triple;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Linear Gradient is used to build a {@link Gradient} object to use for {@link RadialGradientPaint}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class RadialGradient implements Gradient{
	
	/**
	 * Radial Gradient Builder is a builder for {@link RadialGradient}.
	 *
	 * The only required parameters to specify are the {@link #colorPoint(float, Color) color points}. You must specify
	 * at least two, so that it's an actual gradient.
	 *
	 * The following parameters are optional:
	 * <ul>
	 *     <li>{@link #cycleMethod(MultipleGradientPaint.CycleMethod) cycleMethod} -
	 *              defaults to {@link MultipleGradientPaint.CycleMethod#NO_CYCLE}</li>
	 *     <li>{@link #colorSpace(MultipleGradientPaint.ColorSpaceType) colorSpace} -
	 *              defaults to {@link MultipleGradientPaint.ColorSpaceType#SRGB}</li>
	 *     <li>{@link #gradientTransform(AffineTransform) gradientTransform} -
	 *              defaults to new AffineTransform()</li>
	 *     <li>{@link #circleFunc(Function) circleFunc} -
	 *              defaults to calculating a circle centered in the given space with focus in the center</li>
	 * </ul>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class RadialGradientBuilder extends GradientBuilder{
		/** A function taking in the dimension of the space to determine the center, radius, and focus of the circle */
		private Function<Dimension, Triple<Point2D, Float, Point2D>> circleFunc = size -> {
			float centerX = (float) size.getWidth()/2;
			float centerY = (float) size.getHeight()/2;
			Point2D center = new Point2D.Float(centerX, centerY);
			return Triple.of(center, Math.min(centerX, centerY), center);
		};
		
		// Not allowed to create outside of RadialGradient
		private RadialGradientBuilder(){ }
		
		/**
		 * @param circleFunc A function taking in the dimension of the space to determine the center, radius, and
		 *                   focus of the circle
		 * @return this, to continue building
		 */
		public RadialGradientBuilder circleFunc(Function<Dimension, Triple<Point2D, Float, Point2D>> circleFunc){
			this.circleFunc = circleFunc;
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public RadialGradientBuilder colorPoint(float fraction, Color color){
			return (RadialGradientBuilder) super.colorPoint(fraction, color);
		}
		
		/** {@inheritDoc} */
		@Override
		public RadialGradientBuilder cycleMethod(MultipleGradientPaint.CycleMethod cycleMethod){
			return (RadialGradientBuilder) super.cycleMethod(cycleMethod);
		}
		
		/** {@inheritDoc} */
		@Override
		public RadialGradientBuilder colorSpace(MultipleGradientPaint.ColorSpaceType colorSpace){
			return (RadialGradientBuilder) super.colorSpace(colorSpace);
		}
		
		/** {@inheritDoc} */
		@Override
		public RadialGradientBuilder gradientTransform(AffineTransform gradientTransform){
			return (RadialGradientBuilder) super.gradientTransform(gradientTransform);
		}
		
		/**
		 * Checks that {@link #circleFunc} is not null
		 *
		 * @return An ArrayList of any errors found
		 */
		@Override
		protected List<String> checkForSubclassErrors(){
			List<String> errors = new ArrayList<>();
			
			if(circleFunc == null){
				errors.add("Can't have a null function for circle's center, radius, and focus!");
			}
			
			return errors;
		}
		
		/** {@inheritDoc} */
		@Override
		protected Gradient buildGradient(){
			return new RadialGradient(circleFunc, FloatUtil.convertListToArray(fractions),
					colors.toArray(new Color[0]), cycleMethod, colorSpace, gradientTransform);
		}
	}
	
	/** A function taking in the dimension of the space to determine the center, radius, and focus of the circle */
	protected Function<Dimension, Triple<Point2D, Float, Point2D>> circleFunc;
	/** The fractions involved in this LinearGradient */
	protected float[] fractions;
	/** The {@link Color}s involved in this LinearGradient */
	protected Color[] colors;
	/** The {@link MultipleGradientPaint.CycleMethod cycle method} involved in this LinearGradient */
	protected MultipleGradientPaint.CycleMethod cycleMethod;
	/** The {@link MultipleGradientPaint.ColorSpaceType color space} involved in this LinearGradient */
	protected MultipleGradientPaint.ColorSpaceType colorSpace;
	/** The {@link AffineTransform} involved in this LinearGradient */
	protected AffineTransform gradientTransform;
	
	/**
	 * Constructs a new Radial Gradient using the given parameters.
	 *
	 * @param circleFunc A function taking in the dimension of the space to determine the center, radius, and
	 *                   focus of the circle
	 * @param fractions The fractions involved in this LinearGradient
	 * @param colors The {@link Color}s involved in this LinearGradient
	 * @param cycleMethod The {@link MultipleGradientPaint.CycleMethod cycle method} involved in this LinearGradient
	 * @param colorSpace The {@link MultipleGradientPaint.ColorSpaceType color space} involved in this LinearGradient
	 * @param gradientTransform The {@link AffineTransform} involved in this LinearGradient
	 */
	private RadialGradient(Function<Dimension, Triple<Point2D, Float, Point2D>> circleFunc,
	                       float[] fractions, Color[] colors,
	                       MultipleGradientPaint.CycleMethod cycleMethod,
	                       MultipleGradientPaint.ColorSpaceType colorSpace,
	                       AffineTransform gradientTransform){
		this.circleFunc = circleFunc;
		this.fractions = fractions;
		this.colors = colors;
		this.cycleMethod = cycleMethod;
		this.colorSpace = colorSpace;
		this.gradientTransform = gradientTransform;
	}
	
	/**
	 * @return A {@link RadialGradientBuilder} to use in building a RadialGradient
	 */
	public static RadialGradientBuilder builder(){
		return new RadialGradientBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	public float[] getFractions(){
		return fractions;
	}
	
	/** {@inheritDoc} */
	@Override
	public Color[] getColors(){
		return colors;
	}
	
	/**
	 * Creates a {@link RadialGradientPaint} using the {@link #circleFunc} to determine the center, radius, and
	 * focus of the circle, and sending the other parameters as specified.
	 *
	 * @param size The dimensions of the object the Gradient will go on
	 * @return A new {@link RadialGradientPaint} using the parameters of this RadialGradient
	 */
	@Override
	public Paint getPaint(Dimension size){
		Triple<Point2D, Float, Point2D> circleParams = circleFunc.apply(size);
		
		return new RadialGradientPaint(circleParams.getLeft(), circleParams.getMiddle(), circleParams.getRight(),
				fractions, colors, cycleMethod, colorSpace, gradientTransform);
	}
}
