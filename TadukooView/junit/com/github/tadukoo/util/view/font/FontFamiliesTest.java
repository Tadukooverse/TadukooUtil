package com.github.tadukoo.util.view.font;

import org.junit.jupiter.api.Test;

import java.awt.Font;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FontFamiliesTest{
	
	@Test
	public void testFromName(){
		for(FontFamilies family: FontFamilies.values()){
			FontFamilies fromNameFamily = FontFamilies.fromName(family.getFamily().getName());
			assertEquals(family, fromNameFamily);
		}
	}
	
	@Test
	public void testFromNameInvalid(){
		assertNull(FontFamilies.fromName("dewojgerogk"));
	}
	
	/*
	 * Logical Fonts Section
	 */
	
	@Test
	public void testDialog(){
		FontFamily family = FontFamilies.DIALOG.getFamily();
		assertEquals(Font.DIALOG, family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testDialogInput(){
		FontFamily family = FontFamilies.DIALOG_INPUT.getFamily();
		assertEquals(Font.DIALOG_INPUT, family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testMonospaced(){
		FontFamily family = FontFamilies.MONOSPACED.getFamily();
		assertEquals(Font.MONOSPACED, family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testSansSerif(){
		FontFamily family = FontFamilies.SANS_SERIF.getFamily();
		assertEquals(Font.SANS_SERIF, family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testSerif(){
		FontFamily family = FontFamilies.SERIF.getFamily();
		assertEquals(Font.SERIF, family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertNull(family.getAlternate());
	}
	
	/*
	 * Proprietary Fonts and Their Open Source Substitutes Section
	 */
	
	@Test
	public void testArimo(){
		String name = "Arimo";
		FontFamily family = FontFamilies.ARIMO.getFamily();
		assertEquals(name, family.getName());
		assertEquals("arimo/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(4, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testArial(){
		FontFamily family = FontFamilies.ARIAL.getFamily();
		assertEquals("Arial", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.ARIMO.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testHelvetica(){
		FontFamily family = FontFamilies.HELVETICA.getFamily();
		assertEquals("Helvetica", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.ARIMO.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testCarlito(){
		String name = "Carlito";
		FontFamily family = FontFamilies.CARLITO.getFamily();
		assertEquals(name, family.getName());
		assertEquals("carlito/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(4, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testCalibri(){
		FontFamily family = FontFamilies.CALIBRI.getFamily();
		assertEquals("Calibri", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.CARLITO.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testCaladea(){
		String name = "Caladea";
		FontFamily family = FontFamilies.CALADEA.getFamily();
		assertEquals(name, family.getName());
		assertEquals("caladea/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(4, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testCambria(){
		FontFamily family = FontFamilies.CAMBRIA.getFamily();
		assertEquals("Cambria", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.CALADEA.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testCousine(){
		String name = "Cousine";
		FontFamily family = FontFamilies.COUSINE.getFamily();
		assertEquals(name, family.getName());
		assertEquals("cousine/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(4, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testCourier(){
		FontFamily family = FontFamilies.COURIER.getFamily();
		assertEquals("Courier", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.COUSINE.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testCourierNew(){
		FontFamily family = FontFamilies.COURIER_NEW.getFamily();
		assertEquals("Courier New", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.COUSINE.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testComicRelief(){
		String name = "Comic Relief";
		FontFamily family = FontFamilies.COMIC_RELIEF.getFamily();
		assertEquals(name, family.getName());
		assertEquals("comic-relief/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(2, files.size());
		assertEquals("ComicRelief." + FontConstants.TTF, files.get(0));
		assertEquals("ComicRelief-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testComicSansMS(){
		FontFamily family = FontFamilies.COMIC_SANS_MS.getFamily();
		assertEquals("Comic Sans MS", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.COMIC_RELIEF.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testGelasio(){
		String name = "Gelasio";
		FontFamily family = FontFamilies.GELASIO.getFamily();
		assertEquals(name, family.getName());
		assertEquals("gelasio/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(8, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertEquals(name + "-" + FontConstants.MEDIUM + "." + FontConstants.TTF, files.get(4));
		assertEquals(name + "-" + FontConstants.MEDIUM_ITALIC + "." + FontConstants.TTF, files.get(5));
		assertEquals(name + "-" + FontConstants.SEMI_BOLD + "." + FontConstants.TTF, files.get(6));
		assertEquals(name + "-" + FontConstants.SEMI_BOLD_ITALIC + "." + FontConstants.TTF, files.get(7));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testGeorgia(){
		FontFamily family = FontFamilies.GEORGIA.getFamily();
		assertEquals("Georgia", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.GELASIO.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testSelawik(){
		String name = "Selawik";
		FontFamily family = FontFamilies.SELAWIK.getFamily();
		assertEquals(name, family.getName());
		assertEquals("selawik/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(5, files.size());
		assertEquals("selawk.ttf", files.get(0));
		assertEquals("selawkb.ttf", files.get(1));
		assertEquals("selawkl.ttf", files.get(2));
		assertEquals("selawksb.ttf", files.get(3));
		assertEquals("selawksl.ttf", files.get(4));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testSegoeUI(){
		FontFamily family = FontFamilies.SEGOE_UI.getFamily();
		assertEquals("Segoe UI", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.SELAWIK.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testWineTahoma(){
		String name = "Wine Tahoma";
		FontFamily family = FontFamilies.WINE_TAHOMA.getFamily();
		assertEquals(name, family.getName());
		assertEquals("wine-tahoma/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(2, files.size());
		assertEquals("fonts_tahoma.ttf", files.get(0));
		assertEquals("fonts_tahomabd.ttf", files.get(1));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testTahoma(){
		FontFamily family = FontFamilies.TAHOMA.getFamily();
		assertEquals("Tahoma", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.WINE_TAHOMA.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testTinos(){
		String name = "Tinos";
		FontFamily family = FontFamilies.TINOS.getFamily();
		assertEquals(name, family.getName());
		assertEquals("tinos/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(4, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testTimes(){
		FontFamily family = FontFamilies.TIMES.getFamily();
		assertEquals("Times", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.TINOS.getFamily(), family.getAlternate());
	}
	
	@Test
	public void testTimesNewRoman(){
		FontFamily family = FontFamilies.TIMES_NEW_ROMAN.getFamily();
		assertEquals("Times New Roman", family.getName());
		assertNull(family.getDirectory());
		assertNull(family.getFiles());
		assertEquals(FontFamilies.TINOS.getFamily(), family.getAlternate());
	}
	
	/*
	 * Other Open Source Fonts Section
	 */
	
	@Test
	public void testBangers(){
		String name = "Bangers";
		FontFamily family = FontFamilies.BANGERS.getFamily();
		assertEquals(name, family.getName());
		assertEquals("bangers/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(1, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testCalligraserif(){
		String name = "Calligraserif";
		FontFamily family = FontFamilies.CALLIGRASERIF.getFamily();
		assertEquals(name, family.getName());
		assertEquals("calligraserif/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(1, files.size());
		assertEquals("Calligraserif.ttf", files.get(0));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testLeckerliOne(){
		String name = "LeckerliOne";
		FontFamily family = FontFamilies.LECKERLI_ONE.getFamily();
		assertEquals("Leckerli One", family.getName());
		assertEquals("leckerli-one/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(1, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testLobster(){
		String name = "Lobster";
		FontFamily family = FontFamilies.LOBSTER.getFamily();
		assertEquals(name, family.getName());
		assertEquals("lobster/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(1, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testRoboto(){
		String name = "Roboto";
		FontFamily family = FontFamilies.ROBOTO.getFamily();
		assertEquals(name, family.getName());
		assertEquals("roboto/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(12, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertEquals(name + "-" + FontConstants.BLACK + "." + FontConstants.TTF, files.get(4));
		assertEquals(name + "-" + FontConstants.BLACK_ITALIC + "." + FontConstants.TTF, files.get(5));
		assertEquals(name + "-" + FontConstants.LIGHT + "." + FontConstants.TTF, files.get(6));
		assertEquals(name + "-" + FontConstants.LIGHT_ITALIC + "." + FontConstants.TTF, files.get(7));
		assertEquals(name + "-" + FontConstants.MEDIUM + "." + FontConstants.TTF, files.get(8));
		assertEquals(name + "-" + FontConstants.MEDIUM_ITALIC + "." + FontConstants.TTF, files.get(9));
		assertEquals(name + "-" + FontConstants.THIN + "." + FontConstants.TTF, files.get(10));
		assertEquals(name + "-" + FontConstants.THIN_ITALIC + "." + FontConstants.TTF, files.get(11));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testRobotoCondensed(){
		String name = "RobotoCondensed";
		FontFamily family = FontFamilies.ROBOTO_CONDENSED.getFamily();
		assertEquals("Roboto Condensed", family.getName());
		assertEquals("roboto-condensed/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(6, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertEquals(name + "-" + FontConstants.LIGHT + "." + FontConstants.TTF, files.get(4));
		assertEquals(name + "-" + FontConstants.LIGHT_ITALIC + "." + FontConstants.TTF, files.get(5));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testSatisfy(){
		String name = "Satisfy";
		FontFamily family = FontFamilies.SATISFY.getFamily();
		assertEquals(name, family.getName());
		assertEquals("satisfy/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(1, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertNull(family.getAlternate());
	}
	
	@Test
	public void testSourceCodePro(){
		String name = "SourceCodePro";
		FontFamily family = FontFamilies.SOURCE_CODE_PRO.getFamily();
		assertEquals("Source Code Pro", family.getName());
		assertEquals("source-code-pro/", family.getDirectory());
		List<String> files = family.getFiles();
		assertEquals(14, files.size());
		assertEquals(name + "-" + FontConstants.REGULAR + "." + FontConstants.TTF, files.get(0));
		assertEquals(name + "-" + FontConstants.BOLD + "." + FontConstants.TTF, files.get(1));
		assertEquals(name + "-" + FontConstants.ITALIC + "." + FontConstants.TTF, files.get(2));
		assertEquals(name + "-" + FontConstants.BOLD_ITALIC + "." + FontConstants.TTF, files.get(3));
		assertEquals(name + "-" + FontConstants.BLACK + "." + FontConstants.TTF, files.get(4));
		assertEquals(name + "-" + FontConstants.BLACK_ITALIC + "." + FontConstants.TTF, files.get(5));
		assertEquals(name + "-" + FontConstants.EXTRA_LIGHT + "." + FontConstants.TTF, files.get(6));
		assertEquals(name + "-" + FontConstants.EXTRA_LIGHT_ITALIC + "." + FontConstants.TTF, files.get(7));
		assertEquals(name + "-" + FontConstants.LIGHT + "." + FontConstants.TTF, files.get(8));
		assertEquals(name + "-" + FontConstants.LIGHT_ITALIC + "." + FontConstants.TTF, files.get(9));
		assertEquals(name + "-" + FontConstants.MEDIUM + "." + FontConstants.TTF, files.get(10));
		assertEquals(name + "-" + FontConstants.MEDIUM_ITALIC + "." + FontConstants.TTF, files.get(11));
		assertEquals(name + "-" + FontConstants.SEMI_BOLD + "." + FontConstants.TTF, files.get(12));
		assertEquals(name + "-" + FontConstants.SEMI_BOLD_ITALIC + "." + FontConstants.TTF, files.get(13));
		assertNull(family.getAlternate());
	}
}
