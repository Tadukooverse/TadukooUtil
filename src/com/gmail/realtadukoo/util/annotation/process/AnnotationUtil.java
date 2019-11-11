package com.gmail.realtadukoo.util.annotation.process;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * A collection of common functions to be used by Annotation Processors.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
public class AnnotationUtil{
	/** The file path for the Annotation Processor META-INF file */
	public static final String ANNOTATION_PROCESSOR_FILE = "META-INF/services/javax.annotation.processing.Processor";
	
	/** The {@link Elements Element Util} for util methods related to {@link Element Elements} */
	private Elements elementUtil;
	/** The {@link Types Type Util} for util methods related to {@link TypeMirror Types} */
	private Types typeUtil;
	/** The {@link Messager} for sending messages to the compiler */
	private Messager messager;
	/** The {@link Filer} for creating and editing files */
	private Filer filer;
	
	/**
	 * Grabs the {@link #elementUtil}, {@link #typeUtil}, {@link #messager}, and 
	 * {@link #filer} off the given {@link ProcessingEnvironment} and stores them for 
	 * later use in the various util methods in this class.
	 * 
	 * @param processingEnv The ProcessingEnvironment to grab stuff from
	 */
	public AnnotationUtil(ProcessingEnvironment processingEnv){
		elementUtil = processingEnv.getElementUtils();
		typeUtil = processingEnv.getTypeUtils();
		messager = processingEnv.getMessager();
		filer = processingEnv.getFiler();
	}
	
	/**
	 * Grabs the Canonical Class Name of the given Element.
	 * 
	 * @param element The element to get the canonical class name of
	 * @return The canonical class name of the given Element
	 */
	public String getCanonicalClassName(Element element){
		return getTypeElement(element).getQualifiedName().toString();
	}
	
	/**
	 * Grabs the {@link TypeElement} represented by the given canonical 
	 * class name.
	 * 
	 * @param canonicalClassName The canonical class name of the class to get a TypeElement of
	 * @return The TypeElement representing the class specified
	 */
	public TypeElement getTypeElement(CharSequence canonicalClassName){
		return elementUtil.getTypeElement(canonicalClassName);
	}
	
	/**
	 * Casts the given {@link Element} as a {@link TypeElement} if it is an 
	 * instance of one.
	 * 
	 * @param element The Element to get as a TypeElement
	 * @return The Element cast as a TypeElement, or null if it's not an instanceof TypeElement
	 */
	public TypeElement getTypeElement(Element element){
		if(element instanceof TypeElement){
			return (TypeElement) element;
		}else{
			return null;
		}
		
	}
	
	/**
	 * Grabs a {@link TypeElement} representing the given {@link Class}.
	 * 
	 * @param clazz The Class to get as a TypeElement
	 * @return A TypeElement representing the given Class
	 */
	public TypeElement getTypeElement(Class<?> clazz){
		return getTypeElement(clazz.getCanonicalName());
	}
	
	/**
	 * Grabs the {@link TypeMirror} of the given {@link TypeElement}. 
	 * If the given TypeElement is null, it will return null.
	 * 
	 * @param typeElement The TypeElement to get the TypeMirror of
	 * @return The TypeMirror for the given TypeElement, or null if the TypeElement is null
	 */
	public TypeMirror getType(TypeElement typeElement){
		if(typeElement == null){
			return null;
		}
		return typeElement.asType();
	}
	
	/**
	 * Grabs the {@link TypeMirror} of the given {@link Element}, if it can be 
	 * converted to a TypeMirror (may return null).
	 * 
	 * @param element The Element to grab the TypeMirror of
	 * @return The TypeMirror of the given Element, or null
	 */
	public TypeMirror getType(Element element){
		return getType(getTypeElement(element));
	}
	
	/**
	 * Grabs a {@link TypeMirror} representing the given {@link Class}.
	 * 
	 * @param clazz The Class to get a TypeMirror for
	 * @return A TypeMirror representing the given Class
	 */
	public TypeMirror getType(Class<?> clazz){
		return getType(getTypeElement(clazz));
	}
	
	/**
	 * Checks if the given {@link Element} is a subtype of the given {@link Class}.
	 * 
	 * @param element The Element that may be a subtype of the Class
	 * @param clazz The Class that may be a supertype of the Element
	 * @return If the Element is a subtype of the Class or not
	 */
	public boolean isSubType(Element element, Class<?> clazz){
		TypeMirror elementType = getType(element);
		TypeMirror clazzType = getType(clazz);
		return typeUtil.isSubtype(elementType, clazzType);
	}
	
	/**
	 * Displays a compile warning for the given {@link Element} with the given 
	 * warning message.
	 * 
	 * @param element The Element to show the warning on
	 * @param warning The warning message to display
	 */
	public void displayCompileWarning(Element element, String warning){
		messager.printMessage(Kind.WARNING, warning, element);
	}
	
	/**
	 * Displays a compile error for the given {@link Element} with the given 
	 * error message.
	 * 
	 * @param element The Element to show the error on
	 * @param error The error message to display
	 */
	public void displayCompileError(Element element, String error){
		messager.printMessage(Kind.ERROR, error, element);
	}
	
	/**
	 * Creates a new file (as a {@link FileObject}) using the given filename/path.
	 * 
	 * @param filename The file name/path to use
	 * @return A FileObject for the new file
	 */
	public FileObject createFile(String filename) throws IOException{
		return filer.createResource(StandardLocation.CLASS_OUTPUT, "", filename);
	}
	
	/**
	 * Grabs the file (as a {@link FileObject}) specified by the filename/path.
	 * 
	 * @param filename The file name/path to use
	 * @return A FileObject for the existing file
	 */
	public FileObject getExistingFile(String filename) throws IOException{
		return filer.getResource(StandardLocation.CLASS_OUTPUT, "", filename);
	}
	
	/**
	 * Grabs the existing file specified using {@link #getExistingFile} and 
	 * then opens a {@link Reader} for it and returns it.
	 * 
	 * @param filename The file name/path to use
	 * @return A Reader for the existing file
	 */
	public Reader getFileReader(String filename) throws IOException{
		FileObject file = getExistingFile(filename);
		return file.openReader(true);
	}
	
	/**
	 * Creates a new file using {@link #createFile} and then opens a 
	 * {@link Writer} for it and returns it.
	 * <br><br>
	 * <b>Note</b>: This will overwrite the file if it already exists.
	 * 
	 * @param filename The file name/path to use
	 * @return A Writer for the newly created file
	 */
	public Writer getFileWriter(String filename) throws IOException{
		FileObject file = createFile(filename);
		return file.openWriter();
	}
}
