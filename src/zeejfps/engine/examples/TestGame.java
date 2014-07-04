package zeejfps.engine.examples;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import zeejfps.engine.core.Game;
import zeejfps.engine.graphics.Bitmap;


public class TestGame extends Game{

	Bitmap test;
	long time;
	
	public TestGame() {
		super(640, 480, 2);
		
		try {
			
			BufferedImage image = ImageIO.read(new File("res/Logo.png"));
			test = new Bitmap(image.getWidth(), image.getHeight(), image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onStart() {
		time = System.currentTimeMillis();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (System.currentTimeMillis() - time >= 1000) {
			test.rotate180();
			time = System.currentTimeMillis();
		}
	}

	@Override
	public void render(double interp) {
		screen.clearBuffer(); // clears the screen
		if (test != null) {
			renderer.draw(0, 0, test);
		}
		screen.swapBuffer(); // swaps the buffer and draws to the screen
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

}
