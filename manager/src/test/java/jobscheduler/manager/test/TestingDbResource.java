package jobscheduler.manager.test;

import jobscheduler.manager.dao.AppDao;
import jobscheduler.manager.dao.AppDaoImpl;
import jobscheduler.manager.doma.AppConfig;

import org.junit.rules.ExternalResource;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * 
 * @author t_endo
 */
public class TestingDbResource extends ExternalResource {

    private AppDao dao = new AppDaoImpl(AppConfig.singleton());

    private TransactionManager tm = AppConfig.singleton()
            .getTransactionManager();

    @Override
    protected void before() throws Throwable {
        tm.required(() -> {
            dao.create();
        });
    }

    @Override
    protected void after() {
        tm.required(() -> {
            dao.drop();
        });
    }

    public void execute(Runnable runnable) {
        tm.required(runnable);
    }
}
