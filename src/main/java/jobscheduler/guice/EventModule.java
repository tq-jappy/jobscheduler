package jobscheduler.guice;

import jobscheduler.jobrunner.JobStartRunner;
import jobscheduler.listener.JobStartListener;
import jobscheduler.listener.ScheduleStartListener;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author t_endo
 *
 */
public class EventModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        bind(EventBus.class).in(Scopes.SINGLETON);
        bind(JobStartRunner.class).asEagerSingleton();
        bind(ScheduleStartListener.class).asEagerSingleton();
        bind(JobStartListener.class).asEagerSingleton();
    }
}
