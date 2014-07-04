package zeejfps.engine.examples;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import zeejfps.engine.core.Game;

public class Launcher {

	public static void main(String[] args) {
		
		/*
		 * This will create the game as well as the JFrame in witch to place the game's canvas in.
		 */
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Game game = new TestGame();
				
				JFrame window = new JFrame("Test Game v0.1");
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setResizable(false);
				
				// getting the canvas from the game's screen
				window.add(game.getScreen().getCanvas());
				
				window.pack();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				
				// starting the game
				game.start();
				
			}
		});
		
	}
	
}
