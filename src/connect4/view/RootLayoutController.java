package connect4.view;

import connect4.ConnectFour;
import javafx.fxml.FXML;

public class RootLayoutController {
	
	private ConnectFour mainApp;

	public RootLayoutController() {
		// TODO Auto-generated constructor stub
		// This controller might not do much anyways 
	}

	/**
	 * Sets the main application for the controller
	 * @param app the Application
	 */
	public void setMainApp(ConnectFour app) {
		this.mainApp = app;
	}

}
