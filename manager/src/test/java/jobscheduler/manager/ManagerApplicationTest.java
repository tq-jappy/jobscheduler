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
        app.run(new String[] { "server", "setting/configuration.yml" });
    }

    @Test
    public void test2() throws Exception {
        ManagerApplication app = new ManagerApplication();
        app.run(new String[] { "db", "migrate", // "--dry-run",
                "setting/configuration.yml" });
    }
}
