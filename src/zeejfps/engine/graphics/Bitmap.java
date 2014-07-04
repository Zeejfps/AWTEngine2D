package zeejfps.engine.graphics;

/**
 * This is a utility class that stores the color of each pixel in a single int[] that later can be modified and rendered to screen.
 * @author Zeejfps
 */
public class Bitmap {
	
	// the width and height of the bitmap.
	private int width, height;
	// the color values for each pixel
	private int[] pixels;
	
	/**
	 * Creates a bitmap with given width, height, and pixel color data.
	 * @param width the width of the bitmap.
	 * @param height the height of the bitmap.
	 * @param pixelData the color data of each pixel.
	 */
	public Bitmap(int width, int height, int[] pixelData) {
		this.width = width;
		this.height = height;
		this.pixels = pixelData;
	}
	
	/**
	 * Creates a bitmap with given width and height and initializes all the pixels to 0.
	 * @param width the width of the bitmap.
	 * @param height the height of the bitmap.
	 */
	public Bitmap(int width, int height) {
		this(width, height, new int[width*height]);
	}
	
	/**
	 * Creates a copy of passed in bitmap.
	 * @param copy the bitmap you want to copy.
	 */
	public Bitmap(Bitmap copy) {
		this(copy.width, copy.height, copy.pixels);
	}
	
	/**
	 * This method sets an individual pixel to a given color. 
	 * Using this formula to determine witch pixel: (y * width + x)
	 * @param x the x location of the pixel.
	 * @param y the y location of the pixel.
	 * @param color the color of the pixel, a HEX value.
	 */
	public void setPixel(int x, int y, int color) {
		if (x >= width || x < 0) return;
		if (y >= height || y < 0) return;

		pixels[y * width + x] = color;
	}
	
	/**
	 * This method returns the HEX color value of a pixel at given x and y.
	 * @param x the x location of the pixel.
	 * @param y the y location of the pixel.
	 * @return HEX color value of the pixel.
	 */
	public int getPixel(int x, int y) {
		return pixels[y * width + x];
	}
	
	/**
	 * 
	 * @return the width of the bitmap.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return the height of the bitmap.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * 
	 * @return the array of pixels containing the color data.
	 */
	public int[] getPixels() {
		return pixels;
	}
	
	/**
	 * This method will rotate the bitmap by 90 degrees counter clock wise.
	 * @return 'this' bitmap after rotation.
	 */
	public Bitmap rotate90CCW() {
		
		int newWidth = height;
		int newHeight = width;
		int[] rotatedPixels = new int[newWidth * newHeight];
		
		for (int i = 0; i < newHeight; i++) {
			
			for (int j = 0; j < newWidth; j++) {
				
				rotatedPixels[i * newWidth + j] = getPixel(newHeight - i -1, j);

			}
			
		}
		
		pixels = rotatedPixels;
		width = newWidth;
		height = newHeight;
		
		return this;
	}
	
	/**
	 * This method will rotate the bitmap by 90 degrees clock wise.
	 * @return 'this' bitmap after rotation.
	 */
	public Bitmap rotate90CW() {
		
		int newWidth = height;
		int newHeight = width;
		int[] rotatedPixels = new int[newWidth * newHeight];
		
		for (int i = 0; i < newHeight; i++) {
			
			for (int j = 0; j < newWidth; j++) {
				
				rotatedPixels[i * newWidth + j] = getPixel(i, newWidth - j - 1);
						
			}
			
		}
		
		pixels = rotatedPixels;
		width = newWidth;
		height = newHeight;
		
		return this;
	}
	
	/**
	 * This method will rotate the bitmap by 180 degrees.
	 * @return 'this' bitmap after rotation.
	 */
	public Bitmap rotate180() {
		
		int[] rotatedPixels = new int[width * height];
		
		for (int i = 0; i < height; i++) {
			
			for (int j = 0; j < width; j++) {
				
				rotatedPixels[i * width + j] = getPixel(width-1-j, height-1-i);
						
			}
			
		}
		
		pixels = rotatedPixels;
		return this;
	}
	
	// These static methods are here if you want to rotate a bitmap without modifying the original.
	
	/**
	 * 
	 * @param bitmap bitmap that you want to rotate.
	 * @return returns a rotated copy of the given bitmap.
	 */
	public static Bitmap rotate90CCW(Bitmap bitmap) {
		Bitmap result = new Bitmap(bitmap);
		result.rotate90CCW();
		
		return result;
	}
	
	/**
	 * 
	 * @param bitmap bitmap that you want to rotate.
	 * @return returns a rotated copy of the given bitmap.
	 */
	public static Bitmap rotate90CW(Bitmap bitmap) {
		Bitmap result = new Bitmap(bitmap);
		result.rotate90CW();
		
		return result;
	}
	
	/**
	 * 
	 * @param bitmap bitmap that you want to rotate.
	 * @return returns a rotated copy of the given bitmap.
	 */
	public static Bitmap rotate180(Bitmap bitmap) {
		Bitmap result = new Bitmap(bitmap);
		result.rotate180();
		
		return result;
	}
	
}
