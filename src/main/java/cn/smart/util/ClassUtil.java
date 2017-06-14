package cn.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by shu on 2017/3/22.
 */
//提供与类操作的相关的方法
public class ClassUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
    //获取类加载器
    public static ClassLoader getClassLoader() {
        //获取当前线程的ClassLoader
        return Thread.currentThread().getContextClassLoader();
    }

    //加载类
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls = null;
        try {
            //false参数，在加载类时，不执行静态代码,可提高效率
            cls = Class.forName(className, false, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("class not right load", e);
            e.printStackTrace();
        }

        return cls;
    }

    //加载某个包下的类
    public static Set<Class<?>> getClassSet(String packageName) {
        HashSet<Class<?>> set = new HashSet<>();

        try {
            //将包名的点替换成路径分隔符
            Enumeration<URL> resources = getClassLoader().getResources(packageName.replace(".", File.separator));
            while (resources.hasMoreElements()) {
                //获取资源路径
                URL url = resources.nextElement();
                if (null != url) {
                    //获取文件类型
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        //用%20代替空格
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(set, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile  jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntrys = jarFile.entries();
                                while (jarEntrys.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntrys.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
                                                .replaceAll("/", ".");
                                        doAddClass(set, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("class not right load", e);
            e.printStackTrace();
        }

        return set;
    }

    //加载类
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (pathname.isFile() && pathname.getName().endsWith(".class"))
                        || pathname.isDirectory();
            }
        });

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;

                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = subPackageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}
