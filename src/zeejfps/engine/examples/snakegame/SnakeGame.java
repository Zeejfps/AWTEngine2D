package zeejfps.engine.examples.snakegame;

import zeejfps.engine.core.Game;
import zeejfps.engine.graphics.SpriteSheet;

public class SnakeGame extends Game{

	public static final SpriteSheet SPRITE_SHEET = new SpriteSheet(8, 8, "res/snakegame/spritesheet.png");
	public static final int EMPTY = 0, SNAKE_BODY = 1, SNAKE_HEAD = 2, APPLE = 3;
	public static final int GRID_SIZE = 8;
	
	
	
	private int[][] grid;
	private Snake snake;
	
	public SnakeGame() {
		
		super(640, 480, 2);
		
		setMaxFps(30);
		setMaxUps(10);
		
		screen.setClearColor(0xffA59643);
	}

	@Override
	public void onStart() {
		

		grid = new int[screen.getScaledHeight()/GRID_SIZE][screen.getScaledWidth()/GRID_SIZE];
		snake = new Snake(this, grid);
		snake.spawnApple();

	}

	@Override
	public void onPause() {

	}

	@Override
	public void update() {

		snake.update();

	}

	@Override
	public void render(double interp) {
		 // clears the screen
		screen.clearBuffer();
		
		snake.render();
		
		// swaps the buffer and draws to the screen
		screen.swapBuffer(); 
	}

	@Override
	public void onResume() {

	}

	@Override
	public void onStop() {

	}

}
