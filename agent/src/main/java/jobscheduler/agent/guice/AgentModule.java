package jobscheduler.agent.guice;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;

import jobscheduler.agent.task.FileWatcherTask;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author t_endo
 *
 */
public class AgentModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(FileWatcherTask.class,
                FileWatcherTask.class).build(TaskFactory.class));
    }

    @Provides
    public WatchService provideWatchService() throws IOException {
        return FileSystems.getDefault().newWatchService();
    }
}
