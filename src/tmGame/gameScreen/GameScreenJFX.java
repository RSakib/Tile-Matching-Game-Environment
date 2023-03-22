package tmGame.gameScreen;

import grid.Grid;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

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

	public int tileWidth(Grid g) {
		return (int) (scene.getWidth() / g.getNumCols());
	}

	public int tileHeight(Grid g) {
		return (int) (scene.getHeight() / g.getNumRows());
	}
	
	public abstract void displayGrid(Grid grid);
}
