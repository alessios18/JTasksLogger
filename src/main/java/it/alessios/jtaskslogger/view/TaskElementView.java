package it.alessios.jtaskslogger.view;

import java.io.IOException;
import java.text.ParseException;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.utility.ConstantsLibrary;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.controllers.TaskElementController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TaskElementView {
	private JTasksLogger mainApp;
	private AnchorPane rootLayout = null;
	private Scene scene = null;
	private Task task;
	
	private TaskElementController controller;
	
	public TaskElementView(JTasksLogger mainApp,Task t) throws ParseException, UnsupportedOperatingSystemException {
		this.mainApp = mainApp;
		this.task = t;
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
            controller = loader.getController();
            controller.setMainApp(mainApp,this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Scene getScene() {
		return scene;
	}
	public Pane getPane() {
		return rootLayout;
	}

	public TaskElementController getController() {
		return controller;
	}
	
	
}
