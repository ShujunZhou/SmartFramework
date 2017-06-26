package cn.smart.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by shu on 2017/6/25.
 * 代理管理器
 */
public class ProxyManager {
    @SuppressWarnings("unchecked")
    public static  <T> T createProxy(final Class<T> cls, final List<Proxy> proxyList) {
        return (T)Enhancer.create(cls, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(cls, o, method, methodProxy, objects, proxyList).doProxyChain();
            }
        });
    }
}
