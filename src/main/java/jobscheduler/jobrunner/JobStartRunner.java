package jobscheduler.jobrunner;

import jobscheduler.entity.Schedule;

/**
 * @author t_endo
 *
 */
public class JobStartRunner {

    public String start(Schedule schedule) {
        return schedule.getName();
    }
}
