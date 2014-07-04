package zeejfps.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Screen {
	
	private final Canvas canvas;

	private final int width, height;
	private final BufferedImage buffer;
	private final Bitmap bitmap;
	
	private int clearColor = 0xff000000;
	
	public Screen(int width, int height, int scale) {
		
		this.width = width;
		this.height = height;
		
		this.buffer = new BufferedImage(width/scale, height/scale, BufferedImage.TYPE_INT_ARGB);
		int[] pixelData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
		this.bitmap = new Bitmap(width/scale, height/scale, pixelData);
		
		canvas = new Canvas();
		canvas.setBackground(Color.BLACK);
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(true);
	}
	
	public void clearBuffer() {
		Arrays.fill(bitmap.getPixels(), clearColor);
	}
	
	public void swapBuffer() {
		
		if (canvas.isDisplayable()) {
			
			BufferStrategy bs = canvas.getBufferStrategy();
			if (bs == null) {
				canvas.createBufferStrategy(2);
				return;
			}
			
			Graphics2D g = (Graphics2D) bs.getDrawGraphics(); 
			g.drawImage(buffer, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
			g.dispose();
			
			bs.show();
		}
		
	}
	
	protected Bitmap getBitmap() {
		return bitmap;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setClearColor(Color color) {
		setClearColor(color.getRGB());
	}
	
	public void setClearColor(int color) {
		canvas.setBackground(new Color(color));
		clearColor = color;
	}

}
