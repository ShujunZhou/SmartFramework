package cn.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by shu on 2017/3/23.
 */
//反射工具类
public final class ReflectionUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    //创建实例
    public static Object newInstance(Class<?> cls) {
        Object instance = null;

        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance is failure", e);
            e.printStackTrace();
        }

        return instance;
    }

    //调用方法,设置注入(一个方法可能有多个参数)
    public static Object invokeMethod(Object obj, Method method, Object... params) {
        Object result = null;

        try {
            result = method.invoke(obj, params);
        } catch (Exception e) {
            LOGGER.error("method execute failure", e);
            e.printStackTrace();
        }
        return result;
    }

    //设置成员变量的值（一个字段对应一个值）
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("field set failure", e);
            e.printStackTrace();
        }
    }
}
