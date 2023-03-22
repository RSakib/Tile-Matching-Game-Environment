package tmGame.InputHandler;

import grid.Direction;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tmGame.FallingBlockGame;
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
            System.out.println("Key pressed");
            FallingBlockGame fbGame = (FallingBlockGame) game;
            KeyCode input = keyEvent.getCode();
            if(fbGame.isRunning()) {
                if (input == LEFTCODE) {
                    fbGame.shiftFaller(Direction.LEFT);
                }
                else if (input == RIGHTCODE) {
                    fbGame.shiftFaller(Direction.RIGHT);
                }
                else if (input == UPCODE || input == ACTIONCODE) {
                    fbGame.rotateFaller();
                }
                else if (input == DOWNCODE) {
                    fbGame.moveFallerDown();
                }
                else {
                    return;
                }
                fbGame.display();
            }
	    }
	};

    public FallingBlockInputHandler(FallingBlockGame game) {
        super(game);
        Platform.runLater(() -> {
            game.getScreen().getScene().setOnKeyPressed(new InputSelect());
        });
    }

}
