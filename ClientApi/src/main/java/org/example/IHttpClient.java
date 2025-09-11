package org.example;

import java.util.Map;

public interface IHttpClient {
    String get(String url, Map<String, String> headers, Map<String, String> params);
    String post(String url, Map<String, String> headers, Map<String, String> data);
    String put(String url, Map<String, String> headers, Map<String, String> data);
    String delete(String url, Map<String, String> headers, Map<String, String> data);
}
