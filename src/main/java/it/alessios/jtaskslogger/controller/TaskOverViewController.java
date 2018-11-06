/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.model.RunningTask;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.util.DataStorage;
import it.alessios.jtaskslogger.view.TaskListCell;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

/**
 * @author alessio
 *
 */
public class TaskOverViewController {

	@FXML
	private TableView<Task> taskTable;
	@FXML
	private TableColumn<Task, String> firstNameColumn;
	@FXML
	private TableColumn<Task, LocalDate> creationDateColumn;
	@FXML
	private ListView<RunningTask> taskList;

	// Reference to the main application.
	private MainApp mainApp;

	private ArrayList<Integer> addedTask = new ArrayList<>();

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getNameTaskProperty());
		creationDateColumn.setCellValueFactory(
				cellData -> cellData.getValue().getCreationDateProperty());

		taskList.setCellFactory(new Callback<ListView<RunningTask>, ListCell<RunningTask>>() {

			@Override
			public ListCell<RunningTask> call(ListView<RunningTask> param) {
				// TODO Auto-generated method stub
				return new TaskListCell(mainApp);
			}


		});
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		taskTable.setItems(mainApp.getTaskData());
		taskList.setItems(mainApp.getRunningTaskData());
	}

	@FXML
	private void handleAddRunningTask() {
		int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex > -1) {
			if(checkAlreadyAdded(selectedIndex)) {
				mainApp.getRunningTaskData().add(new RunningTask(mainApp.getTaskData().get(selectedIndex)));
			}else {
				Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Task already added");
	            alert.setHeaderText("Task already added");
	            alert.setContentText("it will not add");
	            
	            alert.showAndWait();
			}
		}
	}

	public boolean checkAlreadyAdded(int index) {
		for (Integer integer : addedTask) {
			if(index == integer) {
				return false;
			}
		}
		addedTask.add(index);
		return true;
	}

	@FXML
	private void handleNewTask() {
		Task newTask = new Task();
		boolean okClicked = mainApp.showNewTaskDialog(newTask);
		if (okClicked) {
			mainApp.getTaskData().add(newTask);
			try {
				mainApp.saveTaskDataToFile(DataStorage.getinstance().getTaskFile());
			} catch (UnsupportedOperatingSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
