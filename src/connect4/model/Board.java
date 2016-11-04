package connect4.model;

import connect4.ConnectFour;

public class Board {
	private Player[][] board;
	private int emptySpaces = 0;
	private int boardSize = ConnectFour.getBoardSize();
	private int scoreToWin = ConnectFour.getScoreToWin();

	public Board() {
		board = new Player[ConnectFour.getBoardSize()][ConnectFour.getBoardSize()];

	}

	public Player[][] getBoard() {
		return board;
	}

	public void clear() {
		board = new Player[ConnectFour.getBoardSize()][ConnectFour.getBoardSize()];
	}

	public int getEmptySpaces() {
		for (int i = 0; i < ConnectFour.getBoardSize(); i++)

			for (int j = 0; j < ConnectFour.getBoardSize(); j++) {
				board[i][j] = null;
				emptySpaces++;

			}

		return emptySpaces;
	}
	public Player checkNextDiagonal(int row, int col){
		return board[row+1][col+1];
	}
	public Player checkNextDiagonal1(int row, int col){
		return board[row+1][col-1];
	}
	
	public boolean checkDiagonals() {
		int matches;
		// Itterate through the 0th row down to the last row where winning by the score is possible
		for (int row = 0; row <= boardSize - scoreToWin; row++) {
			// iterate trhough all of the columnns from the 0th column to the las possible column to win
			for(int column = 0; column < scoreToWin+1; column++){
				if(board[row][column]!=null){
					//there is a node here
					matches = 1;
					for(int w = 0 ; w < scoreToWin-1; w++ ){
						if (board[row][column]==checkNextDiagonal(row+w, column+w)){
							matches++;
						}
					}
					
					if(matches == scoreToWin){
						return true;
					}
					
				}
			}
		}

		for (int row = 0; row <= boardSize - scoreToWin; row++) {
			// iterate trhough all of the columnns from the 0th column to the las possible column to win
			for(int column = boardSize-1; column >= boardSize-scoreToWin; column--){
				if(board[row][column]!=null){
					//there is a node here
					matches = 1;
					for(int w = 0 ; w < scoreToWin-1; w++ ){
						if (board[row][column]==checkNextDiagonal1(row+w, column-w)){
							matches++;
						}
					}
					
					if(matches == scoreToWin){
						return true;
					}
					
				}
			}
		}
		return false;
	}

	public boolean checkRows() {
		for (int i = 0; i < ConnectFour.getBoardSize(); i++) {
			int matches = 1;
			for (int j = 1; j < ConnectFour.getBoardSize(); j++) {
				if (board[i][j] != null) // this checks whether or not its
											// empty, since im using a int array
											// atm
				{
					if (board[i][j] == board[i][j - 1]) // we would need to
														// change these to
														// colors
					{
						matches++;
					} else
						matches = 1;
				}
				if (matches >= ConnectFour.getScoreToWin()) {
					// System.out.println("Player" + " " + board[i][j] + " " +
					// "Wins");
					return true;
				} else
					continue;
			}
		}
		return false;

	}

	public boolean checkColumns() {
		for (int j = 0; j < ConnectFour.getBoardSize(); j++) {
			int matches = 0;
			for (int i = 1; i < ConnectFour.getBoardSize(); i++) {
				if (board[i][j] != null) // this checks whether or not its
											// empty, since im using a int array
											// atm
				{
					if (board[i][j] == board[i - 1][j]) // we would need to
														// change these to
														// colors
					{
						matches++;
					} else
						matches = 1;
				}
				if (matches >= ConnectFour.getScoreToWin()) {
					// System.out.println("Player" + " " + board[i][j] + " " +
					// "Wins"); //this will be changed to end the game here +
					// doing something else
					return true;
				} else
					continue;

			}
		}
		return false;

	}

	public boolean checkDiagnols() {
		for (int j = 0; j < ConnectFour.getBoardSize(); j++) // checks top left
																// to bottom
																// right
		{
			int matches = 1;
			for (int i = 1; i < ConnectFour.getBoardSize(); i++) {
				if (i + j >= ConnectFour.getBoardSize()) {
					break;
				}
				if (board[i][i + j] != null) {
					if (board[i - 1][i + j - 1] == board[i][i + j]) {
						matches++;
					}
				} else
					matches = 1;
				if (matches >= ConnectFour.getScoreToWin()) {
					// System.out.println("Player" + " " + board[i][i+j] + " " +
					// "Wins");
					return true;
				}

			}

		}

		for (int i = 0; i < ConnectFour.getBoardSize(); i++) {
			int matches = 1;
			for (int j = 1; j < ConnectFour.getBoardSize(); i++) {
				if (i + j >= ConnectFour.getBoardSize()) {
					break;
				}
				if (board[i + j][j] != null) {
					if (board[i + j - 1][j - 1] == board[i + j][j]) {
						matches++;
					}
				} else
					matches = 1;
				if (matches >= ConnectFour.getScoreToWin()) {
					// System.out.println("Player" + " " + board[i+j][j] + " " +
					// "Wins");
					return true;
				}
			}
		}

		for (int j = 0; j < ConnectFour.getBoardSize(); j++)// checks top right
															// to bottom left
		{
			int matches = 1;
			for (int i = 1; i < ConnectFour.getBoardSize(); i++) {
				if (j - i < 0) {
					break;
				}
				if (board[i][j - i] != null) {
					if (board[i - 1][j - i + 1] == board[i][j - i]) {
						matches++;
					}
				} else
					matches = 1;

				if (matches >= ConnectFour.getScoreToWin()) {
					// System.out.println("Player" + board[i][i+j] + "Wins");
					return true;
				}
			}

		}
		for (int i = 0; i < ConnectFour.getBoardSize(); i++) {
			int matches = 1;
			for (int j = ConnectFour.getBoardSize() - 2; j >= 0; j--) {
				if (j - i < 0) {
					break;
				}
				if (board[j - i][j] != null) {
					if (board[j - i - 1][j + 1] == board[j - i][j]) {
						matches++;
					}
				} else
					matches = 1;
				if (matches >= ConnectFour.getScoreToWin()) {
					// System.out.println("Player" + " " + board[j-1][j] + " " +
					// "Wins");
					return true;
				}
			}
		}
		return false;

	}

	public void checkDraw() {
		if (emptySpaces == 0) {
			System.out.println("it's a draw!");
		}
	}

	public int placeChip(int j, Player p) // j = column
	{
		if (board[0][j] != null) // top slot of column is full, so the whole
									// column is full
		{
			return -1;
		}

		for (int i = 0; i < ConnectFour.getBoardSize(); i++) // if the slot
																// below is
																// full, then
																// the disk on
																// top of the
																// current one
																// must be empty
		{
			if (board[i][j] != null) {
				board[i - 1][j] = p;
				return i - 1;
			}
		}
		board[ConnectFour.getBoardSize() - 1][j] = p;// if none of the slots are
														// full, then the bottom
														// one is the first
														// available slot
		return ConnectFour.getBoardSize() - 1;

	}

	public boolean setColumnFull(int j) {
		if (board[0][j] != null) // the top slot of the column j is not empty,
									// therefore the whole column is full and
									// used
		{
			return true;
		}
		return false;
	}

	public boolean setRowUsed(int i) // the top slot of the column i is not
										// empty, therefore the whole column is
										// full and used
	{
		int counter = 0;
		for (int j = 0; j < ConnectFour.getBoardSize(); j++) {
			if (board[i][j] != null) {
				counter++;
			}
		}
		if (counter == ConnectFour.getBoardSize() - 1) {
			return true;
		}
		return false;
	}

}