package tmGame.inputHandlers.eventHandlers;

import grid.Position;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import tmGame.TileMatchingGame;
import tmGame.gameScreen.GameScreenJFX;

public class ClickEventHandler implements EventHandler<MouseEvent> {
    private TileMatchingGame game;
    private PositionAction action;

    public ClickEventHandler(TileMatchingGame game, PositionAction action) {
        this.game = game;
        this.action = action;
        game.getScreen().getBoard().setOnMouseClicked(this);
    }

    private Position positionFromClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        GameScreenJFX screen = game.getScreen();
        int row = (int) (y / screen.tileHeight());
        int col = (int) (x / screen.tileWidth());

        System.out.println("Clicked: " + new Position(row, col));

        return new Position(row, col);
    }


    @Override
    public void handle(MouseEvent event) {
        Position p = positionFromClick(event);
        action.perform(p);
        game.display();
    }
    
}
