import javafx.application.Application;
import javafx.stage.Stage;
import tmge.TMGE;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class TMGEController extends Application{
	
	TMGE tmge;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		tmge = new TMGE();
		primaryStage.setTitle("TMGE");
		
		Button button = new Button("Coolio");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
