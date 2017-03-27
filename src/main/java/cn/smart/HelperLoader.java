package cn.smart;

import cn.smart.helper.BeanHelper;
import cn.smart.helper.ClassHelper;
import cn.smart.helper.ControllerHelper;
import cn.smart.helper.IocHelper;
import cn.smart.util.ClassUtil;

/**
 * Created by shu on 2017/3/26.
 */
//初始化框架
public final class HelperLoader {
    public static void init() {
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classes) {
            ClassUtil.loadClass(cls.getName(), false);
        }
    }
}
