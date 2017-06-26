package cn.smart.helper;

import cn.smart.annotation.Aspect;
import cn.smart.proxy.Proxy;
import cn.smart.proxy.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by shu on 2017/6/25.
 * AOP
 */

public final class AopHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    //通过静态块来初始化整个AOP框架
    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);

            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            LOGGER.error("AOP init failure", e);
        }
    }

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> classSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)) {
            classSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }

        return classSet;
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
            Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
            Set<Class<?>> proClassSet = ClassHelper.getClassSetBySuper(Aspect.class);
            for (Class<?> proxyClass : proClassSet) {
                if (proxyClass.isAnnotationPresent(Aspect.class)) {
                    Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                    Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                    proxyMap.put(proxyClass, targetClassSet);
                }
            }

            return proxyMap;
    }

    //创建代理类与目标集合之间的映射关系
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();

        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();

            for (Class<?> targetClass : targetClassSet) {
                Proxy proxy = (Proxy)proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }

        return targetMap;
    }
}
