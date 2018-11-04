/**
 * 
 */
package it.alessios.jtaskslogger.view.events;

import java.util.Optional;

import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.view.ChooserPanelDrawer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

/**
 * @author alessio
 *
 */
public class NewTaskEventHandler implements EventHandler<ActionEvent> {
	ChooserPanelDrawer chooserPanelDrawer;
	
	public NewTaskEventHandler(ChooserPanelDrawer chooserPanelDrawer){
		this.chooserPanelDrawer = chooserPanelDrawer;
	}

	public void handle(ActionEvent event) {
		
		
		TextInputDialog dialog = new TextInputDialog("task name..");
		dialog.setTitle("New Task");
		dialog.setHeaderText("Insert the task name");
		dialog.setContentText("Task Name: ");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			Task task;
			try {
				task = new Task(result.get());
				task.save();
				chooserPanelDrawer.reloadTaskList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
