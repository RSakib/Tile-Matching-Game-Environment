package tmge;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TMGEView {
	private TMGE model;
	private TMGEModelController controller;
	private Scene scene;
	Optional<String> name = null;
	
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
		
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    
	    Text numPlayerText = new Text(controller.getNumPlayers());

	    Button buttonIncrement = new Button("Increment Players");
	    buttonIncrement.setOnAction(e -> {
	    	controller.incrementPlayers();
	    	numPlayerText.setText(controller.getNumPlayers());
	    });

	    Button buttonDecrement = new Button("Decrement Players");
	    buttonDecrement.setOnAction(e -> {
	    	controller.decrementPlayers();
	    	numPlayerText.setText(controller.getNumPlayers());
	    });
	    
	    
	    numPlayerText.setScaleX(2);
	    numPlayerText.setScaleY(2);
	    numPlayerText.setFill(Color.WHITE);
	    
	    hbox.getChildren().addAll(buttonIncrement, buttonDecrement, numPlayerText);
		
		StackPane menuLayout = new StackPane();
		menuLayout.getChildren().add(button);
		
		BorderPane mainMenuLayout = new BorderPane();
		mainMenuLayout.setTop(hbox);
		mainMenuLayout.setCenter(menuLayout);
		
		scene.setRoot(mainMenuLayout);
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
				for(int i = 0; i < model.getCurrentNumPlayers(); i++) {
					name = null;
					Platform.runLater(() -> {
						name = popUpPlayerPrompt().showAndWait();
					});
					
					// Find a better way to do Nothing in a while loop
					// Because the java compiler optimizes this wait away when its necessary
					while (name == null) {
						System.out.print("");
					}
					controller.findOrCreatePlayer(name.get());

					Parent prevRoot = scene.getRoot();
					controller.runGame(((Button)event.getSource()).getId(), scene);
					scene.setRoot(prevRoot);
				}
            }
        };
			
		// Run the task in a background thread
		Thread backgroundThread = new Thread(task);
		// Terminate the running thread if the application exits
		backgroundThread.setDaemon(true);
		// Start the thread
		backgroundThread.start();
			
    }   
	
	public TextInputDialog popUpPlayerPrompt() {
		TextInputDialog td = new TextInputDialog("enter any text");
		  
        // setHeaderText
        td.setHeaderText("enter your name");
        
        return td;
	}
}
