package jobscheduler.agent;

import java.util.concurrent.ConcurrentHashMap;

import jobscheduler.agent.task.JobTask;

import com.google.inject.Singleton;

/**
 * 実行されているジョブタスクを開始、停止できるように保持しておくためのマップ
 * 
 * @author t_endo
 */
@Singleton
public class JobTaskMap {

    ConcurrentHashMap<Integer, JobTask> map;

    public void put(JobTask task) {

    }
}
