package jobscheduler.manager.entity;

import jobscheduler.manager.bean.JobNetParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ジョブネット
 * 
 * @author t_endo
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobNet extends JobUnit {

    JobNetParameter jobNetParameter;
}