package cn.smart.test;

public class Test {
    public static void main(String[] args) {
        Hello helloProxy = CGLibProxyEnum.CG_LIB_PROXY_ENUM.getProxy(HelloImpl.class);
        helloProxy.sayHello();
    }
}
