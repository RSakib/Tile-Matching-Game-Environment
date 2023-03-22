package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.scene.paint.Color;
import tile.EmptyTile;
import tmGame.TileMatchingGame;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TetrisGameScreen extends GameScreenJFX{

    @Override
    public void displayGrid(TileMatchingGame game) {
        // TODO Auto-generated method stub
		GridPane gameBoard = new GridPane();
        int rows = game.getGrid().getNumRows();
        int cols = game.getGrid().getNumCols();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				Rectangle tile = new Rectangle(10, 10);
                if(game.visibleTileAt(new Position(i,j)).isEmpty()) {
                    tile.setFill(Color.GREY);
                    if (i < 4) {
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
