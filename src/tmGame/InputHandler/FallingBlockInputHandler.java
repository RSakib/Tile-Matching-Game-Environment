package tmGame.InputHandler;

import grid.Direction;
import grid.FallingBlockGrid;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tmGame.TileMatchingGame;

public class FallingBlockInputHandler extends InputHandlerJFX {
    KeyCode LEFTCODE = KeyCode.A;
    KeyCode RIGHTCODE = KeyCode.D;
    KeyCode UPCODE = KeyCode.W;
    KeyCode DOWNCODE = KeyCode.S;
    KeyCode ACTIONCODE = KeyCode.SPACE;
    class InputSelect implements EventHandler<KeyEvent> {
	    @Override
	    public void handle(KeyEvent keyEvent) {
            KeyCode input = keyEvent.getCode();
            if(game.isGameRunning() && !((FallingBlockGrid)game.getGrid()).getCurrentFaller().isFrozen()) {
                if (input == LEFTCODE) {
                    ((FallingBlockGrid)game.getGrid()).shiftFaller(Direction.LEFT);
                }
                else if (input == RIGHTCODE) {
                    ((FallingBlockGrid)game.getGrid()).shiftFaller(Direction.RIGHT);
                }
                else if (input == UPCODE || input == ACTIONCODE) {
                    ((FallingBlockGrid)game.getGrid()).rotateFaller();
                }
                else if (input == DOWNCODE) {
                    ((FallingBlockGrid)game.getGrid()).moveFallerDown();
                }
                else {
                    return;
                }
            game.display();
            }
	    }
	};

    public FallingBlockInputHandler(TileMatchingGame game) {
        super(game);
        Platform.runLater(() -> {
            game.getScreen().getScene().setOnKeyPressed(new InputSelect());
        });
    }

}
