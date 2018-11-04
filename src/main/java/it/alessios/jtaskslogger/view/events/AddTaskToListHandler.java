/**
 * 
 */
package it.alessios.jtaskslogger.view.events;

import java.text.ParseException;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.controller.TaskManager;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.ChooserPanelDrawer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author alessio
 *
 */
public class AddTaskToListHandler implements EventHandler<ActionEvent> {
	private JTasksLogger mainApp;
	private ChooserPanelDrawer chooserPanelDrawer;

	public AddTaskToListHandler(JTasksLogger mainApp,ChooserPanelDrawer chooserPanelDrawer){
		this.mainApp = mainApp;
		this.chooserPanelDrawer = chooserPanelDrawer;
	}

	public void handle(ActionEvent event) {
		Task t = chooserPanelDrawer.getController().getSelectedTask();
		if(t != null) {
			TaskManager.getinstance().addCurrentTask(t);
			try {
				mainApp.updateList();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedOperatingSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
