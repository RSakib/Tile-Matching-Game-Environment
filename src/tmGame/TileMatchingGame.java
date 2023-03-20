package tmGame;

import java.time.Clock;
import java.time.Duration;

import grid.Grid;
import tmGame.InputHandler.InputHandlerJFX;
import tmGame.gameScreen.GameScreenJFX;

public abstract class TileMatchingGame {
	Grid grid;
	GameScreenJFX screen;
	static java.time.Clock clock;
	InputHandlerJFX inputHandler;
	GameOverCondition gameOver;
	boolean isGameRunning;
	
	public TileMatchingGame(Grid grid) {
		screen = null;
		this.grid = grid;
		clock = Clock.systemUTC();
		isGameRunning = true;
	}
	

	
	public void quit() {
		System.exit(0);
	}
	
	public void run() {
		var nextTick = Clock.offset(clock, Duration.ofSeconds(1)).instant();
		while(isGameRunning) {
			if (clock.instant().compareTo(nextTick) > 0) {
				System.out.println("It has been 1 second");
				nextTick = Clock.offset(clock, Duration.ofSeconds(1)).instant();
				handleInput();
				onClockTick();
				display();
			}
			
		}
	}
	
	public abstract boolean isGameOver();
	public abstract void handleInput();
	public abstract void onClockTick();

	public void display() {
		screen.displayGrid(grid);
	}
	
	public GameScreenJFX getScreen() {
		return screen;
	}

	public void setScreen(GameScreenJFX screen) {
		this.screen = screen;
	}

	public int getScore() {
		return grid.getScore();
	}

	public Grid getGrid() {
		return grid;
	}

}
