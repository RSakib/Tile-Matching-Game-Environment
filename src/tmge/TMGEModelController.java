package tmge;

import javafx.scene.Scene;

public class TMGEModelController {
	private TMGE model;
	
	public TMGEModelController(TMGE model) {
		this.model = model;
	}
	
	public void runGame(String gameName, Scene pScene) {
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
