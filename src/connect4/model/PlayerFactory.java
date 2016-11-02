package connect4.model;

import java.util.LinkedList;

import javafx.scene.paint.Color;

/**
 * A player factory class that creates human and computer players with different
 * colors.
 * 
 * @author Juan Castillo
 *
 */
public class PlayerFactory {

	private LinkedList<Color> colors;

	public PlayerFactory() {
		colors = new LinkedList<Color>();
		colors.add(Color.web("yellow"));
		colors.add(Color.web("red"));
		colors.add(Color.web("green"));
	}

	/**
	 * Creates a player instance 
	 * @param player
	 * @return
	 */
	public Player createPlayer(String player) {
		if (!colors.isEmpty()) {
			return createPlayer(player, colors.pop());
		} else {
			return null;
		}
	}

	public Player createPlayer(String player, Color c) {
		Player p = null;
		if(c ==null){
			return createPlayer(player);
		}
		if (player.equals("computer")) {
			// TODO implement creating a computer to play this game
		} else {
			p = new Player();
			p.setPlayerColor(c);
			
		}
		return p;
	}
}
