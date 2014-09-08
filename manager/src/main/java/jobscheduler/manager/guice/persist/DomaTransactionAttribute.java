package jobscheduler.manager.guice.persist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.doma.jdbc.tx.TransactionAttribute;

/**
 * 
 * @author t_endo
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DomaTransactionAttribute {

    // Class<? extends Exception>[] rollbackOn() default RuntimeException.class;

    // Class<? extends Exception>[] ignore() default {};

    TransactionAttribute attribute() default TransactionAttribute.REQURED;
}
