package jobscheduler.manager.entity;

import lombok.Value;
import lombok.experimental.Builder;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * Node master.
 * 
 * @author t_endo
 */
@Entity(immutable = true, naming = NamingType.SNAKE_LOWER_CASE)
@Value
@Builder
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "NODE_SEQ")
    final Integer id;

    /**
     * host name or IP address
     */
    final String hostName;

    public Node(Integer id, String hostName) {
        this.id = id;
        this.hostName = hostName;
    }
}
