package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import tile.Color;

public class BejeweledGameScreen extends GameScreenJFX{

    @Override
    public void displayGrid(Grid grid) {
        GridPane gameBoard = new GridPane();

        for (int i = 0; i < grid.getNumRows(); i++) {
			for (int j = 0; j < grid.getNumCols(); j++) {

				Rectangle tile = new Rectangle(10, 10);
                
                switch (grid.tileAt(new Position(i,j)).getColor()) {
                    case RED:
                        tile.setFill(javafx.scene.paint.Color.RED);
                        break;
                    case ORANGE:
                        tile.setFill(javafx.scene.paint.Color.ORANGE);
                        break;
                    case YELLOW:
                        tile.setFill(javafx.scene.paint.Color.YELLOW);
                        break;
                    case GREEN:
                        tile.setFill(javafx.scene.paint.Color.GREEN);
                        break;
                    case BLUE:
                        tile.setFill(javafx.scene.paint.Color.BLUE);
                        break;
                    case PURPLE:
                        tile.setFill(javafx.scene.paint.Color.PURPLE);
                        break;
                    case WHITE:
                        tile.setFill(javafx.scene.paint.Color.WHITE);
                        break;
                
                    default:
                        tile.setFill(javafx.scene.paint.Color.GRAY);
                        break;
                }

				tile.setStroke(javafx.scene.paint.Color.BLACK);

				gameBoard.add(new StackPane(tile), j, i);

			}
        }

        scene.setRoot(gameBoard);
    }
    
}
