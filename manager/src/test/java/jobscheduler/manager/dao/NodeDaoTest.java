package jobscheduler.manager.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import jobscheduler.manager.doma.AppConfig;
import jobscheduler.manager.entity.Node;
import jobscheduler.manager.test.TestingDbResource;

import org.junit.Rule;
import org.junit.Test;

/**
 * @author t_endo
 *
 */
public class NodeDaoTest {

    @Rule
    public final TestingDbResource dbResource = new TestingDbResource();

    private final NodeDao dao = new NodeDaoImpl(AppConfig.singleton());

    @Test
    public void selectById() {
        dbResource.execute(() -> {
            Node actual = dao.selectById(1);

            assertThat(actual, is(notNullValue()));
            assertThat(actual.getId(), is(1));
            assertThat(actual.getHostName(), is("aries"));
        });
    }

    @Test
    public void insert() {
        dbResource.execute(() -> {
            Node node = Node.builder().hostName("localhost").build();

            int result = dao.insert(node);
            assertThat(result, is(1));
        });
    }
}
