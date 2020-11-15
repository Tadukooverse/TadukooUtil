package com.github.tadukoo.util.view.border;

import com.github.tadukoo.util.view.paint.SizablePaint;
import com.github.tadukoo.util.view.shapes.Shaped;
import com.github.tadukoo.util.view.shapes.ShapeInfo;
import com.github.tadukoo.util.view.shapes.Shapes;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * Shaped Etched Border mimics the functionality of a {@link javax.swing.border.BevelBorder BevelBorder}, but uses the
 * {@link ShapeInfo} (if present) on the component to draw the border, rather than making a rectangular border, or
 * it can have its own {@link ShapeInfo} if desired.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 * @since Alpha v.0.2
 */
public class ShapedBevelBorder extends AbstractBorder{
	
	/**
	 * An enum representing the Bevel type to be used (RAISED or LOWERED) for a border
	 */
	public enum BevelType{
		RAISED, LOWERED
	}
	
	/**
	 * Shaped Bevel Border Builder is used to create a {@link ShapedBevelBorder}. It uses the following parameters:
	 * <ul>
	 *     <li>shapeInfo - The {@link ShapeInfo} to use on the Border (may be null to use the component's shape) -
	 *     defaults to null</li>
	 *     <li>etchType - the {@link BevelType} (RAISED or LOWERED) - defaults to LOWERED</li>
	 *     <li>highlightInnerPaint - The {@link SizablePaint} for the inner highlight - defaults to using the
	 *     Component's getBackground().brighter()</li>
	 *     <li>highlightOuterPaint - The {@link SizablePaint} for the outer highlight - defaults to using the
	 *     Component's getBackground().brighter().brighter()</li>
	 *     <li>shadowInnerPaint - The {@link SizablePaint} for the inner shadow - defaults to using the
	 *     Component's getBackground().darker()</li>
	 *     <li>shadowOuterPaint - The {@link SizablePaint} for the outer shadow - defaults to using the
	 *     Component's getBackground().darker()</li>
	 * </ul>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2.1
	 * @since Alpha v.0.2
	 */
	public static class ShapedBevelBorderBuilder{
		/** The {@link ShapeInfo} to use on the Border (may be null to use the component's shape) */
		private ShapeInfo shapeInfo = null;
		/** The {@link BevelType type of bevel} (RAISED or LOWERED) */
		private BevelType bevelType = BevelType.LOWERED;
		/** The {@link SizablePaint} for the inner highlight */
		private SizablePaint highlightInnerPaint = null;
		/** The {@link SizablePaint} for the outer highlight */
		private SizablePaint highlightOuterPaint = null;
		/** The {@link SizablePaint} for the inner shadow */
		private SizablePaint shadowInnerPaint = null;
		/** The {@link SizablePaint} for the outer shadow */
		private SizablePaint shadowOuterPaint = null;
		
		// Can't create outside of ShapedBevelBorder
		private ShapedBevelBorderBuilder(){ }
		
		/**
		 * @param shapeInfo The {@link ShapeInfo} to use on the Border (may be null to use the component's shape)
		 * @return this, to continue building
		 */
		public ShapedBevelBorderBuilder shapeInfo(ShapeInfo shapeInfo){
			this.shapeInfo = shapeInfo;
			return this;
		}
		
		/**
		 * @param bevelType The {@link BevelType type of bevel} (RAISED or LOWERED)
		 * @return this, to continue building
		 */
		public ShapedBevelBorderBuilder bevelType(BevelType bevelType){
			this.bevelType = bevelType;
			return this;
		}
		
		/**
		 * @param highlightInnerPaint The {@link SizablePaint} for the inner highlight
		 * @return this, to continue building
		 */
		public ShapedBevelBorderBuilder highlightInnerPaint(SizablePaint highlightInnerPaint){
			this.highlightInnerPaint = highlightInnerPaint;
			return this;
		}
		
		/**
		 * @param highlightOuterPaint The {@link SizablePaint} for the outer highlight
		 * @return this, to continue building
		 */
		public ShapedBevelBorderBuilder highlightOuterPaint(SizablePaint highlightOuterPaint){
			this.highlightOuterPaint = highlightOuterPaint;
			return this;
		}
		
		/**
		 * @param shadowInnerPaint The {@link SizablePaint} for the inner shadow
		 * @return this, to continue building
		 */
		public ShapedBevelBorderBuilder shadowInnerPaint(SizablePaint shadowInnerPaint){
			this.shadowInnerPaint = shadowInnerPaint;
			return this;
		}
		
