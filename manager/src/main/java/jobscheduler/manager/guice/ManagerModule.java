package jobscheduler.manager.guice;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.doma.DomaConfig;
import jobscheduler.manager.guice.persist.DomaLocalTxInterceptor;
import jobscheduler.manager.guice.persist.DomaTransactionAttribute;
import jobscheduler.manager.resource.v1.NodeResource;

import org.aopalliance.intercept.MethodInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.matcher.Matchers;

/**
 * 
 * @author t_endo
 */
public abstract class ManagerModule extends AbstractModule {

    @Provides
    public abstract DomaConfig providesDomaConfig();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        bind(NodeDao.class);

        MethodInterceptor interceptor = new DomaLocalTxInterceptor();
        bindInterceptor(Matchers.annotatedWith(DomaTransactionAttribute.class),
                Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(DomaTransactionAttribute.class),
                interceptor);

        bind(NodeResource.class);
    }
}
