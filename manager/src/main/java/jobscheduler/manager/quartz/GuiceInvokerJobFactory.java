package jobscheduler.manager.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Object-Injected job factory (like EJB3InvokerJob)
 * 
 * @author t_endo
 */
public class GuiceInvokerJobFactory implements JobFactory {

    private Injector injector;

    @Inject
    public GuiceInvokerJobFactory(Injector injector) {
        this.injector = injector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler)
            throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        return injector.getInstance(jobClass);
    }
}
