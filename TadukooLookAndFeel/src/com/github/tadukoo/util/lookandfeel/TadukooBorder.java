package com.github.tadukoo.util.lookandfeel;

import com.github.tadukoo.util.components.Shaped;

import javax.swing.border.AbstractBorder;
import javax.swing.plaf.UIResource;
import java.awt.*;

public class TadukooBorder extends AbstractBorder implements UIResource{
	public static Insets borderInsets = new Insets(10, 10, 10, 10);
	
	@Override
	public Insets getBorderInsets(Component c){
		return borderInsets;
	}
	
	@Override
	public Insets getBorderInsets(Component c, Insets insets){
		return borderInsets;
	}
	
	public void paintBorder(Component c, Graphics g, int x, int y, int w, int h){
		if(!(c instanceof Shaped)){
			return;
		}
		
		Shape shape = ((Shaped) c).getShapeFunc().apply(x, y, w, h);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		
		g.setColor(Color.RED);
		((Graphics2D) g).draw(shape);
	}
}
