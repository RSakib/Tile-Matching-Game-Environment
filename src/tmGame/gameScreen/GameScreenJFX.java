package tmGame.gameScreen;

import grid.Grid;
import grid.Position;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tile.Tile;
import tmGame.TileMatchingGame;

public abstract class GameScreenJFX {
	protected TileMatchingGame game;
	protected Scene scene;
	protected GridPane gamePane;
	protected GridPane boardPane;
	protected GridPane scorePane;
	protected Text scoreText;

	private boolean setRoot = false;
	
	public GameScreenJFX() {
		scene = null;
		gamePane = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(15);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(70);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(15);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(100);
		gamePane.getColumnConstraints().addAll(col1,col2,col3);
		gamePane.getRowConstraints().addAll(row1);

		boardPane = new GridPane();

		scorePane = new GridPane();
		scoreText = new Text();
		scorePane.getChildren().add(scoreText);

		gamePane.add(scorePane, 0, 0);
		gamePane.add(boardPane, 1, 0);
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

	public double tileWidth() {
		return boardPane.getWidth() / game.getGrid().getNumCols();
	}

	public double tileHeight() {
		return boardPane.getHeight() / game.getGrid().getNumRows();
	}

	public int minTileWidth() {
		return 30;
	}

	public int minTileHeight() {
		return 30;
	}

	// public Parent getRoot() {
	// 	return board;
	// }
	public void display(TileMatchingGame game) {
		this.game = game;
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
		boardPane.getChildren().clear();

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				Position p = new Position(r, c);
				Tile tile = game.visibleTileAt(p);
				Node tileElement = getTileElement(p, tile);
				boardPane.add(new AnchorPane(tileElement), c, r);
				GridPane.setConstraints(tileElement, c, r, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
			}
        }
	}

	public void displayScore() {
		scoreText.setText("Score: " + game.getScore());
	}


	public abstract Node getTileElement(Position p, Tile t);
}
