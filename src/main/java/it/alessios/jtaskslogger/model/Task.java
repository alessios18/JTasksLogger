/**
 * 
 */
package it.alessios.jtaskslogger.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.alessios.jtaskslogger.util.IdAdapter;
import it.alessios.jtaskslogger.util.IdStacker;
import it.alessios.jtaskslogger.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author alessio
 *
 */
public class Task {
	private final IntegerProperty idTask;
	private final StringProperty nameTask;
	private final ObjectProperty<LocalDate> creationDate;
	
	public Task() {
		super();
		this.idTask = new SimpleIntegerProperty(IdStacker.getInstance().getId());
		this.nameTask = new SimpleStringProperty("");
		this.creationDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}

	public Task(String nameTask) {
		super();
		this.idTask = new SimpleIntegerProperty(IdStacker.getInstance().getId());
		this.nameTask = new SimpleStringProperty(nameTask);
		this.creationDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}
	
	public Task(int id,String nameTask,LocalDate creationDate) {
		super();
		this.idTask = new SimpleIntegerProperty(id);
		this.nameTask = new SimpleStringProperty(nameTask);
		this.creationDate = new SimpleObjectProperty<LocalDate>(creationDate);
	}
	
	@XmlJavaTypeAdapter(IdAdapter.class)	
	public void setIdTask(Integer id) {
		getIdTaskProperty().set(id);
	}

	public Integer getIdTask() {
		return getIdTaskProperty().get();
	}

	public void setNameTask(String name) {
		getNameTaskProperty().set(name);
	}

	public String getNameTask() {
		return getNameTaskProperty().get();
	}
	
	public void setCreationDate(LocalDate creationDate) {
		getCreationDateProperty().set(creationDate);
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)	
	public LocalDate getCreationDate() {
		return getCreationDateProperty().get();
	}

	public IntegerProperty getIdTaskProperty() {
		return idTask;
	}
	public StringProperty getNameTaskProperty() {
		return nameTask;
	}
	public ObjectProperty<LocalDate> getCreationDateProperty() {
		return creationDate;
	}
}

