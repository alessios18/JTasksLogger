package it.alessios.jtaskslogger;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import it.alessios.jtaskslogger.controller.ExportDialogController;
import it.alessios.jtaskslogger.controller.NewTaskDialogController;
import it.alessios.jtaskslogger.controller.RootPaneController;
import it.alessios.jtaskslogger.controller.TaskOverViewController;
import it.alessios.jtaskslogger.exceptions.UnsupportedOperatingSystemException;
import it.alessios.jtaskslogger.model.RunningTask;
import it.alessios.jtaskslogger.model.Task;
import it.alessios.jtaskslogger.model.wrapper.RunningTaskDataWrapper;
import it.alessios.jtaskslogger.model.wrapper.TaskDataWrapper;
import it.alessios.jtaskslogger.util.DataStorage;
import it.alessios.jtaskslogger.view.ExceptionDialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {
	public final static String FXML_FILE_PATH = "/fxmlfiles/";

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Task> taskData = FXCollections.observableArrayList();
	private ObservableList<RunningTask> runningTaskData	 = FXCollections.observableArrayList();

	public MainApp() {
		try {
			DataStorage.getinstance().checkFiles();
		} catch (UnsupportedOperatingSystemException e) {
			ExceptionDialog.showException(e);
		} catch (IOException e) {
			ExceptionDialog.showException(e);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("JTasksLogger");
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon_app.png")));

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

			// Give the controller access to the main app.
			RootPaneController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();

			loadTaskDataFromFile(DataStorage.getinstance().getTaskFile());
			loadRunningTaskDataToFile(DataStorage.getinstance().getRunningTaskFile());
			primaryStage.setOnCloseRequest((WindowEvent event1) -> {
				try {
					saveTaskDataToFile(DataStorage.getinstance().getTaskFile());
					saveRunningTaskDataToFile(DataStorage.getinstance().getRunningTaskFile());
				} catch (UnsupportedOperatingSystemException | IOException e) {
					// TODO Auto-generated catch block
					ExceptionDialog.showException(e);
				}
			});
		} catch (IOException | UnsupportedOperatingSystemException e) {
			ExceptionDialog.showException(e);
		}


	}	



	public Stage getPrimaryStage() {
		return primaryStage;
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
			ExceptionDialog.showException(e);
		}
	}

	public boolean showNewTaskDialog(Task task) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(FXML_FILE_PATH+"NewTaskDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			NewTaskDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTask(task);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			ExceptionDialog.showException(e);
			return false;
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

	public Task getTaskById(int id) {
		for(int i=0;i<this.getTaskData().size();i++){
			if(this.getTaskData().get(i).getIdTask()==id) {
				return this.getTaskData().get(i);
			}
		}
		return null;
	}

	public Task getTaskById(int id,ObservableList<Task> list) {
		for(int i=0;i<list.size();i++){
			if(list.get(i).getIdTask()==id) {
				return list.get(i);
			}
		}
		return null;
	}

	public void showExportDialog() throws IOException {
		// Load the fxml file and create a new stage for the popup dialog.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource(MainApp.FXML_FILE_PATH+"ExportDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Edit Person");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		// Set the person into the controller.
		ExportDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setMainApp(this);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

	}

	/**
	 * Saves the current tasks data to the specified file.
	 * 
	 * @param file
	 */
	public void saveTaskDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(TaskDataWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			TaskDataWrapper wrapper = new TaskDataWrapper();
			wrapper.setTasks(taskData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	public void filterTaskData(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(TaskDataWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			TaskDataWrapper wrapper = new TaskDataWrapper();
			wrapper.setTasks(taskData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());

			alert.showAndWait();
		}
	}


	/**
	 * Loads tasks data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadTaskDataFromFile(File file) {
		try {
			TaskDataWrapper wrapper = loadTask(file);

			taskData.clear();
			if(wrapper != null && wrapper.getTasks()!= null) {
				taskData.addAll(wrapper.getTasks());
			}

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	public TaskDataWrapper loadTask(File file) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance(TaskDataWrapper.class);
		Unmarshaller um = context.createUnmarshaller();

		if(file.exists()) {
			// Reading XML from the file and unmarshalling.
			TaskDataWrapper wrapper = (TaskDataWrapper) um.unmarshal(file);
			return wrapper;
		}
		return null;
	}

	public void saveRunningTaskDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(RunningTaskDataWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			RunningTaskDataWrapper wrapper = new RunningTaskDataWrapper();
			wrapper.setRunningTasks(runningTaskData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	public void loadRunningTaskDataToFile(File file) {
		try {
			RunningTaskDataWrapper wrapper = loadRunningTask(file);

			runningTaskData.clear();
			if(wrapper != null && wrapper.getRunningTasks() != null) {
				runningTaskData.addAll(wrapper.getRunningTasks());
			}
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	public RunningTaskDataWrapper loadRunningTask(File file) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance(RunningTaskDataWrapper.class);
		Unmarshaller um = context.createUnmarshaller();

		if(file.exists()) {
			// Reading XML from the file and unmarshalling.
			RunningTaskDataWrapper wrapper = (RunningTaskDataWrapper) um.unmarshal(file);
			return wrapper;
		}
		return null;
	}

}
