/**
 * 
 */
package it.alessios.jtaskslogger.graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import it.alessios.jtaskslogger.interfaces.GraphicsInteface;

/**
 * @author alessio
 *
 */
public class TasksLoggerFrame extends JFrame implements GraphicsInteface{
	int width = 300;
	int height = 300;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5487248759818715800L;

	public TasksLoggerFrame(){
		initializeUI();
		this.setVisible(true);
	}

	public void initializeUI() {
		this.setTitle("JTasksLogger");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sheight = screenSize.height/2;
		int swidth = screenSize.width/2;
		this.setSize(sheight-width/2, swidth - height/2);
		this.setSize(width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
