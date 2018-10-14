/**
 * 
 */
package it.alessios.jtaskslogger;

import it.alessios.jtaskslogger.datastorage.DataStorage;
import it.alessios.jtaskslogger.graphics.TasksLoggerFrame;

/**
 * @author alessio
 *
 */
public class JTasksLogger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TasksLoggerFrame frame = new TasksLoggerFrame();
		DataStorage.getinstance();
	}
	
	

}