		/**
		 * @param shadowOuterPaint The {@link SizablePaint} for the outer shadow
		 * @return this, to continue building
		 */
		public ShapedBevelBorderBuilder shadowOuterPaint(SizablePaint shadowOuterPaint){
			this.shadowOuterPaint = shadowOuterPaint;
			return this;
		}
		
		/**
		 * @return A newly created {@link ShapedEtchedBorder} using the set parameters
		 */
		public ShapedBevelBorder build(){
			return new ShapedBevelBorder(shapeInfo, bevelType,
					highlightInnerPaint, highlightOuterPaint, shadowInnerPaint, shadowOuterPaint);
		}
	}
	
	/** The {@link ShapeInfo} to use on the Border (may be null to use the component's shape) */
	private final ShapeInfo shapeInfo;
	/** The {@link BevelType type of bevel} (RAISED or LOWERED) */
	private final BevelType bevelType;
	/** The {@link SizablePaint} for the inner highlight */
	private final SizablePaint highlightInnerPaint;
	/** The {@link SizablePaint} for the outer highlight */
	private final SizablePaint highlightOuterPaint;
	/** The {@link SizablePaint} for the inner shadow */
	private final SizablePaint shadowInnerPaint;
	/** The {@link SizablePaint} for the outer shadow */
	private final SizablePaint shadowOuterPaint;
	
	/**
	 * Creates a new Shaped Bevel Border with the given parameters
	 *
	 * @param shapeInfo The {@link ShapeInfo} to use on the Border (may be null to use the component's shape)
	 * @param bevelType The {@link BevelType type of bevel} (RAISED or LOWERED)
	 * @param highlightInnerPaint The {@link SizablePaint} for the inner highlight
	 * @param highlightOuterPaint The {@link SizablePaint} for the outer highlight
	 * @param shadowInnerPaint The {@link SizablePaint} for the inner shadow
	 * @param shadowOuterPaint The {@link SizablePaint} for the outer shadow
	 */
	private ShapedBevelBorder(ShapeInfo shapeInfo, BevelType bevelType,
	                          SizablePaint highlightInnerPaint, SizablePaint highlightOuterPaint,
	                          SizablePaint shadowInnerPaint, SizablePaint shadowOuterPaint){
		this.shapeInfo = shapeInfo;
		this.bevelType = bevelType;
		this.highlightInnerPaint = highlightInnerPaint;
		this.highlightOuterPaint = highlightOuterPaint;
		this.shadowInnerPaint = shadowInnerPaint;
		this.shadowOuterPaint = shadowOuterPaint;
	}
	
	/**
	 * @return A {@link ShapedBevelBorderBuilder builder} to create a {@link ShapedBevelBorder}
	 */
	public static ShapedBevelBorderBuilder builder(){
		return new ShapedBevelBorderBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	public Insets getBorderInsets(Component c){
		return new Insets(2, 2, 2, 2);
	}
	
	/** {@inheritDoc} */
	@Override
	public Insets getBorderInsets(Component c, Insets insets){
		insets.set(2, 2, 2, 2);
		return insets;
	}
	
	/** {@inheritDoc} */
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int w, int h){
		// Determine ShapeInfo to use
		ShapeInfo shapeInfoToUse;
		if(shapeInfo == null ||
				shapeInfo.getTopLeftDrawFunc() == ShapeInfo.noDrawFunc ||
				shapeInfo.getBottomRightDrawFunc() == ShapeInfo.noDrawFunc){
			if(c instanceof Shaped){
				shapeInfoToUse = ((Shaped) c).getShapeInfo();
				// If the shape doesn't support top-left and bottom-right separation, use a rectangle
				if(shapeInfoToUse.getTopLeftDrawFunc() == ShapeInfo.noDrawFunc ||
						shapeInfoToUse.getBottomRightDrawFunc() == ShapeInfo.noDrawFunc){
					shapeInfoToUse = Shapes.RECTANGLE.getShapeInfo();
				}
			}else{
				// Use a Rectangle if it's not a Shaped object
				shapeInfoToUse = Shapes.RECTANGLE.getShapeInfo();
			}
		}else{
			shapeInfoToUse = shapeInfo;
		}
		
		switch(bevelType){
			case RAISED -> paintRaisedBevel(c, g, x, y, w, h, shapeInfoToUse);
			case LOWERED -> paintLoweredBevel(c, g, x, y, w, h, shapeInfoToUse);
		}
	}
	
