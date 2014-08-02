package zeejfps.engine.core.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Canvas {
	
	private final int width, height, scale;
	private final java.awt.Canvas awtCanvas;

	private final BufferedImage buffer;
	private final int[] pixelData;

	private int clearColor = 0xff000000;
	
	public Canvas(int width, int height, int scale) {
		
		this.width = width;
		this.height = height;
		this.scale = scale;
		
		this.buffer = new BufferedImage(width/scale, height/scale, BufferedImage.TYPE_INT_ARGB);
		pixelData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
		
		awtCanvas = new java.awt.Canvas();
		awtCanvas.setBackground(Color.BLACK);
		awtCanvas.setPreferredSize(new Dimension(width, height));
		awtCanvas.setFocusable(true);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getScale() {
		return scale;
	}

	public void drawBitmap(Bitmap bitmap, Transform transform) {
		
	}
	
	public void drawBitmap(Bitmap bitmap, double x, double y) {
		
		int w = buffer.getWidth();
		int h = buffer.getHeight();
		
		int winX = (int) Math.round((( x + 1 ) * 0.5) * w);
		int winY = (int) Math.round((( 1 - y ) * 0.5) * h);
		
		int xPix;
		int yPix;
		
		for (int i = 0; i < bitmap.getHeight(); i++) {
			
			for (int j = 0; j < bitmap.getWidth(); j++) {
			
				xPix = j + winX;
				yPix = i + winY;
				if (yPix > 0 && yPix < h && xPix > 0 && xPix < w) {
					pixelData[yPix*w + xPix] = bitmap.getPixel(j, i);
				}
			}
		}
	}
	
	public void setClearColor(Color color) {
		setClearColor(color.getRGB());
	}
	
	public void setClearColor(int color) {
		this.clearColor = color;
	}
	
	public void clear() {
		for (int i = 0; i < pixelData.length; i++) {
			pixelData[i] = clearColor;
		}
	}
	
	public void update() {
		
		if (awtCanvas.isDisplayable()) {

			BufferStrategy bs = awtCanvas.getBufferStrategy();
			if (bs == null) {
				awtCanvas.createBufferStrategy(2);
				return;
			}

			Graphics2D g = (Graphics2D) bs.getDrawGraphics(); 
			g.drawImage(buffer, 0, 0, width, height, null);
			g.dispose();

			bs.show();
		}
		
	}
	
	public java.awt.Canvas getAWTCanvas() {
		return awtCanvas;
	}
	
}
