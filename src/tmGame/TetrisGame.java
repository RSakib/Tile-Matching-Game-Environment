package tmGame;

import grid.TetrisGrid;
import tmGame.InputHandler.FallingBlockInputHandler;
import tmGame.gameOverConditions.GridOverflowed;
import tmGame.gameScreen.TetrisGameScreen;

public class TetrisGame extends TileMatchingGame {

    public TetrisGame(TetrisGrid grid) {
        super(grid);
        screen = new TetrisGameScreen();
        gameOver = new GridOverflowed(grid);
        inputHandler = new FallingBlockInputHandler(this);
    }

    @Override
    public void onClockTick() {
        TetrisGrid tGrid = ((TetrisGrid)grid);
        tGrid.moveFallerDown();
        isGameOver();
    }
    
}
