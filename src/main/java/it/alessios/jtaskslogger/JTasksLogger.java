/**
 * 
 */
package it.alessios.jtaskslogger;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;

import it.alessios.jtaskslogger.controller.DataStorage;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.view.swing.TasksLoggerFrame;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author alessio
 *
 */
public class JTasksLogger extends Application{

	private Stage primaryStage;
	private AnchorPane rootLayout;
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
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JTasksLogger.class.getResource("view/JtaskLoggerView.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);   
            ComboBox<Task> taskList = (ComboBox<Task>) scene.lookup("#taskList");
            ObservableList<Task> observer = FXCollections.observableList(DataStorage.getinstance().readTasksFile());
            taskList.getItems().clear();
            taskList.setItems(observer);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	public static void main(String[] args) {
		
		launch(args);
//		// schedule this for the event dispatch thread (edt)
//	    SwingUtilities.invokeLater(new Runnable()
//	    {
//	      public void run()
//	      {
//	        displayJFrame();
//	      }
//	    });		
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
