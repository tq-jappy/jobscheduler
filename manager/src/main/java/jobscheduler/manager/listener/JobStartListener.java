package jobscheduler.manager.listener;

import javax.ws.rs.core.MediaType;

import jobscheduler.manager.entity.CommandJob;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author t_endo
 *
 */
public class JobStartListener {

    // private EventBus eventBus;

    @Inject
    public JobStartListener(EventBus eventBus) {
        // this.eventBus = eventBus;
        eventBus.register(this);
    }

    @Subscribe
    public void onMessage(CommandJob job) {
        System.out.println("exec: " + job.getCommand());

        Client client = Client.create();
        WebResource r = client.resource("http://localhost:12345/aa");

        Object request = new Object();
        String respose = r.type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).post(String.class, request);
    }
}
