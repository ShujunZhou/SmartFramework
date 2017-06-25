package cn.smart.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shu on 2017/6/25.
 * 代理链
 */
public class ProxyChain {
    //目标类
    private final Class<?> targetClass;
    //目标对象
    private final Object targetObject;
    //目标方法
    private final Method targetMethod;
    //方法代理
    private final MethodProxy methodProxy;
    //方法参数
    private final Object[] params;
    //代理列表
    private List<Proxy> proxyList = new ArrayList<>();
    //列表索引
    private int proxyIndex;

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod,
                      MethodProxy methodProxy, Object[] params, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.params = params;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getParams() {
        return params;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;

        if (proxyIndex < proxyList.size()) {
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            methodResult = methodProxy.invoke(targetObject, params);
        }

        return methodResult;
    }
}
