package jobscheduler.entity;

import jobscheduler.domain.JobType;
import lombok.Data;
import lombok.experimental.Builder;

/**
 * Job master.
 * 
 * @author t_endo
 */
@Data
@Builder
public class Job {

    int id;

    String name;

    JobType jobType;

    JobParameters jobParameters;
}
