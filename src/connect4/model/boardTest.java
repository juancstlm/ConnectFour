package connect4.model;

import connect4.model.Connect4.Board;

import java.util.LinkedList;


public class boardTest {


	public static void main(String[] args){
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		LinkedList<Player> players = new LinkedList<>();
		players.add(p1);
		players.add(p2);

		Player curPlayer = players.pop();
		Board board = new Board();
		for(int j = 0; j < 7; j++){
			for(int i =0; i< 7; i++){
				board.insert(i, curPlayer.getPlayerID());
				players.add(curPlayer);
				curPlayer = players.pop();
			}
		}

	}
}
