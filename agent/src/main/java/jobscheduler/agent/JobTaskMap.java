package jobscheduler.agent;

import java.util.concurrent.ConcurrentHashMap;

import jobscheduler.agent.task.JobTask;

import com.google.inject.Singleton;

/**
 * @author t_endo
 */
@Singleton
public class JobTaskMap {

    ConcurrentHashMap<Integer, JobTask> map;

    public void put(JobTask task) {

    }
}
