package tmGame;

import grid.Grid;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class NullGameScreen extends GameScreen {

	public NullGameScreen(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void displayGrid(Grid grid) {
		// TODO Auto-generated method stub
		StackPane gridLayout = new StackPane();
		System.out.println("PLEASE Work");
		gridLayout.getChildren().add(new Text("This is a Null Game, get out of here :["));
		
		scene.setRoot(gridLayout);
		
	}

}
