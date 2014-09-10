package jobscheduler.agent.dto;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class JobRequest {

    String message;
}
