package jobscheduler.manager.dao;

import java.util.List;

import jobscheduler.manager.doma.InjectConfig;
import jobscheduler.manager.entity.JobUnit;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

/**
 * 
 * @author t_endo
 */
@Dao
@InjectConfig
public interface JobDao {

    @Select
    List<JobUnit> selectAll();

    @Select
    JobUnit selectById(Integer id);

    @Insert
    int insert(JobUnit jobUnit);

    @Update
    int update(JobUnit jobUnit);

    @Delete
    int delete(JobUnit jobUnit);
}
