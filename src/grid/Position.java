package grid;

import java.util.Objects;

public class Position {
	public int row;
	public int col;

	public Position(int row, int col) {
		this.row = row; 
		this.col = col;
	}


	@Override
	public int hashCode() {
		return row * 31 + col;
		//return Objects.hash(Integer.valueOf(row), Integer.valueOf(col));
	}

	@Override
	public boolean equals(Object o) {
		Position other;
		try {
			other = (Position) o;
		} catch (Exception e) {
			return false;
		}
		return this.row == other.row && this.col == other.col;
	}


	public Position translate(Direction d) {
		if (d == Direction.UP) {
			return new Position(row - 1, col);
		} else if (d == Direction.DOWN) {
			return new Position(row + 1, col);
		} else if (d == Direction.LEFT) {
			return new Position(row, col - 1);
		} else if (d == Direction.RIGHT) {
			return new Position(row, col + 1);
		} else {
			return null; // throw error maybe
		}
	}
}