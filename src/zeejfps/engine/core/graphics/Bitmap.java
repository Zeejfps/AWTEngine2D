package zeejfps.engine.core.graphics;

import java.awt.image.BufferedImage;

/**
 * This is a utility class that stores the color of each pixel in a single int[] that later can be modified and rendered to screen.
 * @author Zeejfps
 */
public class Bitmap {
	
	private final int width, height;
	private final int[] pixels;
	
	private Bitmap(int width, int height, int[] pixelData) {
		
		this.width = width;
		this.height = height;
		this.pixels = pixelData;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public void setPixel(int x, int y, int color) {
		pixels[y * width + x] = color;
	}
	
	public int getPixel(int x, int y) {
		return pixels[y*width + x];
	}
	
	public static Bitmap create(int width, int height, int[] pixelData) {
		return new Bitmap(width, height, pixelData);
	}
	
	public static Bitmap create(int width, int height) {
		
		int[] pixelData = new int[width*height];
		for (int i = 0; i < pixelData.length; i++) {
			pixelData[i] = 0;
		}
		
		return new Bitmap(width, height, pixelData);
	}
	
	public static Bitmap create(BufferedImage image) {
		
		int[] pixelData = new int[image.getWidth() * image.getHeight()];
		pixelData = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		
		return new Bitmap(image.getWidth(), image.getHeight(), pixelData);
	}
	
}
