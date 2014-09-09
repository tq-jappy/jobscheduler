package jobscheduler.manager.guice;

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
public class CommonModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        // XXX: GuiceBundle を利用する場合、initialize 内では Configuration の
        // インスタンスが取得できず、DomaConfig が初期化されないため、インジェクションしようとしても
        // DaoImpl に渡す Configuration が null のため失敗してしまう。[dropwizard-guice #19]
        // bind(NodeDao.class).to(NodeDaoImpl.class).asEagerSingleton();

        MethodInterceptor interceptor = new DomaLocalTxInterceptor();
        requestInjection(interceptor);

        bindInterceptor(Matchers.annotatedWith(DomaTransactionAttribute.class),
                Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(DomaTransactionAttribute.class),
                interceptor);

        bind(NodeResource.class);
    }
}
