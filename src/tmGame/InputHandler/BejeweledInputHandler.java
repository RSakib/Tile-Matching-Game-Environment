package tmGame.InputHandler;


import grid.Position;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import tmGame.BejeweledGame;
import tmGame.TileMatchingGame;
import tmGame.gameScreen.GameScreenJFX;

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

            GameScreenJFX screen = game.getScreen();
            int row = (int) (y / screen.tileHeight(game.getGrid()));
            int col = (int) (x / screen.tileWidth(game.getGrid()));
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
