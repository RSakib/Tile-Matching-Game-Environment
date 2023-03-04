package tmge;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class TMGEView {
	private TMGE model;
	private TMGEModelController controller;
	private Scene scene;
	
	public TMGEView(TMGE model, TMGEModelController controller, Scene scene) {
		this.model = model;
		this.controller = controller;
		this.scene = scene;
		
		setMainMenuScreen();
	}
	
	public void setMainMenuScreen() {
		Button button = new Button("Coolio");
		button.setId("NULLGAME");
		button.setOnAction(new GameOptionHandler());
		
		StackPane menuLayout = new StackPane();
		menuLayout.getChildren().add(button);
		
		scene.setRoot(menuLayout);;
	}
	
	class GameOptionHandler implements EventHandler<ActionEvent> {
	    @Override
	    public void handle(ActionEvent event) {
	    	startGame(event);
	    }
	};
	
	public void startGame(ActionEvent event) 
    {
        // Create a Runnable
        Runnable task = new Runnable()
        {
            public void run()
            {
            	Parent prevRoot = scene.getRoot();
    	    	controller.runGame(((Button)event.getSource()).getId(), scene);
    	    	scene.setRoot(prevRoot);
            }
        };
 
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }   
}
