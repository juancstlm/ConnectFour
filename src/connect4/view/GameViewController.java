package connect4.view;

import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
	private int boardWidth; // size of the board
    private int boardHeight;
	private Queue<Player> players; // a queue of players so I can switch

	@FXML
	private GridPane gameGrid; // THE GUI grid
	@FXML
	private Label p1Score; //p1scre
	@FXML
	private Label p2Score;
	@FXML
	private Circle player1Circle;
	@FXML
	private Circle player2Circle;

	@FXML
	public void initialize() {
		// get the board size from the main application this needs to be static
		// since main app is null
		// when the initialize method is called
		boardHeight = ConnectFour.DEFAULT_BOARD_HEIGHT;
		boardWidth = ConnectFour.DEFAULT_BOARD_WIDTH;
		createBoard(boardHeight,boardWidth);
	}

	/**
	 * Creates the Game board to display the game with a specified amount of
	 * rows and columns plus an extra row on top for selecting the column to
	 * drop the peg.
	 * 
	 * @param rows
     * @paran coluns
	 *            the size of the board
	 */
	private void createBoard(int rows, int columns) {

		for (int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(computeColumnSize());
			gameGrid.getColumnConstraints().add(column);
		}

		for (int i = 0; i < rows + 1; i++) {
			RowConstraints row = new RowConstraints(computeColumnSize());
			gameGrid.getRowConstraints().add(row);
		}

		// gameGrid.setGridLinesVisible(true); // Debug only
		gameGrid.setHgap(8);
		gameGrid.setVgap(8);

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows + 1; j++) {
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
		gameGrid.getChildren().clear();
		mainApp.getGameBoard().clear();
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeight+ 1; j++) {
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

	private void switchPlayer() {
		if(currentPlayer.getPlayerID() == 1){
			deselectCircle(player2Circle);
			selectCircle(player1Circle);
		}else if(currentPlayer.getPlayerID() == 2){
			deselectCircle(player1Circle);
			selectCircle(player2Circle);
		}
		players.add(currentPlayer);
		currentPlayer = players.remove();
	}
	
	private void deselectCircle(Circle c){
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.setAutoReverse(false);
		final KeyValue kv = new KeyValue(c.strokeWidthProperty(), 0);
		final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private void selectCircle(Circle c){
		c.setStroke(Color.web("#2196F3"));
		c.setStrokeType(StrokeType.INSIDE);
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.setAutoReverse(false);
		final KeyValue kv = new KeyValue(c.strokeWidthProperty(), 8);
		final KeyFrame kf = new KeyFrame(Duration.millis(300), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private void drop(Player player, Pane pane) {
		int column = gameGrid.getColumnIndex(pane);// find what column this
		Board board = mainApp.getGameBoard();
		// pane is in
		int row = boardHeight - board.insert(column, player.getPlayerID());
		if (row >= 0) { // if i can place a chip here
			Circle c = (Circle) getNodePosition(row , column);
			c.setFill(currentPlayer.getPlayerColor());
			if (board.checkColumns()) {
				showWinDialogue("Columns");
				
			} else if (board.checkDiagonals()) {
				showWinDialogue("Diagonal");
			} else if (board.checkRows()) {
				showWinDialogue("Rows");
			} else if(board.checkDraw()){
				showDrawDialouge();
			}
			else {
				switchPlayer();
			}
		}
	}

	private void showDrawDialouge() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("Its A DRAW");
		alert.setHeaderText("Press ok to start a new Game");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    clear();
		} else {
		    
		}
	}

	private void showWinDialogue(String s) {
		incraseScore(currentPlayer);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("PLayer " + currentPlayer.getPlayerID() + " Wins");
		alert.setHeaderText("PLayer " + currentPlayer.getPlayerID() + " Wins by " + s +"\n Press ok to start a new Game");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    clear();
		} else {
		    
		}
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

	private int computeColumnSize() {
		int gap = 8;
		int columnSize = 0;

		int boardWidth = 600;
		columnSize = (boardWidth - (gap * this.boardWidth)) / this.boardWidth;
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
		selectCircle(player1Circle);
		setPlayerColors(); // queue
	}

	private void setPlayerColors() {
		player1Circle.setFill(currentPlayer.getPlayerColor());
		player2Circle.setFill(players.peek().getPlayerColor());
	}
	
	public void incraseScore(Player p){
		p.increaseScore();
		if(p.getPlayerID() == 1){
			p1Score.setText(Integer.toString(p.getScore()));
		} else if(p.getPlayerID() == 2 ){
			p2Score.setText(Integer.toString(p.getScore()));
		}
	}

}
