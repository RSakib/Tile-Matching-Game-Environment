package tmge;

import java.util.ArrayList;

public class Player {
	private String username;
	private ArrayList<Integer> Scores;
	
	public Player(String name){
		username = name;
	}

	public String getUsername() {
		return username;
	}

	public ArrayList<Integer> getScores() {
		return Scores;
	}

	public void setScores(ArrayList<Integer> scores) {
		Scores = scores;
	}
	

}
