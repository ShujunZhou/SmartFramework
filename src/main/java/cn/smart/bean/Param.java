package cn.smart.bean;

import cn.smart.util.CastUtil;
import cn.smart.util.CollectionUtil;
import cn.smart.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Update by shu on 2017/7/4.
 *
 */
public class Param {
    //表单字段参数
    private List<FormParam> formParamList;
    //文件参数
    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this(formParamList);
        this.fileParamList = fileParamList;
    }

    //获取请求参数映射
    public Map<String, Object> getFieldMap() {
        Map<String, Object> fieldMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(formParamList)) {
            for (FormParam param : formParamList) {
                String fieldName = param.getFieldName();
                Object fieldValue = param.getFieldValue();
                //在key值重复的情况下，加分隔符，改变对应value的值
                if (fieldMap.containsKey(fieldName)) {
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARETOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    //获取上传文件映射
    public Map<String, List<FileParam>> getFileMap() {
        Map<String, List<FileParam>> fileMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(fileParamList)) {
            for (FileParam fileParam : fileParamList) {
                String fieldName = fileParam.getFileName();
                List<FileParam> fileParamList;
                if (fileMap.containsKey(fieldName)) {
                    fileParamList = fileMap.get(fieldName);
                } else {
                    fileParamList = new ArrayList<FileParam>();
                }
                fileParamList.add(fileParam);
                fileMap.put(fieldName, fileParamList);
            }
        }

        return fileMap;
    }

    //获取所有上传文件
    public List<FileParam> getFileParamList(String fieldName) {
        return getFileParamList(fieldName);
    }

    //获取单一上传文件
    public FileParam getFile(String fieldName) {
        List<FileParam> fileParamList = getFileParamList(fieldName);

        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1) {
            return fileParamList.get(0);
        }
        return null;
    }

    //验证参数是否为空
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
    }

    //根据参数名获取String型参数值
    public String getString(String  name) {
        return CastUtil.caseString(getFieldMap().get(name));
    }

    //根据参数名获取double型参数值
    public double getDouble(String name) {
        return CastUtil.caseDouble(getFieldMap().get(name));
    }

    //根据参数名获取long型参数值
    public long getLong(String name) {
        return CastUtil.caseLong(getFieldMap().get(name));
    }

    //根据参数名获取int型参数值
    public int getInt(String name) {
        return CastUtil.caseInt(getFieldMap().get(name));
    }

    //根据参数名获取boolean型参数值
    public boolean getBoolean(String name) {
        return CastUtil.caseBoolean(getFieldMap().get(name));
    }
}
