package connect4;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import connect4.model.Player;
import connect4.view.GameViewController;
import connect4.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConnectFour extends Application {

	private BorderPane rootLayout; // the root pane in witch the application is run
	private Stage primaryStage; // the stage were the game is played
	private static int boardSize = 7; // board size length and width 
	private static int scoreToWin = 4; // number of connections in a row need to win
	private Queue<Player> players; // The players of the game 
	private Player[][] gameboard; //The game board
	
	public static void main(String[] args) {
		// TODO use the provided arguments to set the score to win and board
		// size.
		launch();
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/**
		 * DEBUG ONLY
		 */
		gameboard = new Player[boardSize][boardSize];
		Player red = new Player();
		red.setPlayerColor(Color.RED);
		Player yellow = new Player();
		yellow.setPlayerColor(Color.YELLOW);
		players = new LinkedList<Player>();
		players.add(yellow);
		players.add(red);
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Connect Four");
		initRootLayout();
		initGameView();
	}

	/**
	 * Private Method to initialize the game view from FXML 
	 */
	private void initGameView() {
		try {
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
	 * @return
	 */
	public Queue<Player> getPlayers(){
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
	 * @param x score need to win must be greater than 4 
	 */
	private void setScoreToWin(String x){
		//TODO implement 
	}

	/**
	 * Sets the game board size.
	 * Should only be used one in the main method to parse the arguments
	 * @param x the new board size 
	 */
	private void setBoardSize(String x) {
		if (x == null) {
			// TODO implement so that x is not null and not negative or small
			// board size
		}
		boardSize = Integer.valueOf(x);
		gameboard = new Player[boardSize][boardSize];
	}
	
	/**
	 * Gets the gameboard
	 * @return
	 */
	public Player[][] getGameBoard(){
		return gameboard;
	}
	
	public int getAvailbleRow(int column){
		for(int r = boardSize-1; r>= 0;r--){
			if(gameboard[r][column] == null){
				return r;
			}
		}
		return -1;
	}

}
