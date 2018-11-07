/**
 * 
 */
package it.alessios.jtaskslogger.model;

import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.alessios.jtaskslogger.util.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author alessio
 *
 */
public class RowTask {
	private final ObjectProperty<Float> ours;
	private final StringProperty nameTask;
	private final ObjectProperty<LocalDate> date;
	
	public RowTask() {
		super();
		this.ours = new SimpleObjectProperty<Float>();
		this.nameTask = new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<LocalDate>();
	}
	
	public RowTask(Task task,RunningTask runningTask) {
		super();
		this.ours = new SimpleObjectProperty<Float>(millisecondToDecimalOurs(runningTask.getLife()));
		this.nameTask = new SimpleStringProperty(task.getNameTask());
		this.date = new SimpleObjectProperty<LocalDate>(runningTask.getCreationDate());
	}
	
	public void setOurs(float id) {
		getOursProperty().set(id);
	}

	public float getOurs() {
		return getOursProperty().get();
	}

	public void setNameTask(String name) {
		getNameTaskProperty().set(name);
	}

	public String getNameTask() {
		return getNameTaskProperty().get();
	}
	
	public void setDate(LocalDate date) {
		getDateProperty().set(date);
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)	
	public LocalDate getDate() {
		return getDateProperty().get();
	}

	public ObjectProperty<Float> getOursProperty() {
		return ours;
	}
	public StringProperty getNameTaskProperty() {
		return nameTask;
	}
	public ObjectProperty<LocalDate> getDateProperty() {
		return date;
	}
	
	public float millisecondToDecimalOurs(long milli) {
		double ours = (((double)milli/1000)/60/60);
		return (float) (Math.round(ours * 100.0) / 100.0);
	}
}
