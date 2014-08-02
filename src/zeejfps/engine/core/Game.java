package zeejfps.engine.core;

import zeejfps.engine.core.graphics.Canvas;
import zeejfps.engine.core.input.Keyboard;
import zeejfps.engine.core.input.Mouse;
/**
 * This class is responsible for running the game loop as well as containing 
 * all necessary variables and objects that are used to interact with the game.
 * 
 * @author Zeejfps
 */
public abstract class Game {
	
	// Variables that will be used widely in a game
	protected final Mouse mouse;
	protected final Canvas canvas;
	protected final Keyboard keyboard;
	
	// Variable that holds the reference to the main game thread
	private final Thread gameThread;
	private final GameLoop gameLoop;
	
	// Couple of variables used to keep trap of fps and running / pausing the game
	private int maxUpdates = 30, maxFps = 60;
	private volatile boolean running = false;;
	
	/**
	 * Creates a game with a screen of given width and height and a buffer scaled down by the scale.
	 * 
	 * @param width the width of the screen in pixels.
	 * @param height the height of the screen in pixels.
	 * @param scale the amount you want the image to be scaled down by, higher = better performance.
	 */
	public Game(int width, int height, int scale) {

		gameLoop = new GameLoop();
		gameThread = new Thread(gameLoop);
		
		canvas = new Canvas(width, height, scale);
		mouse = new Mouse(canvas);
		keyboard = new Keyboard(canvas);
	}
	
	/**
	 * This method is the entry point of the game.
	 * When called the game thread is started and the game loop begins to run,
	 * onStart method is then called.
	 */
	public final void run() {
		
		if (!running) {
			running = true;
			init();
			gameThread.start();
			
		} 
		
	}
	
	/**
	 * Called when the game is started.
	 * This is where you want to do all of your creating logic or 
	 * anything you want right before the main loop is started.
	 */
	public abstract void init();
	
	/**
	 * Called over and over in game loop at specified max ups.
	 * Do all of your game logic in this method.
	 */
	public abstract void update();
	
	/**
	 * Called over and over in game loop at specified max fps, however, the updates are prioritized.
	 * Do your rendering logic in here.
	 * @param interp how far between updates this is called.
	 */
	public abstract void render(double interp);
	
	/**
	 * Called when the game is stopped.
	 * Do all of your clean up logic in here.
	 */
	public abstract void onExit();
	
	/**
	 * This method will set running flag to false and then waits until the game thread has 
	 * completed running, then calls the onStop method.
	 * 
	 */
	public final void exit() {
		if (running) {
			running = false;
			onExit();
			System.exit(0);
		}
	}
	
	/**
	 * This method sets the preferred max frames per second.
	 * @param maxFps preferred frames per second.
	 */
	public void setMaxFps(int maxFps) {
		this.maxFps = maxFps > 0 ? maxFps : 9999;
	}
	
	/**
	 * This method sets the preferred max updates per second.
	 * @param maxUps preferred updates per second.
	 */
	public void setMaxUps(int maxUps) {
		maxUpdates = maxUps > 0 ? maxUps : 9999;
	}
	
	/**
	 * @return the game screen object.
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 * @return the keyboard object.
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}
	
	/**
	 * @return the mouse object.
	 */
	public Mouse getMouse() {
		return mouse;
	}
	
	/**
	 * 
	 * @return true if the game's running flag is set to true.
	 */
	public boolean isRunning() {
		return running;
	}
	
	/*
	 * This is where the magic happens.
	 * The game loop will try to update the game at the maxUps regardless of frame rate.
	 */
	private class GameLoop implements Runnable {

	    private final double nsPerSec = 1000000000;

		@Override
		public void run() {
			
			// for debugging purposes
	    	//int updates = 0, frames = 0;
	    	
	    	// variables to keep track of how long the updates and renders take.
	    	double debugTime = System.currentTimeMillis();
			double previous = System.nanoTime();
			double start = System.nanoTime();
			double lag = 0.0;
			
			// we want this running as long as the game is running
			while (running) {
				
				// calculating the updates and frames per second in terms of nano seconds
				double nsPerUpdate = nsPerSec / maxUpdates;
				double nsPerRender = nsPerSec / maxFps;
				
				double current = System.nanoTime();
				double elapsed = current - previous;
				
				previous = current;
				lag += elapsed;

				keyboard.reset();
				// if enough time has passed, update.
				while (lag >= nsPerUpdate) {
					
					update();
					//updates ++;
					lag -= nsPerUpdate;
				}

				// if enough time has passed, render.
				if (current - start >= nsPerRender) {
					//frames ++;
					render(lag / nsPerUpdate);
					start = System.nanoTime();
				}
			
				// this is here to log the fps and updates the game is actualy running at.
				if (System.currentTimeMillis() - debugTime >= 1000) {
					
					//updates = 0;
					//frames = 0;
					debugTime = System.currentTimeMillis();
					
				}
				
			}
			
		}
		
	}
	
}
