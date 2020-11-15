package com.github.tadukoo.util.view.paint.gradient;

import com.github.tadukoo.util.FloatUtil;
import com.github.tadukoo.util.tuple.Pair;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Linear Gradient is used to build a {@link Gradient} object to use for {@link LinearGradientPaint}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class LinearGradient implements Gradient{
	
	/**
	 * The direction for a {@link LinearGradient} to go. This is used to determine the start and end points of a
	 * {@link LinearGradientPaint Linear Gradient}.
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public enum GradientDirection{
		/** Used for a Gradient going left to right */
		HORIZONTAL,
		/** Used for a Gradient going top to bottom */
		VERTICAL,
		/** Used for a Gradient going top-left to bottom-right */
		DIAGONAL_DOWN_RIGHT,
		/** Used for a Gradient going top-right to bottom-left */
		DIAGONAL_DOWN_LEFT
	}
	
	/**
	 * Linear Gradient Builder is a builder for {@link LinearGradient}.
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
	 *     <li>{@link #direction(GradientDirection) direction} -
	 *              defaults to {@link GradientDirection#HORIZONTAL}</li>
	 *     <li>{@link #startAndEndPointsFunc(BiFunction) startAndEndPointsFunc} -
	 *              defaults to calculating based on the direction given</li>
	 * </ul>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class LinearGradientBuilder extends GradientBuilder{
		/** The direction for the {@link LinearGradient} to go. This may be ignored by the
		 * {@link #startAndEndPointsFunc} */
		private GradientDirection direction = GradientDirection.HORIZONTAL;
		/** A function taking in the dimensions of the space and the chosen {@link GradientDirection} to return the
		 * start and end points in space for the {@link LinearGradient} */
		private BiFunction<Dimension, GradientDirection, Pair<Point2D, Point2D>> startAndEndPointsFunc =
				(size, direction) -> {
			// Use direction to determine start and end points
			float startX = 0, startY = 0, endX = 0, endY = 0;
			switch(direction){
				case HORIZONTAL -> endX = size.width;
				case VERTICAL -> endY = size.height;
				case DIAGONAL_DOWN_RIGHT -> {
					endX = size.width;
					endY = size.height;
				}
				case DIAGONAL_DOWN_LEFT -> {
					startX = size.width;
					endY = size.height;
				}
			}
			return Pair.of(new Point2D.Float(startX, startY), new Point2D.Float(endX, endY));
		};
		
		// Not allowed to create outside of LinearGradient
		private LinearGradientBuilder(){ }
		
		/**
		 * @param direction The direction for the {@link LinearGradient} to go. This may be ignored by the
		 * {@link #startAndEndPointsFunc}
		 * @return this, to continue building
		 */
		public LinearGradientBuilder direction(GradientDirection direction){
			this.direction = direction;
			return this;
		}
		
		/**
		 * @param startAndEndPointsFunc A function taking in the dimensions of the space and the chosen
		 * {@link GradientDirection} to return the start and end points in space for the {@link LinearGradient}
		 * @return this, to continue building
		 */
		public LinearGradientBuilder startAndEndPointsFunc(BiFunction<Dimension, GradientDirection,
				Pair<Point2D, Point2D>> startAndEndPointsFunc){
			this.startAndEndPointsFunc = startAndEndPointsFunc;
			return this;
		}
		
		/** {@inheritDoc} */
		@Override
		public LinearGradientBuilder colorPoint(float fraction, Color color){
			return (LinearGradientBuilder) super.colorPoint(fraction, color);
		}
		
		/** {@inheritDoc} */
		@Override
		public LinearGradientBuilder cycleMethod(MultipleGradientPaint.CycleMethod cycleMethod){
			return (LinearGradientBuilder) super.cycleMethod(cycleMethod);
		}
		
		/** {@inheritDoc} */
		@Override
		public LinearGradientBuilder colorSpace(MultipleGradientPaint.ColorSpaceType colorSpace){
			return (LinearGradientBuilder) super.colorSpace(colorSpace);
		}
		
		/** {@inheritDoc} */
		@Override
		public LinearGradientBuilder gradientTransform(AffineTransform gradientTransform){
			return (LinearGradientBuilder) super.gradientTransform(gradientTransform);
		}
		
		/**
		 * Checks that {@link #startAndEndPointsFunc} is not null
		 *
		 * @return An ArrayList of any errors found
		 */
		@Override
		protected List<String> checkForSubclassErrors(){
			List<String> errors = new ArrayList<>();
			
			if(startAndEndPointsFunc == null){
				errors.add("Can't have a null function for start and end points!");
			}
			
			return errors;
		}
		
		/** {@inheritDoc} */
		@Override
		protected LinearGradient buildGradient(){
			return new LinearGradient(direction, startAndEndPointsFunc, FloatUtil.convertListToArray(fractions),
					colors.toArray(new Color[0]), cycleMethod, colorSpace, gradientTransform);
		}
	}
	
	/** The direction for this LinearGradient to go. This may be ignored by the {@link #startAndEndPointsFunc} */
	protected final GradientDirection direction;
	/** A function taking in the dimensions of the space and the chosen {@link GradientDirection} to return the
	 * start and end points in space for this LinearGradient */
	protected final BiFunction<Dimension, GradientDirection, Pair<Point2D, Point2D>> startAndEndPointsFunc;
	/** The fractions involved in this LinearGradient */
	protected final float[] fractions;
	/** The {@link Color}s involved in this LinearGradient */
	protected final Color[] colors;
	/** The {@link MultipleGradientPaint.CycleMethod cycle method} involved in this LinearGradient */
	protected final MultipleGradientPaint.CycleMethod cycleMethod;
	/** The {@link MultipleGradientPaint.ColorSpaceType color space} involved in this LinearGradient */
	protected final MultipleGradientPaint.ColorSpaceType colorSpace;
	/** The {@link AffineTransform} involved in this LinearGradient */
	protected final AffineTransform gradientTransform;
	
	/**
	 * Constructs a new Linear Gradient using the given parameters.
	 *
	 * @param direction The direction for this LinearGradient to go. This may be ignored by the
	 *                  {@link #startAndEndPointsFunc}
	 * @param startAndEndPointsFunc A function taking in the dimensions of the space and the chosen
	 *                  {@link GradientDirection} to return the start and end points in space for this LinearGradient
	 * @param fractions The fractions involved in this LinearGradient
	 * @param colors The {@link Color}s involved in this LinearGradient
	 * @param cycleMethod The {@link MultipleGradientPaint.CycleMethod cycle method} involved in this LinearGradient
	 * @param colorSpace The {@link MultipleGradientPaint.ColorSpaceType color space} involved in this LinearGradient
	 * @param gradientTransform The {@link AffineTransform} involved in this LinearGradient
	 */
	private LinearGradient(GradientDirection direction,
	                       BiFunction<Dimension, GradientDirection, Pair<Point2D, Point2D>> startAndEndPointsFunc,
	                       float[] fractions, Color[] colors,
	                       MultipleGradientPaint.CycleMethod cycleMethod,
	                       MultipleGradientPaint.ColorSpaceType colorSpace,
	                       AffineTransform gradientTransform){
		this.direction = direction;
		this.startAndEndPointsFunc = startAndEndPointsFunc;
		this.fractions = fractions;
		this.colors = colors;
		this.cycleMethod = cycleMethod;
		this.colorSpace = colorSpace;
		this.gradientTransform = gradientTransform;
	}
	
	/**
	 * @return A {@link LinearGradientBuilder} to use in building a LinearGradient
	 */
	public static LinearGradientBuilder builder(){
		return new LinearGradientBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	public Color[] getColors(){
		return colors;
	}
	
	/** {@inheritDoc} */
	@Override
	public float[] getFractions(){
		return fractions;
	}
	
	/**
	 * Creates a {@link LinearGradientPaint} using the {@link #startAndEndPointsFunc} to determine the start and
	 * end points, and sending the other parameters as specified.
	 *
	 * @param size The dimensions of the object the Gradient will go on
	 * @return A new {@link LinearGradientPaint} using the parameters of this LinearGradient
	 */
	@Override
	public Paint getPaint(Dimension size){
		Pair<Point2D, Point2D> startAndEndPoints = startAndEndPointsFunc.apply(size, direction);
		
		// Construct a new LinearGradientPaint
		return new LinearGradientPaint(startAndEndPoints.getLeft(), startAndEndPoints.getRight(),
				fractions, colors, cycleMethod, colorSpace, gradientTransform);
	}
}
