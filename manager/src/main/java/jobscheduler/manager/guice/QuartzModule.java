package jobscheduler.manager.guice;

import jobscheduler.quartz.GuiceInvokerJobFactory;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * @author t_endo
 *
 */
public class QuartzModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        bind(JobFactory.class).to(GuiceInvokerJobFactory.class)
                .asEagerSingleton();
    }

    @Provides
    public Scheduler providesScheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        return scheduler;
    }
}
