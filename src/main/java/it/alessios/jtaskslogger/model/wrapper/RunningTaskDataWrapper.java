/**
 * 
 */
package it.alessios.jtaskslogger.model.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.alessios.jtaskslogger.model.RunningTask;

/**
 * @author alessio
 *
 */
@XmlRootElement(name = "runningTasks")
public class RunningTaskDataWrapper {
	private List<RunningTask> runningTasks;

    @XmlElement(name = "runningTasks")
    public List<RunningTask> getRunningTasks() {
        return runningTasks;
    }

    public void setRunningTasks(List<RunningTask> runningTasks) {
        this.runningTasks = runningTasks;
    }
}
