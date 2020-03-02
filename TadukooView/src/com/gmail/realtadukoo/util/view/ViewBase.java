package com.gmail.realtadukoo.util.view;

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
	
	/**
	 * Constructs a ViewBase using the given {@link ViewBaseParams}.
	 * 
	 * @param params A ViewBaseParams pojo containing basic settings
	 */
	protected ViewBase(ViewBaseParams params){
		// Initialize this
		initialize();
		
		// Set background color and a few other parameters
		setBackground(params.getBackground());
		setDoubleBuffered(true);
		setFocusable(true);
		
		// Create a frame to display the view
		JFrame frame = new JFrame();
		frame.setTitle(params.getTitle());
		frame.setContentPane(this);
		if(params.isFullscreen()){
			// Fullscreen mode
			frame.setUndecorated(true);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}else{
			// Windowed mode
			frame.setSize(params.getWidth(), params.getHeight());
			setPreferredSize(new Dimension(params.getWidth(), params.getHeight()));
			frame.setResizable(false);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	private final void initialize(){
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
	private final void registerInputListeners(){
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
	public final void handleEvent(ViewChangeEvent e){
		// Close any views being removed from the stack
		List<View> oldViews = e.getOldViews();
		if(oldViews != null){
			oldViews.forEach(view -> view.close());
		}
		
		// Initialize any views being added to the stack
		List<View> newViews = e.getNewViews();
		if(newViews != null){
			newViews.forEach(view -> view.init(context));
		}
	}
	
	// Event handler for key released events
	protected final void handleKeyTyped(KeyEvent e){
		// TODO: Potentially setup a way for multiple layers to be able to be used?
		View view = context.getCurrentView();
		if(view.handleKeyTyped(e)){
			repaint();
			return;
		}
	}
	
	// Event handler for key released events
	protected final void handleKeyReleased(KeyEvent e){
		// TODO: Potentially setup a way for multiple layers to be able to be used?
		View view = context.getCurrentView();
		if(view.handleKeyReleased(e)){
			repaint();
			return;
		}
	}
	
	// Event handler for mouse released events
	protected final void handleMouseReleased(MouseEvent e){
		// TODO: Potentially setup a way for multiple layers to be able to be used?
		View view = context.getCurrentView();
		if(view.handleMouseReleased(e)){
			repaint();
			return;
		}
	}
	
	@Override
	protected final void paintComponent(Graphics g){
		// Draw the background
		super.paintComponent(g);
		
		// Draw the current View stack
		for(View view: context.getCurrentViewStack()){
			view.draw(g);
		}
	}
}
