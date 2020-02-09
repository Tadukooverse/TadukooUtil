package com.gmail.realtadukoo.util.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gmail.realtadukoo.util.event.view.ViewChangeEvent;
import com.gmail.realtadukoo.util.event.view.ViewChangeEventListener;

/**
 * This class is used for the View in general for the client.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@SuppressWarnings("serial")
public abstract class ViewBase extends JPanel implements ViewChangeEventListener{
	private static Context context;
	
	public ViewBase(){
		// Initialize this
		initialize();
		
		// TODO: Change some of the below to be configurable
		
		// Set background to blue and size to 1366x768 (my laptop's res)
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(1366, 768));
		setDoubleBuffered(true);
		
		setFocusable(true);
		
		// Create a frame to display the view
		JFrame frame = new JFrame();
		frame.setTitle("KBWCO");
		frame.setContentPane(this);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void initialize(){
		context = createContext();
		context.addViewToStack(initFirstView());
		context.getCurrentView().init(context);
		context.getViewChangeEventHandler().registerListener(this);
		
		registerInputListeners();
		
		// TODO: Add extra init method to allow sub-classes to do more initialization logic if needed
	}
	
	/**
	 * Creates a {@link Context} object to be used in the program.
	 * 
	 * @return A newly created Context object
	 */
	protected abstract Context createContext();
	
	/**
	 * Creates the first {@link View} object to be seen by the user when the program starts.
	 * 
	 * @return A newly created View object
	 */
	protected abstract View initFirstView();
	
	/**
	 * Registers the {@link MouseAdapter} and {@link KeyAdapter} methods for this class.
	 */
	private void registerInputListeners(){
		// Register key event handlers
		KeyAdapter keyListener = new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e){
				handleKeyTyped(e);
			}
			@Override
			public void keyReleased(KeyEvent e){
				handleKeyReleased(e);
			}
		};
		addKeyListener(keyListener);
		
		// Register mouse event handlers
		MouseAdapter listener = new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e){
				handleMouseReleased(e);
			}
		};
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	
	@Override
	public void handleEvent(ViewChangeEvent e){
		List<View> oldViews = e.getOldViews();
		if(oldViews != null){
			oldViews.forEach(view -> view.close());
		}
		List<View> newViews = e.getNewViews();
		if(newViews != null){
			newViews.forEach(view -> view.init(context));
		}
	}
	
	// Event handler for key released events
	protected void handleKeyTyped(KeyEvent e){
		// TODO: Potentially setup a way for multiple layers to be able to be used?
		View view = context.getCurrentView();
		if(view.handleKeyTyped(e)){
			repaint();
			return;
		}
	}
	
	// Event handler for key released events
	protected void handleKeyReleased(KeyEvent e){
		// TODO: Potentially setup a way for multiple layers to be able to be used?
		View view = context.getCurrentView();
		if(view.handleKeyReleased(e)){
			repaint();
			return;
		}
	}
	
	// Event handler for mouse released events
	protected void handleMouseReleased(MouseEvent e){
		// TODO: Potentially setup a way for multiple layers to be able to be used?
		View view = context.getCurrentView();
		if(view.handleMouseReleased(e)){
			repaint();
			return;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g); // Draw the background.
		
		for(View view: context.getCurrentViewStack()){
			view.draw(g);
		}
	}
}
