package jobscheduler.manager.resource.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jobscheduler.manager.entity.Node;
import jobscheduler.manager.service.NodeService;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@Path("/api/v1/nodes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NodeResource {

    @Inject
    private NodeService nodeService;

    @GET
    public List<Node> getNodes() {
        return nodeService.findAll();
    }

    @GET
    @Path("{id}")
    public Node getNode(@PathParam("id") int id) {
        return nodeService.findById(id);
    }

    @POST
    public Node postNode(Node node) {
        return nodeService.create(node);
    }

    @PUT
    @Path("{id}")
    public Node putNode(@PathParam("id") int id, Node node) {
        return nodeService.update(node);
    }

    @DELETE
    @Path("{id}")
    public void deleteNode(@PathParam("id") int id) {
        nodeService.delete(id);
    }
}
