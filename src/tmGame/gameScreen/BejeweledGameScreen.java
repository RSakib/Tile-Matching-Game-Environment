package tmGame.gameScreen;

import grid.Position;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import tile.Tile;
import tile.BejeweledTiles.HypercubeTile;
import tile.BejeweledTiles.StarTile;
import tile.BejeweledTiles.FlameTile;
import tmGame.BejeweledGame;

public class BejeweledGameScreen extends GameScreenJFX{

    public BejeweledGameScreen() {
        super();
    }

    @Override
    public Node getTileElement(Position pos, Tile tile) {
        double tileWidth = tileWidth();
        double tileHeight = tileHeight();
        
        Shape displayTile = new Rectangle(tileWidth, tileHeight);

        if (tile instanceof FlameTile) {
            Rectangle rect = new Rectangle(tileWidth, tileHeight);
            rect.setArcHeight(20);
            rect.setArcWidth(20);
            displayTile = rect;
        }
        if (tile instanceof StarTile) {
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll( new Double[] {
                tileWidth/2, 0.0,
                0.0, tileHeight,
                tileWidth, tileHeight
            });
            displayTile = triangle;
        }
        if (tile instanceof HypercubeTile) {
            Ellipse circle = new Ellipse(tileWidth/2, tileHeight/2, tileWidth/2, tileHeight/2);
            displayTile = circle;
        }
        
        switch (tile.getColor()) {
            case RED:
                displayTile.setFill(javafx.scene.paint.Color.RED);
                break;
            case ORANGE:
                displayTile.setFill(javafx.scene.paint.Color.ORANGE);
                break;
            case YELLOW:
                displayTile.setFill(javafx.scene.paint.Color.YELLOW);
                break;
            case GREEN:
                displayTile.setFill(javafx.scene.paint.Color.GREEN);
                break;
            case BLUE:
                displayTile.setFill(javafx.scene.paint.Color.BLUE);
                break;
            case PURPLE:
                displayTile.setFill(javafx.scene.paint.Color.PURPLE);
                break;
            case SILVER:
                displayTile.setFill(javafx.scene.paint.Color.GRAY);
                break;
            case MULTICOLOR:
                displayTile.setFill(javafx.scene.paint.Color.BLACK);
                break;
        
            default:
                displayTile.setFill(javafx.scene.paint.Color.WHITE);
                break;
        }

        // outline selected position
        BejeweledGame bejeweledGame = (BejeweledGame) game;
        Position selected = bejeweledGame.selectedPosition();

        displayTile.setStrokeType(StrokeType.INSIDE);
        if (selected != null && selected.equals(pos)) {
            displayTile.setStroke(javafx.scene.paint.Color.LIGHTGRAY);
        } else {
            displayTile.setStroke(javafx.scene.paint.Color.BLACK);
        }

        return displayTile;
    }
    
}
