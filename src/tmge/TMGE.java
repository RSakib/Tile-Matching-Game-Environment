package tmge;

import java.util.ArrayList;
import tmGame.TileMatchingGame;
import tmGame.TileMatchingGameFactory;

public class TMGE {
	private TileMatchingGameFactory gameFactory;
	private ArrayList<Player> players;
	private Player currentPlayer;
	// Supposed to be number of players that are going to play right now
	private int currentNumPlayers;
	
	public TMGE(){
		gameFactory = new TileMatchingGameFactory();
		// have players read from some text file or something for players
		currentNumPlayers = 2;
		players = new ArrayList<Player>();
	}
	
	public TileMatchingGame createGame(String gameName) {
		return gameFactory.createGame(gameName);
	}
	
	public Player createNewPlayer(String name) {
		Player newPlayer = new Player(name);
		players.add(newPlayer);
		return newPlayer;
	}

	public Player findPlayer(String name) {
		for(Player player:players){
			if(player.getUsername().contentEquals(name)) {
				return player;
			};
		}
		return null;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getCurrentNumPlayers() {
		return currentNumPlayers;
	}

	public void setCurrentNumPlayers(int currentNumPlayers) {
		this.currentNumPlayers = currentNumPlayers;
	}
	
}
