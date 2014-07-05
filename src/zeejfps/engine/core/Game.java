package zeejfps.engine.core;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import zeejfps.engine.graphics.Renderer;
import zeejfps.engine.graphics.Screen;
/**
 * This class is responsible for running the game loop as well as containing 
 * all necessary variables and objects that are used to interact with the game.
 * 
 * @author Zeejfps
 */
public abstract class Game {
	
	// Variables that will be used widely in a game
	protected final Mouse mouse;
	protected final Screen screen;
	protected final Renderer renderer;
	protected final Keyboard keyboard;
	
	// Variable that holds the reference to the main game thread
	private final Thread gameThread;
	private final GameLoop gameLoop;
	
	// Couple of variables used to keep trap of fps and running / pausing the game
	private int maxUpdates = 30, maxFps = 60;
	private volatile boolean running = false;;
	
	// Variables used for debugging
	private final Logger debugger = Logger.getLogger("Debugger");
	private final ConsoleHandler debugHandler = new ConsoleHandler();
	
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
		
		screen = new Screen(width, height, scale);
		renderer = new Renderer(screen);
		keyboard = new Keyboard(screen.getCanvas());
		mouse = new Mouse(screen.getCanvas());
	
		debugger.setUseParentHandlers(false);
		debugger.setLevel(Level.FINEST);
		debugger.addHandler(debugHandler);
		
		debugHandler.setLevel(Level.WARNING);
		debugHandler.setFormatter(new Formatter() {
			
			@Override
			public String format(LogRecord record) {
				
				StringBuilder sb = new StringBuilder();
				sb.append("[").append(record.getLevel()).append("]: ");
				sb.append(record.getMessage());
				sb.append("\n");
				
				return sb.toString();
			}
		});
	}
	
	/**
	 * This method is the entry point of the game.
	 * When called the game thread is started and the game loop begins to run,
	 * onStart method is then called.
	 */
	public final void run() {
		
		if (!running) {
			debugger.info("Game Started.");
			running = true;
			init();
			gameThread.start();
			
		} else {
			debugger.warning("Game already running!");
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
			debugger.info("Game is exiting!");
			running = false;
			onExit();
			System.exit(0);
		} else {
			debugger.warning("Game is not running!");
		}
	}
	
	/**
	 * This method sets the level of debug that will be printed in the console.
	 * @param level the highest level of debug you want printed in console.
	 */
	public void setDebugLevel(Level level) {
		debugHandler.setLevel(level);
	}
	
	private void pollInput() {
		keyboard.reset();
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
	public Screen getScreen() {
		return screen;
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
	 * @return the renderer.
	 */
	public Renderer getRenderer() {
		return renderer;
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
	    	int updates = 0, frames = 0;
	    	
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

				// if enough time has passed, update.
				while (lag >= nsPerUpdate) {
					
					update();
					updates ++;
					pollInput();
					lag -= nsPerUpdate;
				}

				// if enough time has passed, render.
				if (current - start >= nsPerRender) {
					frames ++;
					render(lag / nsPerUpdate);
					start = System.nanoTime();
				}
			
				// this is here to log the fps and updates the game is actualy running at.
				if (System.currentTimeMillis() - debugTime >= 1000) {
					
					debugger.fine("Updates: " + updates + " | Frames: " + frames);
					updates = 0;
					frames = 0;
					debugTime = System.currentTimeMillis();
					
				}
				
			}
			
		}
		
	}
	
}
