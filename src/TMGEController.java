import javafx.application.Application;
import javafx.stage.Stage;
import tmge.TMGE;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class TMGEController extends Application{
	
	TMGE tmge;
	
	static final int sWidth = 300;
	static final int sHeight = 300;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		tmge = new TMGE();
		primaryStage.setTitle("TMGE");
		
		Button button = new Button("Coolio");
		StackPane buttonLayout = new StackPane();
		Scene buttonScene = new Scene(buttonLayout, sWidth, sHeight);
		button.setOnMouseClicked(event ->{primaryStage.setScene(buttonScene);});
		
		StackPane menuLayout = new StackPane();
		menuLayout.getChildren().add(button);
		
		Scene menuScene = new Scene(menuLayout, sWidth, sHeight);
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
