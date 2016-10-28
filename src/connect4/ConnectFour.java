package connect4;

import java.io.IOException;

import connect4.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ConnectFour extends Application {

	private BorderPane rootLayout;
	private Stage primaryStage;
	private static int boardSize = 7;
	private static int scoreToWin = 4;
	
	public static void main(String[] args) {
		
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
		// TODO Auto-generated method stub
		// alot like the initRootLayout just slightly differernt 
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



	private void setBoardSize(String x){
		if(x==null){
			
		}
		boardSize = Integer.valueOf(x);
	}

}
