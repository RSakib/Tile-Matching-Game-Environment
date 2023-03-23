package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.scene.paint.Color;
import tile.EmptyTile;
import tile.Tile;
import tmGame.TileMatchingGame;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TetrisGameScreen extends GameScreenJFX {
    private TileMatchingGame game;

    @Override
    public Node getTileElement(Position pos, Tile tile) {
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

        displayTile.setStrokeType(StrokeType.INSIDE);
        displayTile.setStroke(Color.BLACK);

        return displayTile;
    }
    
}
