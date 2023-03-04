package tmGame;

import java.time.Clock;

import grid.Grid;

public abstract class TileMatchingGame {
	Grid grid;
	GameScreen screen;
	int score;
	static java.time.Clock clock;
	
	public TileMatchingGame(Grid grid) {
		screen = null;
		this.grid = grid;
		score = 0;
		clock = Clock.systemUTC();
	}
	
	public void quit() {
		System.exit(0);
	}
	
	public void updateScore(int delta) {
		score += delta;
	}
	
	public void run() {
		while(!isGameOver()) {
			handleInput();
			onClockTick();
			display();
		}
	}
	
	public abstract boolean isGameOver();
	public abstract void display();
	public abstract void handleInput();
	public abstract void onClockTick();
	
	public GameScreen getScreen() {
		return screen;
	}

	public void setScreen(GameScreen screen) {
		this.screen = screen;
	}

}
