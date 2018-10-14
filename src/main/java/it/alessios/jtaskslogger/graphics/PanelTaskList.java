/**
 * 
 */
package it.alessios.jtaskslogger.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.alessios.jtaskslogger.interfaces.GraphicsInteface;
import it.alessios.jtaskslogger.taskmanager.Task;
import it.alessios.jtaskslogger.taskmanager.TaskManager;

/**
 * @author alessio
 *
 */
public class PanelTaskList extends JPanel implements GraphicsInteface {
	
	TasksLoggerFrame parent;
	
	JPanel list = null;
	
	public static TaskManager manger = TaskManager.getinstance();
	
	public PanelTaskList(TasksLoggerFrame parent) {
		this.parent = parent;
		initializeUI();
	}

	/* (non-Javadoc)
	 * @see it.alessios.jtaskslogger.interfaces.GraphicsInteface#initializeUI()
	 */
	public void initializeUI(){
		this.setPreferredSize(new Dimension(300, 250));
		list = new JPanel();
		JScrollPane sc = new JScrollPane(list);
		updateListpane();
		this.add(sc);
	}

	public void updateListpane() {
		list.setLayout(new GridLayout(manger.getCurrentTaskSize(), 1));
		ArrayList<Task> tasks = manger.getTaskList();
		for(int t = 0;t<tasks.size();t++) {
			JPanel pTask = new JPanel();
			pTask.setLayout(new BorderLayout());
			Task tmp = tasks.get(t);
			pTask.add(new Label(tmp.toString()),BorderLayout.CENTER);
			JButton startPause = new JButton("Play");
			JButton stop = new JButton("Stop");
			JPanel pBut=new JPanel();
			pBut.add(startPause);
			pBut.add(stop);
			pTask.add(pBut,BorderLayout.EAST);			
			list.add(pTask);
			list.repaint();
		}
	}
}
