package tmGame.gameScreen;

import grid.Grid;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NullGameScreen extends GameScreenJFX{
	@Override
	public void displayGrid(Grid grid) {
		// TODO Auto-generated method stub
		GridPane gameBoard = new GridPane();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				Rectangle tile = new Rectangle(50, 50);
				tile.setFill(Color.BURLYWOOD);
				tile.setStroke(Color.BLACK);

				Text text = new Text("sweet");
				text.setFont(Font.font(12));
				gameBoard.add(new StackPane(tile, text), j, i);
				
			}
    }
		System.out.println("Working");


		scene.setRoot(gameBoard);
		
	}


}
