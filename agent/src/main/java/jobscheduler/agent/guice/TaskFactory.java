package jobscheduler.agent.guice;

import jobscheduler.agent.task.FileWatcherTask;

/**
 * 
 * @author t_endo
 */
public interface TaskFactory {

    /**
     * 
     * @param path
     * @return
     */
    public FileWatcherTask create(String path);
}
