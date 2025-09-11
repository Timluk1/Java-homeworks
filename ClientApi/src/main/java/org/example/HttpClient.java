package org.example;

import java.net.*;
import java.util.Map;

public class HttpClient implements IHttpClient {
    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            URL urlObj = new URL(UrlParser.parseUrl(url, params));
            HttpURLConnection connection = ConnectionBuilder.buildConnection("GET", urlObj, headers);
            return HttpResponseParser.parseResponse(connection);
        } catch (Exception e) {
            System.out.println("GET EXCEPTION: " + e.getMessage());
            return "";
        }
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = ConnectionBuilder.buildConnection("POST", urlObj, headers);

            connection.setDoOutput(true);
            if (data != null && !data.isEmpty()) {
                String body = UrlParser.parseParams(data);
                connection.getOutputStream().write(body.getBytes());
            }

            return HttpResponseParser.parseResponse(connection);
        } catch (Exception e) {
            System.out.println("POST EXCEPTION: " + e.getMessage());
            return "";
        }
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = ConnectionBuilder.buildConnection("PUT", urlObj, headers);

            connection.setDoOutput(true);
            if (data != null && !data.isEmpty()) {
                String body = UrlParser.parseParams(data);
                connection.getOutputStream().write(body.getBytes());
            }

            return HttpResponseParser.parseResponse(connection);
        } catch (Exception e) {
            System.out.println("PUT EXCEPTION: " + e.getMessage());
            return "";
        }
    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            URL urlObj = new URL(UrlParser.parseUrl(url, params));
            HttpURLConnection connection = ConnectionBuilder.buildConnection("DELETE", urlObj, headers);
            return HttpResponseParser.parseResponse(connection);
        } catch (Exception e) {
            System.out.println("DELETE EXCEPTION: " + e.getMessage());
            return "";
        }
    }
}
