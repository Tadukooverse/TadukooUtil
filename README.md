# TadukooUtil
Tadukoo Util is a collection of utilities for creating other interesting projects. They can be used in conjunction with 
[Tadukoo Engine/Launcher](https://tadukooverse.github.io/projects/TadukooEngine.html), or can be used separately in other 
programs if desired. This collection is not meant as a full smorgasbord of all libraries, but is just meant to include 
the most common libraries to be reused in most projects (e.g. common null-safe string/boolean checks, multimaps, 
view utilities, etc.)

#### Table of Contents
* [Modules](#modules)
	* [Tadukoo Annotation Processor](#tadukoo-annotation-processor)
	* [Tadukoo Database](#tadukoo-database)
	* [Tadukoo File Format](#tadukoo-file-format)
	* [Tadukoo Lang](#tadukoo-lang)
	* [Tadukoo Look & Feel](#tadukoo-look--feel)
	* [Tadukoo Util](#tadukoo-util)
	* [Tadukoo View](#tadukoo-view)
* [Current Plans](#current-plans)

## Modules

### Tadukoo Annotation Processor
Tadukoo Annotation Processor makes it easier to create annotation processors.

It contains the @AnnotationProcessor annotation, which should be used in annotation processors so they're properly added 
to the META_INF services file. This annotation is handled by AnnotationProcessorProcessor, which just adds the canonical 
class name to the META_INF/services/javax.annotation.processing.Processor file.

AbstractAnnotationProcessor should be extended by annotation processors you create. It handles the standard methods and 
provides an instance of AnnotationUtil to make common functionality easier for processing annotations.

For more information on creating annotation processors using Tadukoo Annotation Processor, check out the 
[Tadukoo Annotation Processor Guide](https://tadukooverse.github.io/guides/tadukoo-annotation-processor.html)

### Tadukoo Database
Tadukoo Database is used to make interfacing with a MySQL database easier.

### Tadukoo File Format
Tadukoo File Format is used to handle custom file formats.

### Tadukoo Lang
Tadukoo Lang provides common utility functions along with tuples.

### Tadukoo Look & Feel
Tadukoo Look & Feel allows you to customize the look & feel of view components more easily than the default Java look & feels allow for.

### Tadukoo Util
Tadukoo Util provides a collection of useful utilities that don't quite fit into other specific modules in this list.

### Tadukoo View
Tadukoo View provides utilities to make it easier to handle view functionality, such as drawing to the screen and handling images.

## Current Plans
Check out [the project page](https://tadukooverse.github.io/projects/TadukooUtil.html) for information about current plans for Tadukoo Util.
