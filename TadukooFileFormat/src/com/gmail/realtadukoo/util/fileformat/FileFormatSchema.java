package com.gmail.realtadukoo.util.fileformat;

import java.util.List;

/**
 * Represents the definition of a {@link FileFormat}. The reason this 
 * is a separate class is to allow for a FileFormat to update and maintain 
 * its old versions to allow for updating from one schema to another.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class FileFormatSchema{
	/** The file extension on files using this schema */
	private String fileExtension;
	/** The {@link FormatNode}s that define this schema */
	private List<FormatNode> formatNodes;
	
	/**
	 * Constructs a FileFormatSchema with the given file extensions and 
	 * {@link FormatNode}s.
	 * 
	 * @param fileExtension The file extension on files using this schema
	 * @param formatNodes The {@link FormatNode}s that define this schema
	 */
	public FileFormatSchema(String fileExtension, List<FormatNode> formatNodes){
		this.fileExtension = fileExtension;
		this.formatNodes = formatNodes;
	}
	
	/**
	 * @return The file extension on files using this schema
	 */
	public String getFileExtension(){
		return fileExtension;
	}
	
	/**
	 * @return The List of {@link FormatNode}s that define this schema
	 */
	public List<FormatNode> getFormatNodes(){
		return formatNodes;
	}
}
