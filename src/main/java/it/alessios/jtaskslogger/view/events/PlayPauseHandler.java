/**
 * 
 */
package it.alessios.jtaskslogger.view.events;

import java.util.ArrayList;

import javax.swing.JButton;

import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.view.TaskElementView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * @author alessio
 *
 */
public class PlayPauseHandler implements EventHandler<ActionEvent> {
	Task t = null;
	ArrayList<JButton> bList = null;
	boolean isPlaing = false;
	TaskElementView pane;
	
	public PlayPauseHandler(TaskElementView pane) {
		this.pane = pane;
		this.t = pane.getTask();
	}

	public void handle(ActionEvent event) {
		
		Button b = (Button) event.getSource();
		if(!isPlaing){
			t.startTask();
			b.setText("Pause");
			//resetOther();
		}else{
			t.pauseTask();
			System.out.println(t.getLife());
			b.setText("Play");
		}
		isPlaing=!isPlaing;
	}

}
