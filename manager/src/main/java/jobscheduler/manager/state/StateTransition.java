package jobscheduler.manager.state;

import jobscheduler.manager.domain.Status;

/**
 * @author t_endo
 *
 */
public interface StateTransition {

    public void validate(Status from, Status to);
}
