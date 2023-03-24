package tmGame;


public class TileMatchingGameFactory {
	public TileMatchingGame createGame(String title) {
		if(title.contentEquals("TETRISGAME")) {
			return new TetrisGame();
		}
		else if(title.contentEquals("BEJEWELEDGAME")) {
			return new BejeweledGame();
		}
		return null;
	}
}
