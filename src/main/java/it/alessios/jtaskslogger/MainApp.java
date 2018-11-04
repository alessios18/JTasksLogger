package it.alessios.jtaskslogger;

import java.io.IOException;

import it.alessios.jtaskslogger.controller.TaskOverViewController;
import it.alessios.jtaskslogger.model.RunningTask;
import it.alessios.jtaskslogger.model.Task;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	public final static String FXML_FILE_PATH = "view/fxmlfile/";
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Task> taskData = FXCollections.observableArrayList();
    private ObservableList<RunningTask> runningTaskData	 = FXCollections.observableArrayList();
    
    public MainApp() {
    	taskData.add(new Task("Test Task 111111111"));
    	taskData.add(new Task("Test Task 2"));
    	taskData.add(new Task("Test Task 323"));
    	taskData.add(new Task("Test Task 4123"));
    	taskData.add(new Task("Test Task 22e"));
    	taskData.add(new Task("Test Task 31e"));
    	taskData.add(new Task("Test Task 445y"));
    	taskData.add(new Task("Test Task 4742"));
    	taskData.add(new Task("Test Task 363737"));
    	taskData.add(new Task("Test Task 4264"));
    	taskData.add(new Task("Test Task 22424"));
    	taskData.add(new Task("Test Task 363543"));
    	taskData.add(new Task("Test Task 4356"));
    	taskData.add(new Task("Test Task 25252"));
    	taskData.add(new Task("Test Task 3890"));
    	taskData.add(new Task("Test Task 257974"));
    	taskData.add(new Task("Test Task 279889"));
    	taskData.add(new Task("Test Task 3780"));
    	taskData.add(new Task("Test Task 6784"));
    	taskData.add(new Task("Test Task 2686"));
    	taskData.add(new Task("Test Task 6803"));
    	taskData.add(new Task("Test Task 6468"));
    	
    }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("JTasksLogger");
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon_app.png"));

        initRootLayout();

        showTaskOverview();
	}
	
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(FXML_FILE_PATH+"RootPane.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

//            // Give the controller access to the main app.
//            RootLayoutController controller = loader.getController();
//            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }	
	
	public void showTaskOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(FXML_FILE_PATH+"TaskOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            TaskOverViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	

	public ObservableList<Task> getTaskData() {
		return taskData;
	}
	
	public ObservableList<RunningTask> getRunningTaskData() {
		return runningTaskData;
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
