package cn.smart.annotation;

import java.lang.annotation.*;

/**
 * Created by shu on 2017/3/23.
 */
//依赖注入
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
