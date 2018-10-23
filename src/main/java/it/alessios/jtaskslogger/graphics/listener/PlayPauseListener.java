/**
 * 
 */
package it.alessios.jtaskslogger.graphics.listener;

import it.alessios.jtaskslogger.taskmanager.Task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

/**
 * @author alessio
 *
 */
public class PlayPauseListener implements ActionListener {
	Task t = null;
	ArrayList<JButton> bList = null;
	boolean isPlaing = false;
	
	public PlayPauseListener(Task t,ArrayList<JButton> bList){
		this.t= t;
		this.bList = bList;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(!isPlaing){
			t.startTask();
			b.setText("Pause");
			resetOther();
		}else{
			t.pauseTask();
			b.setText("Play");
		}
		isPlaing=!isPlaing;
	}

	private void resetOther() {
		for(int i = 0;bList != null && i<bList.size();i++){
			ActionListener[] arr = bList.get(i).getActionListeners();
			for(int l = 0;l<arr.length;l++){
				if(arr[l] instanceof PlayPauseListener){
					if(((PlayPauseListener)arr[l]).isPlaing()){
						bList.get(i).doClick();
					}
				}
			}
		}
	}
	
	public boolean isPlaing(){
		return isPlaing;
	}
	
	public void reset(){
		isPlaing=false;
	}

}
