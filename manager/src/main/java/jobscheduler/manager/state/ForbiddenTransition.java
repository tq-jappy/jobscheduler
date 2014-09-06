package jobscheduler.manager.state;

import jobscheduler.manager.domain.Status;

/**
 * @author t_endo
 *
 */
public class ForbiddenTransition implements StateTransition {

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Status from, Status to) {
        // TODO Auto-generated method stub

    }
}
