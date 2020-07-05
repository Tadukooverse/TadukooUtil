package com.github.tadukoo.util.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class contains utility methods for dealing with images.
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha
 */
public class ImageUtil{
	
	// Not allowed to create an ImageUtil
	private ImageUtil(){ }
	
	/**
	 * Loads an image from the given filepath using getResourceAsStream.
	 *
	 * @param filePath The path to the image file
	 * @return The loaded image
	 * @throws IOException If it goes wrong
	 */
	public static BufferedImage loadImageAsResource(String filePath) throws IOException{
		return ImageIO.read(ImageUtil.class.getResourceAsStream(filePath));
	}
}
