package jobscheduler.entity;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Node master.
 * 
 * @author t_endo
 */
@Data
@Builder
public class Node {

    /**
     * host name or IP address
     */
    String hostName;
}
