package jobscheduler.quartz;

import jobscheduler.entity.JobParameters;

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
        jobscheduler.entity.Job job = jobscheduler.entity.Job.builder()
                .jobParameters(params).build();

        eventBus.post(job);
    }
}
