package com.gmail.realtadukoo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Util functions for dealing with Files.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 * @since Pre-Alpha
 */
public final class FileUtil{
	
	// Not allowed to create a FileUtil
	private FileUtil(){ }
	
	/**
	 * Finds the file extension for the given filepath, if one exists.
	 * 
	 * @param filepath The filepath to grab the extension for
	 * @return The found file extension, or null
	 */
	public static final String getFileExtension(String filepath){
		// Find the last dot in the filepath
		int lastDotIndex = filepath.lastIndexOf('.');
		
		// If one isn't found, return null
		if(lastDotIndex == -1){
			return null;
		}else{
			// If we found a dot, return whatever's after it
			return filepath.substring(lastDotIndex + 1);
		}
	}
	
	/**
	 * Creates a file at the given filepath, including any directories necessary, 
	 * and returns the {@link File} object to be used.
	 * 
	 * @param filepath The path for the File to be created
	 * @return The newly created File
	 */
	public static final File createFile(String filepath) throws IOException{
		// Create a File object from the given filepath
		File file = new File(filepath);
		
		// If a directory is specified, create it if it doesn't exist
		File parentFile = file.getParentFile();
		if(parentFile != null && !parentFile.exists()){
			file.getParentFile().mkdirs();
		}
		
		// If the file doesn't exist, create it
		if(!file.exists()){
			file.createNewFile();
		}
		
		return file;
	}
	
	/**
	 * Creates a List of Strings for each line in the file being read in the 
	 * given {@link Reader}.
	 * 
	 * @param reader The Reader to use in reading
	 * @return A List of lines in the file
	 */
	public static final List<String> getLinesAsList(Reader reader) throws IOException{
		// Make a BufferedReader out of the given Reader
		BufferedReader buffReader = new BufferedReader(reader);
		// Create a List of Strings to store the lines
		List<String> lines = new ArrayList<String>();
		
		// Read the first line of the file
		String line = buffReader.readLine();
		
		// Continue to read lines until there are no more
		while(line != null){
			// Add the line to the list
			lines.add(line);
			// Grab the next line
			line = buffReader.readLine();
		}
		return lines;
	}
	
	/**
	 * Writes the given string to the file given by the filepath. 
	 * Will create the file and its directories if they don't exist.
	 * 
	 * @param filepath The path to save the file to
	 * @param content The content of the file to be written
	 */
	public static final void writeFile(String filepath, String content) throws IOException{
		// Create the File
		File file = createFile(filepath);
		
		// Actually write to the file using a FileWriter
		writeFile(new FileWriter(file), content);
	}
	
	/**
	 * Writes the given string to the file given in the {@link Writer}.
	 * 
	 * @param writer The Writer to use in writing
	 * @param content The content of the file to be written
	 */
	public static final void writeFile(Writer writer, String content) throws IOException{
		// Make a BufferedWriter out of the given Writer
		BufferedWriter buffWriter = new BufferedWriter(writer);
		
		// Write the content to the file
		buffWriter.write(content);
		
		// flush and close the writer
		buffWriter.flush();
		buffWriter.close();
	}
	
	/**
	 * Writes the given lines to the file given in the {@link Writer}.
	 * 
	 * @param writer The Writer to use in writing
	 * @param lines The content of the file to be written
	 */
	public static final void writeFile(Writer writer, Collection<String> lines) throws IOException{
		writeFile(writer, StringUtil.buildStringWithNewLines(lines));
	}
}
