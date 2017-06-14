package cn.smart.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//控制器注解
@Target(ElementType.TYPE) //限制注解只能用于类以及接口
@Retention(RetentionPolicy.RUNTIME) //类文件中的注解，并由虚拟机载入。通过反射API获取它们
public @interface Controller {
}

