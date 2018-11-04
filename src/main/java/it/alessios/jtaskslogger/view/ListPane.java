/**
 * 
 */
package it.alessios.jtaskslogger.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.utility.ConstantsLibrary;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.controllers.ListPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author alessio
 *
 */
public class ListPane {
	
	
	private AnchorPane rootLayout = null;
	private Scene scene = null;
	
	private JTasksLogger mainApp;
	private ListPaneController controller;
	
	public ListPane(JTasksLogger mainApp) throws ParseException, UnsupportedOperatingSystemException {
		this.mainApp = mainApp;
		initRootLayout();
	}
	
	public void initRootLayout() throws ParseException, UnsupportedOperatingSystemException {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JTasksLogger.class.getResource(ConstantsLibrary.FXML_PATH+"ListPane.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            scene = new Scene(rootLayout);   
            
            controller = loader.getController();
            controller.setMainApp(mainApp);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void updateList() throws ParseException, UnsupportedOperatingSystemException {
		getController().updateList();
	}

	public ListPaneController getController() {
		return controller;
	}

	public Scene getScene() {
		return scene;
	}
	public Pane getPane() {
		return rootLayout;
	}
}
