package tmGame.gameScreen;

import javafx.scene.Scene;

public abstract class JavaFXScreen {
	protected Scene scene;
	
	public JavaFXScreen() {
		scene = null;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
