package connect4.view;

import connect4.ConnectFour;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameViewController {

	private ConnectFour mainApp;

	@FXML
	private GridPane gameGrid;

	@FXML
	public void initialize() {
		// get the board size
		int boardSize = ConnectFour.getBoardSize();
		int circleSize = 600 / boardSize;
		//the space between the buttons
		gameGrid.setHgap(8);
		gameGrid.setVgap(8);
		gameGrid.setGridLinesVisible(true); //Debug only 
		// this metohd gets called after the creation of the class.

		/*
		for (int i = 0; i < boardSize; i++) {
			ColumnConstraints column = new ColumnConstraints(100);
			gameGrid.getColumnConstraints().add(column);
		}

		for (int i = 0; i < boardSize; i++) {
			RowConstraints row = new RowConstraints(100);
			gameGrid.getRowConstraints().add(row);
		}
*/
		// Create the board objects

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				Circle c = new Circle((circleSize/2)-4, Color.web("white"));
				//c.resize(boardSize, boardSize);

				//c.getStyleClass().add("circle-white");
				//c.applyCss();
				// Color col = new Color(1, 1, 1, 1);
				// c.fillProperty().setValue(col);
				//c.setVisible(true);
				gameGrid.add(c, i, j);
			}
		}
	}

	public GameViewController() {
		// TODO Auto-generated constructor stub
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
