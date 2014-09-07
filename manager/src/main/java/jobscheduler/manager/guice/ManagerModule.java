package jobscheduler.manager.guice;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.dao.NodeDaoImpl;

import com.google.inject.AbstractModule;

/**
 * 
 * @author t_endo
 */
public class ManagerModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        bind(NodeDao.class).toInstance(new NodeDaoImpl());
    }
}
