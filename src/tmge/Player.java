package tmge;

public class Player {
	private String username;
	private int highScore;

	public Player(String name){
		username = name;
	}

	public String getUsername() {
		return username;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}
