package jobscheduler.jobrunner;

import jobscheduler.entity.Schedule;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

/**
 * @author t_endo
 *
 */
public class JobStartRunner {

    private EventBus eventBus;

    @Inject
    public JobStartRunner(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void start(Schedule schedule) {
        eventBus.post(schedule);
    }
}
