package connect4.view;

import connect4.ConnectFour;
import connect4.model.Board;
import connect4.model.Player;
import connect4.model.PlayerFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class MenuViewController {

	private ConnectFour mainApp;
	private Color p1Color;
	private Color p2Color;
	private PlayerFactory playerFactory;

	@FXML
	private ColorPicker p1ColorPicker;
	@FXML
	private ColorPicker p2ColorPicker;
	@FXML
	private ChoiceBox<String> p1Selector;
	@FXML
	private ChoiceBox<String> p2Selector;

	@FXML
	private void initialize() {
		playerFactory = new PlayerFactory();

		p1Selector.getItems().add("Human");
		p1Selector.getItems().add("Computer");
		p2Selector.getItems().add("Human");
		p2Selector.getItems().add("Computer");
		
		p1ColorPicker.setValue(Color.YELLOW);
		p2ColorPicker.setValue(Color.RED);

		p1ColorPicker.setOnAction(e -> {
			setColor(p1ColorPicker, p1Color);
		});
		p1ColorPicker.setOnAction(e -> {
			setColor(p2ColorPicker, p2Color);
		});
	}

	private void setColor(ColorPicker colorPicker, Color c) {
		c = colorPicker.getValue();
	}

	public void setMainApp(ConnectFour connectFour) {
		this.mainApp = connectFour;
	}

	@FXML
	private boolean startGame() {
		Board board = new Board();
		Player p1 = playerFactory.createPlayer((String) p1Selector.getValue());
		Player p2 = playerFactory.createPlayer((String) p2Selector.getValue());
		mainApp.addPlayer(p1);
		mainApp.addPlayer(p2);
		mainApp.setBoard(board);
		mainApp.initGameView();
		return false;
	}

}
