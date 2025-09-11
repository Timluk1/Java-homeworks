package org.example;

import java.util.Map;

public class UrlParser {
    public static String parseUrl(String url, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        return url + "?" + parseParams(params);
    }

    public static String parseParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
