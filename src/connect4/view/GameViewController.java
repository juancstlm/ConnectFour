package connect4.view;

import java.util.Queue;

import connect4.ConnectFour;
import connect4.model.Board;
import connect4.model.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class GameViewController {

	private ConnectFour mainApp; // reference to the main application
	private Player currentPlayer; // the current player
	private int boardSize; // size of the board
	private Queue<Player> players; // a queue of players so I can switch
	final Timeline timeline = new Timeline();

	@FXML
	private GridPane gameGrid; // THE GUI grid
	@FXML
	private Label p1Score;
	@FXML
	private Rectangle playerIndicator;
	@FXML
	private Circle player1Circle;
	@FXML
	private Circle player2Circle;

	@FXML
	public void initialize() {
		// get the board size from the main application this needs to be static
		// since main app is null
		// when the initialize method is called

		// Create the player turn indicator
		createPlayerIndicator();
		boardSize = ConnectFour.getBoardSize();
		createBoard(boardSize);
	}

	private void createPlayerIndicator() {
		timeline.setAutoReverse(true);
		timeline.setCycleCount(1);
		final KeyValue kv = new KeyValue(playerIndicator.xProperty(), 650);
		final KeyValue kv2 = new KeyValue(playerIndicator.xProperty(), 100);
		final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
		final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
		timeline.getKeyFrames().addAll(kf,kf2);
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
			ColumnConstraints column = new ColumnConstraints(computeColumnSize());
			gameGrid.getColumnConstraints().add(column);
		}

		for (int i = 0; i < size + 1; i++) {
			RowConstraints row = new RowConstraints(computeColumnSize());
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
					Circle circle = new Circle((computeColumnSize() / 2) - 1, Color.web("#0D47A1"));

					circle.setStrokeType(StrokeType.OUTSIDE);
					circle.setStroke(Color.web("blue", 0.75));
					circle.setStrokeWidth(2);
					pane.getChildren().add(circle);
					circle.centerXProperty().bind(pane.widthProperty().divide(2));
					circle.centerYProperty().bind(pane.heightProperty().divide(2));
					gameGrid.add(pane, i, j);
				} else {
					Circle circle = new Circle();
					circle = new Circle((computeColumnSize() / 2) - 1, Color.web("white", 0.90));
					circle.setStrokeType(StrokeType.OUTSIDE);
					circle.setStroke(Color.web("blue", 0.25));
					circle.setStrokeWidth(2);
					circle.centerXProperty().bind(gameGrid.getColumnConstraints().get(i).prefWidthProperty().divide(2));
					circle.centerYProperty().bind(gameGrid.getColumnConstraints().get(i).prefWidthProperty().divide(2));
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
		togglePlayerIndicator();
	}

	private void togglePlayerIndicator() {
		if(timeline.isAutoReverse()){
			timeline.play();
			//timeline.setAutoReverse(false);
		}else{
			timeline.play();
			timeline.setAutoReverse(true);
		}
		
	}

	private void drop(Player player, Pane pane) {
		int column = gameGrid.getColumnIndex(pane);// find what column this
		Board board = mainApp.getGameBoard();
		// pane is in
		int row = board.placeChip(column, player);
		if (row >=0) { // if i can place a chip here

			Circle c = (Circle) getNodePosition(boardSize-row, column);
			//Circle c = (Circle) getAvilableRowNode(column);
			setSelected(player, c);
		}
		if (true) {
			board.checkColumns();
			board.checkDiagnols();
			board.checkRows();
		}
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
		circle.setFill(Color.web("#0D47A1"));
		circle.setStrokeType(StrokeType.OUTSIDE);
		circle.setStroke(Color.web("#E3F2FD"));
		circle.setStrokeWidth(2);
	}

	private void setSelected(Player player, Pane c) {
		Circle circle = (Circle) c.getChildren().get(0);
		circle.setFill(player.getPlayerColor());
		circle.setStrokeType(StrokeType.OUTSIDE);
		circle.setStroke(player.getPlayerColor());
		circle.setStrokeWidth(2);
	}

	private void setSelected(Player player, Circle c) {
		c.setFill(player.getPlayerColor());
	}

	private Player getCurrentPlayer() {
		return currentPlayer;
	}

	private int getVerticalSize() {
		int gap = 8;
		// DEBUG ONLY
		System.out.println(gameGrid.getPrefWidth());
		System.out.println(gameGrid.getWidth());
		double gameGridSize = gameGrid.getWidth();
		return (950 - (gap * boardSize)) / (boardSize);
	}

	private int getHorizontalSize() {
		int gap = 8;
		int boardSize = this.boardSize + 1;
		return (900 - (gap * boardSize)) / boardSize;
	}

	private int computeColumnSize() {
		int gap = 8;
		int columnSize = 0;

		int boardWidth = 900;
		columnSize = (boardWidth - (gap * boardSize)) / boardSize;
		return columnSize;
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
		setPlayerColors(); // queue
	}

	private void setPlayerColors() {
		player1Circle.setFill(currentPlayer.getPlayerColor());
		player2Circle.setFill(players.peek().getPlayerColor());
	}

}
