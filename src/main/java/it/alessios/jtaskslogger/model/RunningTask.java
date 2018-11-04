/**
 * 
 */
package it.alessios.jtaskslogger.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import it.alessios.jtaskslogger.util.IdStacker;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author alessio
 *
 */
public class RunningTask {
	private final IntegerProperty idRunningTask;
	private final ObjectProperty<Task> task;
	private final ObjectProperty<Long> life;
	
	private long startTime = 0;
	
	private boolean isRunning = false;
	
	public RunningTask(Task task) {
		super();
		this.task = new SimpleObjectProperty<Task>(task);
		this.idRunningTask = new SimpleIntegerProperty(IdStacker.getInstance().getId());
		this.life = new SimpleObjectProperty<Long>(0L);
	}
	
	public void setIdRunningTask(int id) {
		getIdRunningTaskProperty().set(id);
	}

	public int getIdRunningTask() {
		return getIdRunningTaskProperty().get();
	}

	public void setTask(Task task) {
		getTaskProperty().set(task);
	}

	public Task getTask() {
		return getTaskProperty().get();
	}
	
	public void setLife(Long life) {
		getLifeProperty().set(life);
	}

	public Long getLife() {
		return getLifeProperty().get();
	}

	public IntegerProperty getIdRunningTaskProperty() {
		return idRunningTask;
	}
	public ObjectProperty<Task> getTaskProperty() {
		return task;
	}
	public ObjectProperty<Long> getLifeProperty() {
		return life;
	}
	
	public void startTask() {
		startTime = System.currentTimeMillis();
		isRunning = true;
	}
	
	public void pauseTask() {
		if(startTime != 0) {
			isRunning = false;
			long endTime = System.currentTimeMillis();
			setLife(getLife()+(endTime-startTime));
			startTime = 0;
		}
	}
	public void stopTask() {
		if(startTime != 0) {
			isRunning = false;
			long endTime = System.currentTimeMillis();
			setLife(getLife()+(endTime-startTime));
			startTime = 0;
		}
	}
	
	public long getInterval() {
		long endTime = System.currentTimeMillis();
		return getLife()+(endTime-startTime);
	}
	
	public String getFormattedLife(long life) {
		Date date = new Date(life);
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateFormatted = formatter.format(date);
		return dateFormatted;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}	
}
