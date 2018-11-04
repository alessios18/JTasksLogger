/**
 * 
 */
package it.alessios.jtaskslogger.view;

import java.io.IOException;
import java.text.ParseException;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.controller.DataStorage;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.utility.ConstantsLibrary;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.controllers.ChooserPaneController;
import it.alessios.jtaskslogger.view.controllers.ListPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author alessio
 *
 */
public class ChooserPanelDrawer {
	private JTasksLogger mainApp;
	private AnchorPane rootLayout = null;
	private Scene scene = null;
	ObservableList<Task> observer;
	
	ChooserPaneController controller;
	
	public ChooserPanelDrawer(JTasksLogger mainApp) throws ParseException, UnsupportedOperatingSystemException {
		this.mainApp = mainApp;
		initRootLayout();
	}
	
	public void initRootLayout() throws ParseException, UnsupportedOperatingSystemException {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JTasksLogger.class.getResource(ConstantsLibrary.FXML_PATH+"JTaskChooserPane.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            scene = new Scene(rootLayout);   
            controller = loader.getController();
            controller.setMainApp(mainApp,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Scene getScene() {
		return scene;
	}
	public Pane getPane() {
		return rootLayout;
	}

	public ObservableList<Task> getObserverTaskList() throws IOException, ParseException, UnsupportedOperatingSystemException {
		observer = FXCollections.observableList(DataStorage.getinstance().readTasksFile());
		return observer;
	}
		
	public ChooserPaneController getController() {
		return controller;
	}

	public void reloadTaskList() throws IOException, ParseException, UnsupportedOperatingSystemException {
		controller.reloadTaskList();
	}
}
