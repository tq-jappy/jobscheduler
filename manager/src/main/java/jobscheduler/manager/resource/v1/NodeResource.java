package jobscheduler.manager.resource.v1;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.entity.Node;
import jobscheduler.manager.guice.persist.DomaTransactionAttribute;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@Path("/node")
@Produces(MediaType.APPLICATION_JSON)
@DomaTransactionAttribute
public class NodeResource {

    @Inject
    private NodeDao dao;

    @GET
    public List<Node> getNodes() {
        return dao.selectAll();
    }

    @GET
    @Path("{id}")
    public Node getNode(@PathParam("id") int id) {
        return Node.builder().id(id).hostName("host: " + id).build();
    }
}
