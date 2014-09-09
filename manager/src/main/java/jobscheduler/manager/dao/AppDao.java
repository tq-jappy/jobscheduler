package jobscheduler.manager.dao;

import jobscheduler.manager.doma.InjectConfig;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;

/**
 * @author t_endo
 *
 */
@Dao
@InjectConfig
public interface AppDao {

    @Script
    public void create();

    @Script
    public void drop();
}