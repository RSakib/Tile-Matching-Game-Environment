package tmge;

import javafx.scene.Scene;
import tmGame.TileMatchingGame;
import tmGame.gameScreen.JavaFXScreen;

public class TMGEModelController {
	private TMGE model;
	
	public TMGEModelController(TMGE model) {
		this.model = model;
	}
	
	public void runGame(String gameName, Scene pScene) {
		for(int i = 0; i < model.getCurrentNumPlayers(); i++) {
			//Player currentPlayer = promptPlayerName();
			TileMatchingGame game = model.createGame(gameName);
			if(game.getScreen() instanceof JavaFXScreen) {
				System.out.println("set scene to be primary scene");
				((JavaFXScreen)game.getScreen()).setScene(pScene);
			}
			game.run();
		}
		
		return;
	}
	
	public void promptForPlayer() {
		return;
	}
	
	public void incrementPlayers() {
		model.setCurrentNumPlayers(model.getCurrentNumPlayers() + 1);
	}
	
	public void decrementPlayers() {
		if(model.getCurrentNumPlayers() != 2) {
			model.setCurrentNumPlayers(model.getCurrentNumPlayers() - 1);
		}
	}
	
}
