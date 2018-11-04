package it.alessios.jtaskslogger.view.controllers;

import java.text.ParseException;
import java.util.ArrayList;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.controller.TaskManager;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.TaskElementView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

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
		elTaskList.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
		    @Override
		    public ListCell<Student> call(ListView<Student> studentListView) {
		        return new StudentListViewCell();
		    }
		});
		// Add observable list data to the table
		try {
			updateList();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperatingSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateList() throws ParseException, UnsupportedOperatingSystemException {
		elTaskList.setItems(this.getList());
	}

	public ObservableList<AnchorPane> getList() throws ParseException, UnsupportedOperatingSystemException {
		ArrayList<AnchorPane> listPane = new ArrayList<AnchorPane>();
		if(elTaskList.getItems().size()>0) {

		}else {
			ArrayList<Task> list =TaskManager.getinstance().getTaskList();

			for (Task task : list) {

				listPane.add((AnchorPane) new TaskElementView(mainApp,task).getPane());
			}
		}

		ObservableList<AnchorPane> observer = FXCollections.observableList(listPane);
		//elTaskList.getItems().clear();
		return (observer);
	}
}
