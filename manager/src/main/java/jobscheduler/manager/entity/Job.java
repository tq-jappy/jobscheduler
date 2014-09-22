package jobscheduler.manager.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Builder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ジョブ
 * 
 * @author t_endo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Job extends JobUnit {

    // @JsonIgnore
    // JobParameter jobParameter;

    @JsonProperty(value = "parameters")
    Map<String, String> map;
}
