/**
 * 
 */
package it.alessios.jtaskslogger.datastorage;

import java.io.File;

import it.alessios.jtaskslogger.utility.OsCheck;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;

/**
 * @author alessio
 *
 */
public class DataStorage {
	private static DataStorage dataStorage = null;
	private static String rootPath = null;

	private DataStorage() throws UnsupportedOperatingSystemException{
		checkFiles();
	}

	public static DataStorage getinstance(){
		if(dataStorage == null) {
			try {
				dataStorage = new DataStorage();
			} catch (UnsupportedOperatingSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataStorage;
	}

	public void checkFiles() throws UnsupportedOperatingSystemException {
		rootPath = getRootPath();
		if(rootPath != null) {
			File root = new File(rootPath);
			if(!root.exists()) {
				root.mkdirs();
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
}
