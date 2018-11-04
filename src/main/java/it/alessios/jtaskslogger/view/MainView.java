/**
 * 
 */
package it.alessios.jtaskslogger.view;

import java.io.IOException;
import java.text.ParseException;

import it.alessios.jtaskslogger.JTasksLogger;
import it.alessios.jtaskslogger.utility.ConstantsLibrary;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author alessio
 *
 */
public class MainView {
	private ChooserPanelDrawer chooserPane;
	private ListPane listView = null;
	private BorderPane rootLayout = null;
	private Scene scene = null;
	private JTasksLogger mainApp;
	
	public MainView(JTasksLogger mainApp) throws ParseException, UnsupportedOperatingSystemException {
		this.mainApp = mainApp;
		initRootLayout();
	}
	
	public void initRootLayout() throws ParseException, UnsupportedOperatingSystemException {
        try {
        	listView = new ListPane(mainApp);
        	chooserPane = new ChooserPanelDrawer(mainApp);
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JTasksLogger.class.getResource(ConstantsLibrary.FXML_PATH+"MainView.fxml"));
            rootLayout = (BorderPane) loader.load();
            rootLayout.setTop(chooserPane.getPane());
            rootLayout.setCenter(listView.getPane());
            // Show the scene containing the root layout.
            scene = new Scene(rootLayout); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void updateList() throws ParseException, UnsupportedOperatingSystemException {
		listView.updateList();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public Pane getPane() {
		return rootLayout;
	}
}
