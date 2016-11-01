package connect4;

import java.io.IOException;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.Queue;

import connect4.model.Board;
import connect4.model.Player;
import connect4.view.GameViewController;
import connect4.view.MenuViewController;
import connect4.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConnectFour extends Application {

	private BorderPane rootLayout; // the root pane in witch the application is
									// run
	private Stage primaryStage; // the stage were the game is played
	private static int boardSize = 7; // board size length and width
	private static int scoreToWin = 4; // number of connections in a row need to
										// win
	private Queue<Player> players; // The players of the game
	//private Player[][] gameboard; // The game board
	private Board gameBoard; 

	public static void main(String[] args) {
		if (args.length > 2) {
			setBoardSize(args[0]);
			setScoreToWin(args[1]);
		}
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
			
			rootLayout.getChildren().clear();
			
			// Load the layout from the FXML file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ConnectFour.class.getResource("view/GameView.fxml"));
			// Create the game view
			AnchorPane gameview = (AnchorPane) loader.load();
			// get the controller for the game view
			GameViewController contoller = loader.getController();
			// give the controller access to the main app.
			contoller.setMainApp(this);
			// Set the center of the root layout as the game view.
			rootLayout.setCenter(gameview);
			//primaryStage.getScene().setRoot(rootLayout);
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
	public static int getBoardSize() {
		return boardSize;
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
	 * @return number of connections in a row needed to win the game.
	 */
	public static int getScoreToWin() {
		return scoreToWin;
	}

	/**
	 * Sets the score needed to win to be greater than the standard 4
	 * 
	 * @param x
	 *            score need to win must be greater than 4
	 */
	private static void setScoreToWin(String x) {
		if (Integer.parseInt(x) <= 2) {
			scoreToWin = 2;
		} else
			scoreToWin = Integer.parseInt(x);
	}

	/**
	 * Sets the game board size. Should only be used one in the main method to
	 * parse the arguments
	 * 
	 * @param x
	 *            the new board size
	 */
	private static void setBoardSize(String x) {
		if (x == null || Integer.parseInt(x) <= 3) {
			boardSize = 3;
		} else
			boardSize = Integer.valueOf(x);
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
		if(players.size()<=2){
			players.add(p);
		}
	}

	public void setBoard(Board board) {
		gameBoard = board;
	}

}
