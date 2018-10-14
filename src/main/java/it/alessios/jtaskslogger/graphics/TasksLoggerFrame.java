/**
 * 
 */
package it.alessios.jtaskslogger.graphics;

import java.awt.BorderLayout;
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

	private PanelTaskChooser pTaskChooser = null;
	private PanelTaskList pTasklist = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5487248759818715800L;

	public TasksLoggerFrame() throws Exception{
		initializeUI();
		
	}

	public void initializeUI() throws Exception {
		this.setTitle("JTasksLogger");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sheight = screenSize.height/2;
		int swidth = screenSize.width/2;
		this.setSize(sheight-width/2, swidth - height/2);
		this.setSize(width, height);
		this.setLayout(new BorderLayout());
		
		pTaskChooser = new PanelTaskChooser(this);
		this.add(pTaskChooser,BorderLayout.NORTH);
		
		pTasklist = new PanelTaskList(this);
		this.add(pTasklist,BorderLayout.CENTER);
		
		this.pack();
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

	public PanelTaskChooser getpTaskChooser() {
		return pTaskChooser;
	}

	public PanelTaskList getpTasklist() {
		return pTasklist;
	}
	
	
}
