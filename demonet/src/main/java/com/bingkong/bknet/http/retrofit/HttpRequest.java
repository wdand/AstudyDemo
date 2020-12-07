package com.bingkong.bknet.http.retrofit;

import java.util.HashMap;
import java.util.Map;

/**
 * 构建Api请求参数
 *
 * @author niejiahuan
 */

public class HttpRequest {

    public static final String appKey = "1889b37351288";
    private static final String k_key = "key";

    /**
     * 获取基础request
     *
     * @author niejiahuan
     */
    public static final Map<String, Object> getRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put(k_key, appKey);
        return map;
    }


}
