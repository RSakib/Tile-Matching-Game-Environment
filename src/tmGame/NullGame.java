package tmGame;

import tmGame.gameScreen.NullGameScreen;

public class NullGame extends TileMatchingGame {

	static final int LOOPS = 10;
	static final int DURATION = 100;
	static int i = LOOPS;
	
	public NullGame() {
		super(null);
		screen = new NullGameScreen();
	}

	@Override
	public void isGameOver() {
		// TODO Auto-generated method stub
		if(i == 0) {
			i = LOOPS;
			isGameRunning = false;
		}
		else {
			i--;
			isGameRunning = true;
		}
		
	}

	@Override
	public void display() {
		super.display();

	}

	@Override
	public void handleInput() {
		return;

	}

	@Override
	public void onClockTick() {
		try {
			Thread.sleep(DURATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;

	}

}
