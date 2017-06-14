package cn.smart.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public enum CGLibProxyEnum implements MethodInterceptor {
    CG_LIB_PROXY_ENUM;

    public  <T> T getProxy(Class<T> cls) {
        return (T)Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(o, objects);
    }
}
