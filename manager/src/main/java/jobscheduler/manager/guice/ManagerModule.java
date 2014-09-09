package jobscheduler.manager.guice;

import com.google.inject.AbstractModule;

/**
 * 
 * @author t_endo
 */
public class ManagerModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        install(new CommonModule());
        install(new EventModule());
        install(new QuartzModule());
    }
}
