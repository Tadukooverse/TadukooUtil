package com.gmail.realtadukoo.util.fileformat;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import com.gmail.realtadukoo.util.FileUtil;

/**
 * A class representing a defined File Format, including multiple versions 
 * (if applicable) to be used in verifying file formatting and in updating 
 * files from older versions to newer ones.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public abstract class FileFormat{
	/** 
	 * This will be updated if the general Tad Format changes, and there will be a way in the future 
	 * to update between Tad Format Versions. Note: This does not directly correspond to the current 
	 * project's version, only to actual formatting changes.
	 */
	public static final int TAD_FORMAT_VERSION_NUM = 1;
	/** The name of this file format */
	private String name;
	/** A mapping of version strings to {@link FileFormatSchema}s */
	private Map<String, FileFormatSchema> schemas;
	
	/**
	 * Constructs a FileFormat. Sends the given {@link Logger} to 
	 * {@link #createSchemas} to be used in logging messages related 
	 * to creating the schemas. 
	 * 
	 * @param logger The Logger to use in logging messages
	 */
	public FileFormat(Logger logger, String name){
		this.name = name;
		schemas = createSchemas(logger);
	}
	
	/**
	 * @return The name of the File Format
	 */
	public final String getName(){
		return name;
	}
	
	/**
	 * Creates the {@link FileFormatSchema}s for this FileFormat, 
	 * mapping them by their respective version strings. Uses the 
	 * given {@link Logger} to log any messages about creating the schemas.
	 * 
	 * @param logger The Logger to use in logging messages
	 * @return The Map of version strings to FileFormatSchemas
	 */
	protected abstract Map<String, FileFormatSchema> createSchemas(Logger logger);
	
	/**
	 * Grabs the {@link FileFormatSchema} for the given version string.
	 * 
	 * @param version The version string to grab the FileFormatSchema for
	 * @return The appropriate FileFormatSchema for the given version string
	 */
	public final FileFormatSchema getSchema(String version){
		return schemas.get(version);
	}
	
	/**
	 * Updates a file of this format (given as the head {@link Node}) from whatever 
	 * old version it was to a newer version (using the given version strings).
	 * 
	 * @param oldFile The file to convert as its head {@link Node}
	 * @param oldVersion The old version string of the file
	 * @param newVersion The new version string to use to update the file
	 * @return The head {@link Node} of the converted file
	 */
	public abstract Node updateFile(Node oldFile, String oldVersion, String newVersion);
	
	/**
	 * Saves the given headNode to the file specified by the given filepath, adding the 
	 * Tad Format Header at the start of the file using the given schema and current FileFormat. 
	 * This will also verify that the file appropriately matches the format.
	 * 
	 * @param logger The Logger to use in logging any messages
	 * @param filepath The path to save the file at
	 * @param headNode The Head content Node
	 * @param schema The FileFormatSchema used to create the Nodes (to be used in Tad Format header)
	 */
	protected final void saveFile(Logger logger, String filepath, Node headNode, FileFormatSchema schema){
		// Generate the format header for this file format + schema
		Node formatHeader = TadFormatNodeHeader.createHeader(this, schema);
		
		// Set the given head Node as the next sibling to the format header
		formatHeader.setNextSibling(headNode);
		
		// Check that the format is correct prior to saving
		boolean matchesFormat = FileFormatSchemaVerification.verifyFileFormat(logger, this, schema, filepath, formatHeader);
		
		// If format doesn't match, throw an IllegalArgumentException
		if(!matchesFormat){
			throw new IllegalArgumentException("The given Nodes do not match the format!");
		}
		
		// Actually save the file
		try{
			FileUtil.writeFile(filepath, formatHeader.fullToString());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		// Check that the format is correct of the saved file
		matchesFormat = FileFormatSchemaVerification.verifyFileFormat(logger, this, schema, filepath);
		
		// If format doesn't match, throw an IllegalArgumentException
		if(!matchesFormat){
			throw new IllegalArgumentException("The saved file doesn't match the format!");
		}
	}
}
