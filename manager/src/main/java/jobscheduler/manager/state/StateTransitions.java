package jobscheduler.manager.state;

import static jobscheduler.manager.domain.Status.*;

import java.util.HashMap;
import java.util.Map;

import jobscheduler.manager.domain.Status;
import lombok.EqualsAndHashCode;

/**
 * @author t_endo
 *
 */
public class StateTransitions {

    private static final Map<TransitionKey, Class<? extends StateTransition>> map = create();

    private static Map<TransitionKey, Class<? extends StateTransition>> create() {
        Map<TransitionKey, Class<? extends StateTransition>> map = new HashMap<>();
        map.put(TransitionKey.of(COMPLETED, RUNNING), ForbiddenTransition.class);
        return map;
    }

    public static Class<? extends StateTransition> get(Status oldStatus,
            Status newStatus) {
        return map.getOrDefault(TransitionKey.of(oldStatus, newStatus),
                ForbiddenTransition.class);
    }

    @EqualsAndHashCode
    private static final class TransitionKey {

        final Status fromStatus;

        final Status toStatus;

        static TransitionKey of(Status from, Status to) {
            return new TransitionKey(from, to);
        }

        TransitionKey(Status from, Status to) {
            this.fromStatus = from;
            this.toStatus = to;
        }
    }
}
