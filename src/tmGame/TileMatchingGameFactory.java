package tmGame;

import grid.*;


public class TileMatchingGameFactory {
	public TileMatchingGame createGame(String title) {
		if(title.contentEquals("NULLGAME")) {
			return new NullGame();
		}
		else if(title.contentEquals("TETRISGAME")) {
			return new TetrisGame();
		}
		else if(title.contentEquals("BEJEWELEDGAME")) {
			return new BejeweledGame();
		}
		return null;
	}
}
