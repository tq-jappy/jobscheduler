package jobscheduler.manager.resource.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jobscheduler.manager.guice.persist.DomaTransactionAttribute;

/**
 * 
 * @author t_endo
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
@DomaTransactionAttribute
public class RootResource {

    @GET
    @Path("/hello")
    public String hello() {
        return "hello";
    }
}
