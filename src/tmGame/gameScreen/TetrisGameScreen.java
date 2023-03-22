package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.scene.paint.Color;
import tile.EmptyTile;
import tile.Tile;
import tmGame.TileMatchingGame;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TetrisGameScreen extends GameScreenJFX{

    public TetrisGameScreen(TileMatchingGame game) {
        super(game);
    }

    @Override
    public void displayTile(Position pos, Tile tile) {
        Rectangle displayTile = new Rectangle(tileWidth(), tileHeight());

        if(tile.isEmpty()) {
            displayTile.setFill(Color.GREY);
            if (pos.row < 4) {
                displayTile.setFill(Color.BLACK);
            }
        }
        else {
            displayTile.setFill(Color.RED);
        }
        displayTile.setStroke(Color.BLACK);

        boardPane.add(new StackPane(displayTile), pos.col, pos.row);
    }
    
}
