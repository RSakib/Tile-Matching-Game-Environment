package tmGame;

import grid.NullGrid;

public class TileMatchingGameFactory {
	public TileMatchingGame createGame(String title) {
		if(title.contentEquals("NULLGAME")) {
			return new NullGame(new NullGrid(0,0));
		}
		return null;
	}
}
