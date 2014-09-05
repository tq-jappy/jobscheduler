package jobscheduler.state;

import jobscheduler.domain.Status;

/**
 * @author t_endo
 *
 */
public interface StateTransition {

    public void validate(Status from, Status to);
}
