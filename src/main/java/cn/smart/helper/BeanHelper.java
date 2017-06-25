package cn.smart.helper;

import cn.smart.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by shu on 2017/3/23.
 */
public final class BeanHelper {
    //定义Bean映射（用于存放Bean类与Bean实例的映射关系）
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> set = ClassHelper.getBeanClsSet(); //获取类集合
            for (Class<?> cls : set) {
                BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls));//建立类和实例的映射关系
            }
    }

    //获取Bean映射
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    //根据类名获取Bean实例
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not  get bean class :" + cls);
        }

        return (T)BEAN_MAP.get(cls);
    }

    public static void setBeanMap(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }
}
