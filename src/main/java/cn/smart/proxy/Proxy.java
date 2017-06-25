package cn.smart.proxy;

/**
 * Created by shu on 2017/6/25.
 * 代理接口
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
