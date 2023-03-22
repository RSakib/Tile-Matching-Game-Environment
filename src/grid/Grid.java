package grid;

import java.util.ArrayList;
import java.util.List;

import tile.EmptyTile;
import tile.IMatcher;
import tile.Tile;

public class Grid {
	private int numRows;
	private int numColumns;
	private Tile[][] grid;
	
	public Grid(int rows, int cols){
		this.numRows = rows;
		this.numColumns = cols;
		grid = new Tile[rows][cols];
		// initialize to empty tiles
		initializeGrid();
	}
	

	public int getNumRows() {
		return numRows;
	}
	

	public int getNumCols() {
		return numColumns;
	}


	/**
	 * Checks if position is within the array bounds
	 * @param p
	 * @return
	 */
	public boolean validPosition(Position p)
	{
		if(p.row >= 0 && p.row < numRows && p.col >= 0 && p.col < numColumns)
		{
			return true;
		}
		return false;
	}
	
	
	public Tile tileAt(Position p) {
		// TODO: if not valid position throw exception
		return grid[p.row][p.col];
	}


	public void setTile(Position p, Tile t){
		// TODO: assert valid position
		grid[p.row][p.col] = t;
	}


	public List<Position> allPositions() {
		List<Position> allPositions = new ArrayList<>();
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumCols(); c++) {
				allPositions.add(new Position(r, c));
			}
		}
		return allPositions;
	}

	/**
	 * Sets all exploded tiles to empty tiles
	 */
	public void removeExplodedTiles() {
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumCols(); c++) {
				Position p = new Position(r, c);
				if (tileAt(p).isExploded()) {
					setTile(p, new EmptyTile());
				}
			}
		}
	}


	/**
	 * Recursively computes a list of positions that should be exploded by starting an explosion at the given position
	 * @param p
	 * @return
	 */
	public List<Position> getExplodedTiles(Position p) {
		Tile tile = tileAt(p);
		if (tile.isExploded()) {
			return new ArrayList<>();
		}

		List<Position> allExplodedPositions = new ArrayList<>();
		List<Position> explodedPositions = tile.explode(this, p);
		allExplodedPositions.addAll(explodedPositions);
		for (Position explodedPos: explodedPositions) {
			Tile explodedTile = tileAt(explodedPos);
			if (! explodedPos.equals(p)) {
				allExplodedPositions.addAll(getExplodedTiles(explodedPos));
			}
			explodedTile.setExploded(true);
		}
		return allExplodedPositions;
	}
	

	public void swapTilesAt(Position p1, Position p2) {
		Tile temp = tileAt(p1);
		setTile(p1, tileAt(p2));
		setTile(p2, temp);
	}


	public void swapRows(int r1, int r2) {
		for (int i = 0; i < getNumCols(); i++) {
			Position p1 = new Position(r1, i);
			Position p2 = new Position(r2, i);
			swapTilesAt(p1, p2);
		}
	}


	public boolean isEmptyRow(int rowNum) {
		for (int i = 0; i < getNumCols(); i++) {
            if ( tileAt(new Position(rowNum, i)).isEmpty()) {
                return false;
            }
        }
        return true;
	}


	public Position nonEmptyPositionAbove(Position startPos) {
		for (int r = startPos.row - 1; r >= 0; r--) {
            Position p = new Position(r, startPos.col);
            if (! tileAt(p).isEmpty()) {
                return p;
            }
        }
        return null;
	}

	/**
	 * Returns the row number of the next non empty row above the given starting row
	 * Returns -1 if there are no non-empty rows above the given starting row
	 * @param startRow
	 * @return
	 */
	public int nonEmptyRowAbove(int startRow) {
        for (int row = startRow - 1; row >= 0; row--) {
            if (! isEmptyRow(row)) {
                return row;
            }
        }
        return -1;
    }
	


	
	private void initializeGrid() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				setTile(new Position(i, j), new EmptyTile());
			}
		}
	}


	public String toString() {
		String str = "";
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumCols(); c++) {
				if (tileAt(new Position(r, c)) instanceof EmptyTile) {
					str += "0 ";
				} else {
					str += "1 ";
				}
			}
			str += "\n";
		}
		return str;
	}
}
