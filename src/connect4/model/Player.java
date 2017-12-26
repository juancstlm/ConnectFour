package connect4.model;

import javafx.scene.paint.Color;

public class Player {
	private Color playerColor;
	private String playerName;
	private int plalyerID;
	private int score;

	protected Player(int id) {
		plalyerID = id;
		score = 0;
	}

	/**
	 * Increases the players score by 1;
	 */
	public void increaseScore() {
		score++;
	}
	
	public int getScore(){
		return score;
	}

	/**
	 * Resets the players score
	 */
	public void resetScore() {
		score = 0;
	}

	public void setPlayerColor(Color c) {
		playerColor = c;
		playerName = c.toString();
	}

	/**
	 * Gets the color of this player
	 * 
	 * @return
	 */
	public Color getPlayerColor() {
		return playerColor;
	}

	/**
	 * Gets the ID of the player
	 * @return
	 */
	public int getPlayerID() {
		return plalyerID;
	}
}
