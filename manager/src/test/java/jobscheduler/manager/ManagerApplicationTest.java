package jobscheduler.manager;

import static org.junit.Assume.*;
import jobscheduler.manager.ManagerApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * @author t_endo
 *
 */
public class ManagerApplicationTest {

    @Rule
    public TestName testName = new TestName();

    final String test = "server";

    @Before
    public void setUp() {
        assumeTrue(testName.getMethodName().equals(test));
    }

    @Test
    public void check() throws Exception {
        ManagerApplication app = new ManagerApplication();
        app.run(new String[] { "check", "setting/configuration.yml" });
    }

    @Test
    public void server() throws Exception {
        ManagerApplication app = new ManagerApplication();
        app.run(new String[] { "server", "setting/configuration.yml" });
    }

    @Test
    public void dbmigrate() throws Exception {
        ManagerApplication app = new ManagerApplication();
        app.run(new String[] { "db", "migrate", // "--dry-run",
                "setting/configuration.yml" });
    }
}
