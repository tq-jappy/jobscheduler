package jobscheduler.manager.dao;

import java.util.List;

import jobscheduler.manager.doma.AppConfig;
import jobscheduler.manager.entity.Node;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

/**
 * 
 * @author t_endo
 */
@Dao(config = AppConfig.class)
public interface NodeDao {

    @Select
    List<Node> selectAll();

    @Select
    Node selectById(Integer id);

    @Insert
    int insert(Node node);

    @Update
    int update(Node node);

    @Delete
    int delete(Node node);
}
