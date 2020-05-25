package com.github.tadukoo.util.components;

import java.awt.*;

public interface TadukooShape extends Shaped{
	
	@Override
	default Polygon getShape(int x, int y, int width, int height){
		Polygon polygon = new Polygon();
		polygon.addPoint(x + 1, y + 1);
		polygon.addPoint(x + width - 10, y + 1);
		polygon.addPoint(x + width - 1, y + 10);
		polygon.addPoint(x + width - 1, y + height - 1);
		polygon.addPoint(x + 10, y + height - 1);
		polygon.addPoint(x + 1, y + height - 10);
		return polygon;
	}
}
