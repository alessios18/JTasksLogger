/**
 * 
 */
package it.alessios.jtaskslogger.datastorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.alessios.jtaskslogger.taskmanager.Task;
import it.alessios.jtaskslogger.utility.OsCheck;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;

/**
 * @author alessio
 *
 */
public class DataStorage {
	private static final String TASKS = "tasks"; 

	private static File tasks;

	private static DataStorage dataStorage = null;
	private static String rootPath = null;

	private DataStorage() throws UnsupportedOperatingSystemException, IOException{
		checkFiles();
	}

	public static DataStorage getinstance() throws UnsupportedOperatingSystemException, IOException{
		if(dataStorage == null) {
			dataStorage = new DataStorage();
		}
		return dataStorage;
	}

	public void checkFiles() throws UnsupportedOperatingSystemException, IOException {
		rootPath = getRootPath();
		if(rootPath != null) {
			File root = new File(rootPath);
			if(!root.exists()) {
				root.mkdirs();
			}
			tasks = new File(rootPath+OsCheck.getSeparator()+TASKS);
			if(!tasks.exists()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(tasks));
				writer.write("#id;name;lastUsage\n");
				writer.close();
			}
		}else {
			throw new UnsupportedOperatingSystemException();
		}
	}

	protected String getRootPath() {
		String path = null;
		if(OsCheck.OSType.Linux.equals(OsCheck.getOperatingSystemType())){
			path = OsCheck.getUserHome()+OsCheck.getSeparator()+"JTasksTool"+OsCheck.getSeparator();
		}else if(OsCheck.OSType.Windows.equals(OsCheck.getOperatingSystemType())) {
			path = OsCheck.getUserHome()+OsCheck.getSeparator()+"JTasksTool"+OsCheck.getSeparator();
		}else if(OsCheck.OSType.MacOS.equals(OsCheck.getOperatingSystemType()) 
				|| OsCheck.OSType.Other.equals(OsCheck.getOperatingSystemType()) ) {
			path = OsCheck.getUserHome()+OsCheck.getSeparator()+"JTasksTool"+OsCheck.getSeparator();
		}
		return path;
	}

	public Task[] getTaskList() throws IOException, ParseException {
		ArrayList<Task> list = readTasksFile();
		return list.toArray(new Task[list.size()]);
	}

	public ArrayList<Task> readTasksFile() throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(tasks)); 
		String st; 
		ArrayList<Task> list = new ArrayList<Task>();
		while ((st = br.readLine()) != null) {
			if(!st.startsWith("#")) {
				String args[] = st.split(";");
				int id = Integer.parseInt(args[0]);
				String name = args[1];
				Date last = parseDate(args[2]);
				list.add(new Task(id, name));
			}
		}
		br.close();
		return list;
	} 
	
	public int getNewTaskid() throws IOException, ParseException {
		int id = 0;
		ArrayList<Task> list = readTasksFile();
		for(int i = 0;i< list.size();i++) {
			Task tmp = list.get(i);
			if(id<tmp.id) {
				id=tmp.id;
			}
		}
		return id+1;
	}
	
	public void saveTask(Task t) throws IOException {
		
		Files.write(Paths.get(tasks.getPath()), t.getStorageString().getBytes(), StandardOpenOption.APPEND);
	}
	
	public static Date parseDate(String date) throws ParseException
	{
	    return parseDate(date, "dd-MM-yyyy");
	}
	
	public static Date parseDate(String date, String format) throws ParseException
	{
		
	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    return formatter.parse(date);
	}
	
	public static String dateToString(Date d) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(d);
	}
}
