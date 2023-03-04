package tmGame;

import grid.Grid;

public class NullGame extends TileMatchingGame {

	static int i = 10;
	
	public NullGame(Grid grid) {
		super(grid);
		screen = new NullGameScreen();
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		if(i == 0) {
			i = 10;
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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;

	}

}
