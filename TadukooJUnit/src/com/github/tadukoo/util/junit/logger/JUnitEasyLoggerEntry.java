package com.github.tadukoo.util.junit.logger;

import java.util.logging.Level;

/**
 * JUnit Easy Logger Entry represents a single logged entry in the logger
 * <br><br>
 * Contains: {@link Level}, message, and {@link Throwable}
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public record JUnitEasyLoggerEntry(Level level, String message, Throwable throwable){ }
