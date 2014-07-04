package zeejfps.engine.graphics;


public class Renderer {

	private final Bitmap screenBitmap;
	
	public Renderer(Screen screen) {
		this.screenBitmap = screen.getBitmap();
	}
	
	public void draw(double x, double y, Bitmap bitmap) {
		
		int winX = (int) Math.round((( x + 1 ) * 0.5) * screenBitmap.getWidth());
		int winY = (int) Math.round((( 1 - y ) * 0.5) * screenBitmap.getHeight());
		
		winX -= bitmap.getWidth() * 0.5;
		winY -= bitmap.getHeight() * 0.5;
		
		for (int yPix = 0; yPix < bitmap.getHeight(); yPix++) {
			
			for (int xPix = 0; xPix < bitmap.getWidth(); xPix++) {
				
				int src = bitmap.getRGB(xPix, yPix);
				if (src != 0xffff00ff)
					screenBitmap.setPixel(xPix+winX, yPix+winY, src);
				
			}
			
		}
		
	}

}
