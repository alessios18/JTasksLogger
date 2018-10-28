package it.alessios.jtaskslogger.view;

import java.io.IOException;
import java.text.ParseException;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.utility.ConstantsLibrary;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TaskElementView {
	private AnchorPane rootLayout = null;
	private Scene scene = null;
	
	public TaskElementView() throws ParseException, UnsupportedOperatingSystemException {
		initRootLayout();
	}
	
	public void initRootLayout() throws ParseException, UnsupportedOperatingSystemException {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JTasksLogger.class.getResource(ConstantsLibrary.FXML_PATH+"TaskElement.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            scene = new Scene(rootLayout);   
            
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
}
