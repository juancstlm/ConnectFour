package connect4;

import java.io.IOException;

import connect4.view.GameViewController;
import connect4.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConnectFour extends Application {

	private BorderPane rootLayout;
	private Stage primaryStage;
	private static int boardSize = 7;
	private static int scoreToWin = 4;

	public static void main(String[] args) {
		// TODO use the provided arguments to set the score to win and board
		// size.
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Connect Four");
		initRootLayout();
		initGameView();
	}

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
	 * @return
	 */
	public static int getBoardSize() {
		return boardSize;
	}

	/**
	 * Gets the score needed to win in this game;
	 * 
	 * @return
	 */
	public static int getScoreToWin() {
		return scoreToWin;
	}

	private void setBoardSize(String x) {
		if (x == null) {
			// TODO implement so that x is not null and not negative or small
			// board size
		}
		boardSize = Integer.valueOf(x);
	}

}
