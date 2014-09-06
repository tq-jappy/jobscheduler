package jobscheduler.manager.quartz;

import jobscheduler.manager.entity.JobParameters;

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
        JobParameters params = JobParameters.builder()
                .command(new String[] { "aaaaa" }).build();
        jobscheduler.manager.entity.Job job = jobscheduler.manager.entity.Job.builder()
                .jobParameters(params).build();

        eventBus.post(job);
    }
}
