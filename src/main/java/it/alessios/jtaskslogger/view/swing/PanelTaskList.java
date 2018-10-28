/**
 * 
 */
package it.alessios.jtaskslogger.view.swing;

import it.alessios.jtaskslogger.controller.TaskManager;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.view.swing.interfaces.GraphicsInteface;
import it.alessios.jtaskslogger.view.swing.listener.PlayPauseListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author alessio
 *
 */
public class PanelTaskList extends JPanel implements GraphicsInteface {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8118172287394724205L;

	TasksLoggerFrame parent;
	
	JPanel list = null;
	
	public static TaskManager manger = TaskManager.getinstance();
	
	public PanelTaskList(TasksLoggerFrame parent) {
		super();
		this.parent = parent;
		initializeUI();
	}

	/* (non-Javadoc)
	 * @see it.alessios.jtaskslogger.interfaces.GraphicsInteface#initializeUI()
	 */
	public void initializeUI(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(parent.getMWidth(), parent.getMHeight()));
		list = new JPanel();
		
		updateListpane();
		
	}

	public void updateListpane() {
		ArrayList<JButton> blist = new ArrayList<JButton>();
		list.removeAll();
		ArrayList<Task> tasks = manger.getTaskList();
		int rows = tasks.size()<parent.getMHeight()/30?parent.getMHeight()/30:tasks.size();
		list.setLayout(new GridLayout(rows, 1));
		for(int t = 0;t<tasks.size();t++) {
			JPanel pTask = new JPanel();
			pTask.setBorder(new EmptyBorder(2, 2,2, 2));
			pTask.setLayout(new GridLayout(1, 2));
			Task tmp = tasks.get(t);
			pTask.add(new Label(tmp.toString()));
			JButton startPause = new JButton("Play");
			blist.add(startPause);
			startPause.addActionListener(new PlayPauseListener(tmp,blist));
			JButton stop = new JButton("Stop");
			JPanel pBut=new JPanel();
			pBut.add(startPause);
			pBut.add(stop);
			pTask.add(pBut);			
			list.add(pTask);
		}
		JScrollPane sc = new JScrollPane(list);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.removeAll();
		this.add(sc,BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	public GraphicsInteface getParentContainer() {
		return this.parent;
	}
}
