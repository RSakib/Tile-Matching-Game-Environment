package tmGame.inputHandlers;

import java.util.Map;

import grid.Direction;
import javafx.scene.input.KeyCode;
import tmGame.FallingBlockGame;
import tmGame.TileMatchingGame;
import tmGame.inputHandlers.eventHandlers.Action;
import tmGame.inputHandlers.eventHandlers.KeyboardInputHandler;

public class FallingBlockInputHandler implements InputHandler {
    private static KeyCode LEFTCODE = KeyCode.A;
    private static KeyCode RIGHTCODE = KeyCode.D;
    private static KeyCode UPCODE = KeyCode.W;
    private static KeyCode DOWNCODE = KeyCode.S;
    private static KeyCode ACTIONCODE = KeyCode.SPACE;

    private FallingBlockGame game;

    public FallingBlockInputHandler(FallingBlockGame game) {
        this.game = game;
    }

    @Override
    public void registerEventHandlers() {
        Map<KeyCode, Action> keyBindings = Map.of(
            LEFTCODE, () -> game.shiftFaller(Direction.LEFT),
            RIGHTCODE, () -> game.shiftFaller(Direction.RIGHT),
            UPCODE, () -> game.rotateFaller(),
            DOWNCODE, () -> game.moveFallerDown()
        );
        new KeyboardInputHandler(game, keyBindings);
    }

}
