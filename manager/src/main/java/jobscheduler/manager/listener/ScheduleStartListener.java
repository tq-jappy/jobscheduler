package jobscheduler.manager.listener;

import jobscheduler.manager.entity.Schedule;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

/**
 * @author t_endo
 *
 */
public class ScheduleStartListener {

    EventBus eventBus;

    @Inject
    public ScheduleStartListener(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    @Subscribe
    public void onMessage(Schedule schedule) {
        System.out.println("schedule launched: " + schedule.getName());
        schedule.getChildrenJob().forEach(eventBus::post);
    }
}
