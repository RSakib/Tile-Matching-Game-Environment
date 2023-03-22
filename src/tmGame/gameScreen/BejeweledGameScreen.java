package tmGame.gameScreen;

import grid.BejeweledGrid;
import grid.Grid;
import grid.Position;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import tile.Color;
import tile.SameColorExplode;
import tile.Tile;
import tile.BejeweledTiles.SameColorPowerUp;
import tile.BejeweledTiles.SquareTilePowerUp;

public class BejeweledGameScreen extends GameScreenJFX{

    @Override
    public void displayGrid(Grid grid) {
        GridPane gameBoard = new GridPane();

        for (int i = 0; i < grid.getNumRows(); i++) {
			for (int j = 0; j < grid.getNumCols(); j++) {
                Position pos = new Position(i, j);
                Tile tile = grid.tileAt(pos);

                int tileWidth = tileWidth(grid);
                int tileHeight = tileHeight(grid);
				Shape displayTile = new Rectangle(tileWidth, tileHeight);

                if (tile instanceof SquareTilePowerUp) {
                    ((Rectangle) displayTile).setArcHeight(10);
                    ((Rectangle) displayTile).setArcWidth(10);
                }
                if (tile instanceof SameColorPowerUp) {
                    displayTile = new Ellipse(tileWidth/2, tileHeight/2);
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
                BejeweledGrid bejeweledGrid = (BejeweledGrid) grid;
                Position selected = bejeweledGrid.selectedPosition();
                if (selected != null && selected.equals(pos)) {
                    displayTile.setStroke(javafx.scene.paint.Color.LIGHTGRAY);
                } else {
                    displayTile.setStroke(javafx.scene.paint.Color.BLACK);
                }

				gameBoard.add(new StackPane(displayTile), j, i);

			}
        }

        scene.setRoot(gameBoard);
    }
    
}
