package tmGame;

import grid.Grid;
import tmGame.gameOverConditions.TimeUp;
import tmGame.gameScreen.BejeweledGameScreen;

public class BejeweledGame extends TileMatchingGame{

    public BejeweledGame(Grid grid) {
        super(grid);
        screen = new BejeweledGameScreen();
        gameOver = new TimeUp(clock);
    }

    @Override
    public void onClockTick() {
        
    }
    
}
