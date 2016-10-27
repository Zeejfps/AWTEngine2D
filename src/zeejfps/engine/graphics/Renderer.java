package zeejfps.engine.graphics;

import zeejfps.engine.utils.Transform;
import zeejfps.engine.utils.math.Matrix3;
import zeejfps.engine.utils.math.Vector2;


public class Renderer {
	
	private final Screen screen;
	private final Bitmap screenBitmap;
	
	public Renderer(Screen screen) {
		this.screen = screen;
		this.screenBitmap = screen.getBitmap();
	}
	
	public void draw(Bitmap bitmap, Transform transform) {
			
		Matrix3 transformation = transform.getMatrix();
		
		for (int yPix = 0; yPix < bitmap.getHeight(); yPix++) {
			
			for (int xPix = 0; xPix < bitmap.getWidth(); xPix++) {
				
				Vector2 position = new Vector2(xPix, yPix);
				Vector2 transformed = Matrix3.mult(transformation, position);
				
				int src = bitmap.getPixel(xPix, yPix);
				screenBitmap.setPixel((int)transformed.x, (int)transformed.y, src);
				
			}
			
		}
		
	}
	
	public void draw(int x, int y, Bitmap bitmap) {
		
		x /= screen.getScale();
		y /= screen.getScale();
		
		for (int yPix = 0; yPix < bitmap.getHeight(); yPix++) {
			
			for (int xPix = 0; xPix < bitmap.getWidth(); xPix++) {
				
				int src = bitmap.getPixel(xPix, yPix);
				screenBitmap.setPixel(xPix+x, yPix+y, src);
				
			}
			
		}
		
	}

}
