package jobscheduler.manager.test;

import jobscheduler.dao.AppDao;
import jobscheduler.dao.AppDaoImpl;
import jobscheduler.doma.AppConfig;

import org.junit.rules.ExternalResource;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * 
 * @author t_endo
 */
public class TestingDbResource extends ExternalResource {

    private AppDao dao = new AppDaoImpl();

    @Override
    protected void before() throws Throwable {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            dao.create();
        });
    }

    @Override
    protected void after() {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            dao.drop();
        });
    }
}
