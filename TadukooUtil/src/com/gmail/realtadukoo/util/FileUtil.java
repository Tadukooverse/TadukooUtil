package com.gmail.realtadukoo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
 * @version Pre-Alpha
 */
public class FileUtil{
	
	// Not allowed to create a FileUtil
	private FileUtil(){ }
	
	/**
	 * Creates a List of Strings for each line in the file being read in the 
	 * given {@link Reader}.
	 * 
	 * @param reader The Reader to use in reading
	 * @return A List of lines in the file
	 */
	public static List<String> getLinesAsList(Reader reader) throws IOException{
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
	 * Writes the given string to the file given in the {@link Writer}.
	 * 
	 * @param writer The Writer to use in writing
	 * @param content The content of the file to be written
	 */
	public static void writeFile(Writer writer, String content) throws IOException{
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
	public static void writeFile(Writer writer, Collection<String> lines) throws IOException{
		writeFile(writer, StringUtil.buildStringWithNewLines(lines));
	}
}
