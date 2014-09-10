package jobscheduler.agent;

import static spark.Spark.*;
import jobscheduler.agent.dto.JobResult;
import jobscheduler.agent.transformer.JsonResponseTransformer;
import spark.ResponseTransformer;

/**
 * 
 * @author t_endo
 */
public class Agent {

    public static void main(String... args) throws Exception {
        ResponseTransformer transformer = new JsonResponseTransformer();

        before((request, response) -> {
            System.out.printf("[%s] - %s%n", request.requestMethod(),
                    request.pathInfo());
        });

        get("/hello", (request, response) -> {
            return JobResult.builder().message("hello").build();
        }, transformer);

        get("/status", (request, response) -> {
            return "OK";
        });

        get("/jobs/:id", (request, response) -> {
            return "OK";
        });

        post("/jobs", (request, response) -> {
            String remoteHost = request.ip();
            return "OK: " + remoteHost;
        });

        delete("/jobs/:id", (request, response) -> {
            return "OK";
        });

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
