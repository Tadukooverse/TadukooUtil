package com.gmail.realtadukoo.util.annotation.process;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation to be used to mark a class as being an Annotation Processor so 
 * that it can be added to the appropriate META-INF file.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
@Documented
@Retention(SOURCE)
@Target(TYPE)
public @interface AnnotationProcessor{
	
}
