package jobscheduler.manager;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jobscheduler.manager.guice.EventModule;
import jobscheduler.manager.guice.ManagerModule;
import jobscheduler.manager.guice.QuartzModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;

/**
 * Application main entry class.
 * 
 * @author t_endo
 */
public class ManagerApplication extends Application<ManagerConfiguration> {

    private Injector injector;

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
        injector = Guice.createInjector(new ManagerModule(), new EventModule());

        bootstrap.addBundle(new AssetsBundle("/assets", "/app", "index.html",
                "assets"));

        GuiceBundle<ManagerConfiguration> guiceBundle = GuiceBundle
                .<ManagerConfiguration> newBuilder()
                .addModule(new ManagerModule()).addModule(new EventModule())
                .addModule(new QuartzModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(ManagerConfiguration.class).build();
        bootstrap.addBundle(guiceBundle);

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
        // environment.jersey().register(new RootResource());
        // environment.jersey().register(injector.getInstance(NodeResource.class));
        // environment.jersey().register(NodeResource.class);
    }
}
