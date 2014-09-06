package jobscheduler.manager.listener;

import jobscheduler.manager.entity.Job;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

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
    public void onMessage(Job job) {
        System.out.println("exec: " + job.getJobParameters());
    }
}
