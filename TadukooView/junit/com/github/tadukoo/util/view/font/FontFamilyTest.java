package com.github.tadukoo.util.view.font;

import com.github.tadukoo.util.ListUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FontFamilyTest{
	private final String name = "aName";
	private final String extension = "anExtension";
	
	@Test
	public void testStdFileStr(){
		String type = "aType";
		assertEquals(name + "-" + type + "." + extension,
				FontFamily.stdFileStr(name, type, extension));
	}
	
	@Test
	public void testMakeRegularList(){
		List<String> list = FontFamily.makeRegularList(name, extension);
		assertEquals(1, list.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + extension, list.get(0));
	}
	
	@Test
	public void testMakeStandardList(){
		List<String> list = FontFamily.makeStandardList(name, extension);
		assertEquals(4, list.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + extension, list.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + extension, list.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + extension, list.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + extension, list.get(3));
	}
	
	@Test
	public void testMakeStandardListWithAdditions(){
		List<String> list = FontFamily.makeStandardListWithAdditions(name, extension,
				ListUtil.createList("Derp", "Test"));
		assertEquals(6, list.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + extension, list.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + extension, list.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + extension, list.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + extension, list.get(3));
		assertEquals(name + "-Derp." + extension, list.get(4));
		assertEquals(name + "-Test." + extension, list.get(5));
	}
	
	@Test
	public void testNameConstructor(){
		FontFamily family = new FontFamily(name);
		assertEquals(name, family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testFilesConstructor(){
		FontFamily family = new FontFamily(name, "some_directory",
				ListUtil.createList("aFile", "an_other_file.jpeg"));
		assertEquals(name, family.getName());
		assertEquals("some_directory", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(2, files.size());
		assertEquals("aFile", files.get(0));
		assertEquals("an_other_file.jpeg", files.get(1));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testAlternateConstructor(){
		FontFamily alternate = new FontFamily("Derp");
		FontFamily family = new FontFamily("main", alternate);
		assertEquals("main", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(alternate, family.getAlternate());
	}
}
