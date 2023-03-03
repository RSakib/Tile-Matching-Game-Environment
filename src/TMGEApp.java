import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tmge.TMGE;
import tmge.TMGEModelController;
import tmge.TMGEView;

public class TMGEApp extends Application{
	
	static final int sWidth = 300;
	static final int sHeight = 300;
	
	
	@Override
	public void start(Stage pStage) throws Exception {
		pStage.setTitle("TMGE");
		
		Scene newScene = new Scene(new StackPane(), sWidth, sHeight);
		pStage.setScene(newScene);
		
		TMGE model = new TMGE();
		TMGEModelController controller = new TMGEModelController(model);
		@SuppressWarnings("unused")
		TMGEView view = new TMGEView(model, controller, newScene);
		
		pStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
