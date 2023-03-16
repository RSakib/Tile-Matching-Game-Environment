package grid;
import java.util.List;
import tile.Tile;



public class TetrisReverseLBlock extends IFallable{
    public Direction direction;

    public TetrisReverseLBlock(List<Position> blockPositions, Tile tileType){
        super(blockPositions, tileType);
        direction = Direction.UP;
    }
    public void rotate(){
        if(direction == Direction.UP){

        }
    }
}