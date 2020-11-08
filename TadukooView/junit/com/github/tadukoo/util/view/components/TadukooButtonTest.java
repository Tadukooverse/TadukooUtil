package com.github.tadukoo.util.view.components;

import com.github.tadukoo.util.view.shapes.ShapeInfo;
import com.github.tadukoo.util.view.shapes.Shapes;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class TadukooButtonTest{
	private String text = "Some Text";
	private final Icon icon = new ImageIcon("");
	private final ActionListener actionListener = e -> text = "Some Other Text";
	private final ShapeInfo shapeInfo = Shapes.ELLIPSE.getShapeInfo();
	
	private final TadukooButton button = TadukooButton.builder()
			.text(text).icon(icon)
			.actionListener(actionListener)
			.shapeInfo(shapeInfo)
			.build();
	
	@Test
	public void testBuilderNoValues(){
		TadukooButton simpleButton = TadukooButton.builder().build();
		assertNotNull(simpleButton);
		assertEquals("", simpleButton.getText());
		assertNull(simpleButton.getIcon());
		assertEquals(0, simpleButton.getActionListeners().length);
		assertNull(simpleButton.getShapeInfo());
	}
	
	@Test
	public void testBuilderText(){
		assertEquals(text, button.getText());
	}
	
	@Test
	public void testBuilderIcon(){
		assertEquals(icon, button.getIcon());
	}
	
	@Test
	public void testBuilderActionListener(){
		assertEquals(actionListener, button.getActionListeners()[0]);
	}
	
	@Test
	public void testBuilderShapeInfo(){
		assertEquals(shapeInfo, button.getShapeInfo());
	}
	
	@Test
	public void testSetShapeInfo(){
		assertEquals(shapeInfo, button.getShapeInfo());
		ShapeInfo newInfo = Shapes.ROUND_RECTANGLE.getShapeInfo();
		button.setShapeInfo(newInfo);
		assertEquals(newInfo, button.getShapeInfo());
	}
}
