package connect4.model;

import javafx.scene.paint.Color;

public class Player {
	private Color playerColor;
	private String playerName;
	
	protected Player(){
		
	}
	
	public void setPlayerColor(Color c){
		playerColor = c;
		playerName = c.toString();
	}
	/**
	 * Gets the color of this player
	 * @return
	 */
	public Color getPlayerColor(){
		return playerColor;
	}
}
