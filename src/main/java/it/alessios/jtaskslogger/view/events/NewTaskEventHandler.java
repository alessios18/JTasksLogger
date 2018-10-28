/**
 * 
 */
package it.alessios.jtaskslogger.view.events;

import javax.swing.JOptionPane;

import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.view.ChooserPanelDrawer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
//		String taskName = JOptionPane.showInputDialog(null, "Task Name: ", "New Task", JOptionPane.OK_CANCEL_OPTION);
//		if(taskName != null) {
			Task task;
			try {
				task = new Task("taskName");
				task.save();
				chooserPanelDrawer.reloadTaskList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
		
	}

}
