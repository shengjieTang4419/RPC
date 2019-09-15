package common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解 模仿dubbo的
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Component
public @interface Reference {

    Class<?> interfaceClass() default void.class;

    String interfaceName() default "";

    String version() default "";

    int retries() default 2;

    int timeout() default 0;

}
