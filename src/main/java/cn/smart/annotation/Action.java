package cn.smart.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  //限制注解只能用于方法
@Retention(RetentionPolicy.RUNTIME) //
public @interface Action {
    //请求类型与路径
    String value();
}
