package tmge;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TMGEView {
	private TMGE model;
	private TMGEModelController controller;
	private Scene currentScene;
	private Parent mainMenu;
	private Parent playerView;
	String playerName = "";
	
	public TMGEView(TMGE model, TMGEModelController controller, Scene scene) {
		this.model = model;
		this.controller = controller;
		this.currentScene = scene;
		
		mainMenu = setMainMenuScreen();
		playerView = setPlayerView();

		scene.setRoot(mainMenu);
	}
	
	public Parent setMainMenuScreen() {
		class GameOptionHandler implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				startGame(event);
			}
		};

		Button button = new Button("Player LeaderBoard");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				playerView = setPlayerView()
				currentScene.setRoot(playerView);
			}
		});
		Button tetrisButton = new Button("Tetris");
		tetrisButton.setId("TETRISGAME");
		tetrisButton.setOnAction(new GameOptionHandler());
		
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
		
		VBox buttonPanel = new VBox(
			button, tetrisButton
		);

		buttonPanel.setAlignment(Pos.CENTER);
		buttonPanel.setSpacing(10);

		StackPane menuLayout = new StackPane(
			buttonPanel
		);
		
		BorderPane mainMenuLayout = new BorderPane();
		mainMenuLayout.setTop(hbox);
		mainMenuLayout.setCenter(menuLayout);
		
		return mainMenuLayout;
	}

	public Parent setPlayerView() {
		Text leaderboardText = new Text("LeaderBoard");
		Button backButton = new Button("Back to Main Menu");
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				currentScene.setRoot(mainMenu);
			}
			
		});
		FlowPane topBar = new FlowPane(backButton, leaderboardText);
		topBar.setAlignment(Pos.CENTER);


		VBox PlayerLayout = new VBox();
		PlayerLayout.getChildren().add(topBar);
		for (Player player : model.getPlayers()) {

			PlayerLayout.getChildren().add(new Text(player.getUsername() + " " + player.getHighScore()));
		}
		

		return PlayerLayout;
	}
	
	
	
	public void startGame(ActionEvent event) 
    {
        // Create a Runnable
        Runnable task = new Runnable()
        {
            public void run()
            {
				for(int i = 0; i < model.getCurrentNumPlayers(); i++) {
					final CountDownLatch latch = new CountDownLatch(1);
					Platform.runLater(() -> {
						var popup = popUpPlayerPrompt().showAndWait();
						while (!popup.isPresent()) {
							popup = popUpPlayerPrompt().showAndWait();
						}
						playerName = popup.get();
						latch.countDown();
					});
					
					synchronized(playerName) {
						try {
							latch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.findOrCreatePlayer(playerName);
					}
					

					Parent prevRoot = currentScene.getRoot();
					controller.runGame(((Button)event.getSource()).getId(), currentScene);
					currentScene.setRoot(prevRoot);
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
