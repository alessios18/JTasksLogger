/**
 * 
 */
package it.alessios.jtaskslogger.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.alessios.jtaskslogger.util.IdAdapter;
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
	private final IntegerProperty idTask;
	private final ObjectProperty<Long> life;
	
	private long startTime = 0;
	
	private boolean isRunning = false;
	
	public RunningTask() {
		super();
		this.idTask = new SimpleIntegerProperty();
		this.idRunningTask = new SimpleIntegerProperty();
		this.life = new SimpleObjectProperty<Long>(0L);
	}
	
	public RunningTask(Task task) {
		super();
		this.idTask = new SimpleIntegerProperty(task.getIdTask());
		this.idRunningTask = new SimpleIntegerProperty(IdStacker.getInstance().getId());
		this.life = new SimpleObjectProperty<Long>(0L);
	}
	
	@XmlJavaTypeAdapter(IdAdapter.class)	
	public void setIdRunningTask(int id) {
		getIdRunningTaskProperty().set(id);
	}


	@XmlJavaTypeAdapter(IdAdapter.class)	
	public Integer getIdRunningTask() {
		return getIdRunningTaskProperty().get();
	}

	public void setIdTask(int idTask) {
		getIdTaskProperty().set(idTask);
	}

	public int getIdTask() {
		return getIdTaskProperty().get();
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
	public IntegerProperty getIdTaskProperty() {
		return idTask;
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
