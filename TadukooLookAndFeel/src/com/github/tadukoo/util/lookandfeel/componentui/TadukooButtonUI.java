package com.github.tadukoo.util.lookandfeel.componentui;

import com.github.tadukoo.util.components.Shaped;
import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.function.ThrowingFunction4;
import com.github.tadukoo.util.lookandfeel.paintui.PaintUIResource;
import com.github.tadukoo.util.view.ShapeFunction;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class TadukooButtonUI extends MetalButtonUI{
	
	/**
	 * Returns an instance of {@code TadukooButtonUI}.
	 *
	 * @param c a component
	 * @return an instance of {@code TadukooButtonUI}
	 */
	public static ComponentUI createUI(JComponent c){
		return new TadukooButtonUI();
	}
	
	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b){
		// Cast Graphics to Graphics2D for our purposes
		Graphics2D g2d = (Graphics2D) g;
		
		if(b.isContentAreaFilled()){
			// Grab button size for use in paint and shape functions
			Dimension size = b.getSize();
			
			// Grab the paint to use and set it on the graphics
			PaintUIResource paint = (PaintUIResource) UIManager.get("Button.select.paint");
			g2d.setPaint(paint.getPaint(size));
			
			// Grab the shape function to be used
			ShapeFunction shapeFunc = null;
			if(b instanceof Shaped){
				shapeFunc = ((Shaped) b).getShapeFunc();
			}
			if(shapeFunc == null){
				shapeFunc = (ShapeFunction) UIManager.get("Button.shape");
			}
			// Fill the shape
			g2d.fill(shapeFunc.apply(0, 0, size.width, size.height));
		}
	}
	
	@Override
	protected void paintFocus(Graphics g, AbstractButton b,
	                          Rectangle viewRect, Rectangle textRect, Rectangle iconRect){
		
		Rectangle focusRect = new Rectangle();
		String text = b.getText();
		boolean isIcon = b.getIcon() != null;
		
		// If there is text
		if ( text != null && !text.isEmpty()) {
			if ( !isIcon ) {
				focusRect.setBounds( textRect );
			}
			else {
				focusRect.setBounds( iconRect.union( textRect ) );
			}
		}
		// If there is an icon and no text
		else if ( isIcon ) {
			focusRect.setBounds( iconRect );
		}
		
		PaintUIResource paint = (PaintUIResource) UIManager.get("Button.focus.paint");
		((Graphics2D) g).setPaint(paint.getPaint(focusRect.getSize()));
		g.drawRect((focusRect.x-1), (focusRect.y-1),
				focusRect.width+1, focusRect.height+1);
		
	}
}
