package jobscheduler.manager.entity;

import java.util.Optional;

import jobscheduler.manager.domain.UnitType;
import lombok.Data;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 
 * @author t_endo
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
public abstract class Unit {

    String name;

    UnitType unitType;

    int indexX;

    int indexY;

    Optional<Integer> startHour;

    Optional<Integer> startMinute;

    // TODO: delay, abort timer parameters.

    // Optional<Integer> startDelayHour;
}
