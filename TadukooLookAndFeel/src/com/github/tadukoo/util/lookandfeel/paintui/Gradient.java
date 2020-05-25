package com.github.tadukoo.util.lookandfeel.paintui;

import com.github.tadukoo.util.FloatUtil;
import com.github.tadukoo.util.StringUtil;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gradient{
	
	public enum GradientDirection{
		HORIZONTAL,
		VERTICAL,
		DIAGONAL_DOWN_RIGHT,
		DIAGONAL_DOWN_LEFT;
	}
	
	private static class GradientBuilder{
		private boolean uiResource = false;
		private GradientDirection direction = GradientDirection.HORIZONTAL;
		private final List<Float> fractions = new ArrayList<>();
		private final List<Color> colors = new ArrayList<>();
		private MultipleGradientPaint.CycleMethod cycleMethod = MultipleGradientPaint.CycleMethod.NO_CYCLE;
		private MultipleGradientPaint.ColorSpaceType colorSpace = MultipleGradientPaint.ColorSpaceType.SRGB;
		private AffineTransform gradientTransform = new AffineTransform();
		
		public GradientBuilder uiResource(boolean uiResource){
			this.uiResource = uiResource;
			return this;
		}
		
		public GradientBuilder direction(GradientDirection direction){
			this.direction = direction;
			return this;
		}
		
		public GradientBuilder addColorPoint(float fraction, Color color){
			fractions.add(fraction);
			colors.add(color);
			return this;
		}
		
		public GradientBuilder cycleMethod(MultipleGradientPaint.CycleMethod cycleMethod){
			this.cycleMethod = cycleMethod;
			return this;
		}
		
		public GradientBuilder colorSpace(MultipleGradientPaint.ColorSpaceType colorSpace){
			this.colorSpace = colorSpace;
			return this;
		}
		
		public GradientBuilder gradientTransform(AffineTransform gradientTransform){
			this.gradientTransform = gradientTransform;
			return this;
		}
		
		private void checkForErrors(){
			List<String> errors = new ArrayList<>();
			if(colors.size() != fractions.size()){
				errors.add("Must have the same amount of fractions and colors!");
			}
			
			if(colors.size() < 2 || fractions.size() < 2){
				errors.add("Must have 2 or more colors/fractions, otherwise it's just a Color!");
			}
			
			if(!errors.isEmpty()){
				throw new IllegalArgumentException(StringUtil.buildStringWithNewLines(errors));
			}
		}
		
		public Gradient build(){
			checkForErrors();
			
			float[] fractionArray = FloatUtil.convertListToArray(fractions);
			
			if(uiResource){
				// Change Colors to Color UI Resources
				Color[] colorArray = new Color[colors.size()];
				for(int i = 0; i < colors.size(); i++){
					colorArray[i] = new ColorUIResource(colors.get(i));
				}
				
				return new GradientUIResource(direction, fractionArray, colorArray,
						cycleMethod, colorSpace, gradientTransform);
			}else{
				return new Gradient(direction, fractionArray, colors.toArray(new Color[0]),
						cycleMethod, colorSpace, gradientTransform);
			}
		}
	}
	
	protected GradientDirection direction;
	protected float[] fractions;
	protected Color[] colors;
	protected MultipleGradientPaint.CycleMethod cycleMethod;
	protected MultipleGradientPaint.ColorSpaceType colorSpace;
	protected AffineTransform gradientTransform;
	
	private Gradient(GradientDirection direction, float[] fractions, Color[] colors,
	                 MultipleGradientPaint.CycleMethod cycleMethod, MultipleGradientPaint.ColorSpaceType colorSpace,
	                 AffineTransform gradientTransform){
		this.direction = direction;
		this.fractions = fractions;
		this.colors = colors;
		this.cycleMethod = cycleMethod;
		this.colorSpace = colorSpace;
		this.gradientTransform = gradientTransform;
	}
	
	public static GradientBuilder builder(){
		return new GradientBuilder();
	}
	
	public Paint getPaint(Dimension size){
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
		
		return new LinearGradientPaint(new Point2D.Float(startX, startY), new Point2D.Float(endX, endY),
				fractions, colors, cycleMethod, colorSpace, gradientTransform);
	}
	
	
	
	private static class GradientUIResource extends Gradient implements PaintUIResource{
		private GradientUIResource(GradientDirection direction, float[] fractions, Color[] colors,
		                           MultipleGradientPaint.CycleMethod cycleMethod,
		                           MultipleGradientPaint.ColorSpaceType colorSpace, AffineTransform gradientTransform){
			super(direction, fractions, colors, cycleMethod, colorSpace, gradientTransform);
		}
		
		/**
		 * Makes a ColorUIResource out of the first Color in this Gradient. There are some UI situations where
		 * we can't avoid using a Color instead of a general Paint if we want to support everything, so we need to
		 * return a plain Color in some cases.
		 *
		 * @return The first Color in the Gradient as a ColorUIResource
		 */
		public ColorUIResource getColorUIResource(){
			return new ColorUIResource(colors[0]);
		}
		
		/**
		 * Metal Look and Feel has a trash way of handling gradients. Basically it takes 3 colors and 2 fractions, but
		 * with the 3 colors, it repeats color 1 and does 1, 2, 1, 3. The fractions represent the 2 middle points,
		 * but the 2nd point is calculated as mid1 * 2 + mid2.
		 * <br><br>
		 * If there are 4 colors or more, we send colors 1, 2, and either 3 or 4. Color 3 is sent if it's different than
		 * color 1 (because if it's the same, Metal already handles it with its 1, 2, 1, 3 garbage).
		 * <br>
		 * 3 colors get sent in their given order, and we can't help that 1 is repeated.
		 * <br><br>
		 * If there are 3 fractions or more, we send fraction 2 as midpoint1, and fraction 3 as midpoint2, after
		 * subtracting midpoint1 * 2 from it.
		 * <br><br>
		 * If there are only 2 colors, we send color 1, 2, and 2 again. For the fractions, we send fraction 2 as midpoint 1
		 * and put in 0 for midpoint 2.
		 *
		 * @return The garbage List that Metal Look and Feel expects for gradients that matches best to the one defined here
		 *
		 * @deprecated Forever, because I hate how Metal Look and Feel does this, but I'm trying to support it all
		 */
		@Deprecated
		@Override
		public List<Object> getMetalGradientList(){
			float mid2 = fractions.length > 2?fractions[2] - fractions[1]*2:0;
			ColorUIResource color3;
			if(colors.length > 3 && colors[0].equals(colors[2])){
				// If colors 1 and 3 are the same, Metal stuff handles it that way already without specifying it twice
				color3 = new ColorUIResource(colors[3]);
			}else if(colors.length > 2){
				color3 = new ColorUIResource(colors[2]);
			}else{
				color3 = new ColorUIResource(colors[1]);
			}
			return Arrays.asList(
					new Object[]{fractions[1], mid2,
							new ColorUIResource(colors[0]), new ColorUIResource(colors[1]), color3});
		}
	}
}
