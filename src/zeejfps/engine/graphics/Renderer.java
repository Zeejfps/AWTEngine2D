package zeejfps.engine.graphics;


public class Renderer {
	
	private final Screen screen;
	private final Bitmap screenBitmap;
	
	public Renderer(Screen screen) {
		this.screen = screen;
		this.screenBitmap = screen.getBitmap();
	}
	
	public void draw(int x, int y, Bitmap bitmap) {
		
		x /= screen.getScale();
		y /= screen.getScale();

		// will be implemented later
		//int winX = (int) Math.round((( x + 1 ) * 0.5) * screenBitmap.getWidth());
		//int winY = (int) Math.round((( 1 - y ) * 0.5) * screenBitmap.getHeight());
		
		//winX -= bitmap.getWidth() * 0.5;
		//winY -= bitmap.getHeight() * 0.5;
		
		for (int yPix = 0; yPix < bitmap.getHeight(); yPix++) {
			
			for (int xPix = 0; xPix < bitmap.getWidth(); xPix++) {
				
				int src = bitmap.getPixel(xPix, yPix);
				screenBitmap.setPixel(xPix+x, yPix+y, src);
				
			}
			
		}
		
	}

}
