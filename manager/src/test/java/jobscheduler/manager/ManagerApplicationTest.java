package jobscheduler.manager;

import org.junit.Test;

/**
 * @author t_endo
 *
 */
public class ManagerApplicationTest {

    @Test
    public void test() throws Exception {
        ManagerApplication app = new ManagerApplication();
        app.run(new String[] { "server" });
    }
}
