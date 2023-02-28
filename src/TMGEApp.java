import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tmge.TMGE;
import tmge.TMGEView;

public class TMGEApp extends Application{
	
	private TMGE model;
	private TMGEView view;
	
	static final int sWidth = 300;
	static final int sHeight = 300;
	
	
	@Override
	public void start(Stage pStage) throws Exception {
		Scene newScene = new Scene(new StackPane(), sWidth, sHeight);
		pStage.setScene(newScene);
		
		model = new TMGE();
		view = new TMGEView(model, newScene);
		pStage.setTitle("TMGE");
		
		
		pStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
