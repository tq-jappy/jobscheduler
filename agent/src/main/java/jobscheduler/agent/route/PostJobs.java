package jobscheduler.agent.route;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

import jobscheduler.agent.JobTaskMap;
import jobscheduler.agent.dto.JobRequest;
import jobscheduler.agent.guice.TaskFactory;
import jobscheduler.agent.task.FileWatcherTask;
import jobscheduler.agent.transformer.RequestBodyReader;
import lombok.SneakyThrows;
import spark.Request;
import spark.Response;
import spark.Route;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
public class PostJobs<T> implements Route {

    @Inject
    private JobTaskMap map;

    @Inject
    private TaskFactory taskFactory;

    @Inject
    private ExecutorService executorService;

    /**
     * {@inheritDoc}
     */
    @Override
    @SneakyThrows
    public Optional<T> handle(Request request, Response response) {
        JobRequest jobRequest = new RequestBodyReader().readFrom(request,
                JobRequest.class);

        FileWatcherTask task = taskFactory.create(jobRequest.getMessage());
        map.put(task);

        executorService.submit(task);

        return Optional.empty();
    }
}
