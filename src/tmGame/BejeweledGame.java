package tmGame;

import grid.Grid;
import grid.Position;
import tmGame.InputHandler.BejeweledInputHandler;
import tmGame.gameOverConditions.TimeUp;
import tmGame.gameScreen.BejeweledGameScreen;

public class BejeweledGame extends TileMatchingGame{

    public BejeweledGame(Grid grid) {
        super(grid);
        screen = new BejeweledGameScreen();
        gameOver = new TimeUp(clock);
        inputHandler = new BejeweledInputHandler(this);
    }


    @Override
    public void onClockTick() {
        
    }
    
}
