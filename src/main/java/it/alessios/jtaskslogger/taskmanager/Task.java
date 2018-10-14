/**
 * 
 */
package it.alessios.jtaskslogger.taskmanager;

import java.util.Date;

/**
 * @author alessio
 *
 */
public class Task {
	private String name = null;
	private long life = 0; 
	private Date date = null;

	private long startTime = 0;

	//create task
	public Task(String name) {
		this.name = name;
		this.date = new Date();
	}

	//load task
	
	public Task(String name,long life) {
		this.life = life;
		this.date = new Date();
	}
	public Task(String name,long life,Date date) {
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
}
