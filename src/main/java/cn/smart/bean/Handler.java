package cn.smart.bean;

import java.lang.reflect.Method;

/**
 * Created by shu on 2017/3/24.
 */
public class Handler {
    //Controller类
    private Class<?> controllerClass;

    //action方法
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
