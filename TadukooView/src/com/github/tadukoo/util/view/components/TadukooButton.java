package com.github.tadukoo.util.view.components;

import com.github.tadukoo.util.view.InsetsUtil;
import com.github.tadukoo.util.view.shapes.ShapeInfo;
import com.github.tadukoo.util.view.shapes.Shaped;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Tadukoo Button is an extension of {@link JButton} that allows for specifying more parameters that can be used in
 * Tadukoo Look & Feel, such as {@link ShapeInfo}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class TadukooButton extends JButton implements Shaped{
	
	/**
	 * Builder to be used to create a {@link TadukooButton}. This is the abstract version to be extended
	 * in subclasses of FormField. It has the following parameters:
	 *
	 * <table>
	 *     <tr>
	 *         <th>Name</th>
	 *         <th>Description</th>
	 *         <th>Default Value or Required</th>
	 *     </tr>
	 *     <tr>
	 *         <td>text</td>
	 *         <td>The text to use on the Button</td>
	 *         <td>Defaults to null</td>
	 *     </tr>
	 *     <tr>
	 *         <td>icon</td>
	 *         <td>The icon to use on the Button</td>
	 *         <td>Defaults to null</td>
	 *     </tr>
	 *     <tr>
	 *         <td>actionListener</td>
	 *         <td>The action to perform on click of the Button</td>
	 *         <td>Defaults to null</td>
	 *     </tr>
	 *     <tr>
	 *         <td>shapeInfo</td>
	 *         <td>The {@link ShapeInfo} to use on the Button</td>
	 *         <td>Defaults to null</td>
	 *     </tr>
	 * </table>
	 */
	public static class TadukooButtonBuilder{
		/** The text to use on the Button */
		private String text = null;
		/** The icon to use on the Button */
		private Icon icon = null;
		/** The action to perform on click of the Button */
		private ActionListener actionListener = null;
		/** The {@link ShapeInfo} to use on the Button */
		private ShapeInfo shapeInfo = null;
		
		// Can't create outside of Tadukoo Button
		private TadukooButtonBuilder(){ }
		
		/**
		 * @param text The text to use on the Button
		 * @return this, to continue building
		 */
		public TadukooButtonBuilder text(String text){
			this.text = text;
			return this;
		}
		
		/**
		 * @param icon The icon to use on the Button
		 * @return this, to continue building
		 */
		public TadukooButtonBuilder icon(Icon icon){
			this.icon = icon;
			return this;
		}
		
		/**
		 * @param actionListener The action to perform on click of the Button
		 * @return this, to continue building
		 */
		public TadukooButtonBuilder actionListener(ActionListener actionListener){
			this.actionListener = actionListener;
			return this;
		}
		
		/**
		 * @param shapeInfo The {@link ShapeInfo} to use on the Button
		 * @return this, to continue building
		 */
		public TadukooButtonBuilder shapeInfo(ShapeInfo shapeInfo){
			this.shapeInfo = shapeInfo;
			return this;
		}
		
		/**
		 * Builds a {@link TadukooButton}
		 *
		 * @return A newly created {@link TadukooButton}
		 */
		public TadukooButton build(){
			return new TadukooButton(text, icon, actionListener, shapeInfo);
		}
	}
	
	/** The {@link ShapeInfo} to use on the Button */
	private ShapeInfo shapeInfo;
	
	/**
	 * Creates a new Button with the given parameters
	 *
	 * @param text The text to use on the Button
	 * @param icon The icon to use on the Button
	 * @param actionListener The action to perform on click of the Button
	 * @param shapeInfo The {@link ShapeInfo} to use on the Button
	 */
	private TadukooButton(String text, Icon icon, ActionListener actionListener, ShapeInfo shapeInfo){
		super(text, icon);
		if(actionListener != null){
			addActionListener(actionListener);
		}
		// We do it this way because in the parent constructor, this is set via installDefaults in the Tadukoo button UI
		if(shapeInfo != null){
			setShapeInfo(shapeInfo);
		}
	}
	
	/**
	 * @return A new {@link TadukooButtonBuilder} to use to make a {@link TadukooButton}
	 */
	public static TadukooButtonBuilder builder(){
		return new TadukooButtonBuilder();
	}
	
	/** {@inheritDoc} */
	@Override
	public ShapeInfo getShapeInfo(){
		return shapeInfo;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setShapeInfo(ShapeInfo shapeInfo){
		this.shapeInfo = shapeInfo;
	}
	
	/** {@inheritDoc} */
	@Override
	public Insets getInsets(){
		Insets insets;
		Border border = getBorder();
		if(border != null || shapeInfo != null){
			insets = new Insets(0, 0, 0, 0);
			
			// Use border insets if we have one
			if(border != null){
				insets = border.getBorderInsets(this);
			}
			
			// Use Shape insets if we have any
			if(shapeInfo != null){
				Insets shapeInsets = shapeInfo.getShapeInsetsFunc().apply(getX(), getY(), getWidth(), getHeight());
				insets = InsetsUtil.addInsets(insets, shapeInsets);
			}
		}else{
			insets = super.getInsets();
		}
		return insets;
	}
	
	/** {@inheritDoc} */
	@Override
	public Insets getInsets(Insets insets){
		// Reset insets to 0 before starting
		insets = InsetsUtil.zeroInsets(insets);
		
		// Add in border insets if we have one
		Border border = getBorder();
		if(border != null) {
			if(border instanceof AbstractBorder){
				insets = ((AbstractBorder)border).getBorderInsets(this, insets);
			}else{
				insets = border.getBorderInsets(this);
			}
		}
		
		// Add in Shape insets if we have any
		if(shapeInfo != null){
			Insets shapeInsets = shapeInfo.getShapeInsetsFunc().apply(getX(), getY(), getWidth(), getHeight());
			insets = InsetsUtil.addInsets(insets, shapeInsets);
		}
		
		return insets;
	}
}
