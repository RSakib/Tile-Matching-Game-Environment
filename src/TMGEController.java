import javafx.application.Application;
import javafx.stage.Stage;
import tmge.TMGE;
import tmge.TMGEView;

public class TMGEController extends Application{
	
	private TMGE model;
	private TMGEView view;
	
	static final int sWidth = 300;
	static final int sHeight = 300;
	
	
	@Override
	public void start(Stage pStage) throws Exception {
		model = new TMGE();
		view = new TMGEView();
		pStage.setTitle("TMGE");
		
		pStage.setScene(view.getMainMenuScreen(pStage, sWidth, sHeight));
		pStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
