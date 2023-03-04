package tmGame;

import grid.Grid;
import javafx.scene.Scene;

public abstract class GameScreen {
	protected Scene scene;
	
	public Scene getScreen() {
		return scene;
	}

	public GameScreen(Scene screen) {
		this.scene = screen;
	}
	
	public abstract void displayGrid(Grid grid);
}
