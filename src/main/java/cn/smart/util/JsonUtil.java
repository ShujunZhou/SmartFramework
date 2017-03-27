package cn.smart.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by shu on 2017/3/27.
 */
public final class JsonUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private final static ObjectMapper OBJ_MAPPER = new ObjectMapper();

    //将POJO转换为json字符串
    public static <T> String toJson(T cls) {
        String json;

        try {
            json = OBJ_MAPPER.writeValueAsString(cls);
        } catch (JsonProcessingException e) {
            LOGGER.error("pojo case json failure", e);
            throw new RuntimeException(e);
        }

        return json;
    }

    //将json转换为POJO
    public static <T> T fromJsonToPojo(String json, Class<T> cls) {
        T t;
        try {
            t = OBJ_MAPPER.readValue(json, cls);
        } catch (IOException e) {
            LOGGER.error("json case pojo failure", e);
            throw new RuntimeException(e);
        }
        return t;
    }
}
