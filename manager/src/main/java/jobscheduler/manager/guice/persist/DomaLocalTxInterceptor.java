package jobscheduler.manager.guice.persist;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.seasar.doma.jdbc.tx.TransactionManager;

import com.google.inject.Inject;

/**
 * Guice でトランザクション管理を行うためのインターセプタ。<br />
 * Guice のトランザクション管理機構として標準でサポートされているのは JPA だけなので、Doma2 の
 * {@link TransactionManager} を使った実装にする。
 * 
 * @author t_endo
 */
public class DomaLocalTxInterceptor implements MethodInterceptor {

    private static final DomaTransactionAttribute DEFAULT_TRANSACTIONAL = Internal.class
            .getAnnotation(DomaTransactionAttribute.class);

    @Inject
    private TransactionManager transactionManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        DomaTransactionAttribute transactional = readTransactionMetadata(invocation);

        Supplier<Object> supplier = () -> {
            // ラムダ式内で例外が起きた場合、ロールバックされる
            try {
                return invocation.proceed();
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        };

        Object result;
        switch (transactional.attribute()) {
        case REQURED:
            result = transactionManager.required(supplier);
            break;
        case NOT_SUPPORTED:
            result = transactionManager.notSupported(supplier);
            break;
        case REQURES_NEW:
        default:
            result = transactionManager.requiresNew(supplier);
            break;
        }

        return result;
    }

    /**
     * 対象に付いている {@link DomaTransactionAttribute} アノテーションを取り出す。
     * 
     * @param methodInvocation
     * @return
     */
    private DomaTransactionAttribute readTransactionMetadata(
            MethodInvocation methodInvocation) {
        Method method = methodInvocation.getMethod();
        Class<?> targetClass = methodInvocation.getThis().getClass();

        DomaTransactionAttribute transactional = method
                .getAnnotation(DomaTransactionAttribute.class);
        if (transactional == null) {
            transactional = targetClass
                    .getAnnotation(DomaTransactionAttribute.class);
        }

        if (transactional == null) {
            transactional = DEFAULT_TRANSACTIONAL;
        }

        return transactional;
    }

    @DomaTransactionAttribute
    private static class Internal {
    }
}
