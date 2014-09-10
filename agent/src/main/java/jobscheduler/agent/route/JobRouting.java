package jobscheduler.agent.route;

import static jobscheduler.agent.transformer.RequestBodyReader.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import jobscheduler.agent.JobTaskMap;
import jobscheduler.agent.dto.JobRequest;
import jobscheduler.agent.guice.TaskFactory;
import jobscheduler.agent.task.FileWatcherTask;
import spark.Route;

import com.google.inject.Inject;

/**
 * ジョブ管理系APIの {@link Route} 定義
 * 
 * @author t_endo
 */
public class JobRouting {

    @Inject
    private JobTaskMap map;

    @Inject
    private TaskFactory taskFactory;

    @Inject
    private ExecutorService executorService;

    public Route findAll() {
        return (request, response) -> {
            executorService.submit(() -> {
                System.out.println("dummy!");
            });
            return "OK";
        };
    }

    public Route find() {
        return (request, response) -> {
            return null;
        };
    }

    public Route create() {
        return (request, response) -> {
            JobRequest req = readFrom(request, JobRequest.class);

            FileWatcherTask task = taskFactory.createFileWatcherTask(req
                    .getJobParameter());
            map.put(task);

            CompletableFuture.runAsync(task, executorService);

            return Optional.empty();
        };
    }

    public Route delete() {
        return (request, response) -> {
            return null;
        };
    }
}
