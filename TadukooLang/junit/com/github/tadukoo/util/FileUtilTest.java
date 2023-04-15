package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	public void createAndListAllFilesAsPathsTest() throws IOException{
		File file = FileUtil.createFile("target/test-files/createAndListTest/test.txt");
		assertNotNull(file);
		
		List<Path> files = FileUtil.listAllFilesAsPaths("target/test-files/createAndListTest/");
		assertEquals(1, files.size());
		assertEquals(file, files.get(0).toFile());
	}
	
	@Test
	public void createAndListMultipleFilesAsPathsTest() throws IOException{
		String folder = "target/test-files/createAndListBigTest/";
		File file = FileUtil.createFile(folder + "test1.txt");
		File file2 = FileUtil.createFile(folder + "test2.txt");
		File file3 = FileUtil.createFile(folder + "test3.txt");
		File file4 = FileUtil.createFile(folder + "test4.txt");
		File file5 = FileUtil.createFile(folder + "test5.txt");
		
		List<Path> paths = FileUtil.listAllFilesAsPaths(folder);
		assertEquals(5, paths.size());
		List<File> files = paths.stream().map(Path::toFile).toList();
		assertTrue(files.containsAll(Arrays.asList(file, file2, file3, file4, file5)));
	}
	
	@Test
	public void createAndListAllFilesAsPathsFileTest() throws IOException{
		File file = FileUtil.createFile("target/test-files/createAndListTest/test.txt");
		assertNotNull(file);
		
		List<Path> files = FileUtil.listAllFilesAsPaths(new File("target/test-files/createAndListTest/"));
		assertEquals(1, files.size());
		assertEquals(file, files.get(0).toFile());
	}
	
	@Test
	public void createAndListMultipleFilesAsPathsFileTest() throws IOException{
		String folder = "target/test-files/createAndListBigTest/";
		File file = FileUtil.createFile(folder + "test1.txt");
		File file2 = FileUtil.createFile(folder + "test2.txt");
		File file3 = FileUtil.createFile(folder + "test3.txt");
		File file4 = FileUtil.createFile(folder + "test4.txt");
		File file5 = FileUtil.createFile(folder + "test5.txt");
		
		List<Path> paths = FileUtil.listAllFilesAsPaths(new File(folder));
		assertEquals(5, paths.size());
		List<File> files = paths.stream().map(Path::toFile).toList();
		assertTrue(files.containsAll(Arrays.asList(file, file2, file3, file4, file5)));
	}
	
	@Test
	public void createAndListAllFilesAsPathsPathTest() throws IOException{
		File file = FileUtil.createFile("target/test-files/createAndListTest/test.txt");
		assertNotNull(file);
		
		List<Path> files = FileUtil.listAllFilesAsPaths(Path.of("target/test-files/createAndListTest/"));
		assertEquals(1, files.size());
		assertEquals(file, files.get(0).toFile());
	}
	
	@Test
	public void createAndListMultipleFilesAsPathsPathTest() throws IOException{
		String folder = "target/test-files/createAndListBigTest/";
		File file = FileUtil.createFile(folder + "test1.txt");
		File file2 = FileUtil.createFile(folder + "test2.txt");
		File file3 = FileUtil.createFile(folder + "test3.txt");
		File file4 = FileUtil.createFile(folder + "test4.txt");
		File file5 = FileUtil.createFile(folder + "test5.txt");
		
		List<Path> paths = FileUtil.listAllFilesAsPaths(Path.of(folder));
		assertEquals(5, paths.size());
		List<File> files = paths.stream().map(Path::toFile).toList();
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
	public void testDeleteFile() throws IOException{
		String folder = "target/testDeleteFile";
		FileUtil.createDirectory(folder);
		String filepath = folder + "/test.txt";
		File file = FileUtil.createFile(filepath);
		assertTrue(file.exists());
		FileUtil.deleteFile(filepath);
		assertFalse(file.exists());
	}
	
	@Test
	public void testCreateDirectory(){
		String folderPath = "target/testCreateDirectory";
		
		// Delete folder if it exists
		File folder = new File(folderPath);
		if(folder.exists()){
			assertTrue(folder.delete());
			assertFalse(folder.exists());
		}
		
		folder = FileUtil.createDirectory(folderPath);
		assertTrue(folder.exists());
		assertEquals("testCreateDirectory", folder.getName());
	}
	
	@Test
	public void testDeleteDirectory() throws IOException{
		String folderPath = "target/testDeleteDirectory";
		File folder = FileUtil.createDirectory(folderPath);
		File file1 = FileUtil.createFile(folder + "/test.txt");
		File file2 = FileUtil.createFile(folder + "/test2.txt");
		assertTrue(folder.exists());
		assertTrue(file1.exists());
		assertTrue(file2.exists());
		FileUtil.deleteDirectory(folderPath);
		assertFalse(folder.exists());
		assertFalse(file1.exists());
		assertFalse(file2.exists());
	}
	
	@Test
	public void testFileExistsPathTrue() throws IOException{
		String filepath = "target/testFileExistsPathTrue.txt";
		FileUtil.writeFile(filepath, "test-content");
		
		assertTrue(FileUtil.exists(filepath));
	}
	
	@Test
	public void testFileExistsPathFalse(){
		String filepath = "target/testFileExistsPathFalse.txt";
		assertFalse(FileUtil.exists(filepath));
	}
	
	@Test
	public void testFileExistsFileTrue() throws IOException{
		String filepath = "target/testFileExistsFileTrue.txt";
		FileUtil.writeFile(filepath, "test-content");
		
		assertTrue(FileUtil.exists(new File(filepath)));
	}
	
	@Test
	public void testFileExistsFileFalse(){
		String filepath = "target/testFileExistsFileFalse.txt";
		assertFalse(FileUtil.exists(filepath));
	}
	
	@Test
	public void testFileNotExistsPathTrue(){
		String filepath = "target/testFileNotExistsPathTrue.txt";
		assertTrue(FileUtil.notExists(filepath));
	}
	
	@Test
	public void testFileNotExistsPathFalse() throws IOException{
		String filepath = "target/testFileNotExistsPathFalse.txt";
		FileUtil.writeFile(filepath, "test-content");
		
		assertFalse(FileUtil.notExists(filepath));
	}
	
	@Test
	public void testFileNotExistsFileTrue(){
		String filepath = "target/testFileNotExistsFileTrue.txt";
		assertTrue(FileUtil.notExists(new File(filepath)));
	}
	
	@Test
	public void testFileNotExistsFileFalse() throws IOException{
		String filepath = "target/testFileNotExistsFileFalse.txt";
		FileUtil.writeFile(filepath, "test-content");
		
		assertFalse(FileUtil.notExists(new File(filepath)));
	}
	
	@Test
	public void testReadAsBytesFilepath() throws IOException{
		byte[] bytes = FileUtil.readAsBytes("junit-resource/test.txt");
		assertEquals(11, bytes.length);
		assertEquals(ByteUtil.parseByte("01110011"), bytes[0]);
		assertEquals(ByteUtil.parseByte("01101001"), bytes[1]);
		assertEquals(ByteUtil.parseByte("01101101"), bytes[2]);
		assertEquals(ByteUtil.parseByte("01110000"), bytes[3]);
		assertEquals(ByteUtil.parseByte("01101100"), bytes[4]);
		assertEquals(ByteUtil.parseByte("01100101"), bytes[5]);
		assertEquals(ByteUtil.parseByte("00100000"), bytes[6]);
		assertEquals(ByteUtil.parseByte("01110100"), bytes[7]);
		assertEquals(ByteUtil.parseByte("01100101"), bytes[8]);
		assertEquals(ByteUtil.parseByte("01110011"), bytes[9]);
		assertEquals(ByteUtil.parseByte("01110100"), bytes[10]);
	}
	
	@Test
	public void testReadAsBytesFile() throws IOException{
		byte[] bytes = FileUtil.readAsBytes(new File("junit-resource/test.txt"));
		assertEquals(11, bytes.length);
		assertEquals(ByteUtil.parseByte("01110011"), bytes[0]);
		assertEquals(ByteUtil.parseByte("01101001"), bytes[1]);
		assertEquals(ByteUtil.parseByte("01101101"), bytes[2]);
		assertEquals(ByteUtil.parseByte("01110000"), bytes[3]);
		assertEquals(ByteUtil.parseByte("01101100"), bytes[4]);
		assertEquals(ByteUtil.parseByte("01100101"), bytes[5]);
		assertEquals(ByteUtil.parseByte("00100000"), bytes[6]);
		assertEquals(ByteUtil.parseByte("01110100"), bytes[7]);
		assertEquals(ByteUtil.parseByte("01100101"), bytes[8]);
		assertEquals(ByteUtil.parseByte("01110011"), bytes[9]);
		assertEquals(ByteUtil.parseByte("01110100"), bytes[10]);
	}
	
	@Test
	public void testWriteFileWithPath() throws IOException{
		String filepath = "target/test-files/writeFileWithPath/test.txt";
		FileUtil.writeFile(filepath, "Test\nDerp\nYes");
		
		List<String> lines = FileUtil.readLinesAsList(FileUtil.setupFileReader(filepath));
		assertEquals(3, lines.size());
		assertEquals("Test", lines.get(0));
		assertEquals("Derp", lines.get(1));
		assertEquals("Yes", lines.get(2));
	}
	
	@Test
	public void testWriteFileWithPathReadWithPath() throws IOException{
		String filepath = "target/test-files/writeFileWithPathReadWithPath/test.txt";
		FileUtil.writeFile(filepath, "Test\nDerp\nYes");
		
		List<String> lines = FileUtil.readLinesAsList(filepath);
		assertEquals(3, lines.size());
		assertEquals("Test", lines.get(0));
		assertEquals("Derp", lines.get(1));
		assertEquals("Yes", lines.get(2));
	}
	
	@Test
	public void testWriteFileWithPathReadWithFile() throws IOException{
		String filepath = "target/test-files/writeFileWithPathReadWithFile/test.txt";
		FileUtil.writeFile(filepath, "Test\nDerp\nYes");
		
		List<String> lines = FileUtil.readLinesAsList(new File(filepath));
		assertEquals(3, lines.size());
		assertEquals("Test", lines.get(0));
		assertEquals("Derp", lines.get(1));
		assertEquals("Yes", lines.get(2));
	}
	
	@Test
	public void testWriteFileWithPathReadWithPathAsString() throws IOException{
		String filepath = "target/test-files/writeFileWithPathReadWithPathAsString/test.txt";
		FileUtil.writeFile(filepath, "Test\nDerp\nYes");
		
		String content = FileUtil.readAsString(filepath);
		assertEquals("Test\nDerp\nYes", content);
	}
	
	@Test
	public void testWriteFileWithPathReadWithFileAsString() throws IOException{
		String filepath = "target/test-files/writeFileWithPathReadWithFileAsString/test.txt";
		FileUtil.writeFile(filepath, "Test\nDerp\nYes");
		
		String content = FileUtil.readAsString(new File(filepath));
		assertEquals("Test\nDerp\nYes", content);
	}
	
	@Test
	public void testWriteFileWithWriter() throws IOException{
		String filepath = "target/test-files/writeFileWithWriter/test.txt";
		FileUtil.createFile(filepath);
		FileUtil.writeFile(new FileWriter(filepath), "Test\nDerp\nYes");
		
		List<String> lines = FileUtil.readLinesAsList(FileUtil.setupFileReader(filepath));
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
		
		List<String> lines = FileUtil.readLinesAsList(FileUtil.setupFileReader(filepath));
		assertEquals(3, lines.size());
		assertEquals("Test", lines.get(0));
		assertEquals("Derp", lines.get(1));
		assertEquals("Yes", lines.get(2));
	}
	
	@Test
	public void testZipAndUnzipByFileName() throws IOException{
		String folder = "target/test-files/zipAndUnzipByFileName/";
		String filepath = folder + "test.txt";
		String zipPath = folder + "test.zip";
		String resultPath = folder + "result/";
		File resultDir = new File(resultPath);
		//noinspection ResultOfMethodCallIgnored
		resultDir.mkdirs();
		FileUtil.createFile(filepath);
		FileUtil.writeFile(filepath, "Some content");
		FileUtil.zipFile(filepath, zipPath);
		FileUtil.unzipFile(zipPath, resultPath);
		List<File> contents = FileUtil.listAllFiles(resultPath);
		assertEquals(1, contents.size());
		File resultFile = contents.get(0);
		assertEquals("test.txt", resultFile.getName());
		List<String> lines = FileUtil.readLinesAsList(FileUtil.setupFileReader(resultFile));
		assertEquals(1, lines.size());
		assertEquals("Some content", lines.get(0));
	}
	
	@Test
	public void testZipAndUnzipByDirectoryName() throws IOException{
		String folder = "target/test-files/zipAndUnzipByDirectoryName/";
		String dataFolder = folder + "data/";
		String filepath = dataFolder + "test.txt";
		String filepath2 = dataFolder + "test2.txt";
		String zipPath = folder + "test.zip";
		String resultPath = folder + "result/";
		File resultDir = new File(resultPath);
		//noinspection ResultOfMethodCallIgnored
		resultDir.mkdirs();
		FileUtil.createFile(filepath);
		FileUtil.createFile(filepath2);
		FileUtil.writeFile(filepath, "Some content");
		FileUtil.writeFile(filepath2, "Some other content");
		FileUtil.zipFile(dataFolder, zipPath);
		FileUtil.unzipFile(zipPath, resultPath);
		List<File> contents = FileUtil.listAllFiles(resultPath);
		assertEquals(2, contents.size());
		File resultFile = contents.get(0);
		assertEquals("test.txt", resultFile.getName());
		List<String> lines = FileUtil.readLinesAsList(FileUtil.setupFileReader(resultFile));
		assertEquals(1, lines.size());
		assertEquals("Some content", lines.get(0));
		File resultFile2 = contents.get(1);
		assertEquals("test2.txt", resultFile2.getName());
		List<String> lines2 = FileUtil.readLinesAsList(FileUtil.setupFileReader(resultFile2));
		assertEquals(1, lines2.size());
		assertEquals("Some other content", lines2.get(0));
	}
	
	@Test
	public void testZipAndUnzipByFile() throws IOException{
		String folder = "target/test-files/zipAndUnzipByFileName/";
		String filepath = folder + "test.txt";
		String zipPath = folder + "test.zip";
		String resultPath = folder + "result/";
		File resultDir = new File(resultPath);
		//noinspection ResultOfMethodCallIgnored
		resultDir.mkdirs();
		File file = FileUtil.createFile(filepath);
		FileUtil.writeFile(filepath, "Some content");
		FileUtil.zipFile(file, zipPath);
		FileUtil.unzipFile(zipPath, resultDir);
		List<File> contents = FileUtil.listAllFiles(resultPath);
		assertEquals(1, contents.size());
		File resultFile = contents.get(0);
		assertEquals("test.txt", resultFile.getName());
		List<String> lines = FileUtil.readLinesAsList(FileUtil.setupFileReader(resultFile));
		assertEquals(1, lines.size());
		assertEquals("Some content", lines.get(0));
	}
	
	@Test
	public void testZipAndUnzipByDirectoryFile() throws IOException{
		String folder = "target/test-files/zipAndUnzipByDirectoryName/";
		String dataFolder = folder + "data/";
		String filepath = dataFolder + "test.txt";
		String filepath2 = dataFolder + "test2.txt";
		String zipPath = folder + "test.zip";
		String resultPath = folder + "result/";
		File resultDir = new File(resultPath);
		//noinspection ResultOfMethodCallIgnored
		resultDir.mkdirs();
		FileUtil.createFile(filepath);
		FileUtil.createFile(filepath2);
		FileUtil.writeFile(filepath, "Some content");
		FileUtil.writeFile(filepath2, "Some other content");
		FileUtil.zipFile(new File(dataFolder), zipPath);
		FileUtil.unzipFile(zipPath, resultDir);
		List<File> contents = FileUtil.listAllFiles(resultPath);
		assertEquals(2, contents.size());
		File resultFile = contents.get(0);
		assertEquals("test.txt", resultFile.getName());
		List<String> lines = FileUtil.readLinesAsList(FileUtil.setupFileReader(resultFile));
		assertEquals(1, lines.size());
		assertEquals("Some content", lines.get(0));
		File resultFile2 = contents.get(1);
		assertEquals("test2.txt", resultFile2.getName());
		List<String> lines2 = FileUtil.readLinesAsList(FileUtil.setupFileReader(resultFile2));
		assertEquals(1, lines2.size());
		assertEquals("Some other content", lines2.get(0));
	}
}
