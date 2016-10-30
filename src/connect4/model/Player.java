package connect4.model;

import javafx.scene.paint.Color;

public class Player {
	private Color playerColor= Color.web("yellow");
	
	public void setPlayerColor(Color c){
		playerColor = c;
	}
	/**
	 * Gets the color of this player
	 * @return
	 */
	public Color getPlayerColor(){
		return playerColor;
	}
}
