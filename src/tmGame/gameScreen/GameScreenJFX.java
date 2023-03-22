package tmGame.gameScreen;

import grid.Grid;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import tmGame.TileMatchingGame;

public abstract class GameScreenJFX {
	protected Scene scene;
	// protected GridPane board;
	
	public GameScreenJFX() {
		scene = null;
		// board = new GridPane();
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public int tileWidth(int numCols) {
		return (int) (scene.getWidth() / numCols);
	}

	public int tileHeight(int numRows) {
		return (int) (scene.getHeight() / numRows);
	}

	// public Parent getRoot() {
	// 	return board;
	// }
	
	public abstract void displayGrid(TileMatchingGame game);
}
