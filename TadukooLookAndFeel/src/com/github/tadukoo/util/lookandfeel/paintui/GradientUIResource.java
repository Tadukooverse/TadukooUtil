package com.github.tadukoo.util.lookandfeel.paintui;

import com.github.tadukoo.util.view.paint.gradient.Gradient;
import com.github.tadukoo.util.view.paint.gradient.LinearGradient;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * GradientUIResource is simply a {@link LinearGradient} wrapped as a {@link PaintUIResource} so that it can be
 * used in {@link com.github.tadukoo.util.lookandfeel.TadukooLookAndFeel TadukooLookAndFeel} by putting it
 * in a {@link com.github.tadukoo.util.lookandfeel.TadukooTheme TadukooTheme}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class GradientUIResource implements PaintUIResource, Gradient{
	/** The {@link Gradient} contained in this Gradient UI resource */
	private final Gradient gradient;
	
	/**
	 * Constructs a Gradient UI Resource using the given {@link Gradient}.
	 *
	 * @param gradient The {@link Gradient} to use for this Gradient UI Resource
	 */
	public GradientUIResource(Gradient gradient){
		this.gradient = gradient;
	}
	
	/** {@inheritDoc} */
	@Override
	public Color[] getColors(){
		return gradient.getColors();
	}
	
	/** {@inheritDoc} */
	@Override
	public float[] getFractions(){
		return gradient.getFractions();
	}
	
	/**
	 * Creates a Paint to be used based on the size of the object to be painted. The size is given because in the
	 * case of Gradients, it determines where the points are placed.
	 *
	 * @param size The Dimensions of the object to be painted
	 * @return A Paint
	 */
	@Override
	public Paint getPaint(Dimension size){
		return gradient.getPaint(size);
	}
	
	/**
	 * Makes a ColorUIResource out of the first Color in this Gradient. There are some UI situations where
	 * we can't avoid using a Color instead of a general Paint if we want to support everything, so we need to
	 * return a plain Color in some cases.
	 *
	 * @return The first Color in the Gradient as a ColorUIResource
	 */
	@Override
	public ColorUIResource getColorUIResource(){
		return new ColorUIResource(gradient.getColors()[0]);
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
	 */
	@Override
	public List<Object> getMetalGradientList(){
		Color[] colors = gradient.getColors();
		float[] fractions = gradient.getFractions();
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
