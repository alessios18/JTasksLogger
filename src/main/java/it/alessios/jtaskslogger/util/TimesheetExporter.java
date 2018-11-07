/**
 * 
 */
package it.alessios.jtaskslogger.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import it.alessios.jtaskslogger.MainApp;
import it.alessios.jtaskslogger.model.RowTask;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

/**
 * @author alessio
 *
 */
public class TimesheetExporter {
	private MainApp mainApp;
	private ObservableList<RowTask> rowTask;
	
	private static final String EXT_CSV = "CSV";

	private static final String CSV_SPARATOR = ";";
	
	private static  HashMap<String, Object[]>extensinsMap;
	static {
		ArrayList<String> csvExtensions = new ArrayList<String>();
		csvExtensions.add("*.csv");				
		
		extensinsMap = new HashMap<String, Object[]>();
		extensinsMap.put(EXT_CSV, new Object[] {"CSV files (*.csv)",csvExtensions});
	}
	
	public TimesheetExporter(MainApp mainApp,ObservableList<RowTask> rowTask) {
		this.mainApp=mainApp;
		this.rowTask = rowTask;
	}
	
	public boolean exportCsvFile() throws FileNotFoundException, UnsupportedEncodingException {
		File file = getOutputFile(EXT_CSV);
		if(file != null) {
			writeFileOutPut(file);
		}else {
			return false;
		}
		return true;
	}
	
	public void writeFileOutPut(File file) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		//Header
		writer.println("Date"+CSV_SPARATOR+"Task Name"+CSV_SPARATOR+"Ours"+CSV_SPARATOR);
		for (RowTask rowTask2 : rowTask) {
			writer.println(DateUtil.format(rowTask2.getDate())+CSV_SPARATOR+rowTask2.getNameTask()+CSV_SPARATOR+rowTask2.getOurs()+CSV_SPARATOR);
		}
		writer.close();
	}

	@SuppressWarnings("unchecked")
	public File getOutputFile(String ext) {
		FileChooser fileChooser = new FileChooser();
		Object[] filterPar = extensinsMap.get(ext);
        // Set extension filter
        ArrayList<String> arrayList = (ArrayList<String>)filterPar[1];
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter((String)filterPar[0],arrayList);
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            ArrayList<String> list = arrayList;
			if (!endsWithExtension(file, list)) {
                file = new File(file.getPath() + list.get(0).replace("*", ""));
            }
            
        }
        return file;
	}
	
	private boolean endsWithExtension(File file,ArrayList<String> list) {
		for (String string : list) {
			if (file.getPath().endsWith(string.replace("*", ""))) {
				return true;
			}
		}
		return false;
	}
}
