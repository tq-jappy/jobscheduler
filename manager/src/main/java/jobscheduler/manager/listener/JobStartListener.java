package jobscheduler.manager.listener;

import javax.ws.rs.core.MediaType;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.entity.CommandJob;
import jobscheduler.manager.entity.Node;
import lombok.extern.slf4j.Slf4j;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author t_endo
 */
@Slf4j
public class JobStartListener {

    // private EventBus eventBus;

    @Inject
    private NodeDao nodeDao;

    @Inject
    public JobStartListener(EventBus eventBus) {
        // this.eventBus = eventBus;
        eventBus.register(this);
    }

    @Subscribe
    public void onMessage(CommandJob job) {
        System.out.println("exec: " + job.getCommand());

        Client client = Client.create();
        Node node = nodeDao.selectById(1);
        WebResource r = client.resource(String.format("http://%s:12345/aa",
                node.getHostName()));

        Object request = new Object();
        String response = r.type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).post(String.class, request);
        log.debug("response = {}", response);
    }
}
