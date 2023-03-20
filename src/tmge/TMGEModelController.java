package tmge;

import javafx.scene.Scene;
import tmGame.TileMatchingGame;

public class TMGEModelController {
	private TMGE model;
	
	public TMGEModelController(TMGE model) {
		this.model = model;
	}
	
	public void runGame(String gameName, Scene pScene) {
		
		TileMatchingGame game = model.createGame(gameName);
		game.getScreen().setScene(pScene);
		game.run();
		updatePlayerHighScore(model.getCurrentPlayer(), game.getScore());
		
		return;
	}
	
	public void incrementPlayers() {
		model.setCurrentNumPlayers(model.getCurrentNumPlayers() + 1);
	}
	
	public void decrementPlayers() {
		if(model.getCurrentNumPlayers() != 1) {
			model.setCurrentNumPlayers(model.getCurrentNumPlayers() - 1);
		}
	}
	
	public boolean findOrCreatePlayer(String playerName) {
		Player player = model.findPlayer(playerName);
		if(player == null) {
			player = model.createNewPlayer(playerName);
			model.setCurrentPlayer(player);
			return false;
		}
		else{
			model.setCurrentPlayer(player);
			return true;
		}
	}

	public void updatePlayerHighScore(Player player, int score) {
		if (player.getHighScore() < score) {
			player.setHighScore(score);
		}
	}

	public String getNumPlayers() {
		return String.valueOf(model.getCurrentNumPlayers());
	}
}