	/**
	 * Paints a raised bevel for the specified component with the specified position and size.
	 *
	 * @param c the component for which the bevel is being painted
	 * @param g the paint graphics
	 * @param x the x position of the bevel
	 * @param y the y position of the bevel
	 * @param w the width of the bevel
	 * @param h the height of the bevel
	 * @param shapeInfo The {@link ShapeInfo} to use for the bevel
	 */
	private void paintRaisedBevel(Component c, Graphics g, int x, int y, int w, int h, ShapeInfo shapeInfo){
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = new Dimension(w, h);
		
		g.translate(x, y);
		
		// Draw outer highlight
		g2d.setPaint(getHighlightOuterPaint(c, size));
		shapeInfo.getTopLeftDrawFunc().accept(g, 0, 0, w-1, h-1);
		
		// Draw inner highlight
		g2d.setPaint(getHighlightInnerPaint(c, size));
		shapeInfo.getTopLeftDrawFunc().accept(g, 1, 1, w-2, h-2);
		
		// Draw outer shadow
		g2d.setPaint(getShadowOuterPaint(c, size));
		shapeInfo.getBottomRightDrawFunc().accept(g, 0, 0, w, h);
		
		// Draw inner shadow
		g2d.setPaint(getShadowInnerPaint(c, size));
		shapeInfo.getBottomRightDrawFunc().accept(g, 1, 1, w-1,h-1);
		
		g.translate(-x, -y);
	}
	
	/**
	 * Paints a lowered bevel for the specified component with the specified position and size.
	 *
	 * @param c the component for which the lowered bevel is being painted
	 * @param g the paint graphics
	 * @param x the x position of the lowered bevel
	 * @param y the y position of the lowered bevel
	 * @param w the width of the lowered bevel
	 * @param h the height of the lowered bevel
	 * @param shapeInfo The {@link ShapeInfo} to use for the bevel
	 */
	private void paintLoweredBevel(Component c, Graphics g, int x, int y, int w, int h, ShapeInfo shapeInfo){
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = new Dimension(w, h);
		
		g.translate(x, y);
		
		// Draw inner shadow
		g2d.setPaint(getShadowInnerPaint(c, size));
		shapeInfo.getTopLeftDrawFunc().accept(g, 0, 0, w, h);
		
		// Draw outer shadow
		g2d.setPaint(getShadowOuterPaint(c, size));
		shapeInfo.getTopLeftDrawFunc().accept(g, 1, 1, w-1, h-1);
		
		// Draw outer highlight
		g2d.setPaint(getHighlightOuterPaint(c, size));
		shapeInfo.getBottomRightDrawFunc().accept(g, 1, 1, w, h);
		
		// Draw inner highlight
		g2d.setPaint(getHighlightInnerPaint(c, size));
		shapeInfo.getBottomRightDrawFunc().accept(g, 2, 2, w-1,h-1);
		
		g.translate(-x, -y);
	}
	
	/**
	 * Returns a {@link Paint} to use for the inner highlight on the given component with the given size.
	 *
	 * @param c The {@link Component} we're putting a border on
	 * @param size The size of the space being painted
	 * @return The {@link Paint} to use
	 */
	private Paint getHighlightInnerPaint(Component c, Dimension size){
		return highlightInnerPaint != null?highlightInnerPaint.getPaint(size):c.getBackground().brighter();
	}
	
	/**
	 * Returns a {@link Paint} to use for the outer highlight on the given component with the given size.
	 *
	 * @param c The {@link Component} we're putting a border on
	 * @param size The size of the space being painted
	 * @return The {@link Paint} to use
	 */
	private Paint getHighlightOuterPaint(Component c, Dimension size){
		return highlightOuterPaint != null?highlightOuterPaint.getPaint(size):c.getBackground().brighter().brighter();
	}
	
	/**
	 * Returns a {@link Paint} to use for the inner shadow on the given component with the given size.
	 *
	 * @param c The {@link Component} we're putting a border on
	 * @param size The size of the space being painted
	 * @return The {@link Paint} to use
	 */
	private Paint getShadowInnerPaint(Component c, Dimension size){
		return shadowInnerPaint != null?shadowInnerPaint.getPaint(size):c.getBackground().darker();
	}
	
	/**
	 * Returns a {@link Paint} to use for the outer shadow on the given component with the given size.
	 *
	 * @param c The {@link Component} we're putting a border on
	 * @param size The size of the space being painted
	 * @return The {@link Paint} to use
	 */
	private Paint getShadowOuterPaint(Component c, Dimension size){
		return shadowOuterPaint != null?shadowOuterPaint.getPaint(size):c.getBackground().darker().darker();
	}
}
