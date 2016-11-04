package connect4.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class boardTest {

	@Test
	public void test() {
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		LinkedList<Player> players = new LinkedList<>();
		players.add(p1);
		players.add(p2);
		
		Player curPlayer = players.pop();
		Board board = new Board();
		for(int j = 0; j < 7; j++){
			for(int i =0; i< 7; i++){
				board.placeChip(i, curPlayer);
				players.add(curPlayer);
				curPlayer = players.pop();
				boolean test = board.checkDiagonals();
			}
		}
		
	}

}
