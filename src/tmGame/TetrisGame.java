package tmGame;

import grid.Grid;
import grid.TetrisGrid;
import tmGame.InputHandler.FallingBlockInputHandler;
import tmGame.gameScreen.TetrisGameScreen;

public class TetrisGame extends TileMatchingGame {

    public TetrisGame(TetrisGrid grid) {
        super(grid);
        screen = new TetrisGameScreen();
        gameOver = new GridOverflowed(grid);
        inputHandler = new FallingBlockInputHandler(this);
    }

    @Override
    public void display() {
        super.display();
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub

        return gameOver.isGameOver();
    }

    @Override
    public void onClockTick() {
        TetrisGrid tGrid = ((TetrisGrid)grid);
        tGrid.moveFallerDown();
        isGameRunning = !isGameOver();
    }
    
}
