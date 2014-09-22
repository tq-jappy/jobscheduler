package jobscheduler.manager.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

/**
 * @author t_endo
 *
 */
public class ScheduleStartJob implements Job {

    @Inject
    private EventBus eventBus;

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        jobscheduler.manager.entity.Job job = jobscheduler.manager.entity.Job
                .builder().build();

        eventBus.post(job);
    }
}
