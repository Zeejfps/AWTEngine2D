package zeejfps.engine.examples.snakegame;

import java.util.logging.Level;

import zeejfps.engine.core.Game;
import zeejfps.engine.graphics.Bitmap;
import zeejfps.engine.graphics.SpriteSheet;
import zeejfps.engine.utils.Transform;

public class SnakeGame extends Game{

	public static final SpriteSheet SPRITE_SHEET = new SpriteSheet(8, 8, "res/snakegame/spritesheet.png");
	public static final int EMPTY = 0, SNAKE_BODY = 1, SNAKE_HEAD = 2, APPLE = 3;
	public static final int GRID_SIZE = 16;

	private int[][] grid;
	private Snake snake;
	
	public SnakeGame() {
		
		super(640, 480, 2);
		
		setMaxFps(0);
		setMaxUps(10);
		
		setDebugLevel(Level.FINEST);

		screen.setClearColor(0xffA59643);
		
	}

	@Override
	public void init() {
		
		grid = new int[screen.getHeight()/GRID_SIZE][screen.getWidth()/GRID_SIZE];
		snake = new Snake(this, grid);
		snake.spawnApple();

	}

	@Override
	public void update() {

		snake.update();

	}


	long time = System.currentTimeMillis();
	double x = 2, y = 2;
	@Override
	public void render(double interp) {
		 // clears the screen
		screen.clearBuffer();
		
		snake.render();
		
		// swaps the buffer and draws to the screen
		screen.swapBuffer(); 
	}


	@Override
	public void onExit() {
	}

}
