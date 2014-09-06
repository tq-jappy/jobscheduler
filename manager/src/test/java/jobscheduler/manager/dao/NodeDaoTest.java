package jobscheduler.manager.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import jobscheduler.dao.NodeDao;
import jobscheduler.dao.NodeDaoImpl;
import jobscheduler.doma.AppConfig;
import jobscheduler.entity.Node;
import jobscheduler.manager.test.TestingDbResource;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * @author t_endo
 *
 */
public class NodeDaoTest {

    @Rule
    public final TestingDbResource dbResource = new TestingDbResource();

    private final NodeDao dao = new NodeDaoImpl();

    @Test
    public void selectById() {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            Node actual = dao.selectById(1);

            assertThat(actual, is(notNullValue()));
            assertThat(actual.getId(), is(1));
            assertThat(actual.getHostName(), is("aries"));
        });
    }
}
