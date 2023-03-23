package tmGame.inputHandlers;


import grid.Position;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import tmGame.BejeweledGame;
import tmGame.TileMatchingGame;
import tmGame.inputHandlers.eventHandlers.ClickEventHandler;
import tmGame.gameScreen.GameScreenJFX;

public class BejeweledInputHandler implements InputHandler{
    private BejeweledGame game;

    public BejeweledInputHandler(BejeweledGame game) {
        this.game = game;
    }

    @Override
    public void registerEventHandlers() {
        new ClickEventHandler(game, (p) -> game.newSelectedPosition(p));
    }
}
