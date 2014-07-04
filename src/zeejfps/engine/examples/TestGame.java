package zeejfps.engine.examples;
import zeejfps.engine.core.Game;


public class TestGame extends Game{

	public TestGame() {
		super(640, 480, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		start();
	}

	@Override
	public void render(double interp) {
		screen.clearBuffer(); // clears the screen
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
