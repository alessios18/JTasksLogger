/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import java.io.IOException;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.view.ExceptionDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author alessio
 *
 */
public class RootPaneController {
	// Reference to the main application
    private MainApp mainApp;

    public RootPaneController() {}
    
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleExport() {
    	try {
    		mainApp.showExportDialog();
		} catch (IOException e) {
			ExceptionDialog.showException(e);
		}
    }
    
    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("JTaskLogger");
        alert.setHeaderText("About");
        alert.setContentText("Author: Alessio Segantin\nGitHub:github.com/alessios18/JTasksLogger\nWebsite: alessios18.github.io/JTasksLogger/");
        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
