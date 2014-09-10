package jobscheduler.agent.guice;

import jobscheduler.agent.dto.JobParameter;
import jobscheduler.agent.task.FileWatcherTask;

/**
 * 
 * @author t_endo
 */
public interface TaskFactory {

    /**
     * 
     * @param param
     * @return
     */
    public FileWatcherTask createFileWatcherTask(JobParameter param);
}
