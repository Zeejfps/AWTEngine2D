package zeejfps.engine.graphics;

public class Bitmap {
	
	private final int width, height;
	private final int[] pixels;
	
	public Bitmap(int width, int height, int[] pixelData) {
		this.width = width;
		this.height = height;
		this.pixels = pixelData;
	}
	
	public Bitmap(int width, int height) {
		this(width, height, new int[width*height]);
	}
	
	public void setPixel(int x, int y, int color) {
		if (x >= width || x < 0) return;
		if (y >= height || y < 0) return;

		pixels[y * width + x] = color;
	}
	
	public int getRGB(int x, int y) {
		return pixels[y * width + x];
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
	
}
