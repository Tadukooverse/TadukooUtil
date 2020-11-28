package com.github.tadukoo.util.view.form.field.number;

import com.github.tadukoo.util.view.form.field.FieldType;
import com.github.tadukoo.util.view.form.field.LabelType;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class NumberFormFieldTest{
	
	private static class TestFormField extends NumberFormField<Integer>{
		
		private TestFormField(FieldType type, String key, Integer defaultValue, LabelType labelType,
		                      int rowPos, int colPos, int rowSpan, int colSpan,
		                      Integer minValue, Integer maxValue, Integer stepSize){
			super(type, key, defaultValue, labelType, rowPos, colPos, rowSpan, colSpan, minValue, maxValue, stepSize);
		}
		
		@Override
		protected Integer convertToType(Number number){
			return number.intValue();
		}
	}
	
	private static class TestFormFieldBuilder extends NumberFormField.NumberFormFieldBuilder<Integer>{
		
		@Override
		public NumberFormField<Integer> build(){
			return new TestFormField(FieldType.INT, key, defaultValue, labelType,
					rowPos, colPos, rowSpan, colSpan,
					minValue, maxValue, stepSize);
		}
	}
	
	private NumberFormField<Integer> field = new TestFormFieldBuilder().stepSize(54).build();
	
	@Test
	public void testDefaults(){
		assertNull(field.getMinValue());
		assertNull(field.getMaxValue());
	}
	
	@Test
	public void testSettings(){
		assertEquals(Integer.valueOf(54), field.getStepSize());
	}
	
	@Test
	public void testGetComponent(){
		field = (NumberFormField<Integer>) new TestFormFieldBuilder().minValue(20).maxValue(105)
				.stepSize(54).defaultValue(42).build();
		
		JComponent component = field.getComponent();
		assertTrue(component instanceof JSpinner);
		SpinnerModel model = ((JSpinner) component).getModel();
		assertTrue(model instanceof SpinnerNumberModel);
		assertEquals(20, ((SpinnerNumberModel) model).getMinimum());
		assertEquals(105, ((SpinnerNumberModel) model).getMaximum());
		assertEquals(54, ((SpinnerNumberModel) model).getStepSize());
	}
	
	@Test
	public void testGetValue(){
		assertEquals(Integer.valueOf(120),
				field.getValue(new JSpinner(
						new SpinnerNumberModel(120, null, null, 54))));
	}
	
	@Test
	public void testGetValueNotSpinner(){
		assertNull(field.getValue(new JLabel("Test")));
	}
}
