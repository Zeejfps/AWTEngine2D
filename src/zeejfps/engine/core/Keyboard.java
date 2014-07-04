package zeejfps.engine.core;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is responsible for catching and handling all the events keyEvents
 * on a given canvas and providing easy way to check if a key has been pressed.
 * 
 * @author Zeejfps
 * 
 */
public class Keyboard {

	public static int NUM_KEYS = 256;
	
	private boolean[] down = new boolean[NUM_KEYS];
	private boolean[] released = new boolean[NUM_KEYS];
	
	/**
	 * @param canvas the canvas on witch to listen for keyEvents.
	 */
	public Keyboard(Canvas canvas) {
		
		KeyEventListener kel = new KeyEventListener();
		canvas.addKeyListener(kel);
		
	}
	
	/** 
	 * @param keyCode the keyCode of the keyboard key you want to check, use KeyEvent.
	 * @return true if the key is currently pressed down.
	 */
	public boolean keyDown(int keyCode) {
		return down[keyCode];
	}
	
	/**
	 * 
	 * @param keyCode the keyCode of the keyboard key you want to check, use KeyEvent.
	 * @return true if the key has been released.
	 */
	public boolean keyReleased(int keyCode) {
		return released[keyCode];
	}
	
	protected void reset() {
		for (int i = 0; i < NUM_KEYS; i++) {
			released[i] = false;
		}
	}
	
	/*
	 * This class will actually listen to the key events and then forward them.
	 */
	private class KeyEventListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			down[e.getKeyCode()] = true;
			e.consume();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			down[e.getKeyCode()] = true;
			e.consume();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			released[e.getKeyCode()] = true;
			down[e.getKeyCode()] = false;
			e.consume();
		}
		
	}
	
}
