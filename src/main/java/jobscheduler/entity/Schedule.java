package jobscheduler.entity;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author t_endo
 */
@Data
public class Schedule {

    int id;

    String name;

    List<Job> childrenJob;
}
