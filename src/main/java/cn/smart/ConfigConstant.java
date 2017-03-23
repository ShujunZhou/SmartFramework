package cn.smart;

/**
 * Created by shu on 2017/3/22.
 */
public interface ConfigConstant {
    String CONFIG_FILE = "smart.properties"; //配置文件
    String JDBC_DRIVER = "smart.framework.jdbc.driver"; //驱动
    String JDBC_URL = "smart.framework.jdbc.url"; //路径
    String JDBC_USERNAME = "smart.framework.jdbc.username"; //用户名
    String JDBC_PASSWORD = "smart.framework.jdbc.password"; //密码

    String APP_BASE_PACKAGE = "smart.framework.app.base_package"; //项目基础包名
    String APP_JSP_PATH = "smart.framework.app.jsp_path"; //表示JSP的基础路径
    String APP_ASSET_PATH = "smart.framework.app.asset_path"; //静态资源文件的基础路径，如JS、CSS、图片等
}
