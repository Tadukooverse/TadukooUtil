package com.github.tadukoo.util.view.font;

import com.github.tadukoo.util.ListUtil;

import java.awt.*;

/**
 * Contains an enumeration of {@link FontFamily FontFamilies} supported via use of the Tadukoo Fonts project.
 * This enum includes the logical fonts from Java, some proprietary fonts (which have an open source alternate listed
 * in case the host does not have the proprietary font), and a collection of open source fonts.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public enum Fonts implements FontConstants{
	/*
	 * Logical Fonts Section
	 */
	
	/** Logical Font: {@link Font#DIALOG} */
	DIALOG(new FontFamily(Font.DIALOG)),
	/** Logical Font: {@link Font#DIALOG_INPUT} */
	DIALOG_INPUT(new FontFamily(Font.DIALOG_INPUT)),
	/** Logical Font: {@link Font#MONOSPACED} */
	MONOSPACED(new FontFamily(Font.MONOSPACED)),
	/** Logical Font: {@link Font#SANS_SERIF} */
	SANS_SERIF(new FontFamily(Font.SANS_SERIF)),
	/** Logical Font: {@link Font#SERIF} */
	SERIF(new FontFamily(Font.SERIF)),
	
	/*
	 * Proprietary Fonts and Their Open Source Substitutes Section
	 */
	
	/** Open Source Font: Arimo - made as an alternate to {@link #ARIAL} */
	ARIMO(new FontFamily("Arimo", "arimo/", FontFamily.makeStandardList("Arimo", TTF))),
	/** Proprietary Font: Arial - alternate is {@link #ARIMO} */
	ARIAL(new FontFamily("Arial", ARIMO.getFamily())),
	/** Proprietary Font: Helvetica - alternate is {@link #ARIMO} */
	HELVETICA(new FontFamily("Helvetica", ARIMO.getFamily())),
	
	/** Open Source Font: Carlito - made as an alternate to {@link #CALIBRI} */
	CARLITO(new FontFamily("Carlito", "carlito/", FontFamily.makeStandardList("Carlito", TTF))),
	/** Proprietary Font: Calibri - alternate is {@link #CARLITO} */
	CALIBRI(new FontFamily("Calibri", CARLITO.getFamily())),
	
	/** Open Source Font: Caladea - made as an alternate to {@link #CAMBRIA} */
	CALADEA(new FontFamily("Caladea", "caladea/", FontFamily.makeStandardList("Caladea", TTF))),
	/** Proprietary Font: Cambria - alternate is {@link #CALADEA} */
	CAMBRIA(new FontFamily("Cambria", CALADEA.getFamily())),
	
	/** Open Source Font: Cousine - made as an alternate to {@link #COURIER} */
	COUSINE(new FontFamily("Cousine", "cousine/", FontFamily.makeStandardList("Cousine", TTF))),
	/** Proprietary Font: Courier - alternate is {@link #COUSINE} */
	COURIER(new FontFamily("Courier", COUSINE.getFamily())),
	/** Proprietary Font: Courier New - alternate is {@link #COUSINE} */
	COURIER_NEW(new FontFamily("Courier New", COUSINE.getFamily())),
	
	/** Open Source Font: Comic Relief - made as an alternate to {@link #COMIC_SANS_MS} */
	COMIC_RELIEF(new FontFamily("Comic Relief", "comic-relief/",
			ListUtil.createList("ComicRelief.ttf",
					FontFamily.stdFileStr("ComicRelief", BOLD, TTF)))),
	/** Proprietary Font: Comic Sans MS - alternate is {@link #COMIC_RELIEF} */
	COMIC_SANS_MS(new FontFamily("Comic Sans MS", COMIC_RELIEF.getFamily())),
	
	/** Open Source Font: Gelasio - made as an alternate to {@link #GEORGIA} */
	GELASIO(new FontFamily("Gelasio", "gelasio/",
			FontFamily.makeStandardListWithAdditions("Gelasio", TTF,
			ListUtil.createList(MEDIUM, MEDIUM_ITALIC, SEMI_BOLD, SEMI_BOLD_ITALIC)))),
	/** Proprietary Font: Georgia - alternate is {@link #GELASIO} */
	GEORGIA(new FontFamily("Georgia", GELASIO.getFamily())),
	
	/** Open Source Font: Selawik - made as an alternate to {@link #SEGOE_UI} */
	SELAWIK(new FontFamily("Selawik", "selawik/",
			ListUtil.createList("selawk.ttf", "selawkb.ttf", "selawkl.ttf", "selawksb.ttf", "selawksl.ttf"))),
	/** Proprietary Font: Selawik - alternate is {@link #SELAWIK} */
	SEGOE_UI(new FontFamily("Segoe UI", SELAWIK.getFamily())),
	
	/** Open Source Font: Wine Tahoma - made as an alternate to {@link #TAHOMA} */
	WINE_TAHOMA(new FontFamily("Wine Tahoma", "wine-tahoma/",
			ListUtil.createList("fonts_tahoma.ttf", "fonts_tahomabd.ttf"))),
	/** Proprietary Font: Tahoma - alternate is {@link #WINE_TAHOMA} */
	TAHOMA(new FontFamily("Tahoma", WINE_TAHOMA.getFamily())),
	
	/** Open Source Font: Tinos - made as an alternate to {@link #TIMES_NEW_ROMAN} */
	TINOS(new FontFamily("Tinos", "tinos/", FontFamily.makeStandardList("Tinos", TTF))),
	/** Proprietary Font: Times - alternate is {@link #TINOS} */
	TIMES(new FontFamily("Times", TINOS.getFamily())),
	/** Proprietary Font: Times New Roman - alternate is {@link #TINOS} */
	TIMES_NEW_ROMAN(new FontFamily("Times New Roman", TINOS.getFamily())),
	
	/*
	 * Other Open Source Fonts Section
	 */
	
	/** Open Source Font: Bangers */
	BANGERS(new FontFamily("Bangers", "bangers/", FontFamily.makeRegularList("Bangers", TTF))),
	
	/** Open Source Font: Calligraserif */
	CALLIGRASERIF(new FontFamily("Calligraserif", "calligraserif/",
			ListUtil.createList("Calligraserif.ttf"))),
	
	/** Open Source Font: Leckerli One */
	LECKERLI_ONE(new FontFamily("Leckerli One", "leckerli-one/",
			FontFamily.makeRegularList("LeckerliOne", "ttf"))),
	
	/** Open Source Font: Lobster */
	LOBSTER(new FontFamily("Lobster", "lobster/", FontFamily.makeRegularList("Lobster", TTF))),
	
	/** Open Source Font: Roboto */
	ROBOTO(new FontFamily("Roboto", "roboto/",
			FontFamily.makeStandardListWithAdditions("Roboto", TTF,
			ListUtil.createList(BLACK, BLACK_ITALIC, LIGHT, LIGHT_ITALIC, MEDIUM, MEDIUM_ITALIC,
					THIN, THIN_ITALIC)))),
	
	/** Open Source Font: Roboto Condensed */
	ROBOTO_CONDENSED(new FontFamily("Roboto Condensed", "roboto-condensed/",
			FontFamily.makeStandardListWithAdditions("RobotoCondensed", TTF,
					ListUtil.createList(LIGHT, LIGHT_ITALIC)))),
	
	/** Open Source Font: Satisfy */
	SATISFY(new FontFamily("Satisfy", "satisfy/", FontFamily.makeRegularList("Satisfy", TTF))),
	
	/** Open Source Font: Source Code Pro */
	SOURCE_CODE_PRO(new FontFamily("Source Code Pro", "source-code-pro/",
			FontFamily.makeStandardListWithAdditions("SourceCodePro", TTF,
					ListUtil.createList(BLACK, BLACK_ITALIC, EXTRA_LIGHT, EXTRA_LIGHT_ITALIC,
							LIGHT, LIGHT_ITALIC, MEDIUM, MEDIUM_ITALIC, SEMI_BOLD, SEMI_BOLD_ITALIC))))
	
	;
	
	/** The {@link FontFamily} contained in this enum value */
	private final FontFamily family;
	
	/**
	 * Creates a FontFamily enumeration with the given {@link FontFamily}
	 *
	 * @param family The {@link FontFamily} for this enumeration
	 */
	Fonts(FontFamily family){
		this.family = family;
	}
	
	/**
	 * @return The {@link FontFamily} contained in this enum value
	 */
	public FontFamily getFamily(){
		return family;
	}
}
