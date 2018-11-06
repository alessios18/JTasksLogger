/**
 * 
 */
package it.alessios.jtaskslogger.controller;

import java.io.IOException;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.model.RunningTask;
import it.alessios.jtaskslogger.util.DataStorage;
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

	private static Image playIcon = null;
	private static Image pauseIcon = null;

	private ThreadRefresher tr = null;

	// Reference to the main application.
	private MainApp mainApp;

	public RunningTaskControllers() {
		if(playIcon == null || pauseIcon == null) {
			playIcon = new Image(getClass().getResourceAsStream("/images/play_icon.png"));
			pauseIcon = new Image(getClass().getResourceAsStream("/images/pause_icon.png"));
		}
	}

	@FXML
	private void initialize() {
	}


	public void setMainAppRunningTask(MainApp mainApp,RunningTask runtask) {
		this.mainApp = mainApp;
		this.runtask = runtask;
		ImageView playIconView = new ImageView(playIcon);
		toggle.setGraphic(playIconView);
		toggle.setText("");
		taskName.setText(mainApp.getTaskById(runtask.getIdTask()).getNameTask());
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
				ExceptionDialog.showException(e);
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
			try {
				mainApp.saveRunningTaskDataToFile(DataStorage.getinstance().getCurrentRunningTaskFile());
			} catch (UnsupportedOperatingSystemException e) {
				// TODO Auto-generated catch block
				ExceptionDialog.showException(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ExceptionDialog.showException(e);
			}
		}
	}
	
	
}