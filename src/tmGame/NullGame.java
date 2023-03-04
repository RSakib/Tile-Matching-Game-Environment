package tmGame;

import grid.Grid;

public class NullGame extends TileMatchingGame {

	public NullGame(Grid grid, GameScreen screen) {
		super(grid, screen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void display() {
		this.screen.displayGrid(grid);

	}

	@Override
	public void handleInput() {
		return;

	}

	@Override
	public void onClockTick() {
		return;

	}

}
