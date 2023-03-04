package tmGame;

import grid.Grid;
import javafx.stage.Screen;

public abstract class GameScreen {
	Screen screen;
	
	public Screen getScreen() {
		return screen;
	}

	public GameScreen(Screen screen) {
		this.screen = screen;
	}
	
	public abstract void displayGrid(Grid grid);
}
