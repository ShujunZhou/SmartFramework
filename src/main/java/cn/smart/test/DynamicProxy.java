package cn.smart.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private Object target; //定义被代理的目标对象

    public DynamicProxy(Object target) {
        this.target = target;
    }

     @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("Bye");
    }

    private void before() {
        System.out.println("Hello, MATLAB");
    }

    public <T> T getProxy() {
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        DynamicProxy proxy = new DynamicProxy(hello);
        Hello h = (Hello)Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), proxy);
        h.sayHello();

    }
}
