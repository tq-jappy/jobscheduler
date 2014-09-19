package jobscheduler.manager.service;

import java.util.List;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.entity.Node;
import jobscheduler.manager.guice.persist.DomaTransactionAttribute;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@DomaTransactionAttribute
public class NodeService {

    @Inject
    private NodeDao nodeDao;

    public List<Node> findAll() {
        return nodeDao.selectAll();
    }

    public Node findById(int id) {
        return nodeDao.selectById(id);
    }

    public Node create(Node node) {
        nodeDao.insert(node);
        return node;
    }

    public Node update(Node node) {
        nodeDao.update(node);
        return node;
    }

    public void delete(int id) {
        nodeDao.delete(Node.builder().id(id).build());
    }
}
