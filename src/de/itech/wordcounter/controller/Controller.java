package de.itech.wordcounter.controller;

import de.itech.wordcounter.controller.tab.InputController;
import de.itech.wordcounter.controller.tab.OutputController;
import de.itech.wordcounter.model.Counter;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class Controller {

	@FXML InputController inputController;
	@FXML OutputController outputController;
	@FXML public TabPane tab;
	
	@FXML public void initialize() {
		System.out.println("App started");
		inputController.init(this);
		outputController.init(this);
	}

	public void showText(String text, Boolean topWords) {
		String result = Counter.wordCount(text, topWords);
		outputController.result.setText(result);
		tab.getSelectionModel().select(1);
	}

	public void resetInput() {
		inputController.inputText.setText("");
	}
}
