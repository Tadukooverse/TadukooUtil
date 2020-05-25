package com.gmail.realtadukoo.util.lookandfeel.componentui;

import com.gmail.realtadukoo.util.components.Shaped;
import com.gmail.realtadukoo.util.lookandfeel.paintui.PaintUIResource;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.util.List;

public class TadukooButtonUI extends MetalButtonUI{
	
	/**
	 * Returns an instance of {@code MetalButtonUI}.
	 *
	 * @param c a component
	 * @return an instance of {@code MetalButtonUI}
	 */
	public static ComponentUI createUI(JComponent c){
		return new TadukooButtonUI();
	}
	
	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b){
		if(b.isContentAreaFilled()){
			Dimension size = b.getSize();
			PaintUIResource paint = (PaintUIResource) UIManager.get("Button.select.paint");
			((Graphics2D) g).setPaint(paint.getPaint(size));
			if(b instanceof Shaped){
				g.fillPolygon(((Shaped) b).getShape(0, 0, size.width, size.height));
			}else{
				g.fillRect(0, 0, size.width, size.height);
			}
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
