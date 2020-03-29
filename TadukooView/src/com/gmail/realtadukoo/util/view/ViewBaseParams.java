package com.gmail.realtadukoo.util.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Parameters object to be used in {@link ViewBase} in order to configure basic things 
 * such as fullscreen vs windowed mode and title of the window.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class ViewBaseParams{
	
	/**
	 * Builder for constructing a {@link ViewBaseParams} object.
	 * <br><br>
	 * <b>MUST</b> specify the following:
	 * <ul>
	 * <li>title</li>
	 * <li>width - only if fullscreen = false</li>
	 * <li>height - only if fullscreen = false</li>
	 * </ul>
	 * Defaults are:
	 * <ul>
	 * <li>fullscreen - true</li>
	 * <li>background - {@link Color#BLACK}</li>
	 * </ul>
	 * 
	 * @author Logan Ferree (Tadukoo)
	 * @version 0.1-Alpha-SNAPSHOT
	 */
	public static class ViewBaseParamsBuilder{
		/** Title to be used on the window */
		private String title = null;
		/** Whether to be in fullscreen (true) or windowed (false) mode */
		private boolean fullscreen = true;
		/** The width of the window if in windowed mode */
		private int width = -1;
		/** The height of the window if in windowed mode */
		private int height = -1;
		/** The background color of the window */
		private Color background = Color.BLACK;
		
		// Can't instantiate builder outside of this class
		private ViewBaseParamsBuilder(){ }
		
		/** 
		 * Set the title to be used on the window
		 * 
		 * @param title The title to be used on the window
		 */
		public ViewBaseParamsBuilder title(String title){
			this.title = title;
			return this;
		}
		
		/**
		 * Sets whether to be in fullscreen (true) or windowed (false) mode
		 * 
		 * @param fullscreen Whether to be in fullscreen or windowed mode
		 */
		public ViewBaseParamsBuilder fullscreen(boolean fullscreen){
			this.fullscreen = fullscreen;
			return this;
		}
		
		/**
		 * Sets the width of the window if in windowed mode
		 * 
		 * @param width The width of the window
		 */
		public ViewBaseParamsBuilder width(int width){
			this.width = width;
			return this;
		}
		
		/**
		 * Sets the height of the window if in windowed mode
		 * 
		 * @param height The height of the window
		 */
		public ViewBaseParamsBuilder height(int height){
			this.height = height;
			return this;
		}
		
		/**
		 * Sets the background color of the window
		 * 
		 * @param background The background color of the window
		 */
		public ViewBaseParamsBuilder background(Color background){
			this.background = background;
			return this;
		}
		
		/**
		 * Checks for errors with the current settings for the ViewBaseParams object
		 */
		public void checkForErrors(){
			List<String> errors = new ArrayList<>();
			
			// Title is required
			if(title == null){
				errors.add("Title must be specified");
			}
			
			// If we're in windowed mode, we need width and height dimensions
			if(!fullscreen){
				if(width == -1){
					errors.add("width is needed if not fullscreen");
				}
				if(height == -1){
					errors.add("height is needed if not fullscreen");
				}
			}
			
			// If there were any errors, throw an illegal state exception
			if(!errors.isEmpty()){
				throw new IllegalStateException("Failed to create ViewBaseParams object: " + errors.toString());
			}
		}
		
		/**
		 * Builds the ViewBaseParams object based on the input data if it's valid.
		 * 
		 * @return The built ViewBaseParams object
		 */
		public ViewBaseParams build(){
			checkForErrors();
			
			return new ViewBaseParams(title, fullscreen, width, height, background);
		}
	}
	
	/** Title to be used on the window */
	private String title;
	/** Whether to be in fullscreen (true) or windowed (false) mode */
	private boolean fullscreen;
	/** The width of the window if in windowed mode */
	private int width;
	/** The height of the window if in windowed mode */
	private int height;
	/** The background color of the window */
	private Color background;
	
	/**
	 * Constructs a ViewBaseParams object with the given values.
	 * 
	 * @param title The title to be used on the window
	 * @param fullscreen Whether to be in fullscreen (true) or windowed (false) mode
	 * @param width The width of the window if in windowed mode
	 * @param height The height of the window if in windowed mode
	 * @param background The background color of the window
	 */
	private ViewBaseParams(String title, boolean fullscreen, int width, int height, Color background){
		this.title = title;
		this.fullscreen = fullscreen;
		this.width = width;
		this.height = height;
		this.background = background;
	}
	
	/**
	 * Constructs a {@link ViewBaseParamsBuilder} to be used to make a ViewBaseParams object.
	 * 
	 * @return A new ViewBaseParamsBuilder
	 */
	public static ViewBaseParamsBuilder builder(){
		return new ViewBaseParamsBuilder();
	}
	
	/** 
	 * @return The title to be used on the window
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * @return Whether to be in fullscreen (true) or windowed (false) mode
	 */
	public boolean isFullscreen(){
		return fullscreen;
	}
	
	/**
	 * @return The width of the window if in windowed mode
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @return The height of the window if in windowed mode
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @return The background color of the window
	 */
	public Color getBackground(){
		return background;
	}
}
