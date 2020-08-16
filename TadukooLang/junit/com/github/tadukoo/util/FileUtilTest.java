package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilTest{
	
	@Test
	public void testGetFileExtension(){
		String extension = FileUtil.getFileExtension("test.txt");
		assertEquals("txt", extension);
	}
	
	@Test
	public void testGetFileExtensionMultipleDots(){
		String extension = FileUtil.getFileExtension("test.kbwc.card");
		assertEquals("card", extension);
	}
	
	@Test
	public void testGetFileExtensionNoExtension(){
		String extension = FileUtil.getFileExtension("something with no dot");
		assertNull(extension);
	}
	
	@Test
	public void createAndListAllFilesTest() throws IOException{
		File file = FileUtil.createFile("target/test-files/createAndListTest/test.txt");
		assertNotNull(file);
		
		List<File> files = FileUtil.listAllFiles("target/test-files/createAndListTest/");
		assertEquals(1, files.size());
		assertEquals(file, files.get(0));
	}
	
	@Test
	public void createAndListMultipleFilesTest() throws IOException{
		String folder = "target/test-files/createAndListBigTest/";
		File file = FileUtil.createFile(folder + "test1.txt");
		File file2 = FileUtil.createFile(folder + "test2.txt");
		File file3 = FileUtil.createFile(folder + "test3.txt");
		File file4 = FileUtil.createFile(folder + "test4.txt");
		File file5 = FileUtil.createFile(folder + "test5.txt");
		
		List<File> files = FileUtil.listAllFiles(folder);
		assertEquals(5, files.size());
		assertTrue(files.containsAll(Arrays.asList(file, file2, file3, file4, file5)));
	}
	
	@Test
	public void createFileDoesNotExist() throws IOException{
		String folder = "target/test-files/createFileDoesNotExist/";
		String filepath = folder + "test.txt";
		
		// Delete file if it exists
		File file = new File(filepath);
		if(file.exists()){
			assertTrue(file.delete());
			assertFalse(file.exists());
		}
		
		// Check that file is created successfully
		file = FileUtil.createFile(filepath);
		assertNotNull(file);
		List<File> files = FileUtil.listAllFiles(folder);
		assertEquals(1, files.size());
		assertEquals(file, files.get(0));
	}
	
	@Test
	public void createFileFolderDoesNotExist() throws IOException{
		String folder = "target/test-files/createFileFolderDoesNotExist/";
		String filepath = folder + "test.txt";
		
		// Delete the file and folder if they exist
		File file = new File(filepath);
		File directory = file.getParentFile();
		// Must delete file first (directory must be empty)
		if(file.exists()){
			assertTrue(file.delete());
			assertFalse(file.exists());
		}
		if(directory.exists()){
			assertTrue(directory.delete());
			assertFalse(directory.exists());
		}
		
		// Check that file is created successfully
		file = FileUtil.createFile(filepath);
		assertNotNull(file);
		List<File> files = FileUtil.listAllFiles(directory);
		assertEquals(1, files.size());
		assertEquals(file, files.get(0));
	}
	
	@Test
	public void testWriteFileWithPath() throws IOException{
		String filepath = "target/test-files/writeFileWithPath/test.txt";
		FileUtil.writeFile(filepath, "Test\nDerp\nYes");
		
		List<String> lines = FileUtil.getLinesAsList(new BufferedReader(new FileReader(filepath)));
		assertEquals(3, lines.size());
		assertEquals("Test", lines.get(0));
		assertEquals("Derp", lines.get(1));
		assertEquals("Yes", lines.get(2));
	}
	
	@Test
	public void testWriteFileWithWriter() throws IOException{
		String filepath = "target/test-files/writeFileWithWriter/test.txt";
		FileUtil.createFile(filepath);
		FileUtil.writeFile(new FileWriter(filepath), "Test\nDerp\nYes");
		
		List<String> lines = FileUtil.getLinesAsList(new BufferedReader(new FileReader(filepath)));
		assertEquals(3, lines.size());
		assertEquals("Test", lines.get(0));
		assertEquals("Derp", lines.get(1));
		assertEquals("Yes", lines.get(2));
	}
	
	@Test
	public void testWriteFileWithLines() throws IOException{
		String filepath = "target/test-files/writeFileWithPath/test.txt";
		FileUtil.createFile(filepath);
		List<String> inputLines = Arrays.asList("Test", "Derp", "Yes");
		FileUtil.writeFile(new FileWriter(filepath), inputLines);
		
		List<String> lines = FileUtil.getLinesAsList(new BufferedReader(new FileReader(filepath)));
		assertEquals(3, lines.size());
		assertEquals("Test", lines.get(0));
		assertEquals("Derp", lines.get(1));
		assertEquals("Yes", lines.get(2));
	}
}
