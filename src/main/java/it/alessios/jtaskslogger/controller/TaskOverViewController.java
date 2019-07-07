/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.model.RunningTask;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.util.DataStorage;
import it.alessios.jtaskslogger.view.ExceptionDialog;
import it.alessios.jtaskslogger.view.TaskListCell;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.collections.transformation.FilteredList;

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
	@FXML
	private TextField taskFilter;

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

				return new TaskListCell(mainApp);
			}


		});

		taskFilter.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				filterTask(newValue);
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
		FilteredList<RunningTask> filteredData = new FilteredList<RunningTask>(mainApp.getRunningTaskData(),new Predicate<RunningTask>() {
			@Override
			public boolean test(RunningTask t) {
				LocalDate now = LocalDate.now();
				if(t.getCreationDate().isEqual(now)){
					addedTask.add(t.getIdTask());
					return true;
				}
				return false;
			}
		});
		taskList.setItems(filteredData);
	}

	@FXML
	private void handleAddRunningTask() {
		int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();

		if(selectedIndex > -1) {
			Task selectedTask = taskTable.getItems().get(selectedIndex);
			if(checkAlreadyAdded(selectedTask.getIdTask())) {
				mainApp.getRunningTaskData().add(new RunningTask(selectedTask));
			}else {
				Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Task already added");
	            alert.setHeaderText("Task already added");
	            alert.setContentText("it will not add");
	            
	            alert.showAndWait();
			}
		}
	}

	public boolean checkAlreadyAdded(Integer idTask) {
		for (Integer integer : addedTask) {
			if(idTask == integer) {
				return false;
			}
		}
		addedTask.add(idTask);
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
				ExceptionDialog.showException(e);
			} catch (IOException e) {
				ExceptionDialog.showException(e);
			}
		}
	}

	private void filterTask(String filter) {
		if(filter != null && !filter.isEmpty()){
			FilteredList<Task> filteredData = new FilteredList<Task>(mainApp.getTaskData(),new Predicate<Task>() {
				@Override
				public boolean test(Task t) {
					return t.getNameTask().contains(filter);
				}
			});
			taskTable.setItems(filteredData);
		}else{
			taskTable.setItems(mainApp.getTaskData());
		}
	}
}
