package connect4.model;

import java.util.ArrayList;

public class GameBoard {
	private Column[] columns;
	private int boardSize = 7;
	private int scoreToWin = 4;

	public GameBoard() {
		columns  = new Column[boardSize];
		Token root = new Token();
		
		for(int i = 1 ; i< boardSize; i++){			
			Token token = new Token();
			columns[boardSize-i]= new Column(root);
			token.setMiddleRight(root);
			root.setMiddleLeft(token);
			root = token;
		}
		
		columns[0] =  new Column(root);
	}

	/**
	 * Clears the game board and set it up for a new game.
	 */
	public void clear() {
		//board = new Player[boardSize][boardSize];
	}

	/**
	 * Drops a token from a player into the specified column returns the row
	 * that the token was placed if no space is left will return -1 
	 * 
	 * @return the row that the token was placed
	 */
	public int dropToken(int column, Player token) {
		Column currentColumn = columns[column];
		if(currentColumn.isFull){
			return -1;
		}
		
		checkWin();
		return columns[column].emptyIndex;
	}
	
	private void checkWin(){
		
	}
	
	
	private class WinMessage{
		private ArrayList<Coord> winPoints;
		
	}
	
	private class Coord{
		int column;
		int width;
	}
	private class Column{
		private int emptyIndex;
		private boolean isFull;
		private Token root;
		
		
		/**
		 * Creates a column with an initial size
		 * @param Size the seize of the column
		 */
		public Column(Token root){
			this.root = root;
			emptyIndex = 0;
			isFull = false;
			
		}
		
		public void dropToken(Token token){
			
			emptyIndex++;
		}
		
		public int getSize(){
			return 0;
		}
	}
}
