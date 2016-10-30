package connect4.view;

import java.util.ArrayList;

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

	private ConnectFour mainApp;
	private Player currentPlayer;
	private int boardSize;
	private ArrayList<Player> players;

	@FXML
	private GridPane gameGrid;

	@FXML
	public void initialize() {
		// get the board size from the main application this needs to be static
		// since main app is null
		// when the initialize method is called
		boardSize = ConnectFour.getBoardSize();
		currentPlayer = new Player();
		createBoard(boardSize);
		gameGrid.setOnMouseReleased(e->{switchPlayer();});
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
	private void switchPlayer() {
		// TODO Auto-generated method stub
		
	}

	private void drop(Player player, Pane pane) {
		if (true) { // if i can place a chip here
			int column = gameGrid.getColumnIndex(pane);// find what column this
														// pane is in
			Circle c = (Circle) getLowestAvailablePosition(1, column);
			c.setFill(player.getPlayerColor());
			c.setStrokeType(StrokeType.OUTSIDE);
			c.setStroke(player.getPlayerColor());
			c.setStrokeWidth(2);
		}
	}

	private Node getLowestAvailablePosition(int row, int column) {
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

	private Player getCurrentPlayer() {
		return currentPlayer;
	}

	private int getVerticalSize() {
		int gap = 8;
		return (600 - (gap * boardSize)) / (boardSize);
	}

	private int getHorizontalSize() {
		int gap = 8;
		int boardSize = this.boardSize + 1;
		return (700 - (gap * boardSize)) / boardSize;
	}

	/**
	 * Give the controller access to the main application
	 * 
	 * @param app
	 */
	public void setMainApp(ConnectFour app) {
		this.mainApp = app;
		
	}

}
