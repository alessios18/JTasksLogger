/**
 * 
 */
package it.alessios.jtaskslogger.graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.alessios.jtaskslogger.datastorage.DataStorage;
import it.alessios.jtaskslogger.graphics.listener.AddCurrentTaskListener;
import it.alessios.jtaskslogger.graphics.listener.NewTaskListener;
import it.alessios.jtaskslogger.interfaces.GraphicsInteface;
import it.alessios.jtaskslogger.taskmanager.Task;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;

/**
 * @author alessio
 *
 */
public class PanelTaskChooser extends JPanel implements GraphicsInteface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1369835793170000839L;
	
	private TasksLoggerFrame parent;
	private JComboBox<Task> taskList;
	private JButton addTask;
	//private JButton newTask;
	
	public PanelTaskChooser(TasksLoggerFrame parent) throws Exception {
		super();
		this.parent = parent;
		initializeUI();
	}

	public void initializeUI() throws Exception{
		Task[] elements = DataStorage.getinstance().getTaskList();
		taskList = new JComboBox<Task>(elements);
		JButton newTask;
		//this.setLayout(new GridLayout(1, 3));
		//this.add(taskList);
//		addTask = new JButton("Add task");		
////		addTask.addActionListener(new AddCurrentTaskListener(this));
//		addTask.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "ascas", "ascas", JOptionPane.INFORMATION_MESSAGE);
//				
//			}
//		});
//		this.add(addTask);
		newTask = new JButton("New task");
		newTask.addActionListener(new NewTaskListener(this));
		this.add(newTask);
	}
	
	public void reloadTaskList() throws IOException, ParseException, UnsupportedOperatingSystemException {
		ArrayList<Task> list =DataStorage.getinstance().readTasksFile();
		taskList.removeAllItems();
	    for(Task t:list){
	    	taskList.addItem(t);
	    }
	}

	public TasksLoggerFrame getParent() {
		return parent;
	}

	public JComboBox<Task> getTaskList() {
		return taskList;
	}

	
	
}
