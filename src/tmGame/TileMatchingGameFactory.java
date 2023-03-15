package tmGame;

import grid.TetrisGrid;


public class TileMatchingGameFactory {
	public TileMatchingGame createGame(String title) {
		if(title.contentEquals("NULLGAME")) {
			return new NullGame();
		}
		else if(title.contentEquals("TETRISGAME")) {
			return new TetrisGame(new TetrisGrid());
		}
		return null;
	}
}
