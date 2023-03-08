package tmGame.gameScreen;

import grid.Grid;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class NullGameScreen extends JavaFXScreen implements GameScreen {
	private Scene scene;
	
	public NullGameScreen() {
		scene = null;
	}

	@Override
	public void displayGrid(Grid grid) {
		// TODO Auto-generated method stub
		StackPane gridLayout = new StackPane();
		System.out.println("PLEASE Work");
		gridLayout.getChildren().add(new Text("This is a Null Game, get out of here :["));
		
		scene.setRoot(gridLayout);
		
	}

	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
