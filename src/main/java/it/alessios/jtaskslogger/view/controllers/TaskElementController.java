/**
 * 
 */
package it.alessios.jtaskslogger.view.controllers;


import java.io.IOException;
import java.text.ParseException;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.TaskElementView;
import it.alessios.jtaskslogger.view.events.PlayPauseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author alessio
 *
 */
public class TaskElementController {
	@FXML
	private Button playPause;
	
	@FXML
	private Button stop;
	
	@FXML
	private Label taskLabel;

	// Reference to the main application.
	private TaskElementView pane;
	
	private JTasksLogger mainApp;
	
	public TaskElementController() {
	}
	
	public void setMainApp(JTasksLogger mainApp, TaskElementView pane) throws IOException, ParseException, UnsupportedOperatingSystemException {
		this.mainApp = mainApp;
		this.pane = pane;
		taskLabel.setText(pane.getTask().getName());
		playPause.setOnAction(new PlayPauseHandler(pane));
	}

	public Button getPlayPause() {
		return playPause;
	}
	
	
}
