package jobscheduler.manager.resource.v1;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.entity.Node;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@Path("/node")
@Produces(MediaType.APPLICATION_JSON)
public class NodeResource {

    @Inject
    private NodeDao dao;

    @GET
    public List<Node> getNodes() {
        List<Node> nodes = dao.selectAll();
        return nodes;
    }
}
