package jobscheduler.manager.guice;

import java.util.Objects;

import javax.ws.rs.Path;

import jobscheduler.manager.dao.NodeDao;
import jobscheduler.manager.dao.NodeDaoImpl;
import jobscheduler.manager.guice.persist.DomaLocalTxInterceptor;
import jobscheduler.manager.guice.persist.DomaTransactionAttribute;
import jobscheduler.manager.resource.v1.NodeResource;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * 
 * @author t_endo
 */
public class CommonModule extends AbstractModule {

    private final Config domaConfig;

    public CommonModule(Config domaConfig) {
        this.domaConfig = domaConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        Objects.requireNonNull(domaConfig, "domaConfig");

        bind(Config.class).toInstance(domaConfig);

        bind(NodeDao.class).to(NodeDaoImpl.class);

        bind(TransactionManager.class).toInstance(
                domaConfig.getTransactionManager());

        MethodInterceptor interceptor = new DomaLocalTxInterceptor();
        requestInjection(interceptor);

        NodeResource nodeResource = new NodeResource();
        bind(NodeResource.class).toInstance(nodeResource);

        bindInterceptor(Matchers.annotatedWith(Path.class), Matchers.any(),
                interceptor);
        bindInterceptor(Matchers.annotatedWith(DomaTransactionAttribute.class),
                Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(DomaTransactionAttribute.class),
                interceptor);

        requestInjection(nodeResource);
        bindInterceptor(Matchers.identicalTo(nodeResource), Matchers.any(),
                interceptor);
    }
}
