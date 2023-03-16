package grid;

public class Position {
	public int row;
	public int col;

	public Position(int row, int col) {
		this.row = row; 
		this.col = col;
	}


	public boolean equals(Position other) {
		return this.row == other.row && this.col == other.col;
	}


	public Position translate(Direction d) {
		if (d == Direction.UP) {
			return new Position(row - 1, col);
		} else if (d == Direction.DOWN) {
			return new Position(row + 1, col);
		} else if (d == Direction.LEFT) {
			return new Position(row, col + 1);
		} else {
			return new Position(row, col - 1);
		}
	}
}