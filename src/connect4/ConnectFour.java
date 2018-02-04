package connect4;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import connect4.model.Connect4.Board;
import connect4.model.Player;
import connect4.view.GameViewController;
import connect4.view.MenuViewController;
import connect4.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConnectFour extends Application {

	private BorderPane rootLayout; // the root pane
	private Stage primaryStage; // the stage were the game is played
	public static final int DEFAULT_BOARD_WIDTH = 7; //default board width
	public static final int DEFAULT_BOARD_HEIGHT = 6; // default board height
	public static final int DEFAULT_SCORE_TO_WIN = 4; //default score to win
	private int boardWidth = DEFAULT_BOARD_WIDTH;
	private int boardHeight = DEFAULT_BOARD_HEIGHT;
	private int scoreToWin =  DEFAULT_SCORE_TO_WIN;
	private Queue<Player> players; // The players of the game
	private Board gameBoard;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		players = new LinkedList<Player>();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Connect Four");
		initRootLayout();
		initMenuView();
	}

    /**
     *
     */
	private void initMenuView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ConnectFour.class.getResource("view/MenuView.fxml"));

			AnchorPane menu = (AnchorPane) loader.load();

			MenuViewController controller = loader.getController();
			controller.setMainApp(this);

			rootLayout.setCenter(menu);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Private Method to initialize the game view from FXML
	 */
	public void initGameView() {
		try {

			// rootLayout.getChildren().clear();

			// Load the layout from the FXML file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ConnectFour.class.getResource("view/GameView.fxml"));
			// Create the game view
			AnchorPane gameview = (AnchorPane) loader.load();
			// gameview.getStylesheets().add(ConnectFour.class.getResource("/res/MaterialDesignTheme.css").toExternalForm());
			// get the controller for the game view
			GameViewController contoller = loader.getController();
			// give the controller access to the main app.
			contoller.setMainApp(this);
			// Set the center of the root layout as the game view.
			rootLayout.setCenter(gameview);
			rootLayout.getCenter().autosize();
			// primaryStage.getScene().setRoot(rootLayout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initial method to initialize the root layout
	 */
	private void initRootLayout() {
		try {
			// Load the Layout from the FXML file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ConnectFour.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene from the root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			// Give the controller acces to the main app
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			// show the view
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Gets the length of the square game board
	 * 
	 * @return length and width of the board
	 */
	public int getBoardHeight() {
		return boardHeight;
	}

    public int getBoardWidth() {
        return boardWidth;
    }

    /**
	 * Gets the current que of players
	 * 
	 * @return
	 */
	public Queue<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets the score needed to win in this game;
	 * 
	 * @return number connections needed to win the game .
	 */
	public int getScoreToWin() {
		return scoreToWin;
	}

	/**
	 * Sets the score needed to win a game to be something else than the standard 4
	 * 
	 * @param x
	 *            score need to win must be greater than 4
	 */
	private void setScoreToWin(int x) {
		if (x <= 2) {
			scoreToWin = 2;
		} else
			scoreToWin = x;
	}

	private void setBoardSize(int rows, int columns) {
		if (rows <= 3) {
			boardHeight = 3;
		} else {
			boardHeight = rows;
		}
		if(columns <= 3){
			boardWidth = 3;
		} else{
			boardWidth = columns;
		}
	}

	/**
	 * Gets the gameboard
	 * 
	 * @return
	 */
	public Board getGameBoard() {
		return gameBoard;
	}

	public void addPlayer(Player p) {
		if (players.size() <= 2) {
			players.add(p);
		}
	}

	/**
	 * Sets the logical game board
	 * 
	 * @param board
	 */
	public void setBoard(Board board) {
		gameBoard = board;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
