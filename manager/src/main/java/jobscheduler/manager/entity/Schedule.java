package jobscheduler.manager.entity;

import java.util.List;

import lombok.Data;

/**
 * Schedule master.
 * 
 * @author t_endo
 */
@Data
public class Schedule {

    int id;

    String name;

    List<Job> childrenJob;
}
