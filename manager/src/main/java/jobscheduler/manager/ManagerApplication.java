package jobscheduler.manager;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jobscheduler.manager.doma.DomaBundle;
import jobscheduler.manager.doma.DomaConfig;
import jobscheduler.manager.guice.CommonModule;
import jobscheduler.manager.quartz.SchedulerService;
import jobscheduler.manager.resource.v1.NodeResource;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Application main entry class.
 * 
 * @author t_endo
 */
public class ManagerApplication extends Application<ManagerConfiguration> {

    private DomaBundle<ManagerConfiguration> domaBundle;

    /**
     * main
     * 
     * @param args
     *            "-h": show usage<br />
     *            "-v": show version<br />
     *            "server" to start server or "check" to check only
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        ManagerApplication app = new ManagerApplication();
        app.run(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Bootstrap<ManagerConfiguration> bootstrap) {
        // Doma
        this.domaBundle = new DomaBundle<ManagerConfiguration>("doma") {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    ManagerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };
        bootstrap.addBundle(domaBundle);

        bootstrap.addBundle(new AssetsBundle("/assets", "/app", "index.html",
                "assets"));

        // Migrations
        bootstrap.addBundle(new MigrationsBundle<ManagerConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    ManagerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(ManagerConfiguration configuration, Environment environment)
            throws Exception {
        DomaConfig domaConfig = domaBundle.getDomaConfig();

        Injector injector = Guice.createInjector(new CommonModule(domaConfig));

        environment.jersey().register(injector.getInstance(NodeResource.class));

        environment.lifecycle().manage(
                injector.getInstance(SchedulerService.class));
    }
}
