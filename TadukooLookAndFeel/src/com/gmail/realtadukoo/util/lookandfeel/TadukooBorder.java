package com.gmail.realtadukoo.util.lookandfeel;

import com.gmail.realtadukoo.util.components.Shaped;

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
		
		Polygon shape = ((Shaped) c).getShape(x, y, w, h);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		
		g.setColor(Color.RED);
		g.drawPolygon(shape);
	}
}
