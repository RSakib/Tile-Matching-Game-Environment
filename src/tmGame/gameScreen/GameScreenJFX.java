package tmGame.gameScreen;

import grid.Grid;
import javafx.scene.Scene;

public abstract class GameScreenJFX {
	protected Scene scene;
	
	public GameScreenJFX() {
		scene = null;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public abstract void displayGrid(Grid grid);
}
