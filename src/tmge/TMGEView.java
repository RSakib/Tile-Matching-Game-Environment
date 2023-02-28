package tmge;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TMGEView {
	public Scene getMainMenuScreen(Stage primaryStage, int w, int h) {
		Button button = new Button("Coolio");
		StackPane buttonLayout = new StackPane();
		Scene buttonScene = new Scene(buttonLayout, w, h);
		button.setOnMouseClicked(event ->{primaryStage.setScene(buttonScene);});
		
		StackPane menuLayout = new StackPane();
		menuLayout.getChildren().add(button);
		
		Scene menuScene = new Scene(menuLayout, w, h);
		
		return menuScene;
	}
}
