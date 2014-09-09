package jobscheduler.manager;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author t_endo
 *
 */
public class ManagerApplicationTest {

    @Test
    public void check() throws Exception {
        ManagerApplication app = new ManagerApplication();
        app.run(new String[] { "check", "setting/configuration.yml" });
    }

    @Ignore
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
