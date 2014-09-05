package jobscheduler.entity;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Job Master
 * 
 * @author t_endo
 */
@Data
@Builder
public class Job {

    String name;

    JobParameters jobParameters;
}
