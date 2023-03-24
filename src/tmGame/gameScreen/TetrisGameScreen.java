package tmGame.gameScreen;

import java.util.Map;

import grid.Position;
import javafx.scene.paint.Color;
import tile.TileColor;
import tile.Tile;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.Node;

public class TetrisGameScreen extends GameScreenJFX {
    private static Map<TileColor, Color> colorMap = Map.of(
        TileColor.SILVER, Color.CYAN,
        TileColor.BLUE, Color.BLUE,
        TileColor.ORANGE, Color.ORANGE,
        TileColor.YELLOW, Color.YELLOW,
        TileColor.GREEN, Color.LAWNGREEN,
        TileColor.PURPLE, Color.PURPLE,
        TileColor.RED, Color.RED
    );

    @Override
    public Node getTileElement(Position pos, Tile tile) {
        if (pos.row < 4) {
            return new Rectangle(0,0);
        }

        Rectangle displayTile = new Rectangle(tileWidth(), tileHeight());

        if(tile.isEmpty()) {
            displayTile.setFill(Color.GREY);
            
        }
        else {
            displayTile.setFill(colorMap.get(tile.getColor()));
        }

        displayTile.setStrokeType(StrokeType.INSIDE);
        displayTile.setStroke(Color.BLACK);

        return displayTile;
    }
    
}
