/**
 * 
 */
package it.alessios.jtaskslogger.taskmanager;

import java.io.IOException;
import java.util.Date;

import it.alessios.jtaskslogger.datastorage.DataStorage;
import it.alessios.jtaskslogger.utility.exceptions.UnsupportedOperatingSystemException;

/**
 * @author alessio
 *
 */
public class Task {
	public int id = 0;
	private String name = null;
	private long life = 0; 
	private Date date = null;
	private Date lastDate = null;

	private long startTime = 0;

	//create task
	public Task(String name) throws Exception{
		this.id = DataStorage.getinstance().getNewTaskid();
		this.name = name;
		this.date = new Date();
	}
	
	public Task(int id,String name) {
		this.id = id;
		this.name = name;
		this.date = new Date();
	}

	//load task
	public Task(int id,String name,long life) {
		this.id = id;
		this.name = name;
		this.life = life;
		this.date = new Date();
	}
	public Task(int id,String name,long life,Date date) {
		this.id = id;
		this.name = name;
		this.life = life;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLife() {
		return life;
	}

	public void setLife(long life) {
		this.life = life;
	}

	public void startTask() {
		startTime = System.currentTimeMillis();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void pauseTask() {
		if(startTime == 0) {
			long endTime = System.currentTimeMillis();
			life+=(endTime-startTime);
			startTime = 0;
		}
	}
	
	public void stopTask() {
		if(startTime == 0) {
			long endTime = System.currentTimeMillis();
			life+=(endTime-startTime);
			startTime = 0;
		}
	}

	@Override
	public String toString() {
		return getName();
	}	
	
	public void save() throws IOException, UnsupportedOperatingSystemException {
		DataStorage.getinstance().saveTask(this);
	}

	public String getStorageString() {
		if(lastDate == null) {
			lastDate = new Date();
		}
		return id+";"+name+";"+DataStorage.dateToString(lastDate)+"\n";
	}
}
