package tmGame.inputHandlers;


import tmGame.BejeweledGame;
import tmGame.inputHandlers.eventHandlers.ClickEventHandler;

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
