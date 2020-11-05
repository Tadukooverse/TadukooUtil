package com.github.tadukoo.util.lookandfeel.border;

import com.github.tadukoo.util.view.shapes.Shaped;
import com.github.tadukoo.util.lookandfeel.paintui.PaintUIResource;
import com.github.tadukoo.util.view.shapes.ShapeInfo;
import com.github.tadukoo.util.view.shapes.Shapes;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * Shaped Etched Border mimics the functionality of a {@link javax.swing.border.EtchedBorder EtchedBorder}, but uses the
 * {@link ShapeInfo} (if present) on the component to draw the border, rather than making a rectangular border, or
 * it can have its own {@link ShapeInfo} if desired.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class ShapedEtchedBorder extends AbstractBorder{
	
	/**
	 * An enum representing the Etch type to be used (RAISED or LOWERED) for a border
	 */
	public enum EtchType{
		RAISED, LOWERED;
	}
	
	/**
	 * Shaped Etched Border Builder is used to create a {@link ShapedEtchedBorder}. It uses the following parameters:
	 * <ul>
	 *     <li>shapeInfo - The {@link ShapeInfo} to use on the Border (may be null to use the component's shape) -
	 *     defaults to null</li>
	 *     <li>etchType - the {@link EtchType} (RAISED or LOWERED) - defaults to LOWERED</li>
	 *     <li>highlightPaint - The {@link PaintUIResource} for the highlight - defaults to using the Component's
	 *     getBackground().brighter()</li>
	 *     <li>shadowPaint - The {@link PaintUIResource} for the shadow - defaults to using the Component's
	 *     getBackground().darker()</li>
	 * </ul>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.2
	 */
	public static class ShapedEtchedBorderBuilder{
		/** The {@link ShapeInfo} to use on the Border (may be null to use the component's shape) */
		private ShapeInfo shapeInfo = null;
		/** The {@link EtchType type of etching} (RAISED or LOWERED) */
		private EtchType etchType = EtchType.LOWERED;
		/** The {@link PaintUIResource} for the highlight */
		private PaintUIResource highlightPaint = null;
		/** The {@link PaintUIResource} for the shadow */
		private PaintUIResource shadowPaint = null;
		
		// Can't create builder outside of ShapedEtchedBorder
		private ShapedEtchedBorderBuilder(){ }
		
		/**
		 * @param shapeInfo The {@link ShapeInfo} to use on the Border (may be null to use the component's shape)
		 * @return this, to continue building
		 */
		public ShapedEtchedBorderBuilder shapeInfo(ShapeInfo shapeInfo){
			this.shapeInfo = shapeInfo;
			return this;
		}
		
		/**
		 * @param etchType The {@link EtchType type of etching} (RAISED or LOWERED)
		 * @return this, to continue building
		 */
		public ShapedEtchedBorderBuilder etchType(EtchType etchType){
			this.etchType = etchType;
			return this;
		}
		
		/**
		 * @param highlightPaint The {@link PaintUIResource} for the highlight
		 * @return this, to continue building
		 */
		public ShapedEtchedBorderBuilder highlightPaint(PaintUIResource highlightPaint){
			this.highlightPaint = highlightPaint;
			return this;
		}
		
		/**
		 * @param shadowPaint The {@link PaintUIResource} for the shadow
		 * @return this, to continue building
		 */
		public ShapedEtchedBorderBuilder shadowPaint(PaintUIResource shadowPaint){
			this.shadowPaint = shadowPaint;
			return this;
		}
		
		/**
		 * @return A newly created {@link ShapedEtchedBorder} using the set parameters
		 */
		public ShapedEtchedBorder build(){
			return new ShapedEtchedBorder(shapeInfo, etchType, highlightPaint, shadowPaint);
		}
	}
	/** The {@link ShapeInfo} to use on the Border (may be null to use the component's shape) */
	private final ShapeInfo shapeInfo;
	/** The {@link EtchType type of etching} (RAISED or LOWERED) */
	private final EtchType etchType;
	/** The {@link PaintUIResource} for the highlight */
	private final PaintUIResource highlightPaint;
	/** The {@link PaintUIResource} for the shadow */
	private final PaintUIResource shadowPaint;
	
	/**
	 * Creates a new Shaped Etched Border with the given parameters
	 *
	 * @param shapeInfo The {@link ShapeInfo} to use on the Border (may be null to use the component's shape)
	 * @param etchType The {@link EtchType type of etching} (RAISED or LOWERED)
	 * @param highlightPaint The {@link PaintUIResource} for the highlight
	 * @param shadowPaint The {@link PaintUIResource} for the shadow
	 */
	private ShapedEtchedBorder(ShapeInfo shapeInfo, EtchType etchType,
	                           PaintUIResource highlightPaint, PaintUIResource shadowPaint){
		this.shapeInfo = shapeInfo;
		this.etchType = etchType;
		this.highlightPaint = highlightPaint;
		this.shadowPaint = shadowPaint;
	}
	
	/**
	 * @return A {@link ShapedEtchedBorderBuilder builder} to create a {@link ShapedEtchedBorder}
	 */
	public static ShapedEtchedBorderBuilder builder(){
		return new ShapedEtchedBorderBuilder();
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
		
		Dimension size = new Dimension(w, h);
		Graphics2D g2d = (Graphics2D) g;
		
		// Paint normal full shape
		g2d.setPaint(etchType == EtchType.LOWERED?getShadowPaint(c, size):getHighlightPaint(c, size));
		g2d.draw(shapeInfoToUse.getShapeFunc().apply(x, y, w-1, h-1));
		
		// Paint secondary top left
		g2d.setPaint(etchType == EtchType.LOWERED?getHighlightPaint(c, size):getShadowPaint(c, size));
		shapeInfoToUse.getTopLeftDrawFunc().accept(g, x+1, y+1, w-2, h-2);
		g.drawLine(1, h-3, 1, 1);
		g.drawLine(1, 1, w-3, 1);
		
		// Paint secondary bottom right
		shapeInfoToUse.getBottomRightDrawFunc().accept(g, x, y, w, h);
		g.drawLine(0, h-1, w-1, h-1);
		g.drawLine(w-1, h-1, w-1, 0);
	}
	
	/**
	 * Returns a {@link Paint} to use for the highlight on the given component with the given size.
	 *
	 * @param c The {@link Component} we're putting a border on
	 * @param size The size of the space being painted
	 * @return The {@link Paint} to use
	 */
	private Paint getHighlightPaint(Component c, Dimension size){
		return highlightPaint != null?highlightPaint.getPaint(size):c.getBackground().brighter();
	}
	
	/**
	 * Returns a {@link Paint} to use for the shadow on the given component with the given size.
	 *
	 * @param c The {@link Component} we're putting a border on
	 * @param size The size of the space being painted
	 * @return The {@link Paint} to use
	 */
	private Paint getShadowPaint(Component c, Dimension size){
		return shadowPaint != null?shadowPaint.getPaint(size):c.getBackground().darker();
	}
}
