package tmGame.InputHandler;


import grid.Position;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import tmGame.BejeweledGame;
import tmGame.TileMatchingGame;

public class BejeweledInputHandler extends InputHandlerJFX{

    class OnClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Position p = positionFromClick(event);
            System.out.println(p);

            BejeweledGame bejeweledGame = (BejeweledGame) game;
            bejeweledGame.newSelectedPosition(p);
        }


        private Position positionFromClick(MouseEvent event) {
            double x = event.getX();
            double y = event.getY();

            int row = (int) (y / game.getScreen().getScene().getHeight());
            int col = (int) (x / game.getScreen().getScene().getWidth());
            return new Position(row, col);
        }

    }

    public BejeweledInputHandler(TileMatchingGame game) {
        super(game);
        Platform.runLater(() -> {
            game.getScreen().getScene().setOnMouseClicked(new OnClickHandler());
        });
    }
    
}
