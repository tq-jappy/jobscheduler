package jobscheduler.agent.route;

import static jobscheduler.agent.transformer.RequestBodyReader.*;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

import jobscheduler.agent.JobTaskMap;
import jobscheduler.agent.dto.JobRequest;
import jobscheduler.agent.guice.TaskFactory;
import jobscheduler.agent.task.FileWatcherTask;
import spark.Route;

import com.google.inject.Inject;

/**
 * @author t_endo
 *
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
            return null;
        };
    }

    public Route find() {
        return (request, response) -> {
            return null;
        };
    }

    public Route create() {
        return (request, response) -> {
            JobRequest jobRequest = readFrom(request, JobRequest.class);

            FileWatcherTask task = taskFactory.create(jobRequest.getMessage());
            map.put(task);

            executorService.submit(task);

            return Optional.empty();
        };
    }

    public Route delete() {
        return (request, response) -> {
            executorService.shutdown();
            return null;
        };
    }
}
