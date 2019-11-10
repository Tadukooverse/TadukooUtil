package com.gmail.realtadukoo.util.annotation.process;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.gmail.realtadukoo.util.ExceptionUtil;

/**
 * An Abstract Annotation Process to be used in creating new annotation processors in a 
 * simpler way than having them extend {@link AbstractProcessor} themselves.
 * <br>
 * Also includes {@link AnnotationUtil} to make it easier to perform common operations 
 * in processors.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
public abstract class AbstractAnnotationProcessor extends AbstractProcessor{
	/** {@link AnnotationUtil} to use for common annotation processing operations */
	protected AnnotationUtil annotationUtil;
	
	/** The Annotation class this processor is used for */
	private Class<? extends Annotation> annotation;
	
	/**
	 * Calls the AbstractProcessor constructor and sets the Annotation class this 
	 * processor is used for.
	 * 
	 * @param annotation The Annotation class this is for
	 */
	public AbstractAnnotationProcessor(Class<? extends Annotation> annotation){
		super();
		this.annotation = annotation;
	}
	
	/**
	 * Calls the AbstractProcessor init method and then uses the given 
	 * {@link ProcessingEnvironment} to initialize the {@link AnnotationUtil}.
	 * 
	 * @param processingEnvironment The ProcessingEnvironment this is happening in
	 */
	@Override
	public final void init(ProcessingEnvironment processingEnvironment){
		super.init(processingEnvironment);
		annotationUtil = new AnnotationUtil(processingEnvironment);
	}
	
	/**
	 * Returns the Annotation type this is for, by grabbing the name off the 
	 * stored Annotation class.
	 * 
	 * @return The Annotation type this processor is for
	 */
	@Override
	public final Set<String> getSupportedAnnotationTypes(){
		Set<String> annotations = new HashSet<String>();
		annotations.add(annotation.getCanonicalName());
		return annotations;
	}
	
	/**
	 * Processes the annotation on the elements it's attached to. 
	 * Ultimately calls {@link #processElements} to do the actual processing. 
	 * If {@link #processElements} throws anything, it throws a compile error 
	 * on the relevant annotation with the stack trace.
	 * 
	 * @param annotations The annotations to process (should just be the one this processor is for)
	 * @param roundEnv The RoundEnvironment used for grabbing the elements
	 * @return True
	 */
	@Override
	public final boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){
		if(!roundEnv.processingOver()){
			for(TypeElement te : annotations){
				try{
					processElements(roundEnv.getElementsAnnotatedWith(te));
				}catch(Throwable t){
					String error = "Failed to process annotation " + annotation.getCanonicalName() + ":\n";
					error += ExceptionUtil.getStackTraceAsString(t);
					annotationUtil.displayCompileError(te, error);
				}
			}
		}
		return true;
	}
	
	/**
	 * Actually handle processing the given elements for the annotation this handles.
	 * 
	 * @param elements The Set of elements to process for the annotation
	 */
	protected abstract void processElements(Set<? extends Element> elements) throws Throwable;
}
