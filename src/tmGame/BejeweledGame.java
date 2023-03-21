package tmGame;

import grid.Grid;
import grid.Position;
import tmGame.gameOverConditions.TimeUp;
import tmGame.gameScreen.BejeweledGameScreen;

public class BejeweledGame extends TileMatchingGame{
    private Position selectedPosition;

    public BejeweledGame(Grid grid) {
        super(grid);
        screen = new BejeweledGameScreen();
        gameOver = new TimeUp(clock);
        selectedPosition = null;
    }

    public void newSelectedPosition(Position p) {
        if (selectedPosition == null) {
            selectedPosition = p;
        } else {
            // if p adjacent to selectedPosition swap tiles
            grid.swapTilesAt(p, selectedPosition);
            selectedPosition = null;
        }
    }

    @Override
    public void onClockTick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClockTick'");
    }
    
}
