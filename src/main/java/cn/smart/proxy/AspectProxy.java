package cn.smart.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by shu on 2017/6/25.
 * 切面代理
 */
public class AspectProxy implements Proxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        //获取被代理类
        Class<?> cls = proxyChain.getTargetClass();
        //获取被代理方法
        Method method = proxyChain.getTargetMethod();
        //获取被代理对象方法参数
        Object[] params = proxyChain.getParams();

        begin();

        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params, result);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Throwable throwable) {
            LOGGER.error("proxy failure", throwable);
            error(cls, method, params, throwable);
            throw  throwable;
        } finally {
            end();
        }


        return result;
    }

    public void begin() {}

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {}

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws  Throwable {}

    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {}

    public void end() {}
}
