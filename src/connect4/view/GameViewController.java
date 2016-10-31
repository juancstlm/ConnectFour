package connect4.view;

import java.util.Queue;

import connect4.ConnectFour;
import connect4.model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class GameViewController {

	private ConnectFour mainApp; // reference to the main application
	private Player currentPlayer; // the current player
	private int boardSize; // size of the board
	private Queue<Player> players; // a queue of players so I can switch 

	@FXML
	private GridPane gameGrid; // THE GUI grid 

	@FXML
	public void initialize() {
		// get the board size from the main application this needs to be static
		// since main app is null
		// when the initialize method is called
		boardSize = ConnectFour.getBoardSize();
		// currentPlayer = new Player();
		createBoard(boardSize);
	}

	/**
	 * Creates the Game board to display the game with a specified amount of
	 * rows and columns plus an extra row on top for selecting the column to
	 * drop the peg.
	 * 
	 * @param size
	 *            the size of the board
	 */
	private void createBoard(int size) {

		for (int i = 0; i < size; i++) {
			ColumnConstraints column = new ColumnConstraints(getHorizontalSize());
			gameGrid.getColumnConstraints().add(column);
		}

		for (int i = 0; i < size + 1; i++) {
			RowConstraints row = new RowConstraints(getVerticalSize());
			gameGrid.getRowConstraints().add(row);
		}

		// gameGrid.setGridLinesVisible(true); // Debug only
		gameGrid.setHgap(8);
		gameGrid.setVgap(8);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size + 1; j++) {
				// Create the guide circles differently with panes
				if (j == 0) {
					Pane pane = new Pane();
					// Execute this when the mouse goes over the pane
					pane.setOnMouseEntered(f -> {
						setSelected(currentPlayer, pane);
					});
					pane.setOnMouseExited(e -> {
						setHidden(pane);
					});
					pane.setOnMouseReleased(e -> {
						drop(currentPlayer, pane);
					});
					Circle circle = new Circle((getVerticalSize() / 2) - 1, Color.web("white", 0));

					circle.setStrokeType(StrokeType.OUTSIDE);
					circle.setStroke(Color.web("blue", 0.75));
					circle.setStrokeWidth(2);
					pane.getChildren().add(circle);
					circle.centerXProperty().bind(pane.widthProperty().divide(2));
					circle.centerYProperty().bind(pane.heightProperty().divide(2));
					gameGrid.add(pane, i, j);
				} else {
					Circle circle = new Circle();
					circle = new Circle((getVerticalSize() / 2) - 1, Color.web("white", 0.90));
					circle.setStrokeType(StrokeType.OUTSIDE);
					circle.setStroke(Color.web("blue", 0.25));
					circle.setStrokeWidth(2);
					gameGrid.add(circle, i, j);
				}
			}
		}

	}

	/**
	 * Clears the game board
	 */
	@FXML
	private void clear() {
		createBoard(boardSize);
	}

	private void switchPlayer() {
		players.add(currentPlayer);
		currentPlayer = players.remove();
	}

	private void drop(Player player, Pane pane) {
		int column = gameGrid.getColumnIndex(pane);// find what column this
		// pane is in
		int row = mainApp.getAvailbleRow(column)+1;
		if (mainApp.getAvailbleRow(column) != -1) { // if i can place a chip here

			Player[][] board = mainApp.getGameBoard();
			board[row-1][column] = player;
			Circle c = (Circle) getNodePosition(row, column);
			setSelected(player,c);
		}
		
		// CHECK for winner here 
		switchPlayer();
	}

	private Node getNodePosition(int row, int column) {
		Node result = null;
		ObservableList<Node> childrens = gameGrid.getChildren();

		for (Node node : childrens) {
			if (gameGrid.getRowIndex(node) == row && gameGrid.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return result;
	}

	/**
	 * Sets circle in the pane to be invisible
	 * 
	 * @param p
	 *            the pane where the circle resides
	 */
	private void setHidden(Pane p) {
		Circle circle = (Circle) p.getChildren().get(0);
		circle.setFill(Color.web("white", 0));
		circle.setStrokeType(StrokeType.OUTSIDE);
		circle.setStroke(Color.web("blue", 0.75));
		circle.setStrokeWidth(2);
	}

	private void setSelected(Player player, Pane c) {
		Circle circle = (Circle) c.getChildren().get(0);
		circle.setFill(player.getPlayerColor());
		circle.setStrokeType(StrokeType.OUTSIDE);
		circle.setStroke(player.getPlayerColor());
		circle.setStrokeWidth(2);
	}
	private void setSelected(Player player, Circle c){
		c.setFill(player.getPlayerColor());
	}

	private Player getCurrentPlayer() {
		return currentPlayer;
	}

	private int getVerticalSize() {
		int gap = 8;
		return (900 - (gap * boardSize)) / (boardSize);
	}

	private int getHorizontalSize() {
		int gap = 8;
		int boardSize = this.boardSize + 1;
		return (1000 - (gap * boardSize)) / boardSize;
	}

	/**
	 * Give the controller access to the main application
	 * 
	 * @param app
	 */
	public void setMainApp(ConnectFour app) {
		// set reference to the mamin application
		this.mainApp = app;
		players = mainApp.getPlayers(); // the the player queue
		currentPlayer = players.remove(); // set the current player from the
											// queue
	}

}
