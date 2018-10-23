/**
 * 
 */
package it.alessios.jtaskslogger.graphics.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.alessios.jtaskslogger.graphics.PanelTaskChooser;
import it.alessios.jtaskslogger.graphics.TasksLoggerFrame;
import it.alessios.jtaskslogger.taskmanager.Task;
import it.alessios.jtaskslogger.taskmanager.TaskManager;

/**
 * @author alessio
 *
 */
public class AddCurrentTaskListener implements ActionListener {
	PanelTaskChooser parent;
	
	public AddCurrentTaskListener(PanelTaskChooser parent) {
		super();
		this.parent = parent;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Task t = (Task) parent.getTaskList().getSelectedItem();
		if (t!=null){
		TaskManager.getinstance().addCurrentTask(t);
		((TasksLoggerFrame)parent.getParentContainer()).getpTasklist().updateListpane();
		}else{
			JOptionPane.showMessageDialog(parent, "No task selected, please select one!");
		}
	}

}
