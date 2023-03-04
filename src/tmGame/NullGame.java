package tmGame;

import grid.Grid;

public class NullGame extends TileMatchingGame {

	static int i = 100;
	
	public NullGame(Grid grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		if(i == 0) {
			i = 100;
			return true;
		}
		else {
			i--;
			return false;
		}
		
	}

	@Override
	public void display() {
		screen.displayGrid(grid);

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
