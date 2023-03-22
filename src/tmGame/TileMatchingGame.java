package tmGame;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import grid.Grid;
import grid.Position;
import grid.gravity.IGravity;
import grid.matchingPatterns.IMatchingPattern;
import grid.matchingPatterns.Match;
import grid.matchingPatterns.NoMatch;
import tile.Tile;
import tmGame.InputHandler.InputHandlerJFX;
import tmGame.gameOverConditions.GameOverCondition;
import tmGame.gameScreen.GameScreenJFX;

public abstract class TileMatchingGame {
	protected Grid grid;
	protected GameScreenJFX screen;
	protected InputHandlerJFX inputHandler;
	protected GameOverCondition gameOver;
	protected IMatchingPattern[] matchingPatterns;
	protected IGravity gravity;
	protected int score;
	private boolean isRunning;

	protected static java.time.Clock clock = Clock.systemUTC();

	public TileMatchingGame() {
		this.isRunning = true;
	}


	public final void quit() {
		System.exit(0);
	}

	public final void run() {
		// var nextTick = Clock.offset(clock, Duration.ofSeconds(1)).instant();
		while(isRunning()) {
			// if (clock.instant().compareTo(nextTick) > 0) {
				// System.out.println("It has been 1 second");
				// nextTick = Clock.offset(clock, Duration.ofSeconds(1)).instant();
			onClockTick();
			display();
			//}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void display() {
		screen.displayGrid(this);
	}

	public Tile visibleTileAt(Position p) {
		return grid.tileAt(p);
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public void applyGravity() {
		System.out.println("Applying Gravity");
		gravity.applyGravity(grid);
	}

	public void checkGameOver() {
		System.out.println("Checking for game over");
		this.isRunning = !gameOver.isGameOver();
		System.out.println("Game over: " + !this.isRunning);
	}

	public GameScreenJFX getScreen() {
		return screen;
	}

	public int getScore() {
		return score;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setScreen(GameScreenJFX screen) {
		this.screen = screen;
	}

	public void setInputHandler(InputHandlerJFX inputHandler) {
		this.inputHandler = inputHandler;
	}

	public void updateScore(int points) {
		this.score += points;
	}

	protected Match matchAt(Position pos) 
	{
		//Loop through all matching patterns
		for (IMatchingPattern pattern: matchingPatterns)
		{
			Match match = pattern.findMatch(grid, pos);
			if(match.isMatch())
			{
				// mark matched tiles
				for (Position p : match.getPositions()) {
					grid.tileAt(p).setMatched(true);
				}
				return match; //stub, need to reference the design pattern specifically
			}
		}
		return new NoMatch(); //returns no match if no match
	}


	protected List<Position> explodeAt(Position pos) {
		Tile tile = grid.tileAt(pos);
		if (tile.isExploded()) {
			return new ArrayList<>();
		}

		tile.setExploded(true);
		List<Position> allExplodedPositions = new ArrayList<>();
		List<Position> explodedPositions = tile.explode(grid, pos);
		allExplodedPositions.addAll(explodedPositions);
		for (Position explodedPos: explodedPositions) {
			if (! grid.tileAt(explodedPos).equals(pos)) {
				allExplodedPositions.addAll(explodeAt(explodedPos));
			}
		}
		return allExplodedPositions;
	}




	public abstract void onClockTick();
	public abstract boolean matchTiles();
}
