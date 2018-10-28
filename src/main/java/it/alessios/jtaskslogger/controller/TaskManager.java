/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import java.util.ArrayList;

import it.alessios.jtaskslogger.model.Task;

/**
 * @author alessio
 *
 */
public class TaskManager {
	private static TaskManager taskManager = null;
	
	private ArrayList<Task> list = new ArrayList<Task>();
	
	private TaskManager() {
	}

	public static TaskManager getinstance() {
		if(taskManager == null) {
			taskManager = new TaskManager();
		}
		return taskManager;
	}
	
	public void addCurrentTask(Task t) {
		list.add(t);
	}
	
	public int getCurrentTaskSize() {
		return list.size();
	}
	
	public ArrayList<Task> getTaskList(){
		return list;
	}
}
