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

	public void clear() {
		board = new Player[ConnectFour.getBoardSize()][ConnectFour.getBoardSize()];
	}

	private int getEmptySpaces() {
		for (int i = 0; i < ConnectFour.getBoardSize(); i++)

			for (int j = 0; j < ConnectFour.getBoardSize(); j++) {
				if (board[i][j] == null) {

					emptySpaces++;
				}
			}
		return emptySpaces;
	}

	private Player checkNextDiagonal(int row, int col) {
		return board[row + 1][col + 1];
	}

	private Player checkNextDiagonal1(int row, int col) {
		return board[row + 1][col - 1];
	}

	public boolean checkDiagonals() {
		int matches;
		// Itterate through the 0th row down to the last row where winning by
		// the score is possible
		for (int row = 0; row <= boardSize - scoreToWin; row++) {
			// iterate trhough all of the columnns from the 0th column to the
			// las possible column to win
			for (int column = 0; column < scoreToWin; column++) {
				if (board[row][column] != null) {
					// there is a node here
					matches = 1;
					for (int w = 0; w < scoreToWin - 1; w++) {
						if (board[row][column] == checkNextDiagonal(row + w, column + w)) {
							matches++;
						}
					}

					if (matches == scoreToWin) {
						return true;
					}

				}
			}
		}

		for (int row = 0; row <= boardSize - scoreToWin; row++) {
			// iterate trhough all of the columnns from the 0th column to the
			// las possible column to win
			for (int column = boardSize - 1; column >= boardSize - scoreToWin; column--) {
				if (board[row][column] != null) {
					// there is a node here
					matches = 1;
					for (int w = 0; w < scoreToWin - 1; w++) {
						if (board[row][column] == checkNextDiagonal1(row + w, column - w)) {
							matches++;
						}
					}

					if (matches == scoreToWin) {
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
			int matches = 1;
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

	public boolean checkDraw() {
		if (getEmptySpaces() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * The adds a chip into the gameboard
	 * 
	 * @param j
	 * @param p
	 * @return
	 */
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

}