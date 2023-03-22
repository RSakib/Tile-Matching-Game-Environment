package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import tile.Tile;
import tmGame.TileMatchingGame;

public abstract class GameScreenJFX {
	protected TileMatchingGame game;
	protected Scene scene;
	protected BorderPane gamePane;
	protected GridPane boardPane;
	protected GridPane scorePane;
	protected Text scoreText;

	private boolean setRoot = false;
	
	public GameScreenJFX(TileMatchingGame game) {
		this.game = game;
		scene = null;
		gamePane = new BorderPane();
		boardPane = new GridPane();
		boardPane.setPrefSize(300, 300);
		boardPane.setMaxSize(300, 300);

		scorePane = new GridPane();
		scoreText = new Text();

		scorePane.getChildren().add(scoreText);

		gamePane.setLeft(scorePane);
		gamePane.setCenter(boardPane);
		gamePane.setRight(new Text("hello"));
	}

	public Scene getScene() {
		return scene;
	}

	public Parent getBoard() {
		return boardPane;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public int tileWidth() {
		return (int) (boardPane.getPrefWidth() / game.getGrid().getNumCols());
	}

	public int tileHeight() {
		return (int) (boardPane.getPrefHeight() / game.getGrid().getNumRows());
	}

	// public Parent getRoot() {
	// 	return board;
	// }
	public void display() {
		Platform.runLater(() -> {
			displayGrid();
			displayScore();
	
			if (! setRoot) {
				scene.setRoot(gamePane);
				setRoot = true;
				System.out.println("Adding root");
			}
		});
	}
	
	public void displayGrid() {
		int rows = game.getGrid().getNumRows();
        int cols = game.getGrid().getNumCols();

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				Position p = new Position(r, c);
				Tile tile = game.visibleTileAt(p);
				displayTile(p, tile);
			}
        }
	}

	public void displayScore() {
		scoreText.setText("Score: " + game.getScore());
	}


	public abstract void displayTile(Position p, Tile t);
}
