package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.scene.paint.Color;
import tile.EmptyTile;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TetrisGameScreen extends GameScreenJFX{

    @Override
    public void displayGrid(Grid grid) {
        // TODO Auto-generated method stub
		GridPane gameBoard = new GridPane();

		for (int i = 4; i < grid.getNumRows(); i++) {
			for (int j = 0; j < grid.getNumCols(); j++) {

				Rectangle tile = new Rectangle(10, 10);
                if(grid.tileAt(new Position(i,j)) instanceof EmptyTile) {
                    tile.setFill(Color.GREY);
                    if (i < 5) {
                        tile.setFill(Color.BLACK);
                    }
                }
                else {
                    tile.setFill(Color.RED);
                }
				tile.setStroke(Color.BLACK);

				gameBoard.add(new StackPane(tile), j, i);

			}
        }

		scene.setRoot(gameBoard);
		
	}
    
}
