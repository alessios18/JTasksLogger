/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.model.RunningTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @author alessio
 *
 */
public class RunningTaskControllers {
	@FXML
	Label taskName;
	@FXML
	Label taskLife;
	@FXML
	Button toggle;

	@FXML
	private AnchorPane mainPane;

	private RunningTask runtask;

	private static Image playIcon = new Image("file:resources/images/play_icon.png");
	private static Image pauseIcon = new Image("file:resources/images/pause_icon.png");

	private ThreadRefresher tr = null;

	// Reference to the main application.
	private MainApp mainApp;

	public RunningTaskControllers() {
	}

	@FXML
	private void initialize() {
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setRunningTask(RunningTask runtask) {
		this.runtask = runtask;
		ImageView playIconView = new ImageView(playIcon);
		toggle.setGraphic(playIconView);
		toggle.setText("");
		taskName.setText(runtask.getTask().getNameTask());
		taskLife.setText(runtask.getFormattedLife(runtask.getLife()));
		if(runtask.isRunning()) {
			play();
		}
	}

	public Node getView() {
		return mainPane ;
	}
	private class ThreadRefresher extends Thread{
		public void run() {
			try {
				while(runtask.isRunning()) {
					Platform.runLater(() -> taskLife.setText(runtask.getFormattedLife(runtask.getInterval())));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void play() {
		tr=new ThreadRefresher();
		tr.start();
		ImageView pauseIconView = new ImageView(pauseIcon);
		toggle.setGraphic(pauseIconView);
	}

	private void pause() {
		runtask.getFormattedLife(runtask.getLife());
		ImageView playIconView = new ImageView(playIcon);
		toggle.setGraphic(playIconView);
	}

	@FXML
	private void handlePlayTask() {
		if(!runtask.isRunning()) {
			runtask.startTask();
			play();
		}else {
			runtask.pauseTask();
			pause();
		}
	}
}