/**
 * 
 */
package it.alessios.jtaskslogger.view.swing.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.swing.PanelTaskChooser;

/**
 * @author alessio
 *
 */
public class NewTaskListener implements ActionListener {
	PanelTaskChooser parent;
	
	public NewTaskListener(PanelTaskChooser parent) {
		super();
		this.parent = parent;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			String taskName = JOptionPane.showInputDialog(parent, "Task Name: ", "New Task", JOptionPane.OK_CANCEL_OPTION);
			if(taskName != null) {
				Task task = new Task("taskName");
				task.save();
			}
			parent.reloadTaskList();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (UnsupportedOperatingSystemException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
