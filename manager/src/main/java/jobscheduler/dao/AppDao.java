package jobscheduler.dao;

import jobscheduler.doma.AppConfig;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;

/**
 * 
 * @author t_endo
 */
@Dao(config = AppConfig.class)
public interface AppDao {

    @Script
    void create();

    @Script
    void drop();
}
