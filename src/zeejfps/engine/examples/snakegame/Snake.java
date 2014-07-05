package zeejfps.engine.examples.snakegame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import zeejfps.engine.core.Game;
import zeejfps.engine.graphics.Bitmap;
import zeejfps.engine.math.Vector2;

public class Snake {
	
	public static final Vector2 
	
		NORTH = new Vector2(0, -1), 
		EAST = new Vector2(1, 0),
		SOUTH = new Vector2(0, 1),
		WEST = new Vector2(-1, 0);
			
	
	private final Bitmap snakeHead;
	private final Bitmap snakeBody;
	private final Bitmap apple;
	
	private final int[][] grid;
	private final Game game;
	
	private Vector2 direction = NORTH;
	private Vector2 headPos;
	private Queue<Vector2> bodyPos = new LinkedList<Vector2>();
	
	public Snake(Game game, int[][] grid) {
		
		this.grid = grid;
		this.game = game;
		
		snakeHead = SnakeGame.SPRITE_SHEET.getSprite(0, 0, 8, 8);
		snakeBody = SnakeGame.SPRITE_SHEET.getSprite(1, 0, 8, 8);
		apple = SnakeGame.SPRITE_SHEET.getSprite(2, 0, 8, 8);
		
		int y = grid.length - 5;
		int x = grid[y].length / 2;
		
		headPos = new Vector2(x,y);
		
		grid[y][x] = SnakeGame.SNAKE_HEAD;
	}

	public void update() {
		
		if (game.getKeyboard().keyDown(KeyEvent.VK_W) && direction != SOUTH) {
			
			if(direction == EAST)
				snakeHead.rotate90CCW();
			else if (direction == WEST)
				snakeHead.rotate90CW();
			
			direction = NORTH;
			
		} 
		
		if (game.getKeyboard().keyDown(KeyEvent.VK_D) && direction != WEST) {
			
			if(direction == SOUTH)
				snakeHead.rotate90CCW();
			else if (direction == NORTH)
				snakeHead.rotate90CW();
			
			direction = EAST;
			
		} 
		
		if (game.getKeyboard().keyDown(KeyEvent.VK_S) && direction != NORTH) {
			
			if(direction == WEST)
				snakeHead.rotate90CCW();
			else if (direction == EAST)
				snakeHead.rotate90CW();
			
			direction = SOUTH;
			
		} 
		
		if (game.getKeyboard().keyDown(KeyEvent.VK_A) && direction != EAST) {
			
			if(direction == NORTH)
				snakeHead.rotate90CCW();
			else if (direction == SOUTH)
				snakeHead.rotate90CW();
			
			
			direction = WEST;
			
		}
		
		move();
		
	}
	
	public void render() {
		
		for (int i = 0; i < grid.length; i++) {
			
			for (int j = 0; j < grid[i].length; j++) {
				
				
				int x = j * SnakeGame.GRID_SIZE;
				int y = i * SnakeGame.GRID_SIZE;
				
				if (grid[i][j] == SnakeGame.SNAKE_HEAD) {
					
					game.getRenderer().draw(x, y, snakeHead);
					
				} else if (grid[i][j] == SnakeGame.SNAKE_BODY) {
					
					game.getRenderer().draw(x, y, snakeBody);
					
				} else if (grid[i][j] == SnakeGame.APPLE) {
					
					game.getRenderer().draw(x, y, apple);
					
				}
				
			}
			
		}
	
	}
	
	public void move() {
		
		for (int i = 0; i < grid.length; i++) {
			
			for (int j = 0; j < grid[i].length; j++) {
				
				int id = grid[i][j];
				if (id == SnakeGame.SNAKE_HEAD) {
					
					int x = (int) (j + direction.x);
					int y = (int) (i + direction.y);
					
					if (y < 0 || y >= grid.length || x < 0 || x >= grid[y].length) {
						game.exit();
						return;
					}
					
					switch (grid[y][x]) {
					
					case SnakeGame.APPLE:
						grow();
						spawnApple();
						break;
						
					case SnakeGame.SNAKE_BODY:
						game.exit();
						break;
					
					}
					
					grid[y][x] = SnakeGame.SNAKE_HEAD;
					headPos.x = x; headPos.y = y;

					Vector2 lastpos = bodyPos.poll();
					if (lastpos != null) {
						grid[i][j] = SnakeGame.SNAKE_BODY;
						grid[(int) lastpos.y][(int) lastpos.x] = SnakeGame.EMPTY;
						bodyPos.add(new Vector2(j, i));
					} else {
						grid[i][j] = SnakeGame.EMPTY;
					}
					
					return;
					
				}
				
			}
			
		}
		
	}
	
	public void grow() {

		Vector2[] positions = bodyPos.toArray(new Vector2[bodyPos.size()]);
		
		if (positions.length > 1) {
			
			Vector2 lastPos = positions[positions.length-1];

			int dx = (int) (positions[positions.length - 2].x - lastPos.x);
			int dy = (int) (positions[positions.length - 2].y - lastPos.y);
			
			System.out.println(dx + " " + dy);
			
			bodyPos.add(new Vector2(lastPos.x + dx, lastPos.y + dy));
			
		} else {
			
			bodyPos.add(new Vector2(headPos));
			
		}

	}
	
	public void spawnApple() {
		
		List<Vector2> open = new ArrayList<>();
		
		for (int i = 0; i < grid.length; i++) {
			
			for (int j = 0; j < grid[i].length; j++) {
		
				if (grid[i][j] == SnakeGame.EMPTY) {
					
					open.add(new Vector2(j, i));
					
				}
				
			}
		
		}
		
		Random rand = new Random(System.currentTimeMillis());
		Vector2 randSpot = open.get(rand.nextInt(open.size()));
		
		grid[(int) randSpot.y][(int) randSpot.x] = SnakeGame.APPLE;
		
	}
	
}











