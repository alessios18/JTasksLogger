/**
 * 
 */
package it.alessios.jtaskslogger.model.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.alessios.jtaskslogger.model.Task;

/**
 * @author alessio
 *
 */
@XmlRootElement(name = "tasks")
public class TaskDataWrapper {
	private List<Task> tasks;

    @XmlElement(name = "task")
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
