package com.gmail.realtadukoo.util.view.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Drawable class used for drawing a simple image.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class Image extends Drawable{
	
	/**
	 * Builder for constructing an {@link Image} object.
	 * <br><br>
	 * <b>MUST</b> specify the following:
	 * <ul>
	 * <li>imgFile - the file path to the image to load</li>
	 * <li>x coordinate</li>
	 * <li>y coordinate</li>
	 * </ul>
	 * Defaults are:
	 * <ul>
	 * <li>width - set based on the loaded image's width</li>
	 * <li>height - set based on the loaded image's height</li>
	 * <li>orientation - {@link Draw.ORIENTATION#TOP_LEFT TOP_LEFT}</li>
	 * </ul>
	 * 
	 * @author Logan Ferree (Tadukoo)
	 * @version 0.1-Alpha-SNAPSHOT
	 */
	public static class ImageBuilder{
		/** The file path for the image to load */
		private String imgFile = null;
		/** The x coordinate for the Image - must be specified */
		private int x = -1;
		/** The y coordinate for the Image - must be specified */
		private int y = -1;
		/** The width for the Image - defaults to size of loaded image */
		private int width = -1;
		/** The height for the Image - defaults to size of loaded image */
		private int height = -1;
		/** The orientation of the Image - default TOP_LEFT */
		private Draw.ORIENTATION orientation = Draw.ORIENTATION.TOP_LEFT;
		
		// Cannot create ImageBuilder outside of Image
		private ImageBuilder(){ }
		
		/**
		 * Sets the image file path to be used to load the image 
		 * for the Image object.
		 * 
		 * @param imgFile The file path to the image to use
		 */
		public ImageBuilder imgFile(String imgFile){
			this.imgFile = imgFile;
			return this;
		}
		
		/**
		 * Sets the x coordinate to be used on the Image object
		 * 
		 * @param x The x coordinate for the Image object
		 */
		public ImageBuilder x(int x){
			this.x = x;
			return this;
		}
		
		/**
		 * Sets the y coordinate to be used on the Image object
		 * 
		 * @param y The y coordinate for the Image object
		 */
		public ImageBuilder y(int y){
			this.y = y;
			return this;
		}
		
		/**
		 * Sets the width to be used on the Image object.
		 * This will override the size of the loaded image.
		 * 
		 * @param width The width for the Image object
		 */
		public ImageBuilder width(int width){
			this.width = width;
			return this;
		}
		
		/**
		 * Sets the height to be used on the Image object.
		 * This will override the size of the loaded image.
		 * 
		 * @param height The height for the Image object
		 */
		public ImageBuilder height(int height){
			this.height = height;
			return this;
		}
		
		/**
		 * Sets the orientation to be used on the Image object
		 * 
		 * @param orientation The orientation for the Image object
		 */
		public ImageBuilder orientation(Draw.ORIENTATION orientation){
			this.orientation = orientation;
			return this;
		}
		
		/**
		 * Checks for errors with the current settings for the Image object
		 */
		private void checkForErrors(){
			List<String> errors = new ArrayList<String>();
			// Image file path is required
			if(imgFile == null){
				errors.add("Must specify imgFile");
			}
			
			// x and y coordinates are required
			if(x == -1){
				errors.add("Must specify x");
			}
			if(y == -1){
				errors.add("Must specify y");
			}
			
			/*
			 *  Either specify both width + height to alter it, or specify neither to use 
			 *  the loaded image's dimensions
			 */
			if((width != -1 && height == -1) || (width == -1 && height != -1)){
				errors.add("Must either specify BOTH width and height OR specify neither");
			}
			
			if(!errors.isEmpty()){
				throw new IllegalStateException("Failed to create Image object: " + errors.toString());
			}
		}
		
		/**
		 * Builds the Image object based on the input data if it's 
		 * valid.
		 * 
		 * @return The built Image object
		 */
		public Image build(){
			checkForErrors();
			
			BufferedImage image = Draw.loadImage(imgFile);
			if(width == -1 && height == -1){
				width = image.getWidth();
				height = image.getHeight();
			}
			return new Image(image, x, y, width, height, orientation);
		}
	}
	
	/** The image to draw */
	private BufferedImage image;
	/** The x coordinate of the image (meaning determined by {@link ORIENTATION}) */
	private int originalX;
	/** The y coordinate of the image (meaning determined by {@link ORIENTATION}) */
	private int originalY;
	/** The x coordinate of the image (left edge) */
	private int x;
	/** The y coordinate of the image (top edge) */
	private int y;
	/** The width of the image */
	private int width;
	/** The height of the image */
	private int height;
	/** The {@link ORIENTATION} of the image in relation to the given coordinate */
	private Draw.ORIENTATION orientation;
	
	/**
	 * Creates an Image object with the given image with the specified 
	 * coordinates, size, and orientation.
	 * 
	 * @param imgFile The file path to the image
	 * @param x The x coordinate of the image
	 * @param y The y coordinate of the image
	 * @param width The width of the image
	 * @param height The height of the image
	 * @param orientation The orientation of the image
	 */
	private Image(BufferedImage image, int x, int y, int width, int height, Draw.ORIENTATION orientation){
		this.image = image;
		originalX = x;
		originalY = y;
		this.width = width;
		this.height = height;
		this.orientation = orientation;
		setOriented(false);
	}
	
	/**
	 * Constructs an {@link ImageBuilder} to create an Image object.
	 * 
	 * @return A new ImageBuilder
	 */
	public static ImageBuilder builder(){
		return new ImageBuilder();
	}
	
	/**
	 * Calls {@link Draw#orient} to orient the image based on its 
	 * {@link #orientation}.
	 * 
	 * @param g The Graphics that's unused here
	 */
	protected void orient(Graphics g){
		Point newCoord = Draw.orient(originalX, originalY, width, height, orientation);
		x = (int) newCoord.getX();
		y = (int) newCoord.getY();
		setOriented(true);
	}
	
	/**
	 * Draws the image to the screen with its position and dimensions. 
	 * <br>
	 * Calls {@link Draw#drawImage} to draw it.
	 * 
	 * @param g The Graphics to use in drawing
	 */
	@Override
	public void draw(Graphics g){
		Draw.drawImage(g, image, x, y, width, height);
	}
}
