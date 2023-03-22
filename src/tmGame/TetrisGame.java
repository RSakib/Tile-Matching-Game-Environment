package tmGame;

import java.util.Random;
import java.util.Set;

import grid.Grid;
import grid.Position;
import grid.IFallableBlocks.IFallable;
import grid.IFallableBlocks.TetrisIBlock;
import grid.IFallableBlocks.TetrisJBlock;
import grid.IFallableBlocks.TetrisLBlock;
import grid.IFallableBlocks.TetrisOBlock;
import grid.IFallableBlocks.TetrisSBlock;
import grid.IFallableBlocks.TetrisTBlock;
import grid.IFallableBlocks.TetrisZBlock;
import grid.gravity.DropRowsDown;
import grid.matchingPatterns.HorizontalMatchingPattern;
import grid.matchingPatterns.IMatchingPattern;
import grid.matchingPatterns.Match;
import tmGame.InputHandler.FallingBlockInputHandler;
import tmGame.gameOverConditions.GridOverflowed;
import tmGame.gameScreen.TetrisGameScreen;

public class TetrisGame extends FallingBlockGame {
    private static int ROWS = 20;
    private static int COLS = 10;
    private static int INVISIBLE_ROWS = 4;

    public TetrisGame() {
        super();
        this.grid = new Grid(ROWS + INVISIBLE_ROWS, COLS);
        this.screen = new TetrisGameScreen();
        this.gameOver = new GridOverflowed(this.grid, INVISIBLE_ROWS);
        this.matchingPatterns = new IMatchingPattern[] {
            new HorizontalMatchingPattern(COLS)
        };
        this.gravity = new DropRowsDown();
        this.inputHandler = new FallingBlockInputHandler(this);
    }

    @Override
    public void onClockTick() {
        moveFallerDown();
    }

    @Override
    public void applyGravity() {
        super.applyGravity();
        checkGameOver();
    }

    @Override
    public boolean matchTiles() {
        int rowsMatched = 0;
        // Mark matched tiles
        for (int row = 0; row < grid.getNumRows(); row++) {
            Position pos = new Position(row, 0);
            if (! grid.tileAt(pos).isMatched()) {
                Match m = matchAt(pos);
                if (m.isMatch()) {
                    rowsMatched += 1;
                }
            }
        }
        score += scorePerRow(rowsMatched);

        // explode tiles
        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                Position pos = new Position(row, col);
                if (grid.tileAt(pos).isMatched()) {
                    explodeAt(pos);
                }
            }
        }

        grid.removeExplodedTiles();
        
        return rowsMatched > 0;
    }

    @Override
    public IFallable createNewFaller() {
        IFallable newFaller;
        Random randomGenerator = new Random();
        Position spawnPosition = new Position(INVISIBLE_ROWS - 1, COLS/2-1);

        int i = randomGenerator.nextInt(7);
        if (i == 0) {
            newFaller = new TetrisOBlock(spawnPosition);
        } else if (i == 1) {
            newFaller = new TetrisIBlock(spawnPosition);
        } else if (i == 2) {
            newFaller = new TetrisLBlock(spawnPosition);
        } else if (i == 3) {
            newFaller = new TetrisJBlock(spawnPosition);
        } else if (i == 4) {
            newFaller = new TetrisSBlock(spawnPosition);
        } else if (i == 5){
            newFaller = new TetrisZBlock(spawnPosition);
        } else {
            newFaller = new TetrisTBlock(spawnPosition);
        }
        
        // for (Position point : newFaller.getBlock().keySet()) {
        //     if (!(tileAt(point) instanceof EmptyTile)) {
        //         return null;
        //     }
        // }
        return newFaller;
    }


    @Override
    public void rotateFaller() {
        IFallable currentFaller = getCurrentFaller();
        if (currentFaller == null) {
            return;
        }
        currentFaller.rotateClockwise();
        Set<Position> rotatedPositions = currentFaller.getBlock().keySet();

        for (Position p: rotatedPositions) {
            if (!grid.validPosition(p) || !grid.tileAt(p).isEmpty()) {
                // can't rotate faller, rotate it back
                currentFaller.rotateCounterClockwise();
                System.out.println(p);
                return;
            }
        }
    }


    private int scorePerRow(int rowsCleared) {
        if (rowsCleared == 0) {
            return 0;
        } else if (rowsCleared == 1) {
            return 40;
        } else if (rowsCleared == 2) {
            return 100;
        } else if (rowsCleared == 3) {
            return 300;
        } else if (rowsCleared == 4) {
            return 1200;
        } else {
            return 1000000;
        }
    }
    
}
