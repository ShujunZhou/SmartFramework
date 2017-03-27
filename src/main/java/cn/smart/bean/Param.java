package cn.smart.bean;

import cn.smart.util.CastUtil;

import java.util.Map;

/**
 * Created by shu on 2017/3/26.
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    //根据参数名获取long型参数
    public long getLong(String name) {
        return CastUtil.caseLong(paramMap.get(name));
    }

    //获取所有字段信息
    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
