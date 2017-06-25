package cn.smart.annotation;

import java.lang.annotation.*;

/**
 * Created by shu on 2017/6/25.
 *切面注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
