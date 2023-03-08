package tmGame;

public class TileMatchingGameFactory {
	public TileMatchingGame createGame(String title) {
		if(title.contentEquals("NULLGAME")) {
			return new NullGame();
		}
		return null;
	}
}
