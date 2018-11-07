/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.model.RowTask;
import it.alessios.jtaskslogger.model.RunningTask;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.model.wrapper.RunningTaskDataWrapper;
import it.alessios.jtaskslogger.model.wrapper.TaskDataWrapper;
import it.alessios.jtaskslogger.util.DataStorage;
import it.alessios.jtaskslogger.util.TimesheetExporter;
import it.alessios.jtaskslogger.view.ExceptionDialog;
import it.alessios.jtaskslogger.view.TaskListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author alessio
 *
 */
public class ExportDialogController {

	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;

	@FXML
	private TableView<RowTask> taskTable;
	@FXML
	private TableColumn<RowTask, LocalDate> dateColumn;
	@FXML
	private TableColumn<RowTask, String> tasknameColumn;
	@FXML
	private TableColumn<RowTask, Float> oursColumn;

	private MainApp mainApp;

	private Stage dialogStage;

	private ObservableList<RowTask> rowTask = FXCollections.observableArrayList();

	private ObservableList<Task> taskData = FXCollections.observableArrayList();
	private ObservableList<RunningTask> runningTaskData	 = FXCollections.observableArrayList();

	public ExportDialogController(){

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp=mainApp;

		startDate.setValue(LocalDate.now());
		startDate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handleChangeDate();
			}
		});
		endDate.setValue(LocalDate.now());
		endDate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handleChangeDate();
			}
		});
		extractRowTasksData(mainApp);
		taskTable.setItems(rowTask);
	}
	
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		tasknameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getNameTaskProperty());
		dateColumn.setCellValueFactory(
				cellData -> cellData.getValue().getDateProperty());
		
		oursColumn.setCellValueFactory(
				cellData -> cellData.getValue().getOursProperty());

	}

	public void extractRowTasksData(MainApp mainApp) {
		try {
			rowTask.clear();
			ArrayList<File> files = DataStorage.getinstance().getFilesByDates(startDate.getValue(), endDate.getValue());
			TaskDataWrapper wrapper = mainApp.loadTask(DataStorage.getinstance().getTaskFile());
			if(wrapper != null) {
				taskData.clear();
				taskData.addAll(wrapper.getTasks());
			}
			runningTaskData.clear();
			for (File file : files) {
				RunningTaskDataWrapper runWrapper = mainApp.loadRunningTask(file);
				runningTaskData.addAll(runWrapper.getRunningTasks());
			}
			for(int r = 0; r< runningTaskData.size();r++) {
				rowTask.add(new RowTask(mainApp.getTaskById(runningTaskData.get(r).getIdTask(), taskData), runningTaskData.get(r))); 
			}
		} catch (UnsupportedOperatingSystemException | IOException | JAXBException e) {
			ExceptionDialog.showException(e);
		}
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	private void handleChangeDate() {
		extractRowTasksData(mainApp);
		taskTable.setItems(rowTask);
	}

	@FXML
	private void handleExport() {
		TimesheetExporter exporter = new TimesheetExporter(mainApp, rowTask);
		try {
			exporter.exportCsvFile();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			ExceptionDialog.showException(e);
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
