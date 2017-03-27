package cn.smart.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shu on 2017/3/26.
 */

//返回视图对象
public class View {
    //视图路径
    private String path;

    //视图模型
    public Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
