package jobscheduler.agent.resource.v1;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author t_endo
 */
@Path("/")
public class AgentResource {

    @GET
    @Path("/status")
    public Response getStatus() {
        return Response.ok().build();
    }

    @GET
    @Path("/jobs")
    public Response getJobs(@PathParam("id") String id) {
        return Response.ok().build();
    }

    @POST
    @Path("/jobs/{id}")
    public Response startJob(@Context HttpServletRequest req,
            @PathParam("id") String id) {
        String remoteHost = req.getRemoteHost();
        String remoteAddr = req.getRemoteAddr();
        int remotePort = req.getRemotePort();
        String msg = String.format("%s - %s (%s:%d)", id, remoteHost,
                remoteAddr, remotePort);

        return Response.ok(msg).build();
    }

    @GET
    @Path("/jobs/{id}")
    public Response getJob(@PathParam("id") String id) {
        return Response.ok().build();
    }

    @DELETE
    @Path("/jobs/{id}")
    public Response stopJob(@PathParam("id") String id) {
        return Response.ok().build();
    }

    @GET
    @Path("/properties")
    public Response getProperties() {
        return Response.ok().build();
    }

    @GET
    @Path("/log")
    public Response getLog() {
        return Response.ok().build();
    }
}
