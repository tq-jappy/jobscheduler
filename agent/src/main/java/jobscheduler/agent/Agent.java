package jobscheduler.agent;

import static spark.Spark.*;
import jobscheduler.agent.dto.JobResult;
import jobscheduler.agent.guice.AgentModule;
import jobscheduler.agent.route.JobRouting;
import jobscheduler.agent.transformer.JsonResponseTransformer;
import spark.ResponseTransformer;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * @author t_endo
 */
public class Agent {

    public static void main(String... args) throws Exception {
        ResponseTransformer transformer = new JsonResponseTransformer();

        Injector injector = Guice.createInjector(new AgentModule());
        ;
        JobRouting jobs = injector.getInstance(JobRouting.class);

        before((request, response) -> {
            System.out.printf("[%s] - %s%n", request.requestMethod(),
                    request.pathInfo());
        });

        exception(JsonMappingException.class,
                (exception, request, response) -> {
                    halt(401);
                });

        get("/hello", (request, response) -> {
            return JobResult.builder().message("hello").build();
        }, transformer);

        get("/status", (request, response) -> {
            return "OK";
        });

        get("/jobs", jobs.findAll());

        get("/jobs/:id", jobs.find());

        post("/jobs", jobs.create());

        delete("/jobs/:id", jobs.delete());

        get("/properties", (request, response) -> {
            return "OK";
        });

        get("/log", (request, response) -> {
            return "OK";
        });

        // 不要??
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stop();
        }));
    }
}
