package com.gmail.realtadukoo.util.annotation.process;

import java.util.Set;

import javax.lang.model.element.Element;

import com.gmail.realtadukoo.util.annotation.ShouldBeFinal;

/**
 * Annotation Processor for {@link ShouldBeFinal} that gives a warning message on 
 * methods or fields that include the annotation.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
@AnnotationProcessor
public class ShouldBeFinalProcessor extends AbstractAnnotationProcessor{
	
	/**
	 * Constructor to designate this as being an Annotation Processor for {@link ShouldBeFinal}.
	 */
	public ShouldBeFinalProcessor(){
		super(ShouldBeFinal.class);
	}
	
	/**
	 * Processes the given {@link Element Elements}, putting the warning on them that the 
	 * method/field should be marked as final soon.
	 * 
	 * @param elements The Elements to process
	 */
	@Override
	protected void processElements(Set<? extends Element> elements){
		for(Element element : elements){
			annotationUtil.displayCompileWarning(element, "This method should be marked as final soon");
		}
	}
}
