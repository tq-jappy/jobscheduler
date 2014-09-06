package jobscheduler.manager.dao;

import jobscheduler.manager.doma.AppConfig;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;

/**
 * @author t_endo
 *
 */
@Dao(config = AppConfig.class)
public interface AppDao {

    @Script
    public void create();

    @Script
    public void drop();
}