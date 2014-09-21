package jobscheduler.manager.dao;

import java.util.List;

import jobscheduler.manager.doma.InjectConfig;
import jobscheduler.manager.entity.Job;

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
    List<Job> selectAll();

    @Select
    Job selectById(Integer id);

    @Insert
    int insert(Job node);

    @Update
    int update(Job node);

    @Delete
    int delete(Job node);
}
