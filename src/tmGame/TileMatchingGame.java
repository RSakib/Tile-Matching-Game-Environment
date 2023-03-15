package tmGame;

import java.time.Clock;

import grid.Grid;
import tmGame.gameScreen.GameScreen;

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
	
	public TileMatchingGame() {
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
			handleInput();
			onClockTick();
			display();
		}
	}
	
	public abstract boolean isGameOver();
	public abstract void handleInput();
	public abstract void onClockTick();

	public void display() {
		screen.displayGrid(grid);
	}
	
	public GameScreen getScreen() {
		return screen;
	}

	public void setScreen(GameScreen screen) {
		this.screen = screen;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
