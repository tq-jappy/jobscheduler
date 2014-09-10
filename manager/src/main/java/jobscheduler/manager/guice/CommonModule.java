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
import com.google.inject.Provider;
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

        // XXX: GuiceBundle を利用する場合、initialize 内では Configuration の
        // インスタンスが取得できず、DomaConfig が初期化されないため、インジェクションしようとしても
        // DaoImpl に渡す Configuration が null のため失敗してしまう。[dropwizard-guice #19]
        bind(NodeDao.class).to(NodeDaoImpl.class);

        bind(TransactionManager.class).toInstance(
                domaConfig.getTransactionManager());

        MethodInterceptor interceptor = new DomaLocalTxInterceptor();
        requestInjection(interceptor);

        bind(NodeResource.class).toProvider(new Provider<NodeResource>() {
            @Override
            public NodeResource get() {
                return new NodeResource();
            }
        });

        bindInterceptor(Matchers.annotatedWith(Path.class), Matchers.any(),
                interceptor);
        bindInterceptor(Matchers.annotatedWith(DomaTransactionAttribute.class),
                Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(DomaTransactionAttribute.class),
                interceptor);
    }
}
