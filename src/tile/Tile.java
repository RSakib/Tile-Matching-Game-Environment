package tile;

import java.util.List;

import grid.Grid;
import grid.Position;
import tile.exploders.IExploder;
import tile.matchers.IMatcher;

public abstract class Tile {
	private String imagePath;
	private TileColor color; // could change to enum
	private IExploder exploder;
	private IMatcher matcher;
	private boolean matched;
	private boolean exploded;

	public boolean isEmpty() {
		return this instanceof EmptyTile;
	}

	public String getImagePath() {
		return imagePath;
	}

	public TileColor getColor() {
		return color;
	}

	public IExploder getExploder() {
		return exploder;
	}

	public void setColor(TileColor c) {
		color = c;
	}

	public boolean isMatched() {
		return matched;
	}

	public boolean isExploded() {
		return exploded;
	}
	

	public void setMatcher(IMatcher matcher) {
		this.matcher = matcher;
	}
	
	public void setExploder(IExploder exploder) {
		this.exploder = exploder;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}


	public boolean isMatch(Tile other) {
		return matcher.isMatch(this, other);
	}

	public List<Position> explode(Grid g, Position p) {
		return exploder.explode(g, p.row, p.col);
	}


}
