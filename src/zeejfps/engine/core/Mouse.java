package zeejfps.engine.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import zeejfps.engine.graphics.Screen;

public class Mouse {

	private int xPos = 0, yPos = 0;
	private boolean leftDown = false;
	
	private final Screen screen;
	
	public Mouse(Screen screen) {
		MouseEventListener mel = new MouseEventListener();
		screen.getCanvas().addMouseListener(mel);
		screen.getCanvas().addMouseMotionListener(mel);
		screen.getCanvas().addMouseWheelListener(mel);
		
		this.screen = screen;
	}
	
	public int getScaledX() {
		return xPos / screen.getScale();
	}
	
	public int getScaledY() {
		return yPos / screen.getScale();
	}
	
	public int getRealX() {
		return xPos;
	}
	
	public int getRealY() {
		return yPos;
	}
	
	public boolean isLeftDown() {
		return leftDown;
	}
	
	private class MouseEventListener implements MouseMotionListener, MouseWheelListener, MouseListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			xPos = e.getX();
			yPos = e.getY();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1)
				leftDown = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1)
				leftDown = false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
