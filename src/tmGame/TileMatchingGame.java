package tmGame;

import grid.Grid;

public abstract class TileMatchingGame {
	Grid grid;
	GameScreen screen;
	int score;
	static java.time.Clock clock;
	
	public TileMatchingGame(Grid grid, GameScreen screen) {
		this.grid = grid;
		this.screen = screen;
		score = 0;
	}
	
	public void quit() {
		System.exit(0);
	}
	
	public void updateScore(int delta) {
		score += delta;
	}
	
	public void run() {
		while(!isGameOver()) {
			display();
			handleInput();
			onClockTick();
		}
	}
	
	public abstract boolean isGameOver();
	public abstract void display();
	public abstract void handleInput();
	public abstract void onClockTick();

}
