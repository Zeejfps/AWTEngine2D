package zeejfps.engine.core;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse {

	private int xPos = 0, yPos = 0;
	private boolean leftDown = false;
	
	public Mouse(Canvas canvas) {
		MouseEventListener mel = new MouseEventListener();
		canvas.addMouseListener(mel);
		canvas.addMouseMotionListener(mel);
		canvas.addMouseWheelListener(mel);
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
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
