/**
 * 
 */
package it.alessios.jtaskslogger.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import it.alessios.jtaskslogger.exceptions.UnsupportedOperatingSystemException;

/**
 * @author alessio
 *
 */
public class DataStorage {
	private static final String TASKS = "tasks.xml";
	private static final String RUNNINGTASK = "runningtasks.xml";
	
	private static final String JTASKSLOGGER_FOLDER = ".JTasksTool"; 

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
//			tasks = new File(getRootPath()+TASKS);
//			if(!tasks.exists()) {
//				BufferedWriter writer = new BufferedWriter(new FileWriter(tasks));
//				writer.close();
//			}
		}else {
			throw new UnsupportedOperatingSystemException();
		}
//		File current = getCurrentRunningTaskFile();
//		if(!current.exists()) {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(current));
//			writer.close();
//		}
	}

	protected String getRootPath() {
		String path = null;
		if(OsCheck.OSType.Linux.equals(OsCheck.getOperatingSystemType())){
			path = OsCheck.getUserHome()+OsCheck.getSeparator()+JTASKSLOGGER_FOLDER+OsCheck.getSeparator();
		}else if(OsCheck.OSType.Windows.equals(OsCheck.getOperatingSystemType())) {
			path = OsCheck.getUserHome()+OsCheck.getSeparator()+JTASKSLOGGER_FOLDER+OsCheck.getSeparator();
		}else if(OsCheck.OSType.MacOS.equals(OsCheck.getOperatingSystemType()) 
				|| OsCheck.OSType.Other.equals(OsCheck.getOperatingSystemType()) ) {
			path = OsCheck.getUserHome()+OsCheck.getSeparator()+JTASKSLOGGER_FOLDER+OsCheck.getSeparator();
		}
		return path;
	}
	
	public File getTaskFile() {
		return new File(getRootPath()+TASKS);
	}
	
	public File getCurrentRunningTaskFile() {
		LocalDate now = LocalDate.now();
		return new File(getRootPath()+DateUtil.fileFormat(now)+"_"+RUNNINGTASK);
	}
}

