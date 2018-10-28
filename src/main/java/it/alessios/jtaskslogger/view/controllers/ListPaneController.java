package it.alessios.jtaskslogger.view.controllers;

import java.text.ParseException;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ListPaneController {
	@FXML
	private ListView<AnchorPane>elTaskList;

    // Reference to the main application.
    private JTasksLogger mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ListPaneController() {
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
     */
    public void setMainApp(JTasksLogger mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        try {
			elTaskList.setItems(mainApp.getList());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperatingSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
