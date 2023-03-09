package tile;

public abstract class Tile {
	private String imagePath;
	public int color; // could change to enum

	public String getImagePath() {
		return imagePath;
	}

	public int getColor() {
		return color;
	}
}
