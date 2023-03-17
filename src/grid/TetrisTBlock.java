package grid;
import java.util.List;
import tile.Tile;



public class TetrisTBlock extends IFallable{
    public Direction direction;

    public TetrisTBlock(List<Position> blockPositions, Tile tileType){
        super(blockPositions, tileType);
        direction = Direction.UP;
    }
    public void rotate(){
        if(direction == Direction.UP){

        }
    }
}