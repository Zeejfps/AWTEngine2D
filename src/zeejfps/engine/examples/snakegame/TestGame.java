package zeejfps.engine.examples.snakegame;

import java.util.Random;
import zeejfps.engine.core.Game;
import zeejfps.engine.core.graphics.Bitmap;

public class TestGame extends Game{

	private Bitmap randomBitmap;
	
	public TestGame() {
		
		super(640, 480, 4);
		
		setMaxFps(0);
		setMaxUps(10);
		
	}

	@Override
	public void init() {
		
		randomBitmap = Bitmap.create(40, 40);
		Random rand = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < randomBitmap.getHeight(); i++) {
			
			for (int j = 0; j < randomBitmap.getWidth(); j++) {
				
				randomBitmap.setPixel(j, i, rand.nextInt());
				
			}
			
		}
		
	}

	@Override
	public void update() {


	}

	@Override
	public void render(double interp) {
		canvas.clear();
		canvas.drawBitmap(randomBitmap, 0, 0);
		canvas.update();
	}


	@Override
	public void onExit() {
		
	}

}
