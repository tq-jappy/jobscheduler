package jobscheduler.agent;

import jobscheduler.agent.resource.v1.AgentResource;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.net.httpserver.HttpServer;

/**
 * 
 * @author t_endo
 */
public class Agent {

    public static void main(String... args) throws Exception {
        ClassNamesResourceConfig config = new ClassNamesResourceConfig(
                AgentResource.class);
        config.getProperties().put(
                "com.sun.jersey.spi.container.ContainerRequestFilters",
                "com.sun.jersey.api.container.filter.LoggingFilter");

        HttpServer server = HttpServerFactory.create("http://localhost:4444/",
                config);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop(0);
        }));

        server.start();
    }
}
