package jobscheduler.manager.guice;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.dao.NodeDaoImpl;
import jobscheduler.manager.guice.persist.DomaLocalTxInterceptor;
import jobscheduler.manager.guice.persist.DomaTransactionAttribute;
import jobscheduler.manager.resource.v1.NodeResource;

import org.aopalliance.intercept.MethodInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

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

        MethodInterceptor interceptor = new DomaLocalTxInterceptor();
        bindInterceptor(Matchers.annotatedWith(DomaTransactionAttribute.class),
                Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(DomaTransactionAttribute.class),
                interceptor);

        bind(NodeResource.class);
    }
}
