package jobscheduler.manager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * Job.
 * 
 * @author t_endo
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
@EqualsAndHashCode(callSuper = true)
public class Job extends Unit {

    int outputRecordingMax;

    boolean outputRecordingTail;
}
