package tmGame;

import grid.Grid;
import tmGame.gameScreen.TetrisGameScreen;

public class TetrisGame extends TileMatchingGame {

    public TetrisGame(Grid grid) {
        super(grid);
        screen = new TetrisGameScreen();
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
        return false;
    }

    @Override
    public void onClockTick() {
        
        
    }
    
}
