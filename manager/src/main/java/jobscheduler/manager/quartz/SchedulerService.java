package jobscheduler.manager.quartz;

import io.dropwizard.lifecycle.Managed;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;

import com.google.inject.Inject;

/**
 * Quartz scheduler service.
 * 
 * @author t_endo
 */
public class SchedulerService implements Managed {

    private Scheduler scheduler;

    private JobFactory jobFactory;

    @Inject
    public SchedulerService(Scheduler scheduler, JobFactory jobFactory) {
        this.scheduler = scheduler;
        this.jobFactory = jobFactory;
    }

    @Override
    public void start() throws SchedulerException {
        scheduler.setJobFactory(jobFactory);
        scheduler.start();
    }

    @Override
    public void stop() throws SchedulerException {
        scheduler.shutdown();
    }
}
