package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.controllor.GameControl;


/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		GameControl game = new GameControl();
		game.startGame(io, false, false);
	}

	public void play(IO io, boolean vertical, boolean bmf) {
		// DO NOT CHANGE. Only here for backwards compatibility
		play(io);
	}
}