/**
 * 
 */
package it.alessios.jtaskslogger.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.alessios.jtaskslogger.interfaces.GraphicsInteface;

/**
 * @author alessio
 *
 */
public class TasksLoggerFrame extends JFrame implements GraphicsInteface{
	private int width = 350;
	private int height = 300;

	private PanelTaskChooser pTaskChooser = null;
	private PanelTaskList pTasklist = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5487248759818715800L;

	public TasksLoggerFrame() throws Exception{
		super();
		initializeUI();
		
	}

	public void initializeUI() throws Exception {
		this.setTitle("JTasksLogger");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sheight = screenSize.height/2;
		int swidth = screenSize.width/2;
		this.setLocation(swidth - (width/2),  sheight - (height/2));
		//this.setSize(width, height);
		this.setLayout(new BorderLayout());
		pTaskChooser = new PanelTaskChooser(this);
		this.add(pTaskChooser,BorderLayout.NORTH);
//		
		pTasklist = new PanelTaskList(this);
		this.add(pTasklist,BorderLayout.CENTER);
		
		this.pack();
	}

	public int getMWidth() {
		return width;
	}

	public void setMWidth(int width) {
		this.width = width;
	}

	public int getMHeight() {
		return height;
	}

	public void setMHeight(int height) {
		this.height = height;
	}

	public PanelTaskChooser getpTaskChooser() {
		return pTaskChooser;
	}

	public PanelTaskList getpTasklist() {
		return pTasklist;
	}

	public GraphicsInteface getParentContainer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
