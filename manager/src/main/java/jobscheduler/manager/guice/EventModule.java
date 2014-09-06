package jobscheduler.manager.guice;

import jobscheduler.manager.jobrunner.JobStartRunner;
import jobscheduler.manager.listener.JobStartListener;
import jobscheduler.manager.listener.ScheduleStartListener;

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
