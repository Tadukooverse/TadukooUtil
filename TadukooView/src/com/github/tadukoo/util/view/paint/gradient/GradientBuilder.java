package com.github.tadukoo.util.view.paint.gradient;

import com.github.tadukoo.util.StringUtil;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * Gradient Builder is an abstract builder for a {@link Gradient} to be extended by subclasses for making more
 * specific Gradients, e.g. {@link LinearGradient}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 * @since Alpha v.0.2
 */
public abstract class GradientBuilder<GradientType extends Gradient>{
	/** The fractions involved in the Gradient */
	protected final List<Float> fractions = new ArrayList<>();
	/** The {@link Color}s involved in the Gradient */
	protected final List<Color> colors = new ArrayList<>();
	/** The {@link MultipleGradientPaint.CycleMethod cycle method} involved in the Gradient */
	protected MultipleGradientPaint.CycleMethod cycleMethod = MultipleGradientPaint.CycleMethod.NO_CYCLE;
	/** The {@link MultipleGradientPaint.ColorSpaceType color space} involved in the Gradient */
	protected MultipleGradientPaint.ColorSpaceType colorSpace = MultipleGradientPaint.ColorSpaceType.SRGB;
	/** The {@link AffineTransform} involved in the Gradient */
	protected AffineTransform gradientTransform = new AffineTransform();
	
	/**
	 * Constructs a new GradientBuilder (to be called in subclasses)
	 */
	protected GradientBuilder(){ }
	
	/**
	 * @param fraction The fraction to use for this color point
	 * @param color The {@link Color} to use for this color point
	 * @return this, to continue building
	 */
	public GradientBuilder<GradientType> colorPoint(float fraction, Color color){
		fractions.add(fraction);
		colors.add(color);
		return this;
	}
	
	/**
	 * @param cycleMethod The {@link MultipleGradientPaint.CycleMethod cycle method} involved in the Gradient
	 * @return this, to continue building
	 */
	public GradientBuilder<GradientType> cycleMethod(MultipleGradientPaint.CycleMethod cycleMethod){
		this.cycleMethod = cycleMethod;
		return this;
	}
	
	/**
	 * @param colorSpace The {@link MultipleGradientPaint.ColorSpaceType color space} involved in the Gradient
	 * @return this, to continue building
	 */
	public GradientBuilder<GradientType> colorSpace(MultipleGradientPaint.ColorSpaceType colorSpace){
		this.colorSpace = colorSpace;
		return this;
	}
	
	/**
	 * @param gradientTransform The {@link AffineTransform} involved in the Gradient
	 * @return this, to continue building
	 */
	public GradientBuilder<GradientType> gradientTransform(AffineTransform gradientTransform){
		this.gradientTransform = gradientTransform;
		return this;
	}
	
	/**
	 * Used to verify the given parameters for the Gradient to ensure nothing went wrong.
	 *
	 * @throws IllegalArgumentException If there are any errors
	 */
	protected final void checkForErrors(){
		List<String> errors = new ArrayList<>();
		
		if(colors.size() != fractions.size()){
			errors.add("Must have the same amount of fractions and colors!");
		}
		
		if(colors.size() < 2 || fractions.size() < 2){
			errors.add("Must have 2 or more colors/fractions, otherwise it's just a Color!");
		}
		
		// Let the subclass check for errors too
		errors.addAll(checkForSubclassErrors());
		
		if(!errors.isEmpty()){
			throw new IllegalArgumentException(StringUtil.buildStringWithNewLines(errors));
		}
	}
	
	/**
	 * Called by {@link #checkForErrors()} when checking errors so subclasses can check for their own
	 * specific errors.
	 *
	 * @return A List of errors, which may be empty - it must not be null
	 */
	protected abstract List<String> checkForSubclassErrors();
	
	/**
	 * Builds the Gradient after checking for errors.
	 *
	 * @return The newly created {@link Gradient}
	 */
	public final GradientType build(){
		checkForErrors();
		
		return buildGradient();
	}
	
	/**
	 * Builds the actual {@link Gradient}. This is implemented by the specific subclass.
	 *
	 * @return The newly built {@link Gradient}
	 */
	protected abstract GradientType buildGradient();
}
