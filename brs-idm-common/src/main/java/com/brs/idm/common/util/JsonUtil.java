package com.brs.idm.common.util;


import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tiny lin
 * @version 1.0
 * @className JsonUtil
 * @description TODO
 * @date 2018/11/27
 */
public class JsonUtil {
    static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 返回json响应信息体
     *
     * @param code, msg
     * @return com.alibaba.fastjson.JSONObject
     */
    public static JSONObject responseJson(Integer code, String msg) {
        return responseJson(code, msg, null);
    }

    /**
     * 返回带有数据的json响应信息体
     *
     * @param code, msg, data
     * @return com.alibaba.fastjson.JSONObject
     */
    public static JSONObject responseJson(Integer code, String msg, JSONObject data) {
        JSONObject responseJson = new JSONObject();
        responseJson.put("code", code);
        responseJson.put("msg", msg);
        responseJson.put("data", data);
        return responseJson;
    }

}
