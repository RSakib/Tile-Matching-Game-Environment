package tmge;

import java.util.ArrayList;
import java.util.Scanner;

import tmGame.TileMatchingGame;
import tmGame.TileMatchingGameFactory;

public class TMGE {
	private TileMatchingGameFactory gameFactory;
	private ArrayList<Player> players;
	// Supposed to be number of players that are going to play right now
	private int currentNumPlayers;
	
	public TMGE(){
		gameFactory = new TileMatchingGameFactory();
		// have players read from some text file or something for players
		currentNumPlayers = 2;
	}
	
	public void runGame() {
		for(int i = 0; i < currentNumPlayers; i++) {
			Player currentPlayer = promptPlayerName();
			TileMatchingGame game = gameFactory.createGame("");
			game.run();
		}
	}
	
	public Player promptPlayerName(){
		Scanner input  = new Scanner(System.in);
		String name = input.next();
		input.close();
		for(Player player : players) {
			if(player.getUsername().equals(name)) {
				return player;
			}
		}
		return makeNewPlayer(name);
	}
	
	private Player makeNewPlayer(String name) {
		Player newPlayer = new Player(name);
		players.add(newPlayer);
		return newPlayer;
	}

	public int getCurrentNumPlayers() {
		return currentNumPlayers;
	}

	public void setCurrentNumPlayers(int currentNumPlayers) {
		this.currentNumPlayers = currentNumPlayers;
	}
	
}
