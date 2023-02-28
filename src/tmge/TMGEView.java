package tmge;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class TMGEView {
	private TMGE model;
	private Scene scene;
	
	public TMGEView(TMGE model, Scene scene) {
		this.model = model;
		this.scene = scene;
		
		setMainMenuScreen();
	}
	
	public void setMainMenuScreen() {
		Button button = new Button("Coolio");
		button.setId("NullGame");
		button.setOnAction(new GameOptionHandler());
		
		StackPane menuLayout = new StackPane();
		menuLayout.getChildren().add(button);
		
		scene.setRoot(menuLayout);;
	}
	
	class GameOptionHandler implements EventHandler<ActionEvent> {
	    @Override
	    public void handle(ActionEvent event) {
	    	System.out.println("Clicked " + ((Button)event.getSource()).getId());
	    }
	};
}
