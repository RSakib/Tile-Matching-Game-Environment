package tile;

import java.util.List;

import grid.Grid;
import grid.Position;

public abstract class Tile {
	private String imagePath;
	private int color; // could change to enum
	private boolean matched;
	private boolean exploded;
	private IExploder exploder;


	public String getImagePath() {
		return imagePath;
	}

	public int getColor() {
		return color;
	}


	public void setExploder(IExploder exploder) {
		this.exploder = exploder;
	}


	public void setMatched(boolean matched) {
		this.matched = matched;
	}


	public boolean isMatched() {
		return matched;
	}


	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}


	public boolean isExploded() {
		return exploded;
	}


	public List<Position> explode(Grid g, Position p) {
		return exploder.explode(g, p.row, p.col);
	}
}
