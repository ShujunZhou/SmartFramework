package cn.smart.helper;

import cn.smart.annotation.Inject;
import cn.smart.util.ArrayUtil;
import cn.smart.util.CollectionUtil;
import cn.smart.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by shu on 2017/3/24.
 */
//依赖注入(控制反转,即将构造对象交于框架实现)助手类
public final class IocHelper {

    static {
        Map<Class<?>, Object> classMap = BeanHelper.getBeanMap();

        if (CollectionUtil.isNotEmpty(classMap)) {
            for (Map.Entry<Class<?>, Object> m : classMap.entrySet()) {
                Class<?> cls = m.getKey();
                Object obj = m.getValue();
                Field[] fields = cls.getDeclaredFields(); //获取类中的所有字段
                //遍历Bean field
                if (ArrayUtil.isNotEmpty(fields)) {
                    //判断当前Bean Field是否带有Inject注解
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            //在BeanMap中获取BeanField对应的实例
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = classMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                //通过反射初始化BeanField的值
                                ReflectionUtil.setField(obj, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
