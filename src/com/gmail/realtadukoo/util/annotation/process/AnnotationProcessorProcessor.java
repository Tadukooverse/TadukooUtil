package com.gmail.realtadukoo.util.annotation.process;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.lang.model.element.Element;

import com.gmail.realtadukoo.util.annotation.AnnotationProcessor;

/**
 * Annotation Processor for {@link AnnotationProcessor} annotation. Adds the classes with 
 * the annotation to the META-INF Annotation Processor file, and displays compilation errors 
 * on classes with the annotation that are not Annotation Processors (they must be a sub-class 
 * of {@link AbstractProcessor}).
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
@AnnotationProcessor
public class AnnotationProcessorProcessor extends AbstractAnnotationProcessor{
	
	/**
	 * Constructor to designate this as an annotation processor for {@link AnnotationProcessor}.
	 */
	public AnnotationProcessorProcessor(){
		super(AnnotationProcessor.class);
	}
	
	/**
	 * Processes the {@link Element Elements} that include the {@link AnnotationProcessor} annotation. 
	 * Throws compilation errors on classes that aren't sub-classes of {@link AbstractProcessor} and 
	 * adds the others to the META-INF Annotation Processor file.
	 * 
	 * @param elements The Elements to process
	 */
	@Override
	protected void processElements(Set<? extends Element> elements) throws Throwable{
		String processorListString = "";
		for(Element element: elements){
			if(annotationUtil.isSubType(element, AbstractProcessor.class)){
				processorListString += annotationUtil.getCanonicalClassName(element) + "\n";
			}else{
				annotationUtil.displayCompileError(element, "This class must be a sub-class of AbstractProcessor!");
			}
		}
		
		annotationUtil.writeFile(AnnotationUtil.ANNOTATION_PROCESSOR_FILE, processorListString);
	}
}
