package com.gmail.realtadukoo.util.lookandfeel.componentui;

import com.gmail.realtadukoo.util.components.Shaped;

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
			g.setColor(getSelectColor());
			if(b instanceof Shaped){
				((Graphics2D) g).setPaint(Color.GREEN);
				List<?> gradList = (List<?>) UIManager.get("Button.gradient");
				float mid1 = ((Number) gradList.get(0)).floatValue();
				float mid2 = ((Number) gradList.get(1)).floatValue();
				Color c1 = (Color) gradList.get(2);
				Color c2 = (Color) gradList.get(3);
				Color c3 = (Color) gradList.get(4);
				((Graphics2D) g).setPaint(new LinearGradientPaint(0, 0, size.width, 0, new float[]{0, mid1, mid1*2 + mid2, 1}, new Color[]{c1, c2, c1, c3}));
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
		
		g.setColor(getFocusColor());
		g.drawRect((focusRect.x-1), (focusRect.y-1),
				focusRect.width+1, focusRect.height+1);
		
	}
}
