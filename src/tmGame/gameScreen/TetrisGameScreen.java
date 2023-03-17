package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tile.EmptyTile;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TetrisGameScreen extends JavaFXScreen implements GameScreen{

    @Override
    public void displayGrid(Grid grid) {
        // TODO Auto-generated method stub
		GridPane gameBoard = new GridPane();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				Rectangle tile = new Rectangle(50, 50);
                if(grid.tileAt(new Position(i,j)) instanceof EmptyTile) {
                    tile.setFill(Color.GREY);
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
