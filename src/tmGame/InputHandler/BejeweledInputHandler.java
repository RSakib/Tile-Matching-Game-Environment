package tmGame.InputHandler;


import grid.Position;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import tmGame.BejeweledGame;
import tmGame.TileMatchingGame;
import tmGame.gameScreen.GameScreenJFX;

public class BejeweledInputHandler implements InputHandlerJFX{
    private BejeweledGame game;

    class OnClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Position p = positionFromClick(event);
            System.out.println(p);

            game.newSelectedPosition(p);
            game.display();
        }


        private Position positionFromClick(MouseEvent event) {
            double x = event.getX();
            double y = event.getY();

            GameScreenJFX  screen = game.getScreen();
            int row = (int) (y / screen.tileHeight());
            int col = (int) (x / screen.tileWidth());
            return new Position(row, col);
        }

    }


    @Override
    public void register(TileMatchingGame game) {
        this.game = (BejeweledGame) game;
        Platform.runLater(() -> {
            game.getScreen().getBoard().setOnMouseClicked(new OnClickHandler());
        });
    }
    
}
