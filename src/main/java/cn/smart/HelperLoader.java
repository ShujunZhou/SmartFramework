package cn.smart;

import cn.smart.helper.*;
import cn.smart.util.ClassUtil;

//初始化框架
public final class HelperLoader {
    public static void init() {
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                //AopHelper要在IocHelper之前加载,因为需要通过AopHelper获取代理对象
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classes) {
            ClassUtil.loadClass(cls.getName(), false);
        }
    }
}
