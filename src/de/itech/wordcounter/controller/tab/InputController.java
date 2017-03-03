package de.itech.wordcounter.controller.tab;

import de.itech.wordcounter.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class InputController {
	
	private Controller mainController;
	@FXML public TextArea inputText;
	@FXML private Button clear;
	@FXML private Button show;
	@FXML private CheckBox checkbox;

	@FXML private void inputBtnShowClicked(ActionEvent event) {
		// Shows the result in second tab result textArea
		if (!inputText.getText().isEmpty()) {
			mainController.showText(inputText.getText(), checkbox.isSelected());
		}
	}

	@FXML private void inputBtnClearClicked(ActionEvent event) {
		inputText.setText("");
	}

	public void init(Controller controller) {
		mainController = controller;
	}	
}
