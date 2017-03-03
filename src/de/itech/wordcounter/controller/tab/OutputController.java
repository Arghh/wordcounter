package de.itech.wordcounter.controller.tab;

import de.itech.wordcounter.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class OutputController {

	private Controller mainController;
	@FXML private Button save;
	@FXML private Button reset;	
	@FXML public TextArea result;

	public void init(Controller controller) {
		mainController = controller;
	}

	@FXML private void outBtnResetClicked(ActionEvent event) {
		result.setText("");
		mainController.tab.getSelectionModel().select(0);
		mainController.resetInput();
	}
}
