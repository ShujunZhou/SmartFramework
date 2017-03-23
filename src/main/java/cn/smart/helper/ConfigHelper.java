package cn.smart.helper;

import cn.smart.ConfigConstant;
import cn.smart.util.PropsUtil;

import java.util.Properties;

/**
 * Created by shu on 2017/3/22.
 */
public final class ConfigHelper {
    //加载文件
    private final static Properties CONFIG_PROPS = PropsUtil.loadProperties(cn.smart.ConfigConstant.CONFIG_FILE);

    //获取JDBC驱动
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    //获取URL
    public static String getJdbcURL() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    //获取用户名
    public static String getUsername() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    //获取密码
    public static String getPassword() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    //获取基础应用包名
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    //获取应用JSP路径
    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH);
    }

    //获取应用资源路径
    public static String getAppAssertPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH);
    }
}
