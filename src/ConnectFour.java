import javafx.application.Application;
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
	
	private void setBoardSize(String x){
		if(x==null){
			
		}
		boardSize = Integer.valueOf(x);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

}
