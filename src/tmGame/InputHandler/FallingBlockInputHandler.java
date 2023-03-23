package tmGame.InputHandler;

import grid.Direction;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tmGame.FallingBlockGame;
import tmGame.TileMatchingGame;

public class FallingBlockInputHandler implements InputHandlerJFX {
    private FallingBlockGame game;

    KeyCode LEFTCODE = KeyCode.A;
    KeyCode RIGHTCODE = KeyCode.D;
    KeyCode UPCODE = KeyCode.W;
    KeyCode DOWNCODE = KeyCode.S;
    KeyCode ACTIONCODE = KeyCode.SPACE;
    class InputSelect implements EventHandler<KeyEvent> {
	    @Override
	    public void handle(KeyEvent keyEvent) {
            System.out.println("Key pressed");
            KeyCode input = keyEvent.getCode();
            if(game.isRunning()) {
                if (input == LEFTCODE) {
                    game.shiftFaller(Direction.LEFT);
                }
                else if (input == RIGHTCODE) {
                    game.shiftFaller(Direction.RIGHT);
                }
                else if (input == UPCODE || input == ACTIONCODE) {
                    game.rotateFaller();
                }
                else if (input == DOWNCODE) {
                    game.moveFallerDown();
                }
                else {
                    return;
                }
                game.display();
            }
	    }
	};


    @Override
    public void register(TileMatchingGame game) {
        this.game = (FallingBlockGame) game;
        Platform.runLater(() -> {
            game.getScreen().getScene().setOnKeyPressed(new InputSelect());
        });
    }

}
