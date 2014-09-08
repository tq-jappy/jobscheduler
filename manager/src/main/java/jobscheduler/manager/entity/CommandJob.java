package jobscheduler.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * Command job.
 * 
 * @author t_endo
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommandJob extends Job {

    String command;

    int nodeId;
}