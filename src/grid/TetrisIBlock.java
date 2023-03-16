package grid;
import java.util.List;
import tile.Tile;



public class TetrisIBlock extends IFallable{
    public Direction direction;

    public TetrisIBlock(List<Position> blockPositions, Tile tileType){
        super(blockPositions, tileType);
        direction = Direction.UP;
    }
    public void rotate(){
        if(direction == Direction.UP){

        }
    }
}