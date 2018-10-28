/**
 * 
 */
package it.alessios.jtaskslogger.view.controllers;

import java.io.IOException;
import java.text.ParseException;

import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.ChooserPanelDrawer;
import it.alessios.jtaskslogger.view.events.NewTaskEventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * @author alessio
 *
 */
public class ChooserPaneController {
	@FXML
	private Button newTask;
	
	@FXML
	private ComboBox<Task> taskList;

	// Reference to the main application.
	private ChooserPanelDrawer pane;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public ChooserPaneController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 * @throws UnsupportedOperatingSystemException 
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public void setMainApp(ChooserPanelDrawer pane) throws IOException, ParseException, UnsupportedOperatingSystemException {
		this.pane = pane;
		newTask.setOnAction(new NewTaskEventHandler(pane));
		
		reloadTaskList();
	}
	
	public void reloadTaskList() throws IOException, ParseException, UnsupportedOperatingSystemException {
		taskList.getItems().clear();
        taskList.setItems(pane.getObserverTaskList());
	}
}
