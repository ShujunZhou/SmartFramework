package cn.smart.helper;

import cn.smart.ConfigConstant;
import cn.smart.annotation.Controller;
import cn.smart.annotation.Service;
import cn.smart.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;


//类操作助手类
public final class ClassHelper {
    //定义类集合，用于存放所有加载的类
    private final static Set<Class<?>> CLS_SET;

    static {
        String basePackage = ConfigConstant.APP_BASE_PACKAGE;
        CLS_SET = ClassUtil.getClassSet(basePackage);
    }

    //获取应用包下所有的类
    public static Set<Class<?>> getClsSet() {
        return CLS_SET;
    }

    //获取应用包下所有的Service类
    public static Set<Class<?>> getSerClsSet() {
        Set<Class<?>> set = new HashSet<>();
        for (Class<?> cls : CLS_SET) {
            if (cls.isAnnotationPresent(Service.class)) { //判断是否为service注解类
                set.add(cls);
            }
        }

        return set;
    }

    //获取应用包下所有的Controller类
    public static Set<Class<?>> getConClsSet() {
        Set<Class<?>> set = new HashSet<>();
        for (Class<?> cls : CLS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                set.add(cls);
            }
        }

        return set;
    }

    //获取应用包名下所有的bean类（包括：Service、Controller）
    public static Set<Class<?>> getBeanClsSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getSerClsSet());
        beanClassSet.addAll(getConClsSet());

        return beanClassSet;
    }

    //获取应用包名下某父类(或接口)的所有子类(或实现类)
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

    //获取应用包名下带有某注解的所有类
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }
}
