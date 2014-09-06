package jobscheduler.manager.entity;

import java.util.Arrays;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Job parameters master.
 * 
 * @author t_endo
 */
@Data
@Builder
public class JobParameters {

    String[] command;

    @Override
    public String toString() {
        return Arrays.toString(command);
    }
}
