package com.github.tadukoo.util.view.form;

import com.github.tadukoo.util.view.form.field.FormField;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract Form is the default implementation of {@link Form}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public abstract class AbstractForm extends JPanel implements Form{
	/** The map of actual values on this form */
	private final Map<String, Object> valueMap;
	/** The map of {@link FormField}s on this form */
	private final Map<String, FormField<?>> fields;
	/** The map of {@link JComponent components} on this form */
	private final Map<String, JComponent> components;
	
	protected AbstractForm(Map<String, Object> defaultValues){
		// Initialize the maps
		valueMap = defaultValues;
		fields = new HashMap<>();
		components = new HashMap<>();
		
		// Initialize fields and components
		setDefaultFields();
		createComponents();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean hasKey(String key){
		return valueMap.containsKey(key);
	}
	
	/** {@inheritDoc} */
	@Override
	public Set<String> getKeys(){
		return valueMap.keySet();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean hasItem(String key){
		return valueMap.get(key) != null;
	}
	
	/** {@inheritDoc} */
	@Override
	public Object getItem(String key){
		return valueMap.get(key);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setItem(String key, Object item){
		valueMap.put(key, item);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeItem(String key){
		valueMap.remove(key);
	}
	
	/** {@inheritDoc} */
	@Override
	public Map<String, Object> getMap(){
		return valueMap;
	}
	
	/** {@inheritDoc} */
	@Override
	public void addField(FormField<?> field){
		String key = field.getKey();
		setItem(key, field.getDefaultValue());
		fields.put(key, field);
	}
	
	/** {@inheritDoc} */
	@Override
	public void createComponents(){
		// Use GridBayLayout for this panel
		setLayout(new GridBagLayout());
		
		// Determine if we're placing labels above or to the left of components
		boolean topLabels = labelsOnTop();
		
		for(String key: fields.keySet()){
			// Grab the field and its grid information
			FormField<?> field = fields.get(key);
			int rowPos = field.getRowPos();
			int colPos = field.getColPos();
			int rowSpan = field.getRowSpan();
			int colSpan = field.getColSpan();
			
			JComponent component = field.getComponent();
			
			// Add a Label if the Field includes it
			switch(field.getLabelType()){
				case LABEL -> {
					GridBagConstraints labelCons = new GridBagConstraints();
					labelCons.gridy = topLabels?rowPos*2:rowPos;
					labelCons.gridx = topLabels?colPos:colPos*2;
					labelCons.gridheight = rowSpan;
					labelCons.gridwidth = colSpan;
					labelCons.anchor = topLabels?GridBagConstraints.SOUTH:GridBagConstraints.EAST;
					labelCons.insets = topLabels?new Insets(5, 0, 5, 0):new Insets(0, 5, 0, 5);
					JLabel label = new JLabel(key);
					label.setHorizontalTextPosition(JLabel.RIGHT);
					add(label, labelCons);
				}
				case TITLED_BORDER -> component.setBorder(BorderFactory.createTitledBorder(key));
			}
			
			// Add the Component and its constraints
			GridBagConstraints compCons = new GridBagConstraints();
			compCons.gridy = topLabels?rowPos*2+1:rowPos;
			compCons.gridx = topLabels?colPos:colPos*2+1;
			compCons.gridheight = rowSpan;
			compCons.gridwidth = colSpan;
			add(component, compCons);
			
			// Add the component to the map
			components.put(key, component);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public void saveValues(){
		// Iterate through all the components and save the values
		for(String key: components.keySet()){
			JComponent component = components.get(key);
			FormField<?> field = fields.get(key);
			Object value = field.getValue(component);
			valueMap.put(key, value);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public JComponent getComponentByKey(String key){
		return components.get(key);
	}
}
