package tmGame.inputHandlers.eventHandlers;

import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tmGame.TileMatchingGame;

public class KeyboardInputHandler implements EventHandler<KeyEvent>{
    private TileMatchingGame game;
    private Map<KeyCode, Action> keyBindings;

    public KeyboardInputHandler(TileMatchingGame game, Map<KeyCode, Action> keyBindings) {
        this.game = game;
        this.keyBindings = keyBindings;
        game.getScreen().getScene().setOnKeyPressed(this);
    }

    @Override
    public void handle(KeyEvent event) {
        Action action = keyBindings.get(event.getCode());
        action.perform();
        game.display();
    }
    
}
