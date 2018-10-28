/**
 * 
 */
package it.alessios.jtaskslogger;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.MainView;
import it.alessios.jtaskslogger.view.TaskElementView;
import it.alessios.jtaskslogger.view.swing.TasksLoggerFrame;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author alessio
 *
 */
public class JTasksLogger extends Application{

	private Stage primaryStage;
	private MainView mainView;

	/**
	 * @param args
	 */


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("JTaskLogger");

		try {
			initRootLayout();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperatingSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Initializes the root layout.
	 * @throws UnsupportedOperatingSystemException 
	 * @throws ParseException 
	 */
	public void initRootLayout() throws ParseException, UnsupportedOperatingSystemException {
		mainView = new MainView(this);
		primaryStage.setScene(mainView.getScene());
		primaryStage.show();
	}



	public static void main(String[] args) {


		// schedule this for the event dispatch thread (edt)
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				//displayJFrame();
				runApp();
			}
		});		
	}

	static void runApp() {
		launch(new String[0]);
	}

	public ObservableList<AnchorPane> getList() throws ParseException, UnsupportedOperatingSystemException {
		ArrayList<AnchorPane> list = new ArrayList<AnchorPane>();
		
		list.add((AnchorPane) new TaskElementView().getPane());
		list.add((AnchorPane) new TaskElementView().getPane());
		list.add((AnchorPane) new TaskElementView().getPane());
		list.add((AnchorPane) new TaskElementView().getPane());
		
		ObservableList<AnchorPane> observer = FXCollections.observableList(list);
		//elTaskList.getItems().clear();
		return (observer);
	}


	static void displayJFrame(){
		try {
			TasksLoggerFrame frame = new TasksLoggerFrame();
			showOnScreen(0, frame);
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
