/**
 * 
 */
package it.alessios.jtaskslogger.view;

import java.io.IOException;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.controller.RunningTaskControllers;
import it.alessios.jtaskslogger.model.RunningTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

/**
 * @author alessio
 *
 */
public class TaskListCell extends ListCell<RunningTask> {
	private RunningTaskControllers runningTaskCell = null;
	private MainApp mainApp;
	
	public TaskListCell(MainApp mainApp) {
		this.mainApp = mainApp;
		FXMLLoader mLLoader = new FXMLLoader(MainApp.class.getResource(MainApp.FXML_FILE_PATH+"RunningTask.fxml"));
		try {
			mLLoader.load();
			runningTaskCell = mLLoader.getController();
		} catch (IOException e) {
		}
	}
	@Override
	protected void updateItem(RunningTask item, boolean empty) {
		 super.updateItem(item, empty);
         if(empty) {
        	 setGraphic(null);
         }else {
        	 runningTaskCell.setMainAppRunningTask(mainApp,item);
        	 setGraphic(runningTaskCell.getView());
         }
	}
}