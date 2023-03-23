package tmGame;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import grid.Grid;
import grid.Position;
import grid.gravity.IGravity;
import grid.matchingPatterns.IMatchingPattern;
import grid.matchingPatterns.Match;
import grid.matchingPatterns.NoMatch;
import tile.Tile;
import tmGame.gameOverConditions.GameOverCondition;
import tmGame.gameScreen.GameScreenJFX;
import tmGame.inputHandlers.InputHandler;

public abstract class TileMatchingGame {
	protected Grid grid;
	protected GameScreenJFX screen;
	protected InputHandler inputHandler;
	protected GameOverCondition gameOver;
	protected IMatchingPattern[] matchingPatterns;
	protected IGravity gravity;
	protected int score;
	protected long sleepMilliseconds;
	private boolean isRunning;

	protected static java.time.Clock clock = Clock.systemUTC();

	public void initializeGame(		
		Grid grid, GameScreenJFX screen, InputHandler input, 
		GameOverCondition gameOver, IMatchingPattern[] matchingPatterns, IGravity gravity, double secondsPerTick) 
	{
		this.grid = grid;
		this.screen = screen;
		this.inputHandler = input;
		this.gameOver = gameOver;
		this.matchingPatterns = matchingPatterns;
		this.gravity = gravity;
		this.isRunning = true;
		this.score = 0;
		this.sleepMilliseconds = (long) (1000*secondsPerTick);
	}


	public final void quit() {
		System.exit(0);
	}

	public final void run() {
		inputHandler.registerEventHandlers();
		// var nextTick = Clock.offset(clock, Duration.ofSeconds(1)).instant();
		display();
		while(isRunning()) {
			// if (clock.instant().compareTo(nextTick) > 0) {
				// System.out.println("It has been 1 second");
				// nextTick = Clock.offset(clock, Duration.ofSeconds(1)).instant();
			onClockTick();
			display();
			//}
			try {
				Thread.sleep(sleepMilliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void display() {
		screen.display(this);
	}

	public Tile visibleTileAt(Position p) {
		return grid.tileAt(p);
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public void applyGravity() {
		gravity.applyGravity(grid);
	}

	public void checkGameOver() {
		this.isRunning = !gameOver.isGameOver(this);
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

	public void setInputHandler(InputHandler inputHandler) {
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
