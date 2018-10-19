/**
 * 
 */
package it.alessios.jtaskslogger;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
		// schedule this for the event dispatch thread (edt)
	    SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	        displayJFrame();
	      }
	    });		
	}
	static void displayJFrame(){
		try {
			TasksLoggerFrame frame = new TasksLoggerFrame();
			showOnScreen(1, frame);
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
	
	public static void showOnScreen( int screen, JFrame frame )	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    int width = ((TasksLoggerFrame)frame).getMWidth(), height = ((TasksLoggerFrame)frame).getMHeight();
	    if( screen > -1 && screen < gd.length ) {
	        width = gd[screen].getDefaultConfiguration().getBounds().width;
	        height = gd[screen].getDefaultConfiguration().getBounds().height;
	        frame.setLocation(
	            ((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x, 
	            ((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
	        );
	        frame.setVisible(true);
	    } else {
	        throw new RuntimeException( "No Screens Found" );
	    }
	}
	

}
