package cn.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by shu on 2017/3/27.
 */
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    //将URL编码
    public static String encodeURL(String source) {
        String target = null;

        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure", e);
            e.printStackTrace();
        }

        return target;
    }

    //将URL解码
    public static String decodeURL(String source) {
        String target = null;

        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("decode url failure", e);
            e.printStackTrace();
        }

        return target;
    }
}
