/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import java.time.LocalDate;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.model.RunningTask;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.view.TaskListCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        	mainApp.getRunningTaskData().add(new RunningTask(mainApp.getTaskData().get(selectedIndex)));
    	}
    }
}
