package jobscheduler.agent.resource.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author t_endo
 */
@Path("/")
public class AgentResource {

    @Context
    private UriInfo context;

    // @Context
    // private HttpServletRequest httpServletRequest;

    @GET
    @Path("/status")
    public Response getStatus() {
        return Response.ok().entity("OK").build();
    }

    @GET
    @Path("/jobs")
    public Response getJobs(@PathParam("id") String id) {
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/jobs")
    public Response startJob(String obj) {
        // String remoteHost = httpServletRequest.getRemoteHost();
        // String remoteAddr = httpServletRequest.getRemoteAddr();
        // int remotePort = httpServletRequest.getRemotePort();
        // String msg = String.format("%s (%s:%d)", remoteHost, remoteAddr,
        // remotePort);
        String msg = "test";

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
