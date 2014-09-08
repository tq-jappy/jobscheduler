package jobscheduler.manager.entity;

import java.util.Optional;

import jobscheduler.manager.domain.UnitType;
import lombok.Data;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 
 * @author t_endo
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
public abstract class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "UNIT_SEQ")
    int id;

    String name;

    UnitType unitType;

    int indexX;

    int indexY;

    Optional<Integer> startHour;

    Optional<Integer> startMinute;

    // TODO: delay, abort timer parameters.

    // Optional<Integer> startDelayHour;
}
