/**
 * 
 */
package it.alessios.jtaskslogger;

import java.io.IOException;

import it.alessios.jtaskslogger.datastorage.DataStorage;
import it.alessios.jtaskslogger.graphics.TasksLoggerFrame;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;

/**
 * @author alessio
 *
 */
public class JTasksLogger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DataStorage.getinstance();
			TasksLoggerFrame frame = new TasksLoggerFrame();

			frame.setVisible(true);
		} catch (UnsupportedOperatingSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
