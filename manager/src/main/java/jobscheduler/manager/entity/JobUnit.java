package jobscheduler.manager.entity;

import java.util.Map;
import java.util.Optional;

import jobscheduler.manager.domain.UnitType;
import jobscheduler.manager.util.JSONUtils;
import lombok.Data;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.jdbc.entity.NamingType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * 
 * @author t_endo
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
public class JobUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "JOB_UNIT_ID_SEQ")
    Integer id;

    String name;

    UnitType unitType;

    int indexX;

    int indexY;

    Optional<Integer> startHour;

    Optional<Integer> startMinute;

    // TODO: delay, abort timer parameters.

    // Optional<Integer> startDelayHour;

    @JsonIgnore
    @Column(name = "parameters")
    String parametersJson;

    @JsonSetter(value = "parameters")
    public void setParameters(Map<String, Object> parameters) {
        this.parametersJson = JSONUtils.encode(parameters);
    }

    @JsonGetter(value = "parameters")
    public Map<String, Object> getParameters() {
        return JSONUtils.decodeAsMap(parametersJson);
    }
}
