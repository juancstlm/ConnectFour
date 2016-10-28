package connect4.view;

import connect4.ConnectFour;
import javafx.fxml.FXML;

public class GameViewController {
	
	private ConnectFour mainApp;
	
	@FXML
	public void initialize(){
		// this metohd gets called after the creation of the class.
		// put a for loop that creates the game board and use lamnda to give our board the appropriate event listener
	}

	public GameViewController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Give the controller access to the main application 
	 * @param app
	 */
	public void setMainApp(ConnectFour app){
		this.mainApp = app;
	}

}
