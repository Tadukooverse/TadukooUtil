package com.github.tadukoo.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
	public static String getFileExtension(String filepath){
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
	 * Creates a List of all Files in the given directory and any of 
	 * its sub-directories.
	 * 
	 * @param directoryPath The path to the directory to check
	 * @return A List of all Files in the directory and its sub-directories
	 */
	public static List<File> listAllFiles(String directoryPath) throws IOException{
		return Files.walk(Paths.get(directoryPath))
				.filter(Files::isRegularFile)
				.map(Path::toFile)
				.collect(Collectors.toList());
	}
	
	/**
	 * Creates a List of all Files in the given directory and any of its 
	 * sub-directories.
	 * 
	 * @param directory The directory (as a File) to check
	 * @return A List of all Files in the directory and its sub-directories
	 */
	public static List<File> listAllFiles(File directory) throws IOException{
		return listAllFiles(directory.getPath());
	}
	
	/**
	 * Creates a file at the given filepath, including any directories necessary, 
	 * and returns the {@link File} object to be used.
	 * 
	 * @param filepath The path for the File to be created
	 * @return The newly created File
	 */
	public static File createFile(String filepath) throws IOException{
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
	public static List<String> getLinesAsList(Reader reader) throws IOException{
		// Make a BufferedReader out of the given Reader
		BufferedReader buffReader = new BufferedReader(reader);
		// Create a List of Strings to store the lines
		List<String> lines = new ArrayList<>();
		
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
	public static void writeFile(String filepath, String content) throws IOException{
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
	
	/**
	 * Creates a zip file using the file or directory at the given path.
	 *
	 * @param pathToZip The path to the file or directory to be zipped
	 * @param zipPath The path (and name) of the zip file to be created
	 * @throws IOException If anything goes wrong
	 */
	public static void zipFile(String pathToZip, String zipPath) throws IOException{
		zipFile(new File(pathToZip), zipPath);
	}
	
	/**
	 * Creates a zip file using the given File (can be a file or directory).
	 *
	 * @param fileToZip The File to be zipped (can be file or directory)
	 * @param zipPath The path (and name) of the zip file to be created
	 * @throws IOException If anything goes wrong
	 */
	public static void zipFile(File fileToZip, String zipPath) throws IOException{
		// Setup
		FileOutputStream fos = new FileOutputStream(zipPath);
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		// Call helper method (which handles if it's a directory)
		zipFile(fileToZip, fileToZip.getName(), zos);
		zos.close();
		fos.close();
	}
	
	/**
	 * Creates a zip file using the given File (can be a file or directory), fileName for the file to be zipped,
	 * and a ZipOutputStream to be used. Each call to this method creates a single entry in the zip file, and
	 * if it's a directory, it will make recursive calls for all children of the directory.
	 *
	 * @param fileToZip The File to be zipped (can be file or directory)
	 * @param fileName The name of the file to be zipped
	 * @param zos The ZipOutputStream to be used in zipping
	 * @throws IOException If anything goes wrong
	 */
	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException{
		if(fileToZip.isHidden()){
			return;
		}
		
		// Handle directories differently
		if(fileToZip.isDirectory()){
			// If we're missing the ending /, add it
			if(!fileName.endsWith("/")){
				fileName += "/";
			}
			
			// Add the directory as a zip entry
			zos.putNextEntry(new ZipEntry(fileName));
			zos.closeEntry();
			
			// Grab the children of the directory
			File[] children = fileToZip.listFiles();
			if(children == null){
				return;
			}
			
			// Add all the children to the zip
			for(File childFile: children){
				zipFile(childFile, fileName + "/" + childFile.getName(), zos);
			}
			return;
		}
		
		// If it's not a directory, just zip the single file
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while((length = fis.read(bytes)) >= 0){
			zos.write(bytes, 0, length);
		}
		fis.close();
	}
	
	/**
	 * Unzips a zip file into the given destination path.
	 *
	 * @param zipPath The path to the zip file
	 * @param destinationPath The path to extract the zip file contents to
	 * @throws IOException If anything goes wrong
	 */
	public static void unzipFile(String zipPath, String destinationPath) throws IOException{
		unzipFile(zipPath, new File(destinationPath));
	}
	
	/**
	 * Unzips a zip file into the given destination directory.
	 *
	 * @param zipPath The path to the zip file
	 * @param destDirectory The directory to extract the zip file contents to
	 * @throws IOException If anything goes wrong
	 */
	public static void unzipFile(String zipPath, File destDirectory) throws IOException{
		// Setup
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath));
		ZipEntry zipEntry = zis.getNextEntry();
		
		// Extract each file
		while(zipEntry != null){
			File newFile = new File(destDirectory, zipEntry.getName());
			
			
			// Check destination path to prevent zip slip
			String destDirPath = destDirectory.getCanonicalPath();
			String destFilePath = newFile.getCanonicalPath();
			if(!destFilePath.startsWith(destDirPath + File.separator)){
				throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
			}
			
			// If it's a directory, make the directory instead of a file
			if(zipEntry.isDirectory()){
				if(!newFile.mkdirs() && !newFile.exists()){
					throw new IOException("Failed to create new directory at " + newFile.getAbsolutePath());
				}
			}else{
				// Write the new file
				FileOutputStream fos = new FileOutputStream(newFile);
				int length;
				while((length = zis.read(buffer)) > 0){
					fos.write(buffer, 0, length);
				}
				
				// Close the new file and prepare for next one
				fos.close();
			}
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}
}
