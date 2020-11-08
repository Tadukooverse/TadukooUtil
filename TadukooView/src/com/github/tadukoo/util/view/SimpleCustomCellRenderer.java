package com.github.tadukoo.util.view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Custom Cell Renderer used to just have a component draw itself (for use with custom components).
 * This can be used as either a {@link ListCellRenderer} or as a {@link TableCellRenderer}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public class SimpleCustomCellRenderer extends JComponent implements ListCellRenderer<JComponent>, TableCellRenderer{
	
	/** {@inheritDoc} */
	@Override
	public Component getListCellRendererComponent(JList list, JComponent value, int index, boolean isSelected,
	                                              boolean cellHasFocus){
		// Literally just send the JComponent itself as it should know how to draw itself
		return value;
	}
	
	/** {@inheritDoc} */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	                                               int row, int column){
		// Literally just send the JComponent itself back as it should know how to draw itself
		return (JComponent) value;
	}
}
