package zeejfps.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class SpriteSheet {

	private final int gridWidth, gridHeight;
	private Bitmap bitmap;
	
	public SpriteSheet(int gridWidth, int gridHeight, String dir) {
		
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		
		try {
			
			BufferedImage image = ImageIO.read(new File(dir));
			int[] pixelData = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
			bitmap = new Bitmap(image.getWidth(), image.getHeight(), pixelData);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Bitmap getSprite(int xPos, int yPos, int width, int height) {
		
		int xOffset = xPos * gridWidth;
		int yOffset = yPos * gridHeight;
		
		Bitmap temp = new Bitmap(width, height); 
		
		for (int yPix = yOffset, i = 0; yPix < bitmap.getHeight(); yPix++, i++) {
			
			for (int xPix = xOffset, j = 0; xPix < bitmap.getWidth(); xPix++, j++) {
				
				temp.setPixel(j, i, bitmap.getRGB(xPix, yPix));
				
			}
			
		}
		
		return temp;
	}
	
}
